package com.atlp.rsgl.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


/**
 * JSON的工具类
 *
 * <h3>Here is an example:</h3>
 *
 * <pre>
 *     // 将json通过类型转换成对象
 *     {@link JsonUtil JsonUtil}.json2Obj("{\"username\":\"username\", \"password\":\"password\"}", User.class);
 * </pre>
 * <hr />
 * <pre>
 *     // 传入转换的引用类型
 *     {@link JsonUtil JsonUtil}.fromJson("[{\"username\":\"username\", \"password\":\"password\"}, {\"username\":\"username\", \"password\":\"password\"}]", new TypeReference&lt;List&lt;User&gt;&gt;);
 * </pre>
 * <hr />
 * <pre>
 *     // 将对象转换成json
 *     {@link JsonUtil JsonUtil}.obj2Json(user);
 * </pre>
 * <hr />
 *
 * <pre>
 *     // 将对象转换成json, 传入配置对象
 *     {@link ObjectMapper ObjectMapper} mapper = new ObjectMapper();
 *     mapper.configure({@link Feature Feature.FAIL_ON_UNKNOWN_PROPERTIES}, false);
 *     mapper.configure({@link Feature Feature.FAIL_ON_NUMBERS_FOR_ENUMS}, true);
 *     mapper.setDateFormat(new {@link SimpleDateFormat SimpleDateFormat}("yyyy-MM-dd HH:mm:ss"));
 *     {@link JsonUtil JsonUtil}.toJson(user, mapper);
 * </pre>
 * <hr />
 * <pre>
 *     // 获取Mapper对象
 *     {@link JsonUtil JsonUtil}.getObjectMapper();
 * </pre>
 *
 * @see JsonUtil JsonUtil
 * @see Feature Feature
 * @see ObjectMapper ObjectMapper
 * @see IOException IOException
 * @see SimpleDateFormat SimpleDateFormat
 *
 */
@SuppressWarnings("unchecked")
public final class JsonUtil {
	
	public static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
    private static ObjectMapper mapper = new ObjectMapper();
    /** 格式化时间的string */
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static{
    	//设置时间格式
//		mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
		mapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY) ;      // 属性为空（“”）或者为 NULL 都不序列化
//		mapper.configure(DeserializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性   
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);   
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

    }
    private JsonUtil() {
    	
    }
    /**
     * 返回{@link ObjectMapper ObjectMapper}对象, 用于定制性的操作
     *
     * @return {@link ObjectMapper ObjectMapper}对象
     */
    public static ObjectMapper getObjectMapper(){
    	return mapper;
    }
    /**
     * 将json通过类型转换成对象
     *
     * <pre>
     *     {@link JsonUtil JsonUtil}.json2Obj("{\"username\":\"username\", \"password\":\"password\"}", User.class);
     * </pre>
     *
     * @param json json字符串
     * @param clazz 泛型类型
     * @return 返回对象
     * @throws IOException
     */
    
	public static <T> T json2Obj(String json, Class<T> clazz) {
        try {
			return clazz.equals(String.class) ? (T) json : mapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("json转对象出错,json = "+json,e);
			throw new RuntimeException(json+"===========>json转换为obj错误<===========");
		}
    }
    /**
     * 将对象转换成json
     * 支持Bean、Map、Collection
     *
     * <pre>
     *     {@link JsonUtil JsonUtil}.obj2Json(user);
     * </pre>
     *
     * @param src 对象
     * @return 返回json字符串
     * @throws Exception 
     */
    public static <T> String obj2Json(T src)   {
    	ObjectMapper objectMapper = getObjectMapper();
    	//过滤null 输出""
		objectMapper.getSerializerProvider().setNullValueSerializer(
				new JsonSerializer<Object>() {
					@Override
					public void serialize(Object value, JsonGenerator jg,
							SerializerProvider sp) throws IOException,
							JsonProcessingException {
						jg.writeString("");
					}
				});
        try {
			return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
		} catch (IOException e) {
			logger.debug("===========>>>>>>>转换数据错误");
			e.printStackTrace();
			throw new RuntimeException(src+"===========>>>>>>>转换为json错误");
		}
    }    
    /**
     * 将json转化为Map
     * @date 2013-11-1 上午11:13:31
     * @param json
     * @return
     * @throws IOException
     */
