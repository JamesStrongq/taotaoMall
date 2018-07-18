package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.ItemDao;
import com.taotao.dao.ItemDescDao;
import com.taotao.dao.ItemParamItemDao;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemDescDao itemDescDao;

    @Autowired
    private ItemParamItemDao itemParamItemDao;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Override
    public TaotaoResult getItemBaseInfo(long itemId) {
        try {
            //添加缓存逻辑
            //从缓存中取商品信息，商品id对应的信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            //判断是否有值
            if(!StringUtils.isBlank(json)){
                //把json转换成java对象
                TbItem item = JsonUtils.jsonToPojo(json,TbItem.class);
                return TaotaoResult.ok(item);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        TbItem item = itemDao.getItemById(itemId);
        try {
            //把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
            //设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok(item);
    }

    @Override
    public TaotaoResult getItemDescInfo(long itemId) {
        try{
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if(!StringUtils.isBlank(json)) {
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return TaotaoResult.ok(itemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbItemDesc itemDesc = itemDescDao.getItemDescById(itemId);

        try{
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc",JsonUtils.objectToJson(itemDesc));
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok(itemDesc);
    }

    @Override
    public TaotaoResult getItemParam(long itemId) {

        try{
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if(!StringUtils.isBlank(json)){
                TbItemParamItem tbItemParamItem = JsonUtils.jsonToPojo(json,TbItemParamItem.class);
                return TaotaoResult.ok(tbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        TbItemParamItem itemParamItem = itemParamItemDao.getItemParamItemById(itemId);

        try{
            jedisClient.set(REDIS_ITEM_KEY + ":" +itemId + ":param",JsonUtils.objectToJson(itemParamItem));
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param" ,REDIS_ITEM_EXPIRE);

        }catch (Exception e){
            e.printStackTrace();
        }


        return TaotaoResult.ok(itemParamItem);
    }
}
