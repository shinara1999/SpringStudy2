package com.sist.dao;
/*
	 NO                                        NOT NULL NUMBER
	 GOODS_NAME                                NOT NULL VARCHAR2(1000)
	 GOODS_SUB                                          VARCHAR2(1000)
	 GOODS_PRICE                               NOT NULL VARCHAR2(50)
	 GOODS_DISCOUNT                                     NUMBER
	 GOODS_FIRST_PRICE                                  VARCHAR2(20)
	 GOODS_DELIVERY                            NOT NULL VARCHAR2(20)
	 GOODS_POSTER                                       VARCHAR2(260)
	 HIT                                                NUMBER
 */
public class GoodsVO {
	private int no;
	private String goods_name, goods_price, goods_poster;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_poster() {
		return goods_poster;
	}
	public void setGoods_poster(String goods_poster) {
		this.goods_poster = goods_poster;
	}
	
}
