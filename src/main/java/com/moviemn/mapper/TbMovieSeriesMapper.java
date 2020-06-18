package com.moviemn.mapper;

import com.moviemn.bean.TbMovieSeries;
import com.moviemn.base.BaseMapper;

public interface TbMovieSeriesMapper extends BaseMapper<TbMovieSeries> {

	TbMovieSeries querySingleTbMovieSeries(TbMovieSeries queryObj);

}
