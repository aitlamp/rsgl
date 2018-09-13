package com.atlp.rsgl.common.utils;

import com.atlp.rsgl.common.data.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 公共工具类
 *
 * @author ctc
 * @date 2018年8月22日 22:21:23
 */
public class AtlpUtil {
    private static Logger log = LoggerFactory.getLogger(AtlpUtil.class);

    /**
     * 获取请求客户端的IP地址
     */
    public static String getClientIP(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.equals("") || ip.equals("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断对象是否Empty(null或元素为0) 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null)
            return true;
        if (pObj == "")
            return true;
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            } else if (((String) pObj).equalsIgnoreCase("null")) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection<?>) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map<?, ?>) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置字段属性的值
     *
     * @param tobj      要赋值对象
     * @param fieldName 字段名称
     */
    public static boolean setFieldVal(Object tobj, String fieldName, Object tval) {
        boolean retVal = false;
        try {
            Field tfld = tobj.getClass().getDeclaredField(fieldName);
            boolean flag = tfld.isAccessible();
            tfld.setAccessible(true);
            tfld.set(tobj, tval);
            tfld.setAccessible(flag);
            retVal = true;
        } catch (NoSuchFieldException e) {
            log.debug("类'" + tobj.getClass() + "'中无属性'" + fieldName + "'!");
        } catch (IllegalAccessException e) {
            log.debug("类'" + tobj.getClass() + "'中属性'" + fieldName + "'不能访问!");
        }
        return retVal;
    }

    /**
     * 设置用户信息
     *
     * @param entity  实体对象
     * @param request
     */
    public static void setUserInfo(Object entity, HttpServletRequest request) {
        // 判断实体是否为空
        if (entity == null) {
            return;
        }
        //获取session中的用户信息
        UserInfo userinfo = (UserInfo) request.getSession().getAttribute("userinfo");
        // 当前登录人信息
        AtlpUtil.setFieldVal(entity, "yhid", (userinfo.getYhid() == null ? "testyhid" : userinfo.getYhid()));
        AtlpUtil.setFieldVal(entity, "yhxm", (userinfo.getYhxm() == null ? "testyhxm" : userinfo.getYhxm()));
        AtlpUtil.setFieldVal(entity, "yhdwid", (userinfo.getDwid() == null ? "testdwid" : userinfo.getDwid()));
        AtlpUtil.setFieldVal(entity, "yhdwmc", (userinfo.getDwmc() == null ? "testdwmc" : userinfo.getDwmc()));
    }

    /**
     * 获取值为null的属性名称
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 将源对象赋值给目标对象（只赋值名称相同的属性）
     *
     * @param srcObj 源对象
     * @param aimObj 目标对象
     */
    public static void copyProperties(Object srcObj, Object aimObj) {
        try {
            // 获取已经声明的属性
            Field[] aimfields = aimObj.getClass().getDeclaredFields();
            for (int i = 0; i < aimfields.length; i++) {
                Field tfld = aimfields[i];
                // 获取值
                Object tvalue = null;
                try {
                    Field sfld = srcObj.getClass().getField(tfld.getName());
                    if (sfld.getName().equals(tfld.getName()) && sfld.getType().equals(tfld.getType())) {
                        boolean aflag = sfld.isAccessible();
                        sfld.setAccessible(true);
                        tvalue = sfld.get(srcObj);
                        sfld.setAccessible(aflag);
                    }
                } catch (NoSuchFieldException e) {
                    log.debug("类'" + srcObj.getClass().toString() + "'无此属性：'" + tfld.getName() + "'");
                }
                // 通过方法到父类中获取
                if (tvalue == null) {
                    String methodName = "get" + tfld.getName().substring(0, 1).toUpperCase() + tfld.getName().substring(1);
                    try {
                        Method metd = srcObj.getClass().getMethod(methodName);
                        Object ret = metd.invoke(srcObj);
                        if (ret != null && ret.getClass().equals(tfld.getType()))
                            tvalue = ret;
                    } catch (NoSuchMethodException e) {
                        log.debug("类'" + srcObj.getClass().toString() + "'无此方法：'" + methodName + "'");
                    } catch (InvocationTargetException e) {
                        log.debug("类'" + srcObj.getClass().toString() + "'调用方法：'" + methodName + "'出错");
                    }
                }
                // 设置值
                if (tvalue != null) {
                    boolean flag = tfld.isAccessible();
                    tfld.setAccessible(true);
                    tfld.set(aimObj, tvalue);
                    tfld.setAccessible(flag);
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("对象赋值出错:" + e.getMessage());
        }
    }
}
