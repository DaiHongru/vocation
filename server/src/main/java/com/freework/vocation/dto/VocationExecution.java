package com.freework.vocation.dto;


import com.freework.vocation.entity.Vocation;
import com.freework.vocation.enums.VocationStateEnum;

import java.util.List;

/**
 * @author daihongru
 */
public class VocationExecution {
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
    private Vocation vocation;

    /**
     * vocation列表
     */
    private List<Vocation> vocationList;

    public VocationExecution() {
    }

    /**
     * 操作失败的时候使用的构造器，存储错误信息
     */
    public VocationExecution(VocationStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 操作成功的时候使用的构造器，存储一个对象
     */
    public VocationExecution(VocationStateEnum stateEnum, Vocation vocation) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.vocation = vocation;
    }

    /**
     * 操作成功的时候使用的构造器，存储对象列表
     */
    public VocationExecution(VocationStateEnum stateEnum, List<Vocation> vocationList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.vocationList = vocationList;
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

    public Vocation getVocation() {
        return vocation;
    }

    public void setVocation(Vocation vocation) {
        this.vocation = vocation;
    }

    public List<Vocation> getVocationList() {
        return vocationList;
    }

    public void setVocationList(List<Vocation> vocationList) {
        this.vocationList = vocationList;
    }
}
