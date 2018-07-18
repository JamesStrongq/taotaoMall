package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    @Override
    public ItemInfo getItemById(long itemId) {

        try {
            //调用rest的服务查询商品的基本信息
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            if(!StringUtils.isBlank(json)){
                TaotaoResult result = TaotaoResult.formatToPojo(json,ItemInfo.class);
                if(result.getStatus() == 200){
                    ItemInfo item = (ItemInfo)result.getData();
                    return item;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getItemDescById(long itemId) {

        try {
            //查询商品描述
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            //转换成java对象
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
            if(result.getStatus() == 200){
                TbItemParamItem itemParamItem = (TbItemParamItem) result.getData();
                String str =itemParamItem.getParamData();

                //生成html
                //把规格参数接送转换成java对象
                List<Map> jsonList = JsonUtils.jsonToList(str, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("   <tbody>\n");
                for(Map m1 : jsonList){
                    sb.append("  <tr>\n");
                    sb.append("  <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group")+ "</th>\n");
                    sb.append("</tr>\n");
                    List<Map> list2 = (List<Map>)m1.get("param");
                    for(Map m2 : list2){
                        sb.append("<tr>\n");
                        sb.append("<td class=\"tdTitle\">" + m2.get("k")+"</td>\n");
                        sb.append("<td>" + m2.get("v") +"</td>\n");
                        sb.append("</tr>\n");
                    }
                }
                sb.append("</tbody>/n");
                sb.append("</table>");
                return sb.toString();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParamById(long itemId) {

        try{
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            TaotaoResult result = TaotaoResult.formatToPojo(json,TbItemDesc.class);
            if(result.getStatus() == 200){
                TbItemDesc desc = (TbItemDesc)result.getData();
                String str = desc.getItemDesc();
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
