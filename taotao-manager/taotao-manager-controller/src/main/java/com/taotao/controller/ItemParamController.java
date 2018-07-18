package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/itemId/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParamById(@PathVariable Long itemId){
        TaotaoResult result = itemParamService.getItemParamById(itemId);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid,String paramDate){
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setId(cid);
        tbItemParam.setParamData(paramDate);
        TaotaoResult result = itemParamService.insertItemParam(tbItemParam);
        return result;
    }
}
