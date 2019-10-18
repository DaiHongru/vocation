package com.freework.vocation.dao;


import com.freework.vocation.entity.VocationCategory;

import java.util.List;

/**
 * @author daihongru
 */
public interface VocationCategoryDao {
    /**
     * 查询职业一级分类，返回一个列表
     *
     * @return
     */
    List<VocationCategory> queryVocationCategoryTop();

    /**
     * 查询职业二级分类，返回一个列表
     *
     * @param parentId
     * @return
     */
    List<VocationCategory> queryVocationCategorySecond(Integer parentId);

    /**
     * 根据职业分类ID查询
     * @param vocationCategoryId
     * @return
     */
    VocationCategory queryVocationCategoryById(Integer vocationCategoryId);
}
