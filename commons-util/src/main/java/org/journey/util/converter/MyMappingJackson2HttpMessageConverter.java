package org.journey.util.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wudan-mac
 * @ClassName: MyMappingJackson2HttpMessageConverter
 * @ClassNameExplain: json转换器
 * @Description:
 * @date 16/5/18 上午9:04
 */
public class MyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    Logger logger = LoggerFactory.getLogger(MyMappingJackson2HttpMessageConverter.class);

    private final static String CONTENT_TYPE_KEY = "Content-Type";

    private String contentType = "application/json;charset=UTF-8";

    private String inputDateFormart = "yyyy-MM-dd HH:ss:dd";

    private Gson gson = new GsonBuilder().setDateFormat(inputDateFormart).create();

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        /**
         * 取出requestBody中内容
         * 目前只是做简单的打印
         * 其实这里可以自定义一些处理
         */
        String json = StreamUtils.copyToString(
                inputMessage.getBody(), inputMessage.getHeaders().getContentType().getCharSet());
        //打印入参
        logger.debug("request json : \n" + json);
        return gson.fromJson(json, type);
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        /**
         * 这里目前只是做一下返回参数打印所以启用了一个线程去执行  不影响主线程执行速度
         */
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            String json = gson.toJson(object);
            logger.debug("request json : \n" + json);
        });
        service.shutdown();
        //写响应头 为json 避免一些终端不识别
        outputMessage.getHeaders().add(CONTENT_TYPE_KEY, contentType);
        super.writeInternal(object, type, outputMessage);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getInputDateFormart() {
        return inputDateFormart;
    }

    public void setInputDateFormart(String inputDateFormart) {
        this.inputDateFormart = inputDateFormart;
    }
}
