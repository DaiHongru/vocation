package com.freework.vocation.controller;

import com.freework.vocation.client.vo.VocationCategoryVo;
import com.freework.vocation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author daihongru
 */
@RestController
@RequestMapping(value = "client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("getvocationcategorytop")
    public List<VocationCategoryVo> getVocationCategoryTop() {
        List<VocationCategoryVo> vocationCategoryVoList = clientService.queryVocationCategoryTop();
        return vocationCategoryVoList;
    }
}
