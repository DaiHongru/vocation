package com.freework.vocation.dto;


import com.freework.vocation.entity.VocationCategory;
import com.freework.vocation.enums.VocationCategoryStateEnum;

import java.util.List;

/**
 * @author daihongru
 */
public class VocationCategoryExecution {
    /**
     * 结果状态
     */
    private int state;

    /**
     * 状态标识
     */
    private String stateInfo;

    /**
     * 数量
     */
    private int count;

    /**
     * 操作的vocation
     */
    private VocationCategory vocationCategory;

    /**
     * vocation列表
     */
    private List<VocationCategory> vocationCategoryList;

    public VocationCategoryExecution() {
    }

    /**
     * 操作失败的时候使用的构造器，存储错误信息
     */
    public VocationCategoryExecution(VocationCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 操作成功的时候使用的构造器，存储一个对象
     */
    public VocationCategoryExecution(VocationCategoryStateEnum stateEnum, VocationCategory vocationCategory) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.vocationCategory = vocationCategory;
    }

    /**
     * 操作成功的时候使用的构造器，存储一个对象列表
     */
    public VocationCategoryExecution(VocationCategoryStateEnum stateEnum, List<VocationCategory> vocationCategoryList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.vocationCategoryList = vocationCategoryList;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public VocationCategory getVocationCategory() {
        return vocationCategory;
    }

    public void setVocationCategory(VocationCategory vocationCategory) {
        this.vocationCategory = vocationCategory;
    }

    public List<VocationCategory> getVocationCategoryList() {
        return vocationCategoryList;
    }

    public void setVocationCategoryList(List<VocationCategory> vocationCategoryList) {
        this.vocationCategoryList = vocationCategoryList;
    }
}
