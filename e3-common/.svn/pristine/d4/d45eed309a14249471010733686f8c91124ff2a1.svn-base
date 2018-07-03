package cn.e3mall.common.pojo;

import java.io.Serializable;

public class SearchItem implements Serializable{
	private String id;
	private String sell_point;
	private String title;
	private String image;
	private long price;
	private String category_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String[] getImages(){
		if(image!=null && !"".equals(image)){
			String [] strings= image.split(",");
			return strings;
		}else{
			return null;
		}
	}
	
	
	
}