package com.freework.vocation.client.feign;

import com.freework.vocation.client.vo.VocationVo;
import org.springframework.stereotype.Component;

/**
 * @author daihongru
 */
@Component
public class VocationClientFallback implements VocationClient {

    @Override
    public VocationVo getVocationInfo(Integer vocationId) {
        VocationVo vocationVo = new VocationVo();
        vocationVo.setVocationName("(系统繁忙)");
        return vocationVo;
    }
}
