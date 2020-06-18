package com.moviemn.controller;

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

import com.moviemn.base.BaseController;
import com.moviemn.base.Pagination;
import com.moviemn.base.ServiceException;
import com.moviemn.bean.TbMovie;
import com.moviemn.bean.TbMovieSeries;
import com.moviemn.service.TbMovieSeriesService;
import com.moviemn.service.TbMovieService;

@Controller
@RequestMapping("/tbMovie")
public class TbMovieController extends BaseController{ 
	private static final Log LOG = LogFactory.getLog(TbMovieController.class);
	@Inject
	private TbMovieService tbMovieService;
	
	@Inject 
	TbMovieSeriesService movieSeriesService;

	@RequestMapping()
	public String index(Model model, TbMovie tbMovie) {
		Integer totalCount=tbMovieService.queryTbMoviePaginationCount(tbMovie);
		List<TbMovie> dataList=tbMovieService.queryTbMoviePaginationList(tbMovie);
		model.addAttribute("dataList",dataList);
		return "/tbMovie/movieIndex";
	}
	
	@RequestMapping("tbMovieManage")
	public String tbMovie(Model model) {
		return "/tbMovie/tbMovie";
	}
	
	
	@RequestMapping("viewPlayerbefore")
	public String player(Model model,String src) {
		model.addAttribute("src",src);
		return "/tbMovie/player";
	}
	
	
	@RequestMapping("viewPlayer")
	public String viewMovieSeries(Model model,Integer movieId, String order) {
		//选中的剧集
		Integer count = movieSeriesService.queryTbMovieSeriesCountForColumn("movie_id", movieId);
		TbMovieSeries queryObj = new TbMovieSeries();
		queryObj.setMovieId(movieId);
		queryObj.setOrderNo(order);
		TbMovieSeries tbMovieSeries = movieSeriesService.querySingleTbMovieSeries(queryObj);
		
		model.addAttribute("movie",tbMovieSeries);
		model.addAttribute("count",count);
		return "/tbMovie/player";
	}
	
	@ResponseBody
	@RequestMapping("insertTbMovie")
	public Map<String, Object> insertTbMovie(Model model,TbMovie tbMovie) {
		try {
			tbMovieService.insertTbMovie(tbMovie);
		} catch (Exception e) {
			LOG.error("TbMovieController.insertTbMovie [添加失败]", e);
			return resultFalse(e.getMessage());
		}
		return resultTrue();
	}

	@ResponseBody
	@RequestMapping("updateTbMovie")
	public Map<String, Object> updateTbMovie(Model model,TbMovie tbMovie) {
		try {
			tbMovieService.updateTbMovie(tbMovie);
		} catch (Exception e) {
			LOG.error("TbMovieController.updateTbMovie [修改失败]", e);
			return resultFalse(e.getMessage());
		}
		return resultTrue();
	}

	@ResponseBody
	@RequestMapping("deleteTbMovie")
	public Map<String, Object> deleteTbMovie(Model model,Integer id) {
		try {
			tbMovieService.deleteTbMovie(id);
		} catch (Exception e) {
			LOG.error("TbMovieController.deleteTbMovie [删除失败]", e);
			return resultFalse(e.getMessage());
		}
		return resultTrue();
	}
	

	@ResponseBody
	@RequestMapping("deleteTbMovies")
	public Map<String, Object> deleteTbMovies(Model model,String ids) {
		try {
			tbMovieService.deleteTbMovies(ids);
		} catch (Exception e) {
			LOG.error("TbMovieController.deleteTbMovies [删除失败]", e);
			return resultFalse(e.getMessage());
		}
		return resultTrue();
	}

	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping("queryTbMoviePaginationList")
	public Pagination queryTbMoviePaginationList(Model model,TbMovie tbMovie) {
		try {
			Integer totalCount=tbMovieService.queryTbMoviePaginationCount(tbMovie);
			List<TbMovie> dataList=tbMovieService.queryTbMoviePaginationList(tbMovie);
			return new Pagination(tbMovie, totalCount, dataList);
		} catch (ServiceException e) {
			LOG.error("TbMovieController.queryTbMoviePaginationList [查询列表失败]", e);
		}
		return new Pagination("查询列表异常");
	}

	
	@ResponseBody
	@RequestMapping("queryTbMovieListForSelect")
	public Map<String, Object> queryTbMovieListForSelect(Model model,TbMovie tbMovie) {
		try {
			List<TbMovie> dataList=tbMovieService.queryTbMovieList();
			if(dataList==null||dataList.size()==0){
				return resultTrue(dataList);
			}else{
				List<String> list=new ArrayList<String>();
				for (int i = 0; i < dataList.size(); i++) {
					TbMovie object=dataList.get(i);
					String value="<option value='"+object.getId()+"'>"+object.getName()+"</option>";
					list.add(value);
				}
				return resultTrue(list);
			}
		} catch (ServiceException e) {
			LOG.error("TbMovieController.queryTbMovieListForSelect [查询movie实体列表异常]", e);
		}
		return resultTrue("查询movie实体列表异常");
	}
}
