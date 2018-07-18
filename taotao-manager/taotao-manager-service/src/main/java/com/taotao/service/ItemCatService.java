package com.taotao.service;

import com.taotao.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    List<TbItemCat> getItemCatById(long parentId);
}
