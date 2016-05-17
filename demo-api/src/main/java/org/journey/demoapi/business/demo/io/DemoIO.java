package org.journey.demoapi.business.demo.io;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}