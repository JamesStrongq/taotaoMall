package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/showItem/{itemId}")
    public String getItemParamItemById(@PathVariable Long itemId,Model model){
        String str = itemParamItemService.itemParamItemById(itemId);
        model.addAttribute("itemParam",str);
        return "item";
    }
}
