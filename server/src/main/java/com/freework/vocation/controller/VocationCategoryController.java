package com.freework.vocation.controller;

import com.freework.common.loadon.result.entity.ResultVo;
import com.freework.vocation.entity.VocationCategory;
import com.freework.vocation.service.VocationCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daihongru
 */
@RestController
@RequestMapping(value = "category")
public class VocationCategoryController {
    @Autowired
    private VocationCategoryService vocationCategoryService;

    /**
     * 查询一级岗位分类
     *
     * @return
     */
    @GetMapping("tops")
    public ResultVo<VocationCategory> getVocationCategoryTop() {
        return vocationCategoryService.queryVocationCategoryTop();
    }

    /**
     * 分类查询二级岗位分类
     *
     * @param parentId
     * @return
     */
    @GetMapping("seconds/{parentId}")
    public ResultVo<VocationCategory> getVocationCategorySecondByTop(@PathVariable Integer parentId) {
        return vocationCategoryService.queryVocationCategorySecond(parentId);
    }
}
