package com.moviemn.service;

import com.moviemn.bean.TbMovie;
import java.util.List;

public interface TbMovieService {

	public Integer insertTbMovie(TbMovie tbMovie);

	public Integer updateTbMovie(TbMovie tbMovie);

	public Integer deleteTbMovie(Integer id);

	public Integer deleteTbMovies(String ids);
	
	public Integer deleteTbMovieForColumn(String columnName,Object columnValue);
	
	public TbMovie querySingleTbMovie(Integer id);

	public TbMovie querySingleTbMovieForColumn(String columnName,Object columnValue);
	
	public Integer queryTbMoviePaginationCount(TbMovie tbMovie);

	public List<TbMovie> queryTbMoviePaginationList(TbMovie tbMovie);

	public Integer queryTbMovieCount();
	
	public List<TbMovie> queryTbMovieList();
	
	public Integer queryTbMovieCountForColumn(String columnName,Object columnValue);
	
	public List<TbMovie> queryTbMovieListForColumn(String columnName,Object columnValue);

}
