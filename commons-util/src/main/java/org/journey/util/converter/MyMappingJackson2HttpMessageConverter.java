package org.journey.util.converter;

import com.google.gson.Gson;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author 张光银
 * @ClassName: MyMappingJackson2HttpMessageConverter
 * @ClassNameExplain: json转换器
 * @Description: 替换spring默认的jackson
 * @date 2016年4月18日 上午10:19:04
 */
public class MyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
   /*
    * 使用方法：在springmvc配置文件中的<mvc:annotation-driven>替换为:
	* <mvc:annotation-driven>
	*	<mvc:message-converters>
	*		<bean class="org.journey.util.converter.GsonHttpMessageConverter"/>
	*	</mvc:message-converters>
	*	</mvc:annotation-driven>
	*/

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        //取出requestBody中内容
        String json = StreamUtils.copyToString(
                inputMessage.getBody(), inputMessage.getHeaders().getContentType().getCharSet());
        //打印入参
        logger.info("request json : \n" + json);
        return super.readInternal(clazz, inputMessage);
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        logger.debug("response json : \n" + new Gson().toJson(object));
        super.writeInternal(object, type, outputMessage);
    }
}
