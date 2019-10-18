package com.freework.vocation.client.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author daihongru
 */
public class VocationCategoryVo implements Serializable {
    /**
     * 岗位分类编号，自增主键，唯一标识符，不可为空，新增时不传入
     */
    private Integer vocationCategoryId;

    /**
     * 分类名称，不可为空
     */
    private String vocationCategoryName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近修改时间
     */
    private Date lastEditTime;

    /**
     * 上级分类ID
     */
    private Integer parentId;

    public Integer getVocationCategoryId() {
        return vocationCategoryId;
    }

    public void setVocationCategoryId(Integer vocationCategoryId) {
        this.vocationCategoryId = vocationCategoryId;
    }

    public String getVocationCategoryName() {
        return vocationCategoryName;
    }

    public void setVocationCategoryName(String vocationCategoryName) {
        this.vocationCategoryName = vocationCategoryName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
