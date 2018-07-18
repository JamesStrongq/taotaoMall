package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EUTreeNode> getContentCategoryList(long parentId);
    TaotaoResult insertContentCategory(Long parentId,String name);
    TaotaoResult deleteContentCategory(Long parentId,Long id);
    TaotaoResult updateContentCategory(Long id,String name);
}
