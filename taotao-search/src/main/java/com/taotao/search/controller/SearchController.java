package com.taotao.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value="/query", method=RequestMethod.GET)
    @ResponseBody
    public TaotaoResult searchItemList(@RequestParam("q")String queryString,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "60") Integer rows){
        if(StringUtils.isBlank(queryString)){
            return TaotaoResult.build(400,"查询条件不能为空");
        }

        try {
            //处理GET请求中的乱码问题
            queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        SearchResult result = searchService.searchItem(queryString,page,rows);
        return TaotaoResult.ok(result);
    }
}
