package com.freework.vocation.service.impl;

import com.freework.common.loadon.cache.JedisUtil;
import com.freework.common.loadon.result.entity.ResultVo;
import com.freework.common.loadon.result.enums.ResultStatusEnum;
import com.freework.common.loadon.result.util.ResultUtil;
import com.freework.common.loadon.util.JsonUtil;
import com.freework.vocation.dao.VocationCategoryDao;
import com.freework.vocation.entity.VocationCategory;
import com.freework.vocation.service.VocationCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daihongru
 */
@Service
public class VocationCategoryServiceImpl implements VocationCategoryService {
    private static Logger logger = LoggerFactory.getLogger(VocationCategoryServiceImpl.class);
    @Autowired(required = false)
    private VocationCategoryDao vocationCategoryDao;
    @Autowired(required = false)
    private JedisUtil.Keys jedisKeys;
    @Autowired(required = false)
    private JedisUtil.Strings jedisStrings;

    @Override
    public ResultVo<VocationCategory> queryVocationCategoryTop() {
        String key = VOCATION_CATEGORY_TOP_KEY;
        List<VocationCategory> vocationCategoryList;
        if (!jedisKeys.exists(key)) {
            vocationCategoryList = vocationCategoryDao.queryVocationCategoryTop();
            String jsonString = JsonUtil.objectToJson(vocationCategoryList);
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            vocationCategoryList = JsonUtil.jsonToList(jsonString, VocationCategory.class);
        }
        if (vocationCategoryList.size() == 0 || vocationCategoryList == null) {
            return ResultUtil.error(ResultStatusEnum.NOT_FOUND);
        } else {
            return ResultUtil.success(vocationCategoryList);
        }
    }

    @Override
    public ResultVo<VocationCategory> queryVocationCategorySecond(Integer parentId) {
        if (parentId == null || parentId <= 0) {
            return ResultUtil.error(ResultStatusEnum.BAD_REQUEST);
        }
        List<VocationCategory> vocationCategoryList;
        String key = VOCATION_CATEGORY_SECOND_KEY + "_ParentId" + parentId;
        if (!jedisKeys.exists(key)) {
            vocationCategoryList = vocationCategoryDao.queryVocationCategorySecond(parentId);
            String jsonString = JsonUtil.objectToJson(vocationCategoryList);
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            vocationCategoryList = JsonUtil.jsonToList(jsonString, VocationCategory.class);
        }
        if (vocationCategoryList.size() == 0 || vocationCategoryList == null) {
            return ResultUtil.error(ResultStatusEnum.NOT_FOUND);
        } else {
            return ResultUtil.success(vocationCategoryList);
        }
    }
}
