package cn.e3mall.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.search.service.SearchItemService;
import cn.e3mall.utils.E3Result;

@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	@Override
	public E3Result importAllItem() {
		List<SearchItem> list = itemMapper.getItemList();
		try {
			for (SearchItem searchItem : list) {
			//创建文档
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
			}
			solrServer.commit();
			 return E3Result.ok();
		 } catch (Exception e) {
			 e.printStackTrace();
			return E3Result.build(500, "导入数据库失败");
		}
		
	}

	

}
