package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

public interface SearchService {
    SearchResult searchItem(String queryString,int page,int rows);
}
