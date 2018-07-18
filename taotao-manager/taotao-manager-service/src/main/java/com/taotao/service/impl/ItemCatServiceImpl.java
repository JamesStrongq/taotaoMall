package com.taotao.service.impl;

import com.taotao.dao.ItemCatDao;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatDao itemCatDao;

    @Override
    public List<TbItemCat> getItemCatById(long parentId) {
        List<TbItemCat> list = itemCatDao.getItemCatListById(parentId);
        return list;
    }
}
