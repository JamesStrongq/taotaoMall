package com.taotao.controller;

import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List itemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
        List catList = new ArrayList();
        List<TbItemCat> list = itemCatService.getItemCatById(parentId);
        for(TbItemCat tbItemCat : list){
            Map node = new HashMap<>();
            node.put("id",tbItemCat.getId());
            node.put("text",tbItemCat.getName());

            node.put("state",tbItemCat.getIsParent()?"closed":"open");
            catList.add(node);
        }
        return catList;


    }
}
