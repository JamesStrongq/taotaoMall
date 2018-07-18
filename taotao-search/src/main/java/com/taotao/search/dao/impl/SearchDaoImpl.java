package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* 商品查询Dao
* **/
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult searchItem(SolrQuery query) {
        SearchResult result = new SearchResult();
        try {
            //根据查询条件查询索引
            QueryResponse response = solrServer.query(query);
            //取查询结果
            SolrDocumentList list = response.getResults();
            //取查询结果总数量
            result.setRecordCount(list.getNumFound());
            //商品列表
            List<Item> itemList = new ArrayList<>();
            //取高亮显示
            Map<String,Map<String,List<String>>> highlighting = response.getHighlighting();
            //取商品列表
            for(SolrDocument document : list){
                Item item = new Item();
                item.setId((String) document.get("id"));
                //取高亮显示的结果
                List<String> highList = highlighting.get(document.get("id")).get("item_title");
                String title = "";
                if(highList != null && highList.size() > 0){
                    title = highList.get(0);
                }else{
                    title = (String) document.get("item_title");
                }
                item.setTitle(title);
                item.setCategory_name((String)document.get("item_category_name"));
                item.setImage((String) document.get("item_image"));
                item.setPrice((Long) document.get("item_price"));
                item.setSell_point((String) document.get("item_sell_point"));
                itemList.add(item);
            }
            result.setItemList(itemList);
            return result;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
