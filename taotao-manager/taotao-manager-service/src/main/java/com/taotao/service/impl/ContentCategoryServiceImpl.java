package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.dao.ContentCategoryDao;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryDao contentCategoryDao;
    @Override
    public List<EUTreeNode> getContentCategoryList(long parentId) {
        List<TbContentCategory> contentCategory = contentCategoryDao.getContentCategoryList(parentId);
        List<EUTreeNode> result = new ArrayList<>();
        for(TbContentCategory tbContentCategory : contentCategory){
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            node.setText(tbContentCategory.getName());
            result.add(node);
        }
        return result;
    }

    @Override
    public TaotaoResult insertContentCategory(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategoryDao.insertContentCategoyr(contentCategory);
        TbContentCategory tbContentCategory = contentCategoryDao.getContentCategory(parentId);
        if(!tbContentCategory.getIsParent()){
            tbContentCategory.setIsParent(true);
            contentCategoryDao.updateContentCategory(tbContentCategory);
        }

        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public TaotaoResult deleteContentCategory(Long parentId, Long id) {
        contentCategoryDao.deleteContentCategory(id);
        List<TbContentCategory> list = contentCategoryDao.getContentCategoryList(parentId);
        if(list == null){
            TbContentCategory tbContentCategory = contentCategoryDao.getContentCategory(parentId);
            tbContentCategory.setIsParent(false);
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContentCategory(Long id, String name) {
        TbContentCategory contentCategory = contentCategoryDao.getContentCategory(id);
        contentCategory.setName(name);
        contentCategoryDao.updateContentCategory(contentCategory);
        return TaotaoResult.ok();
    }
}
