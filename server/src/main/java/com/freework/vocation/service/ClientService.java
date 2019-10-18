package com.freework.vocation.service;

import com.freework.vocation.client.vo.VocationCategoryVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daihongru
 */
@Service
public interface ClientService {
    String VOCATION_CATEGORY_TOP_KEY = "vocationCategoryTop";
    String VOCATION_CATEGORY_SECOND_KEY = "vocationCategorySecond";

    List<VocationCategoryVo> queryVocationCategoryTop();
}
