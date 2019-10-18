package com.freework.vocation.service;

import com.freework.common.loadon.result.entity.ResultVo;
import com.freework.vocation.entity.VocationCategory;
import org.springframework.stereotype.Service;

/**
 * @author daihongru
 */
@Service
public interface VocationCategoryService {
    String VOCATION_CATEGORY_TOP_KEY = "vocationCategoryTop";
    String VOCATION_CATEGORY_SECOND_KEY = "vocationCategorySecond";

    /**
     * 查询一级分类
     *
     * @return
     */
    ResultVo<VocationCategory> queryVocationCategoryTop();

    /**
     * 根据parentId查询二级分类
     *
     * @param parentId
     * @return
     */
    ResultVo<VocationCategory> queryVocationCategorySecond(Integer parentId);
}
