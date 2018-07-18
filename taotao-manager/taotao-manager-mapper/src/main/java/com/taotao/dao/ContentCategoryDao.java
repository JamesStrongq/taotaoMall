package com.taotao.dao;

import com.taotao.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryDao {
    List<TbContentCategory> getContentCategoryList(long parentId);
    void insertContentCategoyr(TbContentCategory contentCategory);
    TbContentCategory getContentCategory(long id);
    void updateContentCategory(TbContentCategory tbContentCategory);
    void deleteContentCategory(long id);
}
