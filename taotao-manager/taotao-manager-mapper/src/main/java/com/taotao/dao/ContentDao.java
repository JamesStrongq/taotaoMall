package com.taotao.dao;

import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentDao {
    List<TbContent> getContentList(int firstResult,int maxResults);
    Long findContentCount();
    List<TbContent> getContentById(long contentCid);
    void insertContent(TbContent tbContent);
    List<TbContent> getContentList();
}
