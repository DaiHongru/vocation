package com.freework.vocation.client.feign;

import com.freework.vocation.client.vo.VocationVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daihongru
 */
@FeignClient(name = "VOCATION", fallback = VocationClientFallback.class)
public interface VocationClient {

    /**
     * 查询招聘信息
     *
     * @param vocationId
     * @return
     */
    @PostMapping("/client/getVocationInfo")
    VocationVo getVocationInfo(@RequestBody Integer vocationId);
}
