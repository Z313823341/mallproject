package cn.e3mall.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	public SearchResult search(SolrQuery query) throws Exception{
		//根据查询条件查询
		//取查询结果 1查询结果总记录数 2.查询结果列表 高亮显示
		//返回结果
		    //创建quertResponse对象
			QueryResponse response = solrServer.query(query);
			// 2.查询结果列表
			SolrDocumentList documentList = response.getResults();
			//1查询结果总记录数
			long recordCount = documentList.getNumFound();
			
			SearchResult result = new SearchResult();
			result.setRecordCount(recordCount);
			//商品列表
			List<SearchItem> list = new ArrayList<>();
			//高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			
			for (SolrDocument solrDocument : documentList) {
				SearchItem item = new SearchItem();
				item.setId((String)solrDocument.get("id"));
				item.setImage((String) solrDocument.get("item_image"));
				
				item.setSell_point((String) solrDocument.get("item_sell_point"));
				item.setPrice((long) solrDocument.get("item_price"));
				item.setCategory_name((String) solrDocument.get("item_category_name"));
				//取高亮显示
				List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
				String title = "";
				if(list2!=null&&list2.size()>0){
					title = list2.get(0);
				}else{
					title = (String) solrDocument.get("item_title");
				}
				item.setTitle(title);
				list.add(item);
			}
			result.setList(list);
			
			
			return result;
			
		
		}
		
	}
	
	


