package com.qibao.backend.api.order.controller;

import com.qibao.backend.common.UserContext;
import com.qibao.backend.feign.IOrderBackendFeign;
import com.qibao.backend.feign.IUserInfoFeign;
import com.qibao.backend.model.UserVO;
import com.qibao.backend.utils.ExportExcel;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.order.content.OrderDTO;
import com.qibao.order.enums.OrderStatus;
import com.qibao.order.enums.OrderType;
import com.qibao.order.enums.PayType;
import com.qibao.order.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by ljn on 2018/1/25.
 */
@RestController
@RequestMapping("backend/order")
public class OrderBackendController extends BaseController{

    @Autowired
    private IOrderBackendFeign orderBackendFeign;

    @Autowired
    private IUserInfoFeign userInfoFeign;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static final String FILES_EXPORT_PATH = "/srv/qibao/order_xport/";

    @RequestMapping(value = "/queryOrderList",method = RequestMethod.POST)
    @ApiOperation(value = "查询的订单列表", notes="查询的订单列表")
    BaseResponse<OrderVO> queryOrderList(@RequestBody OrderDTO orderDTO) throws ParseException {
        return orderBackendFeign.queryOrderList(orderDTO);
    }

    @RequestMapping(value="exportOrderList",method = RequestMethod.GET)
    @ApiOperation(value = "导出订单列表", notes="导出订单列表")
    public BaseResponse<OrderVO> exportOrderList(@RequestParam(name="orderType",required = false)Integer orderType,
                                                 @RequestParam(name="userAccount",required = false)String userAccount,
                                                 @RequestParam(name="orderStatus",required = false)Integer orderStatus,
                                                 @RequestParam(name="mallOrderId",required = false)String mallOrderId,
                                                 @RequestParam(name="orderNo",required = false)String orderNo,
                                                 @RequestParam(name="createStartTime",required = false)String createStartTime,
                                                 @RequestParam(name="createEndTime",required = false)String createEndTime,
                                                 @RequestParam(name="serviceAccount",required = false)String serviceAccount,
                                                 HttpServletResponse response) throws ParseException, IOException {
        BaseResponse<OrderVO> orderResponse = new BaseResponse();
        if (orderType == null) {
            throw new BaseException(01,"订单类型不能为空");
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderType(orderType);
        orderDTO.setUserAccount(userAccount);
        orderDTO.setOrderStatus(orderStatus);
        orderDTO.setMallOrderId(mallOrderId);
        orderDTO.setOrderNo(orderNo);
        orderDTO.setCreateStartTime(createStartTime);
        orderDTO.setCreateEndTime(createEndTime);
        orderDTO.setServiceAccount(serviceAccount);
        orderDTO.setPage(0);
        orderDTO.setSize(10000);
        List<OrderVO> list = orderBackendFeign.queryOrderList(orderDTO).getData();
        String path = null;
        if (orderType == OrderType.MALL_BUY.getCode()) {
            path = exportBuyOrder(list);
        }else {
            path = exportOrder(list);
        }

        download(response, path);

        return orderResponse;
    }

    /**
     * 补单
     * @param mainOrderNo
     * @param num
     * @param addOrderType 1:增加，2:减少
     * @return
     */
    @RequestMapping(value = "/additionalOrder",method = RequestMethod.GET)
    public BaseResponse<OrderVO> additionalOrder(@RequestParam("mainOrderNo")String mainOrderNo,
                                                 @RequestParam("num")Double num,
                                                 @RequestParam("addOrderType")Integer addOrderType) {
        BaseResponse<OrderVO> orderResponse = new BaseResponse<>();
        UserVO userVO = UserContext.getCurrentUser();
        if (userVO == null) {
            throw new BaseException(01,"用户未登录");
        }
        orderBackendFeign.additionalOrder(mainOrderNo,num,userVO.getLoginAccount(),addOrderType);
        return orderResponse;
    }


    /**
     * 存入，取出订单
     * @param list
     * @return
     */
    public String exportOrder(List<OrderVO> list) {

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet();

        ExportExcel exportExcel = new ExportExcel(wb, sheet);

        // 创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();

        // 指定单元格居中对齐
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);

        // 创建报表头部
        String headString = "订单列表导出";
        int columnSize = 10;
        exportExcel.createNormalHead(0, headString, columnSize - 1);

        String[] columnHeader = new String[]{
                "订单状态",
                "订单编号",
                "用户",
                "金币商城订单号",
                "金币商城交易商",
                "订单总额",
                "实际交易总额",
                "手续费",
                "交易客服",
                "创建时间"
        };
        exportExcel.createColumHeader(1, columnHeader);

        HSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);

        //设置列的宽度
        sheet.setColumnWidth(0,columnSize*10*60);
        sheet.setColumnWidth(1,columnSize*10*60);
        sheet.setColumnWidth(2,columnSize*10*60);
        sheet.setColumnWidth(3,columnSize*10*60);
        sheet.setColumnWidth(4,columnSize*10*60);
        sheet.setColumnWidth(5,columnSize*10*60);
        sheet.setColumnWidth(6,columnSize*10*60);
        sheet.setColumnWidth(7,columnSize*10*60);
        sheet.setColumnWidth(8,columnSize*10*60);
        sheet.setColumnWidth(9,columnSize*10*60);

