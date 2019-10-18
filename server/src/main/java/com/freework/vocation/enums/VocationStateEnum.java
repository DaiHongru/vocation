package com.freework.vocation.enums;

/**
 * @author daihongru
 */

public enum VocationStateEnum {
    /**
     * 枚举字段
     */
    STOP(0, "停止招聘"),
    ONGOING(1, "招聘中"),
    SUCCESS(1001, "操作成功"),
    ERROR(2001, "操作失败"),
    INNER_ERROR(2002, "内部系统错误"),
    NULL_PARAM(2003, "后台获取参数为空"),
    NULL_RECORD(2004, "无记录");

    /**
     * 状态表示
     */
    private int state;

    /**
     * 状态说明
     */
    private String stateInfo;

    VocationStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     */
    public static VocationStateEnum stateOf(int state) {
        for (VocationStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
