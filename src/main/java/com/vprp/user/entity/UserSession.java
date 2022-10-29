package com.vprp.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users_session", schema = "nrlm_security")
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login_id")
    private String loginId;


    @Column(name = "channel_id")
    private Integer channelId;

    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "expire_time")
    private Date expireTime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_on")
    private Date createOn;

    @Column(name = "updated_on")
    private Date updatedOn;

    public UserSession() {
    }

    public UserSession(String loginId, Integer channelId, Date loginTime, Date expireTime, Integer status, Date createOn, Date updatedOn) {
        this.loginId = loginId;
        this.channelId = channelId;
        this.loginTime = loginTime;
        this.expireTime = expireTime;
        this.status = status;
        this.createOn = createOn;
        this.updatedOn = updatedOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}
