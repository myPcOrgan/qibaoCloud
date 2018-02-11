package com.qibao.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.ParamUtils;
import com.qibao.order.entity.OrderEO;
import com.qibao.order.entity.OrderLogEO;
import com.qibao.order.enums.OrderStatus;
import com.qibao.order.enums.OrderType;
import com.qibao.order.exception.OrderException;
import com.qibao.order.feign.IUserInfoFeign;
import com.qibao.order.mapper.OrderEOMapper;
import com.qibao.order.redis.IOrderRedisDao;
import com.qibao.order.service.IOrderLogService;
import com.qibao.order.service.IOrderService;
import com.qibao.order.utils.HttpClientRequest;
import com.qibao.order.vo.OrderVO;
import com.qibao.user.context.dto.UpdateGoldInfoDTO;
import com.qibao.user.context.enums.GoldTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ljn on 2018/1/10.
 */
@Service
public class OrderServiceImpl extends BaseService<OrderEO> implements IOrderService {

    @Autowired
    private IOrderRedisDao orderRedisDao;

    @Autowired
    private OrderEOMapper orderEOMapper;

    @Autowired
    private IOrderLogService orderLogService;

    @Autowired
    private IUserInfoFeign userInfoFeign;

    @Value("${myYml.informMallUrl}")
    private String informMallUrl = "";

    /**
     * 创建订单
     * @param orderEO
     * @return
     */
    @Override
    @Transactional
    public void createOrder(OrderEO orderEO) {
        String orderNo = null;
        if (orderEO.getOrderType() == OrderType.MALL_BUY.getCode()) {
            orderNo = orderRedisDao.getOrderId(OrderType.MALL_BUY.getPrefix());
            orderEO.setOrderStatus(OrderStatus.WAIT_PAYMENT.getCode());
        }else if (orderEO.getOrderType() == OrderType.DEPOSIT.getCode()) {
            orderNo = orderRedisDao.getOrderId(OrderType.DEPOSIT.getPrefix());
            orderEO.setOrderStatus(OrderStatus.DEPOSTI.getCode());
        } else if (orderEO.getOrderType() == OrderType.GET_BACK.getCode()){
            orderNo = orderRedisDao.getOrderId(OrderType.GET_BACK.getPrefix());
            orderEO.setOrderStatus(OrderStatus.GET_BACK.getCode());
        }
        orderEO.setOrderNo(orderNo);
        orderEO.setCreateTime(new Date());
        orderEO.setLastUpdateTime(new Date());
        orderEO.setIsDeleted(Boolean.FALSE);
        orderEOMapper.insert(orderEO);
        StringBuffer sb = new StringBuffer("用户：");
        sb.append(orderEO.getUserAccount()+"创建订单，订单号为：" + orderEO.getOrderNo());
        OrderLogEO orderLogEO = new OrderLogEO();
        orderLogEO.setContent(sb.toString());
        orderLogEO.setUserAccount(orderEO.getUserAccount());
        orderLogEO.setOrderNo(orderEO.getOrderNo());
        orderLogEO.setCreateTime(new Date());
        orderLogEO.setLastUpdateTime(new Date());
        orderLogService.insert(orderLogEO);
        //调用金币商城接口
        if (orderEO.getOrderType() == OrderType.DEPOSIT.getCode() || orderEO.getOrderType() == OrderType.GET_BACK.getCode()) {
            this.informMall(orderEO);
        }
    }

