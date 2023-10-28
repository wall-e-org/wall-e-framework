package tech.huning.wall.e.specs.model.session;

import tech.huning.wall.e.specs.model.pojo.Tenant;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户Session数据模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class UserSession extends CommonSession implements Serializable {

    private static final long serialVersionUID = 2613372726394084814L;

    // 编码
    private Long id;
    // 账号
    private String account;
    // 名称
    private String username;
    // 昵称
    private String nickname;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 地址
    private String address;
    // 签名
    private String signature;
    // 头像
    private String avatar;
    // 租户
    private Tenant tenant;
    // 登录时间
    private Date loginAt;
    // 拓展数据
    private Object extra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

}
