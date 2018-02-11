package com.qibao.goods.controller.impl;

import com.github.pagehelper.Page;
import com.qibao.goods.controller.ISysMessageControl;
import com.qibao.goods.service.ISysMessageService;
import com.qibao.goods.entity.SysMessageEO;
import com.qibao.common.utils.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@RestController
@RequestMapping("/message")
public class SysMessageControllerImpl implements ISysMessageControl {


    @Autowired
    private ISysMessageService sysMessageService;

    @RequestMapping("list")
    public List<SysMessageEO> list(){

        List<SysMessageEO> list=this.sysMessageService.selectAll();
        return list;
    }

}
