package com.qibao.user.context.dto;



public class MessageDTO {

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
     * 消息类型 1.站内消息 0 短信
     */
    private Integer messageType;

    /**
     * 是否阅读 0 未读 1 已读
     */
    private Boolean isView;

    /**
     * 创建时间
     */
    private String createStartTime;

    private String createEndTime;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer size;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 是否升序排列  true 为升序
     */
    private Boolean isAsc;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getAsc() {
        return isAsc;
    }

    public void setAsc(Boolean asc) {
        isAsc = asc;
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

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}