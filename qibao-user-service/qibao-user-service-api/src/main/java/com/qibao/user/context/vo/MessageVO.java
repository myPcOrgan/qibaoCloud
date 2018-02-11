package com.qibao.user.context.vo;


import java.util.Date;


public class MessageVO {

    private Long id;
    /**
     * 消息标题
     */
    private String name;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息对象
     */
    private Long userId;

    /**
     * 消息类型 1.站内消息 2 短信
     */
    private Integer messageType;

    /**
     * 是否阅读 0 未读 1 已读
     */
    private Boolean isView;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户金币数量的变动
     */
    private Double updateGolds;

    public Double getUpdateGolds() {
        return updateGolds;
    }

    public void setUpdateGolds(Double updateGolds) {
        this.updateGolds = updateGolds;
    }

    /**
     * 获取消息标题
     *
     * @return name - 消息标题
     */
    public String getName() {
        return name;
    }

    /**
     * 设置消息标题
     *
     * @param name 消息标题
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取消息内容
     *
     * @return content - 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息内容
     *
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取消息对象
     *
     * @return user_id - 消息对象
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置消息对象
     *
     * @param userId 消息对象
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取消息类型 1.站内消息 2 短信
     *
     * @return message_type - 消息类型 1.站内消息 2 短信
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * 设置消息类型 1.站内消息 2 短信
     *
     * @param messageType 消息类型 1.站内消息 2 短信
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * 获取是否阅读 0 未读 1 已读
     *
     * @return is_view - 是否阅读 0 未读 1 已读
     */
    public Boolean getIsView() {
        return isView;
    }

    /**
     * 设置是否阅读 0 未读 1 已读
     *
     * @param isView 是否阅读 0 未读 1 已读
     */
    public void setIsView(Boolean isView) {
        this.isView = isView;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}