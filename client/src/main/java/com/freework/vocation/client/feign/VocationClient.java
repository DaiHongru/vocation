package com.freework.vocation.client.feign;

import com.freework.vocation.client.vo.VocationCategoryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author daihongru
 */
@FeignClient(name = "VOCATION")
public interface VocationClient {

    /**
     * 查询一级岗位分类
     *
     * @return
     */
    @GetMapping("/client/getvocationcategorytop")
    List<VocationCategoryVo> getVocationCategoryTop();
}
