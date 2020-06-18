package com.moviemn.controller;

import com.moviemn.bean.TbMovieSeries;
import com.moviemn.service.TbMovieSeriesService;
import com.moviemn.base.BaseController;
import com.moviemn.base.Pagination;
import com.moviemn.base.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tbMovieSeries")
public class TbMovieSeriesController extends BaseController{ 
	private static final Log LOG = LogFactory.getLog(TbMovieSeriesController.class);
	@Inject
	private TbMovieSeriesService tbMovieSeriesService;

	@RequestMapping()
	public String index(Model model) {
		return "tbMovieSeries";
	}
	
	@ResponseBody
	@RequestMapping("insertTbMovieSeries")
	public Map<String, Object> insertTbMovieSeries(Model model,TbMovieSeries tbMovieSeries) {
		try {
			tbMovieSeriesService.insertTbMovieSeries(tbMovieSeries);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesController.insertTbMovieSeries [添加失败]", e);
			return resultFalse(e.getMessage());
		}
		return resultTrue();
	}

	@ResponseBody
	@RequestMapping("updateTbMovieSeries")
	public Map<String, Object> updateTbMovieSeries(Model model,TbMovieSeries tbMovieSeries) {
		try {
			tbMovieSeriesService.updateTbMovieSeries(tbMovieSeries);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesController.updateTbMovieSeries [修改失败]", e);
			return resultFalse(e.getMessage());
		}
		return resultTrue();
	}

	@ResponseBody
	@RequestMapping("deleteTbMovieSeries")
	public Map<String, Object> deleteTbMovieSeries(Model model,Integer id) {
		try {
			tbMovieSeriesService.deleteTbMovieSeries(id);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesController.deleteTbMovieSeries [删除失败]", e);
			return resultFalse(e.getMessage());
		}
		return resultTrue();
	}
	

	@ResponseBody
	@RequestMapping("deleteTbMovieSeriess")
	public Map<String, Object> deleteTbMovieSeriess(Model model,String ids) {
		try {
			tbMovieSeriesService.deleteTbMovieSeriess(ids);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesController.deleteTbMovieSeriess [删除失败]", e);
			return resultFalse(e.getMessage());
		}
		return resultTrue();
	}

	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping("queryTbMovieSeriesPaginationList")
	public Pagination queryTbMovieSeriesPaginationList(Model model,TbMovieSeries tbMovieSeries) {
		try {
			Integer totalCount=tbMovieSeriesService.queryTbMovieSeriesPaginationCount(tbMovieSeries);
			List<TbMovieSeries> dataList=tbMovieSeriesService.queryTbMovieSeriesPaginationList(tbMovieSeries);
			return new Pagination(tbMovieSeries, totalCount, dataList);
		} catch (ServiceException e) {
			LOG.error("TbMovieSeriesController.queryTbMovieSeriesPaginationList [查询列表失败]", e);
		}
		return new Pagination("查询列表异常");
	}

	
	@ResponseBody
	@RequestMapping("queryTbMovieSeriesListForSelect")
	public Map<String, Object> queryTbMovieSeriesListForSelect(Model model,TbMovieSeries tbMovieSeries) {
		try {
			List<TbMovieSeries> dataList=tbMovieSeriesService.queryTbMovieSeriesList();
			if(dataList==null||dataList.size()==0){
				return resultTrue(dataList);
			}else{
				List<String> list=new ArrayList<String>();
				for (int i = 0; i < dataList.size(); i++) {
					TbMovieSeries object=dataList.get(i);
					String value="<option value='"+object.getId()+"'>"+object.getFileName()+"</option>";
					list.add(value);
				}
				return resultTrue(list);
			}
		} catch (ServiceException e) {
			LOG.error("TbMovieSeriesController.queryTbMovieSeriesListForSelect [查询剧集列表异常]", e);
		}
		return resultTrue("查询剧集列表异常");
	}
}
