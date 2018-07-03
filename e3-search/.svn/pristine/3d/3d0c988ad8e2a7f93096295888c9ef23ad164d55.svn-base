package cn.e3mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception{
		//根据query查询索引库
		SolrQuery query = new SolrQuery();
		//设置查询条件按
		query.setQuery(keyword);
		if(page<=0){
			page = 1;
		}
		query.setStart((page-1)*rows);
		query.setRows(rows);
		query.set("df","item_title");
		query.setHighlight(true);		
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color=red\">");
		query.setHighlightSimplePost("</em>");
		//调用dao执行查询
		SearchResult search = searchDao.search(query);
		long recordCount = search.getRecordCount();
		int totalPages = (int)(recordCount/rows);
		if(recordCount%rows!=0){
			totalPages++;
		}
		search.setTotalPages(totalPages);
		return search;
	}
	

}
