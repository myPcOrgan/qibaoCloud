package com.qibao.goods.exception;

import com.qibao.common.exception.BaseException;

/**
 * Created by 周黎钢 on 2018/1/11.
 */
public class GoodsException extends BaseException {
    //普通异常
    public final static Integer COMMON_EXCEPTION=1;
    //严重异常
    public final static Integer FATAL_EXCEPTION=2;

    public final static String ID_NOT_NULL="id不能为空";

    public final static String CATE_CODE_NOT_NULL="分类code不能为空";

    public final static String CATE_NAME_NOT_NULL="类目名称不能为空";

    public final static String GOODS_NAME_NOT_NULL="商品名称不能为空";

    public final static String GOODS_UNIT_NOT_NULL="商品单位不能为空";

    public final static String GOODS_NUM_NOT_NULL="商品数量不能为空";

    public final static String GOODS_AMOUNT_NOT_NULL="商品金额不能为空";
    //不能执行is_deleted为false的操作
    public final static String IS_DELETED ="非法操作，无法执行";

    public GoodsException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
