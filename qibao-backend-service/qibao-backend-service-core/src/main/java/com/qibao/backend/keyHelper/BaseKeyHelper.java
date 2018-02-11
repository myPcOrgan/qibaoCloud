package com.qibao.backend.keyHelper;

/**
 * Created by 周黎钢 on 2018/2/5.
 */
public class BaseKeyHelper {
    private static final String BASE_KEY = "qibao:";
    private static final String BACKEND_BASE_KEY = "backend:";
    private static final String BACKEND_FUNCTIOND = "functions";

    public static String getBackendFunctionsKey() {
        return BASE_KEY + BACKEND_BASE_KEY + BACKEND_FUNCTIOND;
    }
}
