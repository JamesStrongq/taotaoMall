package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

import java.util.List;

public interface ItemService {
    TbItem getItemById(long id);
    EUDataGridResult getItemList(int firstResult, int maxResults);
    TaotaoResult insertItem(TbItem tbItem,String desc,String itemParam);
}
