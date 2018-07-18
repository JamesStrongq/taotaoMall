package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult queryContentList(Integer page,Integer rows){
        int pageSize = rows;//每页显示几条
        int firstResult = pageSize * (page - 1);
        EUDataGridResult result = contentService.getContentList(firstResult,pageSize);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent tbContent){
        TaotaoResult result = contentService.insertContent(tbContent);
        return result;
    }

    @RequestMapping("/getList")
    @ResponseBody
    public TaotaoResult getList(){
        List<TbContent> list = contentService.getList();
        return TaotaoResult.ok(list);
    }
}
