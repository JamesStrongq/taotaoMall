package com.taotao.rest.service.impl;

import com.taotao.dao.ItemCatDao;
import com.taotao.pojo.TbItemCat;
import com.taotao.rest.pojo.ItemCatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatDao itemCatDao;

    @Override
    public ItemCatResult getItemList() {
        ItemCatResult itemCatResult = new ItemCatResult();
        itemCatResult.setData(getCatList(0));
        return null;
    }

    public List<?> getCatList(long parentId){
        List result = new ArrayList();
        List<TbItemCat> list = itemCatDao.getItemCatListById(parentId);
        for(TbItemCat tbItemCat : list){
            //判断是否为父节点
            if(tbItemCat.getIsParent()){
                ItemCatNode node = new ItemCatNode();
                if(parentId == 0){
                    node.setName("<a href='/products/" + tbItemCat.getId()+ ".html'>" + tbItemCat.getName() +"</a>");
                }else{
                    node.setName(tbItemCat.getName());
                }
                node.setUrl("/products/" + tbItemCat.getId() + ".html");
                //利用递归创建子节点
                node.setItem(getCatList(tbItemCat.getId()));
                result.add(node);
            }else{
                result.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
            }
        }
        return result;
    }
}
