package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {
    EUDataGridResult getContentList(int firstResult,int maxResults);
    TaotaoResult insertContent(TbContent tbContent);
    List<TbContent> getList();

}
