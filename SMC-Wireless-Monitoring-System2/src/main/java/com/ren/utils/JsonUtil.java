package com.ren.utils;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    //swift编写的模型转换框架
    private static ObjectMapper objectMapper = new ObjectMapper();
    //初始化objectMapper

    static{

        /**
         * 序列化的配置
         * */
        //把对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);
        //取消默认转换timestamps形式(timestamps默认应该是会将1970/1/1到当前date的日期的毫秒数输送出来,即时间戳),自定义日期时间格式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空bean转Json的错误即置为false，如果为true的话如果想要序列化的对象的属性中有空属性的话，就会报错
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        /**
         *反序列化配置
         * */
        //忽略未知的属性
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * (封装)将model对象转换为Json字符串
     * */
    public static <T> String obj2String(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ?(String)obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.warn("Parse objecr to String error",e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ?(String)obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            logger.warn("Parse objecr to String error",e);
            return null;
        }
    }

    /**
     * (封装)将String字符串转换为model对象
     * */
    public static <T> T string2Obj(String str,Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class)?(T)str:objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            logger.warn("Parse String to Object error", e);
            return null;
        }
    }

    //拓展业务，当需要返回的类型没有指定时
    public static <T> T string2Obj(String str, TypeReference<T> typeReference){
        if(StringUtils.isEmpty(str) || typeReference == null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class)?str:objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            logger.warn("Parse String to Object error",e);
            return null;
        }
    }
    public static <T> T string2Obj(String str,Class<?> collectionClass,Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str,javaType);
        } catch (Exception e) {
            logger.warn("Parse String to Object error",e);
            return null;
        }
    }

}

