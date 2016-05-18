package org.journey.demoapi.business.demo.io;

import org.hibernate.validator.constraints.NotEmpty;
import org.journey.util.converter.RequestHeader;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: DemoIO
 * @ClassNameExplain:
 * @Description:
 * @author wudan-mac
 * @date 2016-05-17 17:25:48
 */

public class DemoIO extends RequestHeader{

    @NotNull (message = "不能为空")
    private Date time;

    @NotEmpty(message = "不能为空或空字符")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}