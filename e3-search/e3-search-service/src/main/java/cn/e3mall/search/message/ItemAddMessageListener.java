package cn.e3mall.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;

/**
 * 监听商品添加事件消息，将对应的商品信息添加到索引库
 * @author 文浩
 *
 */
public class ItemAddMessageListener implements MessageListener {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public void onMessage(Message message) {
		//从消息中取商品id
		//根据id查询商品信息
	    //创建一个文档
		//向文档中添加域
		//把文档写入索引库
		//提交
		try {
		TextMessage textMessage = (TextMessage)message;
		String text;
		text = textMessage.getText();
		Long itemId = new Long(text);
		//等待事务提交
		Thread.sleep(1000);
		SearchItem searchItem = itemMapper.getItemById(itemId);
		
		SolrInputDocument document = new SolrInputDocument();
		//向文档中添加域
		document.addField("id", searchItem.getId());
		document.addField("item_title", searchItem.getTitle());
		document.addField("item_sell_point", searchItem.getSell_point());
		document.addField("item_price", searchItem.getPrice());
		document.addField("item_image", searchItem.getImage());
		document.addField("item_category_name", searchItem.getCategory_name());
		//添加文档到索引库
		solrServer.add(document);
		solrServer.commit();
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
		

	}

}
