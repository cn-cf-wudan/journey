package org.journey.po.demo.share;

import org.journey.dao.redis.annotation.NotInRedis;
import org.journey.dao.redis.annotation.RedisDateFormat;
import org.journey.dao.redis.annotation.RedisKeySuffix;

import java.util.Date;

/**
 * @author wudan-mac
 * @ClassName: DEUser
 * @ClassNameExplain:
 * @Description: demo中的 user类型
 * @date 16/5/16 下午8:55
 */
public class DEUser {

    public final static String VERSION_FIELD_NAME = "version";

    public final static String CURRENT_ACCOUNT_FIELD_NAME = "currentAccount";

    //redis键前缀 主键
    @NotInRedis
    @RedisKeySuffix
    private Integer id;

    //用户名称
    private String userName;

    private Double currentAccount;

    private Long version;

    //创建日期
    @RedisDateFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Double getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Double currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
