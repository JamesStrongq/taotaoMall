package com.taotao.service.impl;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.dao.ContentDao;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import net.sf.ehcache.transaction.xa.EhcacheXAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    @Override
    public EUDataGridResult getContentList(int firstResult, int maxResults) {
        List<TbContent> list = contentDao.getContentList(firstResult,maxResults);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        result.setTotal(contentDao.findContentCount());

        return result;
    }

    @Override
    public TaotaoResult insertContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        contentDao.insertContent(tbContent);

        //添加缓存同步逻辑
        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + tbContent.getCategoryId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getList() {

        return contentDao.getContentList();
    }
}