        // 循环创建中间的单元格的各项的值
        if (!CollectionUtils.isEmpty(list)) {
            int i = 2;
            for (OrderVO item : list) {
                HSSFRow row = sheet.createRow((short) i++);

                /* 订单状态 */
                String orderStatus = OrderStatus.getOrderStatus(item.getOrderStatus()).getStatus();
                /* 订单编号 */
                String orderNo = item.getOrderNo();
                /* 收货商 */
                String trader = item.getTrader();
                /* 存入人 */
                String userAccount = item.getUserAccount();
                /* 金币商城订单号 */
                String mallOrderId = item.getMallOrderId();
                /* 订单总额 */
                String currencyNum = item.getCurrencyNum() == null ? "0" : item.getCurrencyNum().toString();
                /* 实际交易总额 */
                String dealNum = item.getDealNum() == null ? "0" : item.getDealNum().toString();
                /* 手续费 */
                String poundage = item.getPoundage() == null ? "0" : item.getPoundage().toString();
                /* 交易客服 */
                String serviceAccount = item.getServiceAccount();
                /* 创建时间 */
                String createTime = item.getCreateTime() == null ? "" : format.format(item.getCreateTime());

                exportExcel.createCell(wb, row, 0, CellStyle.ALIGN_CENTER, orderStatus);
                exportExcel.createCell(wb, row, 1, CellStyle.ALIGN_CENTER, orderNo);
                exportExcel.createCell(wb, row, 2, CellStyle.ALIGN_CENTER, userAccount);
                exportExcel.createCell(wb, row, 3, CellStyle.ALIGN_CENTER, mallOrderId);
                exportExcel.createCell(wb, row, 4, CellStyle.ALIGN_CENTER, trader);
                exportExcel.createCell(wb, row, 5, CellStyle.ALIGN_CENTER, currencyNum);
                exportExcel.createCell(wb, row, 6, CellStyle.ALIGN_CENTER, dealNum);
                exportExcel.createCell(wb, row, 7, CellStyle.ALIGN_CENTER, poundage);
                exportExcel.createCell(wb, row, 8, CellStyle.ALIGN_CENTER, serviceAccount);
                exportExcel.createCell(wb, row, 9, CellStyle.ALIGN_CENTER, createTime);
            }
        }

        File file = new File(FILES_EXPORT_PATH);
        file.mkdirs();
        String filePath = FILES_EXPORT_PATH + "/" + UUID.randomUUID().toString() + ".xls";
        exportExcel.outputExcel(filePath);
        return filePath;
    }

    /**
     * 购买订单
     * @param list
     * @return
     */
    public String exportBuyOrder(List<OrderVO> list){

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet();

        ExportExcel exportExcel = new ExportExcel(wb, sheet);

        // 创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();

        // 指定单元格居中对齐
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);

        // 创建报表头部
        String headString = "购买列表导出";
        int columnSize = 7;
        exportExcel.createNormalHead(0, headString, columnSize - 1);

        String[] columnHeader = new String[]{
                "订单状态",
                "订单编号",
                "用户",
                "支付方式",
                "金额",
                "数量",
                "创建时间"
        };
        exportExcel.createColumHeader(1, columnHeader);

        HSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);

        //设置列的宽度
        sheet.setColumnWidth(0,columnSize*10*60);
        sheet.setColumnWidth(1,columnSize*10*80);
        sheet.setColumnWidth(2,columnSize*10*60);
        sheet.setColumnWidth(3,columnSize*10*60);
        sheet.setColumnWidth(4,columnSize*10*60);
        sheet.setColumnWidth(5,columnSize*10*60);
        sheet.setColumnWidth(6,columnSize*10*60);
        sheet.setColumnWidth(7,columnSize*10*80);

        // 循环创建中间的单元格的各项的值
        if (!CollectionUtils.isEmpty(list)) {
            int i = 2;
            for (OrderVO item : list) {
                HSSFRow row = sheet.createRow((short) i++);

                /* 订单状态 */
                String orderStatus = OrderStatus.getOrderStatus(item.getOrderStatus()).getStatus();
                /* 订单编号 */
                String orderNo = item.getOrderNo();
                /* 用户 */
                String userAccount = item.getUserAccount();
                /* 支付方式 */
                String payType = null;
                if (item.getPayType() != null) {
                    payType = PayType.getPayType(item.getPayType()).getName();
                }
                /* 金额 */
                String amountMoney = item.getAmountMoney() == null ? "0" : item.getAmountMoney().toString();
                /* 数量 */
                String currencyNum = item.getCurrencyNum() == null ? "" : item.getCurrencyNum().toString();
                /* 创建时间 */
                String createTime = item.getCreateTime() == null ? "" : format.format(item.getCreateTime());

                exportExcel.createCell(wb, row, 0, CellStyle.ALIGN_CENTER, orderStatus);
                exportExcel.createCell(wb, row, 1, CellStyle.ALIGN_CENTER, orderNo);
                exportExcel.createCell(wb, row, 2, CellStyle.ALIGN_CENTER, userAccount);
                exportExcel.createCell(wb, row, 3, CellStyle.ALIGN_CENTER, payType);
                exportExcel.createCell(wb, row, 4, CellStyle.ALIGN_CENTER, amountMoney);
                exportExcel.createCell(wb, row, 5, CellStyle.ALIGN_CENTER, currencyNum);
                exportExcel.createCell(wb, row, 6, CellStyle.ALIGN_CENTER, createTime);
            }
        }

        File file = new File(FILES_EXPORT_PATH);
        file.mkdirs();
        String filePath = FILES_EXPORT_PATH + "/" + UUID.randomUUID().toString() + ".xls";
        exportExcel.outputExcel(filePath);
        return filePath;
    }

    public void download(HttpServletResponse response, String path) throws IOException {

        //读到流中
        InputStream in = new FileInputStream(path);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("application/msexcel");
        response.addHeader("Content-Disposition", "attachment; filename=\"orderList.xls\"");
        OutputStream out = response.getOutputStream();
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = in.read(b)) > 0){
                out.write(b, 0, len);
            }
        } finally {
            out.flush();
            out.close();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