    /**
     * 补单
     * @param mainOrderNo
     * @param num
     * @param serviceAccount
     */
    @Override
    @Transactional
    public void additionalOrder(String mainOrderNo,Double num,String serviceAccount,Integer addOrderType) {
        OrderEO mainOrderEO = orderEOMapper.selectOrderForUpdate(mainOrderNo);
        if (mainOrderEO == null) {
            throw new OrderException("不存在此订单号的订单");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("mainOrderNo",mainOrderNo);
        List<OrderEO> orderList = orderEOMapper.getOrderList(map);
        if (!CollectionUtils.isEmpty(orderList) && orderList.size() > 2) {
            throw new OrderException("此订单已进行3次补单操作，无法再次补单");
        }
        //创建补单订单
        OrderEO orderEO = new OrderEO();
        orderEO.setMainOrderNo(mainOrderNo);
        orderEO.setDealNum(num);
        orderEO.setCurrencyUnit(mainOrderEO.getCurrencyUnit());
        orderEO.setServiceAccount(serviceAccount);
        String orderNo = orderRedisDao.getOrderId(OrderType.ADDITIONAL_ORDER.getPrefix());
        orderEO.setOrderNo(orderNo);
        orderEO.setOrderStatus(OrderStatus.SUCCESS.getCode());
        orderEO.setCreateTime(new Date());
        orderEO.setLastUpdateTime(new Date());
        orderEO.setIsDeleted(Boolean.FALSE);
        orderEOMapper.insert(orderEO);
        //添加补单日志
        this.addOrderLog(orderEO);
        //增加，减少用户库存
        UpdateGoldInfoDTO dto = new UpdateGoldInfoDTO();
        dto.setGoldNum(num);
        dto.setUserId(mainOrderEO.getUserId());
        if (addOrderType == 1) {
            dto.setType(GoldTypeEnum.ADDTOTALGOLD.getCode());
            dto.setDesp("系统为您补单:增加" + num + mainOrderEO.getCurrencyUnit());
        }else if (addOrderType == 2){
            dto.setType(GoldTypeEnum.REDUCETOTALGOLD.getCode());
            dto.setDesp("系统为您补单:减少" + num + mainOrderEO.getCurrencyUnit());
        }
        userInfoFeign.updateUserGoldInfos(dto);
    }

    /**
     * 查询订单列表
     * @return
     */
    @Override
    public List<OrderVO> queryOrderList(Map<String,Object> params,Integer page,Integer size,String orderBy,boolean isAsc) {
        if (page != null && size != null) {
            PageHelper.startPage(page,size);
        }
        if (StringUtils.isNotBlank(orderBy)) {
            params.put("ORDERBY",orderBy);
        }
        if (isAsc) {
            params.put("ORDER","ASC");
        }else{
            params.put("ORDER","DESC");
        }
        List<OrderEO> orderList = orderEOMapper.getOrderList(params);
        List<OrderVO> list = BeanMapper.mapList(orderList, OrderVO.class);
        return list;
    }

    @Override
    public int countByMap(Map<String, Object> params) {
        return orderEOMapper.countByMap(params);
    }

    /**
     * 查询订单详情
     * @param orderNo
     * @return
     */
    @Override
    public OrderEO queryOrderByOrderId(String orderNo) {
        OrderEO orderEO = orderEOMapper.selectOrderForUpdate(orderNo);
        if (orderEO == null) {
            throw new OrderException(OrderException.ORDER_IS_EXIT);
        }
        return orderEO;
    }

    /**
     * 取消订单
     * @param orderNo
     */
    @Override
    @Transactional
    public void cancelOrder(String orderNo) {
        OrderEO orderEO = this.queryOrderByOrderId(orderNo);
        if (orderEO.getOrderStatus() == OrderStatus.CANCELED.getCode() || orderEO.getOrderStatus() == OrderStatus.SUCCESS.getCode()) {
            throw new OrderException(OrderException.ORDER_IS_END);
        }
        int status = orderEO.getOrderStatus();
        orderEO.setOrderStatus(OrderStatus.CANCELED.getCode());
        this.changeOrderStatus(orderEO,OrderStatus.getOrderStatus(status));
    }

    /**
     * 商城购买，交易成功
     * @param orderNo
     */
    @Override
    @Transactional
    public OrderVO tradeSuccess(String orderNo) {
        OrderEO orderEO = this.queryOrderByOrderId(orderNo);
        if (orderEO.getOrderStatus() == OrderStatus.CANCELED.getCode() || orderEO.getOrderStatus() == OrderStatus.SUCCESS.getCode()) {
            throw new OrderException(OrderException.ORDER_IS_END);
        }
        int status = orderEO.getOrderStatus();
        //将通货数量设置为当前的实际交易数量
        orderEO.setDealNum(orderEO.getCurrencyNum());
        orderEO.setOrderStatus(OrderStatus.SUCCESS.getCode());
        this.changeOrderStatus(orderEO,OrderStatus.getOrderStatus(status));
        OrderVO orderVO = BeanMapper.map(orderEO, OrderVO.class);
        return orderVO;
    }

    /**
     * 修改订单状态并添加订单日志
     * @param orderEO
     * @param status
     */
    private void changeOrderStatus(OrderEO orderEO,OrderStatus status) {
        orderEO.setLastUpdateTime(new Date());
        orderEOMapper.updateByPrimaryKey(orderEO);
        StringBuffer sb = new StringBuffer("修改订单状态。");
        sb.append("修改订单号为:" + orderEO.getOrderNo())
                .append("的订单，订单状态从")
                .append(status.getStatus())
                .append("变成")
                .append(OrderStatus.getOrderStatus(orderEO.getOrderStatus()).getStatus());
        OrderLogEO orderLogEO = new OrderLogEO();
        orderLogEO.setContent(sb.toString());
        orderLogEO.setUserAccount(orderEO.getUserAccount());
        orderLogEO.setOrderNo(orderEO.getOrderNo());
        orderLogEO.setCreateTime(new Date());
        orderLogEO.setLastUpdateTime(new Date());
        orderLogService.insert(orderLogEO);
    }

    /**
     * 金币商城分配角色回调调用
     * @param orderNo
     */
    @Override
    @Transactional
    public OrderVO mallAssignRole(String orderNo) {
        OrderEO orderEO = this.queryOrderByOrderId(orderNo);
        if (orderEO.getOrderStatus() == OrderStatus.CANCELED.getCode() || orderEO.getOrderStatus() == OrderStatus.SUCCESS.getCode()) {
            throw new OrderException(OrderException.ORDER_IS_END);
        }
        orderEO.setLastUpdateTime(new Date());
        orderEOMapper.updateByPrimaryKey(orderEO);
        OrderVO orderVO = BeanMapper.map(orderEO, OrderVO.class);
        return orderVO;
    }

    /**
     * 创建订单后，通知金币商城
     * @param orderEO
     */
    private void informMall(OrderEO orderEO) {
        Map<String, Object> map = ParamUtils.convertMap(orderEO);
        LOGGER.info("informMall:"+informMallUrl);
        String str = HttpClientRequest.getClientRequest(informMallUrl,map);
        JSONObject jsonObject = JSON.parseObject(str);
        if (jsonObject.getString("code").equals("00")) {
            String mallOrderId = jsonObject.getString("result");
            orderEO.setMallOrderId(mallOrderId);
            orderEO.setLastUpdateTime(new Date());
            orderEOMapper.updateByPrimaryKey(orderEO);
        }
    }

    /**
     * 金币商城交易成功回调
     * @param orderNo
     * @param dealNum
     * @return
     */
    @Override
    @Transactional
    public OrderVO mallTradeSuccess(String orderNo, Double dealNum) {
        OrderEO orderEO = this.queryOrderByOrderId(orderNo);
        if (orderEO.getOrderStatus() == OrderStatus.CANCELED.getCode() || orderEO.getOrderStatus() == OrderStatus.SUCCESS.getCode()) {
            throw new OrderException(OrderException.ORDER_IS_END);
        }
        if (dealNum > orderEO.getCurrencyNum()) {
            LOGGER.info("金币商城返回参数异常:{实际成交数量:"+dealNum+" 大于订单中的通货数量:}"+orderEO.getCurrencyNum());
            throw new OrderException("金币商城返回参数异常");
        }
        int status = orderEO.getOrderStatus();
        orderEO.setDealNum(dealNum);
        orderEO.setOrderStatus(OrderStatus.SUCCESS.getCode());
        this.changeOrderStatus(orderEO,OrderStatus.getOrderStatus(status));
        OrderVO orderVO = BeanMapper.map(orderEO, OrderVO.class);
        return orderVO;
    }

    /**
     * 平台用户购买的金币总数
     * @param params
     * @return
     */
    @Override
    public double selectBuyGoldNum(Map<String, Object> params) {
        return orderEOMapper.selectBuyGoldNum(params);
    }

    /**
     * 查询金币商城交易金币(包括存入，取回)
     * @param params
     * @return
     */
    @Override
    public double selectMallTradeGold(Map<String, Object> params) {
        return orderEOMapper.selectMallTradeGold(params);
    }

    @Override
    public double selectPoundage(Map<String, Object> params) {
        return orderEOMapper.selectPoundage(params);
    }

    /**
     * 添加补单日志
     * @param orderEO
     */
    private void addOrderLog(OrderEO orderEO) {
        StringBuffer sb = new StringBuffer();
        sb.append(orderEO.getServiceAccount()+"补单，订单号为：" + orderEO.getOrderNo());
        sb.append(",主订单号为：" + orderEO.getMainOrderNo());
        OrderLogEO orderLogEO = new OrderLogEO();
        orderLogEO.setContent(sb.toString());
        orderLogEO.setUserAccount(orderEO.getUserAccount());
        orderLogEO.setOrderNo(orderEO.getOrderNo());
        orderLogEO.setCreateTime(new Date());
        orderLogEO.setLastUpdateTime(new Date());
        orderLogService.insert(orderLogEO);
    }
}
