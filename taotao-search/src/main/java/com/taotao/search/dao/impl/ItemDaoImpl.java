package com.taotao.search.dao.impl;

import com.taotao.search.dao.ItemDao;
import com.taotao.search.pojo.Item;
import javafx.beans.binding.ObjectExpression;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Item> getItemList() {
        Session session = sessionFactory.openSession();
        //String sql = "select a.id,a.title,a.sell_point,a.price,a.image,b.`name` category_name,c.item_desc from tb_item a left join tb_item_cat b on a.cid = b.id left join tb_item_desc c on a.id = c.item_id";
        String hql = "select new com.taotao.search.pojo.Item(a.id,a.title,a.sellPoint,a.price,a.image,b.name,c.itemDesc) from TbItem a left join TbItemCat b on a.cid = b.id left join TbItemDesc c on a.id = c.itemId";
//        SQLQuery query = session.createSQLQuery(sql);
        Query query = session.createQuery(hql);
        List<Item> list = query.getResultList();
        System.out.println(list);
       /*
        List<Item> result = new ArrayList<>();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            Object[] object = (Object[])iterator.next();
            Item item = new Item();
            item.setId((long) object[0]);
            item.setTitle((String)object[1]);
            item.setSell_point((String)object[2]);
            item.setPrice((long)object[3]);
            item.setImage((String)object[4]);
            item.setCategory_name((String)object[5]);
            item.setItem_des((String)object[6]);
            result.add(item);
        }
        System.out.println("result:" + result);
       */
        return list;
    }
}
