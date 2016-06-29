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

    @NotNull (message = "valid.notNull")
    private Date time;

    @NotEmpty(message = "valid.notEmpty")
    private String userName;

    private Integer version;

    @Override
    public String toString() {
        return "DemoIO{" +
                "time=" + time +
                ", userName='" + userName + '\'' +
                ", version=" + version +
                '}';
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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