package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.dao.ItemParamDao;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private ItemParamDao itemParamDao;

    @Override
    public TaotaoResult getItemParamById(long id) {
        List<TbItemParam> list = itemParamDao.getItemParamById(id);
        if(list != null && list.size() > 0){
            return TaotaoResult.ok(list.get(0));
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        itemParamDao.insertItemParam(tbItemParam);
        return TaotaoResult.ok();
    }
}
