package com.taotao.dao;

import com.taotao.pojo.TbItemCat;

import java.util.List;

public interface ItemCatDao {
    List<TbItemCat> getItemCatListById(long parentId);
}
