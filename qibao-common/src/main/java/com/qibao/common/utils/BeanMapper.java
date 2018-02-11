package com.qibao.common.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Created by LinShu on 2017/3/16.
 */
public class BeanMapper {
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public BeanMapper() {
    }

    /**
     * 将源对象转换为目标对象
     *
     * @param source           源对象
     * @param destinationClass 目标对象
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return source == null ? null : dozer.map(source, destinationClass);
    }

    /**
     * 将一组源对象转换为一组目标对象
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        ArrayList<T> destinationList = Lists.newArrayList();
        if (sourceList != null && !sourceList.isEmpty()) {

            for (Object sourceObject : sourceList) {
                T destinationObject = dozer.map(sourceObject, destinationClass);

                destinationList.add(destinationObject);
            }

            return destinationList;
        } else {
            return destinationList;
        }
    }

    /**
     * 深拷贝对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 深拷贝对象
     *
     * @param source               源对象
     * @param target               目标对象
     * @param ignorePropertiesName 需要忽略复制的熟悉名称
     */
    public static void copyProperties(Object source, Object target, String... ignorePropertiesName) {
        try {
            BeanUtils.copyProperties(source, target, ignorePropertiesName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 深拷贝对象, 忽略空值属性
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取空值属性列表
     *
     * @param source 源对象
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }

    /**
     * 对比两个对象，属性值一样的放到目标集合里
     */
    public static void getSamePropertyValuesToList(Object source, Object target, List targetList) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper tg = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        PropertyDescriptor[] tgPds = tg.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            for (PropertyDescriptor tgpd : tgPds) {
                if (src.getPropertyValue(pd.getName()) != null && tg.getPropertyValue(tgpd.getName()) != null &&
                        pd.getName().equals(tgpd.getName()) && src.getPropertyValue(pd.getName()).equals(tg.getPropertyValue(tgpd.getName()))) {
                    targetList.add(target);
                }
            }
        }
    }
}
