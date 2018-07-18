package com.taotao.rest.solerj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrJTest {


    public void addDocument() throws Exception{
        //创建一个连接
        //连单机版使用HttpSolrServer
        //连集群版使用CloudSolrServer
        SolrServer solrServer = new HttpSolrServer("http://192.168.61.130:8088/solr");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","test001");
        document.addField("item_title","测试商品1");
        document.addField("item_price",12345);
        //把文档对象写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();

    }

    public void deleteDocument() throws Exception{
        //创建一个连接
        SolrServer solrServer = new HttpSolrServer("http://192.168.61.130:8088/solr");
        //根据id删除
        //solrServer.deleteById("test001");
        //根据查询内容全部删除
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }

    public void queryDocument() throws Exception{
        SolrServer solrServer = new HttpSolrServer("http://192.168.61.130:8088/solr");
        //创建一个查询对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery("*:*");
        //设置分页参数
        query.setStart(20);
        query.setRows(50);
        //执行查询
        QueryResponse response = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("共查询到的记录数:" + solrDocumentList.getNumFound());
        for(SolrDocument solrDocument : solrDocumentList){
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_category_name"));
            System.out.println(solrDocument.get("item_image"));
        }
    }
}
