package com.qibao.goods.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qibao.goods.model.SysMessage;

import java.util.List;

@Component
@FeignClient(value = "common-service-goods")
public interface SysMessageService  {

    @GetMapping("/message/list")
    public List<SysMessage> list();
}
