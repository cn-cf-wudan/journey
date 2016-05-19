package org.journey.util.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author wudan-mac
 * @ClassName: MyMappingJackson2HttpMessageConverter
 * @ClassNameExplain: json转换器
 * @Description:
 * @date 16/5/18 上午9:04
 */
public class MyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    Logger logger = LoggerFactory.getLogger(MyMappingJackson2HttpMessageConverter.class);

    //响应content-type 的key
    private final static String CONTENT_TYPE_KEY = "Content-Type";

    //响应content-type的值 可以在xml里设置
    private String responseContentType = "application/json;charset=UTF-8";

    //响应文本编码
    private String responseEncode = "UTF-8";

    //当入参json中包含 javaBean中不存在的属性时 是否返回序列化失败 可以在xml里设置
    private boolean failOnUnknownProperties = false;

    public String getResponseContentType() {
        return responseContentType;
    }

    public void setResponseContentType(String responseContentType) {
        this.responseContentType = responseContentType;
    }

    public boolean isFailOnUnknownProperties() {
        return failOnUnknownProperties;
    }

    public void setFailOnUnknownProperties(boolean failOnUnknownProperties) {
        this.failOnUnknownProperties = failOnUnknownProperties;
    }

    public String getResponseEncode() {
        return responseEncode;
    }

    public void setResponseEncode(String responseEncode) {
        this.responseEncode = responseEncode;
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);

        /**
         * 取出requestBody中内容
         * 目前只是做简单的打印
         * 其实这里可以自定义一些处理
         */
        String json = StreamUtils.copyToString(
                inputMessage.getBody(), inputMessage.getHeaders().getContentType().getCharSet());
        //打印入参
        logger.debug("request json : \n" + json);
        JavaType javaType = this.getJavaType(type, contextClass);
        return objectMapper.readValue(json, javaType);
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        String json = objectMapper.writeValueAsString(object);
        logger.debug("request json : \n" + json);
        //写响应头 为json 避免一些终端不识别
        outputMessage.getHeaders().add(CONTENT_TYPE_KEY, responseContentType);
        outputMessage.getBody().write(json.getBytes(responseEncode));
    }



}
