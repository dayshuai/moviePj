package com.moviemn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviemn.base.BaseService;
import com.moviemn.base.ServiceException;
import com.moviemn.bean.TbMovieSeries;
import com.moviemn.mapper.TbMovieSeriesMapper;
import com.moviemn.service.TbMovieSeriesService;

@Transactional
@Service("tbMovieSeriesService")
public class TbMovieSeriesServiceImpl extends BaseService implements TbMovieSeriesService {
	private static final Log LOG = LogFactory.getLog(TbMovieSeriesServiceImpl.class);
	@Autowired
	private TbMovieSeriesMapper tbMovieSeriesMapper;
	private void validation(TbMovieSeries tbMovieSeries,String operatorState) {
		if (tbMovieSeries==null) {
			throw new ServiceException("表单不能为空");
		}
		if (operatorState.equals("update")) {
			if (tbMovieSeries.getId()==null) {
				throw new ServiceException("序号不能为空");
			}
		}
		if (isBlank(tbMovieSeries.getSrcUrl())) {
			throw new ServiceException("资源地址不能为空");
		}
		if (isBlank(tbMovieSeries.getCreateDate())) {
			throw new ServiceException("创建时间不能为空");
		}
		if (isBlank(tbMovieSeries.getUpdateDate())) {
			throw new ServiceException("更新时间不能为空");
		}
		if (isBlank(tbMovieSeries.getMovieId())) {
			throw new ServiceException("movieId不能为空");
		}
		if (isBlank(tbMovieSeries.getFileName())) {
			throw new ServiceException("文件名不能为空");
		}
		if (isBlank(tbMovieSeries.getOrderNo())) {
			throw new ServiceException("排序 不能为空");
		}
		if (isBlank(tbMovieSeries.getMovieDesc())) {
			throw new ServiceException("描述不能为空");
		}
	}
	
	@Override
	public Integer insertTbMovieSeries(TbMovieSeries tbMovieSeries){
		validation(tbMovieSeries,"insert");
		try {
			tbMovieSeriesMapper.insert(tbMovieSeries);
			return tbMovieSeries.getId();
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.insertTbMovieSeries [ "+tbMovieSeries+" ] 添加失败", e);
			throw new ServiceException("添加失败");
		}
	}

	@Override
	public Integer updateTbMovieSeries(TbMovieSeries tbMovieSeries){
		validation(tbMovieSeries,"update");
		try {
			return tbMovieSeriesMapper.update(tbMovieSeries);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.updateTbMovieSeries [ "+tbMovieSeries+" ] 修改失败", e);
			throw new ServiceException("修改失败");
		}
	}

	@Override
	public Integer deleteTbMovieSeries(Integer iD){
		try {
			return tbMovieSeriesMapper.delete(iD);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.deleteTbMovieSeries [ "+iD+" ] 删除失败", e);
			throw new ServiceException("删除失败");
		}
	}

	@Override
	public Integer deleteTbMovieSeriess(String idStrs){
		try {
			return tbMovieSeriesMapper.deletes(idStrs);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.deleteTbMovieSeriess [ "+idStrs+" ] 批量删除失败", e);
			throw new ServiceException("批量删除失败");
		}
	}
	
	@Override
	public Integer deleteTbMovieSeriesForColumn(String columnName,Object columnValue){
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("columnName", columnName);
			map.put("columnValue", columnValue);
			return tbMovieSeriesMapper.deleteForMap(map);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.deleteTbMovieSeriesForColumn [ columnName="+columnName+",columnValue="+columnValue+" ] 根据字段删除失败", e);
			throw new ServiceException("根据字段删除失败");
		}
	}
	
	@Override
	public TbMovieSeries querySingleTbMovieSeries(Integer iD) {
		try {
			return tbMovieSeriesMapper.querySingleObject(iD);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.querySingleTbMovieSeries [ "+iD+" ] 查询对象失败", e);
			throw new ServiceException("查询对象失败");
		}
	}
	
	@Override
	public TbMovieSeries querySingleTbMovieSeriesForColumn(String columnName,Object columnValue) {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("columnName", columnName);
			map.put("columnValue", columnValue);
			return tbMovieSeriesMapper.querySingleObjectForMap(map);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.querySingleTbMovieSeriesForColumn [ columnName="+columnName+",columnValue="+columnValue+" ] 根据字段查询对象失败", e);
			throw new ServiceException("根据字段查询对象失败");
		}
	}
	
	@Override
	public Integer queryTbMovieSeriesPaginationCount(TbMovieSeries tbMovieSeries) {
		try {
			return tbMovieSeriesMapper.queryObjectPaginationCount(tbMovieSeries);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.queryTbMovieSeriesCount [ "+tbMovieSeries+" ] 查询条数失败", e);
			throw new ServiceException("查询条数失败");
		}
	}

	@Override
	public List<TbMovieSeries> queryTbMovieSeriesPaginationList(TbMovieSeries tbMovieSeries) {
		try {
			return tbMovieSeriesMapper.queryObjectPaginationList(tbMovieSeries);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.queryTbMovieSeriesList [ "+tbMovieSeries+" ] 查询列表失败", e);
			throw new ServiceException("查询列表失败");
		}
	}
	
	@Override
	public Integer queryTbMovieSeriesCount() {
		try {
			return tbMovieSeriesMapper.queryObjectCount();
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.queryTbMovieSeriesCount 统计查询失败", e);
			throw new ServiceException("统计查询失败");
		}
	}
	
	@Override
	public List<TbMovieSeries> queryTbMovieSeriesList() {
		try {
			return tbMovieSeriesMapper.queryObjectList();
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.queryTbMovieSeriesSelect 查询下拉框列表失败", e);
			throw new ServiceException("查询下拉框列表失败");
		}
	}
	
	@Override
	public Integer queryTbMovieSeriesCountForColumn(String columnName,Object columnValue) {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("columnName", columnName);
			map.put("columnValue", columnValue);
			return tbMovieSeriesMapper.queryObjectCountForMap(map);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.queryTbMovieSeriesCountForColumnName 根据字段统计查询失败", e);
			throw new ServiceException("根据字段统计查询失败");
		}
	}
	
	@Override
	public List<TbMovieSeries> queryTbMovieSeriesListForColumn(String columnName,Object columnValue) {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("columnName", columnName);
			map.put("columnValue", columnValue);
			return tbMovieSeriesMapper.queryObjectListForMap(map);
		} catch (Exception e) {
			LOG.error("TbMovieSeriesServiceImpl.queryTbMovieSeriesListForColumnName 根据字段查询失败", e);
			throw new ServiceException("根据字段查询失败");
		}
	}

	@Override
	public TbMovieSeries querySingleTbMovieSeries(TbMovieSeries queryObj) {
		return tbMovieSeriesMapper.querySingleTbMovieSeries(queryObj);
	}
}
