package com.freework.vocation.dao;


import com.freework.vocation.entity.Vocation;

import java.util.List;

/**
 * @author daihongru
 */
public interface VocationDao {

    /**
     * 添加一条新的招聘信息
     *
     * @param vocation
     * @return
     */
    int addVocation(Vocation vocation);

    /**
     * 根据传入的条件查询招聘信息，返回一个列表
     *
     * @param vocation
     * @return
     */
    List<Vocation> queryVocationByRequirement(Vocation vocation);

    /**
     * 修改招聘信息状态，冻结或正常
     *
     * @param vocation
     * @return
     */
    int updateVocationStatus(Vocation vocation);

    /**
     * 删除招聘信息
     *
     * @param vocation
     * @return
     */
    int deleteVocation(Vocation vocation);

    /**
     * 修改招聘信息
     *
     * @param vocation
     * @return
     */
    int updateVocation(Vocation vocation);

    /**
     * 查询所有招聘信息
     *
     * @return
     */
    List<Vocation> queryVocationAll();
}
