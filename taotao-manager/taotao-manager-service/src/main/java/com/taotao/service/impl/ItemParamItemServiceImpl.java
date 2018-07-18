package com.taotao.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.ItemParamItemDao;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private ItemParamItemDao itemParamItemDao;

    @Override
    public String itemParamItemById(Long itemId) {
        TbItemParamItem tbItemParamItem = itemParamItemDao.getItemParamItemById(itemId);
        //取规格参数
        String paramData = tbItemParamItem.getParamData();

        //生成html
        //把规格参数接送转换成java对象
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
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
}
