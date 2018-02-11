package com.qibao.common.exception;

/**
 * @author Link
 */
public class DaoException extends BaseException {

    public static final DaoException PK_EMPTY = new DaoException(100, "PrimaryKey is empty");

    public static final DaoException ENTITY_EMPTY = new DaoException(101, "Entity is empty");

    public static final DaoException DATA_EXISTS = new DaoException(102, "Data is exists");

    public DaoException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
