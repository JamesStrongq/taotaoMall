package com.taotao.dao;


import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;

import java.util.List;

public interface ItemDao {
    TbItem getItemById(long id);
    List<TbItem> getItemList(int firstResult,int maxResults);
    Long findCount();
    void insertItem(TbItem tbItem);
    void insertItemDesc(TbItemDesc tbItemDesc);
    void insertItemParamItem(TbItemParamItem tbItemParam);
}
