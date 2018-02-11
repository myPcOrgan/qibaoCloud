package com.qibao.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 密码工具类
 *
 * @author ztjie
 * @date 2016-5-12 上午11:15:35
 */
public class PassWordHelper {
    /**
     * 加密密码
     *
     * @param clearTextPwd  密码明文
     * @param userLoginName 用户名
     * @return
     */
    public static String encyptPassword(String clearTextPwd, String userLoginName) {
        return Base64.encodeBase64Binrary(Digests.sha1(clearTextPwd.getBytes(),
                userLoginName.getBytes()));
    }


    /**
     * 验证密码
     *
     * @param clearTextPwd  请求密码明文
     * @param userLoginName 请求用户名
     * @param dbPassword    数据库保存加密密码
     * @return
     */
    public static boolean verifyPassword(String clearTextPwd, String userLoginName, String dbPassword) {
        //根据请求信息，生成密码
        String genePassword = encyptPassword(clearTextPwd, userLoginName);

        //与数据库保存密码比较
        if (StringUtils.equals(genePassword, dbPassword)) {
            return true;
        }
        return false;
    }

    /**
     * 对字符加星号处理：除前面几位和后面几位外，其他的字符以星号代替
     *
     * @param content  传入的字符串
     * @param frontNum 保留前面字符的位数
     * @param endNum   保留后面字符的位数
     * @return 带星号的字符串
     */

    public static String getStarString(String content, int frontNum, int endNum) {

        if (frontNum >= content.length() || frontNum < 0) {
            return content;
        }
        if (endNum >= content.length() || endNum < 0) {
            return content;
        }
        if (frontNum + endNum >= content.length()) {
            return content;
        }
        StringBuffer starStr = new StringBuffer();
        for (int i = 0; i < (content.length() - frontNum - endNum); i++) {
            starStr.append("*");
        }
        return content.substring(0, frontNum) + starStr
                + content.substring(content.length() - endNum, content.length());

    }
}
