package com.qibao.goods.controller;

import com.qibao.goods.model.SysMessage;
import com.qibao.goods.service.SysMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final String COMMON_SERVICE_NAME = "common-service-goods";

    @Autowired
    private SysMessageService sysMessageService;

    @RequestMapping("test")
    public List<SysMessage> test(){
        List<SysMessage> list=sysMessageService.list();

        return list;
    }
}
