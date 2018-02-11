///************************************************************************************
// * Copyright 2012 WZITech Corporation. All rights reserved.
// * <p>
// * 模	块：		RuntimeConstants
// * 包	名：		com.wzitech.chaos.framework.common
// * 项目名称：	chaos-common
// * 作	者：		Shawn
// * 创建时间：	2012-4-13
// * 描	述：		系统运行时常量定义类
// * 更新纪录：	1. Shawn 创建于 2012-4-13 下午4:15:17
// ************************************************************************************/
//package com.qibao.frontend.api.activity.utils;
//
//import java.io.*;
//
///**
// * 操作js文件工具类
// */
//public class ToJsUtils {
//
//    public static Boolean setJs(String pathName, String str) {
//        FileWriter writer = null;
//        try {
//            String path = System.getProperty("user.dir") + "/src/main/webapp/";
//            path = path + pathName;
//            File file = new File(path);
//            if (!file.exists()) {
//                if (!file.createNewFile()) {
//                    return false;
//                }
//            }
//            writer = new FileWriter(path);
//            writer.write(str);
//            writer.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            try {
//                if (writer != null) {
//                    writer.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//
//    public static String getJs(String pathName) {
//        String jsonString;
//        BufferedReader reader = null;
//        try {
//            String path = System.getProperty("user.dir") + "/src/main/webapp/";
//            path = path + pathName;
//            File file = new File(path);
//            if (!file.exists()) {
//                return null;
//            }
//            reader = new BufferedReader(new FileReader(path));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//            jsonString = sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return jsonString;
//    }
//}
