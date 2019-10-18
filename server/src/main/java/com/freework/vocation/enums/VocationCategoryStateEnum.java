package com.freework.vocation.enums;

/**
 * @author daihongru
 */

public enum VocationCategoryStateEnum {
    /**
     * 枚举字段
     */
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

    VocationCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     */
    public static VocationCategoryStateEnum stateOf(int state) {
        for (VocationCategoryStateEnum stateEnum : values()) {
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
