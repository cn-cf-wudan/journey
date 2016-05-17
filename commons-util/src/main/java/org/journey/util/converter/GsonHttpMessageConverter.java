package org.journey.util.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;

/**
 * @author 张光银
 * @ClassName: GsonHttpMessageConverter
 * @ClassNameExplain: Gson转换器
 * @Description: 替换spring默认的jackson，使用gson，并写入header
 * @date 2016年4月18日 上午10:19:04
 */
public class GsonHttpMessageConverter extends AbstractHttpMessageConverter {

    Logger logger = LoggerFactory.getLogger(GsonHttpMessageConverter.class);

    private final static String CONTENT_TYPE_KEY = "Content-Type";

    private String contentType = "application/json;charset=UTF-8";

    private String dateFormart = "";

    private Gson gson = new GsonBuilder().setDateFormat(dateFormart).create();

    @Override
    protected boolean supports(Class clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        //取出requestBody中内容
        String json = StreamUtils.copyToString(
                inputMessage.getBody(), inputMessage.getHeaders().getContentType().getCharSet());
        //打印入参
        logger.debug("request json : \n" + json);
        return gson.fromJson(json, clazz);
    }

    @Override
    protected void writeInternal(Object t, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        String json = gson.toJson(t);
        logger.debug("request json : \n" + json);
        outputMessage.getHeaders().add(CONTENT_TYPE_KEY, contentType);
        outputMessage.getBody().write(json.getBytes());

    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDateFormart() {
        return dateFormart;
    }

    public void setDateFormart(String dateFormart) {
        this.dateFormart = dateFormart;
    }
}
