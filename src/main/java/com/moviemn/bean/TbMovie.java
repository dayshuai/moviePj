package com.moviemn.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.moviemn.base.BaseBean;
import com.moviemn.utils.DateTimeSerializer;

public class TbMovie extends BaseBean implements java.io.Serializable {
	/** 序号 **/
	private Integer id;
	/** 电影名 **/
	private String name;
	/** 电影url **/
	private String url;
	/** 图片路径 **/
	private String picPath;
	/** 创建时间 **/
	private Date createDate;
	/** 更新时间 **/
	private Date updateDate;
	//以下字段只能用于查询赋值或临时使用

	public TbMovie(){

	}

	public TbMovie(String name,String url,String picPath,Date createDate,Date updateDate){
		this.name=name;
		this.url=url;
		this.picPath=picPath;
		this.createDate=createDate;
		this.updateDate=updateDate;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return this.url;
	}
	
	public void setPicPath(String picPath){
		this.picPath=picPath;
	}
	public String getPicPath(){
		return this.picPath;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate=createDate;
	}
	@JsonSerialize(using=DateTimeSerializer.class)
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setUpdateDate(Date updateDate){
		this.updateDate=updateDate;
	}
	@JsonSerialize(using=DateTimeSerializer.class)
	public Date getUpdateDate(){
		return this.updateDate;
	}
	
	@Override
	public String toString(){
		return "TbMovie [ name="+name+",url="+url+",picPath="+picPath+",createDate="+createDate+",updateDate="+updateDate+"]";
	}
}