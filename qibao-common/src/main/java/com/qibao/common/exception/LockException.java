package com.qibao.common.exception;

/**
 * @author Link
 */
public class LockException extends DaoException {
    public static final LockException VERSION_EMPTY = new LockException(201, "版本不存在");

    public LockException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
