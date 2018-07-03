package cn.e3mall.solorj;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrCloud {
	@Test
	public void testCloudSolr() throws Exception{
	//搜索系统集群的连接，应该使用cloudsolrserver创建
	CloudSolrServer server = new CloudSolrServer("192.168.25.128:2182,192.168.25.128:2183,192.168.25.128:2184");
	//zkhost：zookeeper的地址列表
	//设置一个defaultCollection的属性
	server.setDefaultCollection("collection2");
	//创建一个文档对象
	SolrInputDocument document = new SolrInputDocument();
	//像文档中添加域
	document.addField("id", "solrCloud01");
	document.addField("item_title", "测试商品");
	document.addField("item_price", 123);
	//将文件提交到索引库
	server.add(document);
	server.commit();
	}
	
	@Test
	public void testQueryDocument() throws Exception{
		//创建一个cloudsolrserver对象 zkHost
		CloudSolrServer server = new CloudSolrServer("192.168.25.128:2182,192.168.25.128:2183,192.168.25.128:2184");
		//设置一个defaultCollection的属性
		server.setDefaultCollection("collection2");
		//创建查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		//执行查询
		QueryResponse response = server.query(query);
		//接受查询结果
	    SolrDocumentList list = response.getResults();
	    //取记录总数
	    System.out.println(list.getNumFound());
	    //遍历记录
	    for (SolrDocument solrDocument : list) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
		}
	    
		
		
	}
	
}
