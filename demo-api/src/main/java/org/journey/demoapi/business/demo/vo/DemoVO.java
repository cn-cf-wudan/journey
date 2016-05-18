package org.journey.demoapi.business.demo.vo;

import org.journey.util.converter.ResponseHeader;

import java.util.Date;

/**
 * @ClassName: DemoVO
 * @ClassNameExplain:
 * @Description:
 * @author wudan-mac
 * @date 2016-05-17 17:25:48
 */
public class DemoVO extends ResponseHeader{

    private String photoCode;

    private String userName;

    private Date createDate = new Date();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPhotoCode() {
        return photoCode;
    }

    public void setPhotoCode(String photoCode) {
        this.photoCode = photoCode;
    }
}