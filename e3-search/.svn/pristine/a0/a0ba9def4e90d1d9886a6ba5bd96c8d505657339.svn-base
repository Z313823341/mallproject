package cn.e3mall.solorj;

import java.util.Collection;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolorj {
	
	@Test
	public void addDocument() throws Exception{	
		//创建一个SolrService对象，创建一个连接，
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
		//创建一个文档对象SolrInputDocument
	    SolrInputDocument document = new SolrInputDocument();
		//向文档中添加域，文档中必须包含一个id域，所有的域的名称必须在schema.xml文档中定义
		document.addField("id", "test001");
		document.addField("item_title", "测试商品");
		document.addField("item_price", "199");
		//把文档写入索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
		
	}
	@Test
	public void testDelSolrDocument() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        solrServer.deleteById("test001");
        solrServer.commit();
		
	}
	//查询
	@Test
	public void testQuery() throws Exception{
		//创建solrServer对象
	SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
        //创建solrQuery对象
	SolrQuery query = new SolrQuery();
	    //设置查询条件
	query.set("q", "*:*");
	    //执行查询 queryresponse对象
		QueryResponse response = solrServer.query(query);
		//取文档列表
		SolrDocumentList solrlist = response.getResults();
		System.out.println("记录总数为："+solrlist.getNumFound());
		for (SolrDocument solrDocument : solrlist) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	
		
	}

}
