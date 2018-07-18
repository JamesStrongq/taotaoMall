package com.taotao.service.impl;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.dao.ItemDao;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public TbItem getItemById(long id) {
        return itemDao.getItemById(id);
    }

    @Override
    public EUDataGridResult getItemList(int firstResult, int maxResults) {
        Long total = itemDao.findCount();
        List<TbItem> list = itemDao.getItemList(firstResult,maxResults);
        EUDataGridResult result = new EUDataGridResult();
        result.setTotal(total);
        result.setRows(list);
        return result;
    }

    @Override
    public TaotaoResult insertItem(TbItem tbItem, String desc,String itemParam) {
        Long itemId = IDUtils.getItemId();
        tbItem.setId(itemId);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        tbItem.setStatus((byte)1);
        itemDao.insertItem(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        itemDao.insertItemDesc(tbItemDesc);

        insertItemParamItem(itemId,itemParam);
        return TaotaoResult.ok();
    }

    public void insertItemParamItem(Long itemId,String itemParam){
        TbItemParamItem tbItemParam = new TbItemParamItem();
        tbItemParam.setId(itemId);
        tbItemParam.setParamData(itemParam);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        itemDao.insertItemParamItem(tbItemParam);
    }
}