//    public static Map json2Map(String json) throws IOException{
//    	return mapper.readValue(json,Map.class);
//    }
    /**
     * 将json转换为List<map>
     * @date 2013-11-1 上午11:15:43
     * @param json
     * @return
     * @throws Exception 
     */
    public static List<Map<String, Object>> json2List(String json) {
    	try {
			return mapper.readValue(json, List.class);
		} catch (IOException e) {
			logger.debug("===========>>>>>>>转换数据错误");
			e.printStackTrace();
			throw new RuntimeException(json+"===========>>>>>>>转换为json错误");
		}
    }
    /**
     * 将json通过类型转换成对象
     *
     * <pre>
     *     {@link JsonUtil JsonUtil}.fromJson("[{\"username\":\"username\", \"password\":\"password\"}, {\"username\":\"username\", \"password\":\"password\"}]", new TypeReference&lt;List&lt;User&gt;&gt;);
     * </pre>
     *
     * @param json json字符串
     * @param typeReference 引用类型
     * @return 返回对象
     * @throws Exception 
     */
    public static <T> T fromJson(String json, TypeReference<?> typeReference)   {
        try {
			return (T) (typeReference.getType().equals(String.class) ? json : mapper.readValue(json, typeReference));
		} catch (IOException e) {
			logger.debug("===========>>>>>>>转换数据错误");
			e.printStackTrace();
			throw new RuntimeException(json+"===========>>>>>>>转换为json错误");
		}
    }



    /**
     * 将对象转换成json 可以设置时间显示格式
     * @date 2013-11-1 下午02:02:58
     * @param <T>
     * @param src
     * @param dateTimeFormatString
     * 			 自定义的日期/时间格式。该属性的值遵循java标准的date/time格式规范。如：yyyy-MM-dd
     * @return
     * @throws Exception 
     */
    public static <T> String obj2Json(T src, String dateTimeFormatString)  {
    	ObjectMapper objectMapper = getObjectMapper();
    	objectMapper.setDateFormat(new SimpleDateFormat((dateTimeFormatString)));
		try {
			return objectMapper.writeValueAsString(src);
		} catch (IOException e) {
			logger.debug("------>转换数据错误<------");
			e.printStackTrace();
			throw new RuntimeException(src+"===========>>>>>>>按"+dateTimeFormatString+"时间格式转换为json错误");
		}
    }

    /**
     * 将对象转换成json, 传入配置对象
     *
     * <pre>
     *     {@link ObjectMapper ObjectMapper} mapper = new ObjectMapper();
     *     mapper.setSerializationInclusion({@link Inclusion Inclusion.ALWAYS});
     *     mapper.configure({@link Feature Feature.FAIL_ON_UNKNOWN_PROPERTIES}, false);
     *     mapper.configure({@link Feature Feature.FAIL_ON_NUMBERS_FOR_ENUMS}, true);
     *     mapper.setDateFormat(new {@link SimpleDateFormat SimpleDateFormat}("yyyy-MM-dd HH:mm:ss"));
     *     {@link JsonUtil JsonUtil}.toJson(user, mapper);
     * </pre>
     *
     * {@link ObjectMapper ObjectMapper}
     *
     * @see ObjectMapper
     *
     * @param src 对象
     * @param mapper 配置对象
     * @return 返回json字符串
     * @throws Exception 
     */
    public static <T> String obj2Json(T src, ObjectMapper mapper)   {
        if (null != mapper) {
            if (src instanceof String) {
                return (String) src;
            } else {
                try {
					return mapper.writeValueAsString(src);
				} catch (IOException e) {
					logger.debug("===========>>>>>>>转换数据错误");
					e.printStackTrace();
					throw new RuntimeException(src+"===========>>>>>>>转换为json错误");
				}
            }
        } else {
            return null;
        }
    }
    /**
     * 将对象转换成json,同时忽略部分属性
     * @date 2013-11-18 下午02:54:53
     * @param <T>
     * @param src	需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param propertyArray	要忽视的字段名称
     * @return
     * @throws Exception 
     */
    public static <T> String obj2JsonIgnoreProperties(T src,String... propertyArray) throws Exception {
    	ObjectMapper objectMapper = getObjectMapper();
    	String id = AnnotationUtils.getValue( AnnotationUtils.findAnnotation(src.getClass(), JsonFilter.class)).toString();
    	SimpleFilterProvider fileter = new SimpleFilterProvider();
    	fileter.addFilter(id,SimpleBeanPropertyFilter.serializeAllExcept(propertyArray));
    	objectMapper.setFilterProvider(fileter);
    	String json = obj2Json(src, objectMapper);
    	return json;
    }
    /**
     * 将对象转换成json,仅保留部分属性
     * @date 2013-11-18 下午03:05:01
     * @param <T>
     * @param src	需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param propertyArray	要保留的属性
     * @return
     * @throws Exception
     */
    public static <T> String obj2JsonIncludeProperties(T src,String... propertyArray) throws Exception{
    	ObjectMapper objectMapper = getObjectMapper();
    	String id = AnnotationUtils.getValue( AnnotationUtils.findAnnotation(src.getClass(), JsonFilter.class)).toString();
		SimpleFilterProvider fileter = new SimpleFilterProvider();
		fileter.addFilter(id,SimpleBeanPropertyFilter.filterOutAllExcept(propertyArray));
		objectMapper.setFilterProvider(fileter);
		//String json = obj2Json(src, objectMapper);
		String json =objectMapper.writeValueAsString(src);
    	return json;
    }
    
    
}