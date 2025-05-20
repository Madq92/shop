package tech.oldhorse.shop.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.log4j.Log4j2;
import tech.oldhorse.shop.common.exception.BaseServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class JacksonUtil {
    /**
     * 序列化级别，默认只序列化不为空的字段
     */
    protected static final JsonInclude.Include DEFAULT_PROPERTY_INCLUSION = JsonInclude.Include.NON_NULL;
    /**
     * 是否缩进JSON格式
     */
    protected static final boolean IS_ENABLE_INDENT_OUTPUT = false;
    public static ObjectMapper mapper;

    static {
        try {
            //初始化
            mapper = new ObjectMapper();
            //配置序列化级别
            mapper.setSerializationInclusion(DEFAULT_PROPERTY_INCLUSION);
            //配置JSON缩进支持
            mapper.configure(SerializationFeature.INDENT_OUTPUT, IS_ENABLE_INDENT_OUTPUT);
            //配置普通属性
            config(mapper);
        } catch (Exception e) {
            log.error("jackson config error", e);
        }
    }

    protected static void config(ObjectMapper objectMapper) {
        //序列化BigDecimal时之间输出原始数字还是科学计数, 默认false, 即是否以toPlainString()科学计数方式来输出
        objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        //允许将JSON空字符串强制转换为null对象值
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        //允许单个数值当做数组处理
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        //禁止重复键, 抛出异常
        objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        //禁止使用int代表Enum的order()來反序列化Enum, 抛出异常
        objectMapper.enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS);
        //有属性不能映射的时候不报错
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //使用null表示集合类型字段是时不抛异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        //对象为空时不抛异常
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        //允许在JSON中使用c/c++风格注释
        objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
        //强制转义非ascii字符
        objectMapper.disable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
        //允许未知字段
        objectMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        //在JSON中允许未引用的字段名
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        //时间格式
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //识别单引号
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        //识别特殊字符
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
        //识别Java8时间
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        //识别Guava包的类
        //objectMapper.registerModule(new GuavaModule());
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }


    /**
     * JSON反序列化
     */
    public static <V> V from(InputStream inputStream, Class<V> type) {
        try {
            return mapper.readValue(inputStream, type);
        } catch (IOException e) {
            log.error("jackson from error, type: {}", type, e);
            throw new BaseServiceException("jackson from error, type: " + type.getName(), e);
        }
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(InputStream inputStream, TypeReference<V> type) {
        try {
            return mapper.readValue(inputStream, type);
        } catch (IOException e) {
            log.error("jackson from error, type: {}", type, e);
            throw new BaseServiceException("jackson from error, type: " + type, e);
        }
    }

    /**
     * JSON反序列化（List）
     */
    public static <V> List<V> fromList(InputStream inputStream, Class<V> type) {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, type);
            return mapper.readValue(inputStream, collectionType);
        } catch (IOException e) {
            log.error("jackson from error, type: {}", type, e);
            throw new BaseServiceException("jackson from error, type: " + type, e);
        }
    }


    /**
     * JSON反序列化
     */
    public static <V> V from(String json, Class<V> type) {
        return from(json, (Type) type);
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(String json, TypeReference<V> type) {
        return from(json, type.getType());
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(String json, Type type) {
        if (json == null) {
            return null;
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructType(type);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("jackson from error, json: {}, type: {}", json, type, e);
            throw new BaseServiceException("jackson from error");
        }
    }

    /**
     * JSON反序列化（List）
     */
    public static <V> List<V> fromList(String json, Class<V> type) {
        if (json == null) {
            return null;
        }
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, type);
            return mapper.readValue(json, collectionType);
        } catch (IOException e) {
            log.error("jackson from error, json: {}, type: {}", json, type, e);
            throw new BaseServiceException("jackson from error");
        }
    }

    /**
     * JSON反序列化（Map）
     */
    public static Map<String, Object> fromMap(String json) {
        if (json == null) {
            return null;
        }
        try {
            MapType mapType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
            return mapper.readValue(json, mapType);
        } catch (IOException e) {
            log.error("jackson from error, json: {}", json, e);
            throw new BaseServiceException("jackson from error");
        }
    }

    /**
     * 序列化为JSON
     */
    public static <V> String to(List<V> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.error("jackson to error, data: {}", list, e);
            throw new BaseServiceException("jackson from error");
        }
    }

    /**
     * 序列化为JSON
     */
    public static <V> String to(V v) {
        try {
            return mapper.writeValueAsString(v);
        } catch (JsonProcessingException e) {
            log.error("jackson to error, data: {}", v, e);
            throw new BaseServiceException("jackson to error");
        }
    }


    /**
     * 格式化Json(美化)
     *
     * @return json
     */
    public static String format(String json) {
        try {
            JsonNode node = mapper.readTree(json);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (IOException e) {
            log.error("jackson format json error, json: {}", json, e);
            throw new BaseServiceException("jackson format json error");
        }
    }
}
