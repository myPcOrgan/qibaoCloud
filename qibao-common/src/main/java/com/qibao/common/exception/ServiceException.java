package com.qibao.common.exception;

/**
 * @author Link
 */
public class ServiceException extends BaseException {

    public static final ServiceException ID_EMPTY = new ServiceException(10, "唯一标识为空");

    public static final ServiceException PARAMS_EMPTY = new ServiceException(11, "参数为空");

    public static final ServiceException DATA_EMPTY = new ServiceException(12, "数据为空");

    public ServiceException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
