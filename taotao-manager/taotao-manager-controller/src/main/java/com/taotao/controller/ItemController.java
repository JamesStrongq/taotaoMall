package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        return itemService.getItemById(itemId);
    }



    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page,Integer rows){
            //每页显示的个数
            int pageSize = rows;
            int firstResult = (page - 1) * pageSize;
            EUDataGridResult result = itemService.getItemList(firstResult,pageSize);
            return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertItem(TbItem tbItem,String desc,String itemParam){
            TaotaoResult result = itemService.insertItem(tbItem,desc,itemParam);
            return result;
    }
}
