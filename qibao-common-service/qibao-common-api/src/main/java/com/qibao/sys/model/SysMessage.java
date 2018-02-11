package com.qibao.goods.model;

import java.io.Serializable;
import java.util.Date;

public class SysMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String name;

    private String content;

    private Long userId;

    private Boolean messageType;

    private Boolean isView;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getMessageType() {
        return messageType;
    }

    public void setMessageType(Boolean messageType) {
        this.messageType = messageType;
    }

    public Boolean getIsView() {
        return isView;
    }

    public void setIsView(Boolean isView) {
        this.isView = isView;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}