package com.taotao.dao;

import com.taotao.pojo.TbItemParam;

import java.util.List;

public interface ItemParamDao {
    List<TbItemParam> getItemParamById(long id);
    void insertItemParam(TbItemParam tbItemParam);
}
