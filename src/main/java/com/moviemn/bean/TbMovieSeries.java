package com.moviemn.bean;

import com.moviemn.base.BaseBean;
import com.moviemn.util.DateTimeSerializer;
import java.util.Date;
import com.moviemn.util.DateTimeSerializer;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class TbMovieSeries extends BaseBean implements java.io.Serializable {
	/** 序号 **/
	private Integer id;
	/** 资源地址 **/
	private String srcUrl;
	/** 创建时间 **/
	private Date createDate;
	/** 更新时间 **/
	private Date updateDate;
	/** movieId **/
	private Integer movieId;
	/** 文件名 **/
	private String fileName;
	/** 排序  **/
	private String orderNo;
	/** 描述 **/
	private String movieDesc;
	//以下字段只能用于查询赋值或临时使用

	public TbMovieSeries(){

	}

	public TbMovieSeries(String srcUrl,Date createDate,Date updateDate,Integer movieId,String fileName,String orderNo,String movieDesc){
		this.srcUrl=srcUrl;
		this.createDate=createDate;
		this.updateDate=updateDate;
		this.movieId=movieId;
		this.fileName=fileName;
		this.orderNo=orderNo;
		this.movieDesc=movieDesc;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	
	public void setSrcUrl(String srcUrl){
		this.srcUrl=srcUrl;
	}
	public String getSrcUrl(){
		return this.srcUrl;
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
	
	public void setMovieId(Integer movieId){
		this.movieId=movieId;
	}
	public Integer getMovieId(){
		return this.movieId;
	}
	
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	public String getFileName(){
		return this.fileName;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return this.orderNo;
	}
	
	public void setMovieDesc(String movieDesc){
		this.movieDesc=movieDesc;
	}
	public String getMovieDesc(){
		return this.movieDesc;
	}
	
	@Override
	public String toString(){
		return "TbMovieSeries [ srcUrl="+srcUrl+",createDate="+createDate+",updateDate="+updateDate+",movieId="+movieId+",fileName="+fileName+",orderNo="+orderNo+",movieDesc="+movieDesc+"]";
	}
}