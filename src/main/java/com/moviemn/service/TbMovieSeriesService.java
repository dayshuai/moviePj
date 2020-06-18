package com.moviemn.service;

import com.moviemn.bean.TbMovieSeries;
import java.util.List;

public interface TbMovieSeriesService {

	public Integer insertTbMovieSeries(TbMovieSeries tbMovieSeries);

	public Integer updateTbMovieSeries(TbMovieSeries tbMovieSeries);

	public Integer deleteTbMovieSeries(Integer id);

	public Integer deleteTbMovieSeriess(String ids);
	
	public Integer deleteTbMovieSeriesForColumn(String columnName,Object columnValue);
	
	public TbMovieSeries querySingleTbMovieSeries(Integer id);

	public TbMovieSeries querySingleTbMovieSeriesForColumn(String columnName,Object columnValue);
	
	public Integer queryTbMovieSeriesPaginationCount(TbMovieSeries tbMovieSeries);

	public List<TbMovieSeries> queryTbMovieSeriesPaginationList(TbMovieSeries tbMovieSeries);

	public Integer queryTbMovieSeriesCount();
	
	public List<TbMovieSeries> queryTbMovieSeriesList();
	
	public Integer queryTbMovieSeriesCountForColumn(String columnName,Object columnValue);
	
	public List<TbMovieSeries> queryTbMovieSeriesListForColumn(String columnName,Object columnValue);

	public TbMovieSeries querySingleTbMovieSeries(TbMovieSeries queryObj);
	
}
