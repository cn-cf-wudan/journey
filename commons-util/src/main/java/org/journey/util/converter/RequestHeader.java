package org.journey.util.converter;

/**
 * @author wudan-mac
 * @ClassName: RequestHeader
 * @ClassNameExplain:
 * @Description:
 * @date 16/4/26 上午9:50
 */
public class RequestHeader {

    //app版本号
    private String appVersion;

    //终端系统
    private String systemVersion;

    //手机号码
    private String phone;

    //voip用户唯一标识
    private String userId;

    //终端网络类型
    private String network;

    //请求时间
    private Long requestTime;

    //设备标识
    private String deviceSign;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public String getDeviceSign() {
        return deviceSign;
    }

    public void setDeviceSign(String deviceSign) {
        this.deviceSign = deviceSign;
    }
}
