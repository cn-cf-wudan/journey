package org.journey.util.converter;

/**
 * @author wudan-mac
 * @ClassName: ResponseHeader
 * @ClassNameExplain:
 * @Description:
 * @date 16/4/26 上午9:54
 */
public class ResponseHeader {

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
