package com.moviemn.service.impl;

import com.moviemn.base.ServiceException;
import com.moviemn.bean.TbMovie;
import com.moviemn.mapper.TbMovieMapper;
import com.moviemn.service.TbMovieService;
import com.moviemn.base.BaseService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("tbMovieService")
public class TbMovieServiceImpl extends BaseService implements TbMovieService {
	private static final Log LOG = LogFactory.getLog(TbMovieServiceImpl.class);
	@Autowired
	private TbMovieMapper tbMovieMapper;
	private void validation(TbMovie tbMovie,String operatorState) {
		if (tbMovie==null) {
			throw new ServiceException("表单不能为空");
		}
		if (operatorState.equals("update")) {
			if (tbMovie.getId()==null) {
				throw new ServiceException("序号不能为空");
			}
		}
		if (isBlank(tbMovie.getName())) {
			throw new ServiceException("电影名不能为空");
		}
		if (isBlank(tbMovie.getUrl())) {
			throw new ServiceException("电影url不能为空");
		}
		if (isBlank(tbMovie.getPicPath())) {
			throw new ServiceException("图片路径不能为空");
		}
	}
	
	@Override
	public Integer insertTbMovie(TbMovie tbMovie){
		validation(tbMovie,"insert");
		try {
			tbMovieMapper.insert(tbMovie);
			return tbMovie.getId();
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.insertTbMovie [ "+tbMovie+" ] 添加失败", e);
			throw new ServiceException("添加失败");
		}
	}

	@Override
	public Integer updateTbMovie(TbMovie tbMovie){
		validation(tbMovie,"update");
		try {
			return tbMovieMapper.update(tbMovie);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.updateTbMovie [ "+tbMovie+" ] 修改失败", e);
			throw new ServiceException("修改失败");
		}
	}

	@Override
	public Integer deleteTbMovie(Integer id){
		try {
			return tbMovieMapper.delete(id);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.deleteTbMovie [ "+id+" ] 删除失败", e);
			throw new ServiceException("删除失败");
		}
	}

	@Override
	public Integer deleteTbMovies(String idStrs){
		try {
			return tbMovieMapper.deletes(idStrs);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.deleteTbMovies [ "+idStrs+" ] 批量删除失败", e);
			throw new ServiceException("批量删除失败");
		}
	}
	
	@Override
	public Integer deleteTbMovieForColumn(String columnName,Object columnValue){
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("columnName", columnName);
			map.put("columnValue", columnValue);
			return tbMovieMapper.deleteForMap(map);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.deleteTbMovieForColumn [ columnName="+columnName+",columnValue="+columnValue+" ] 根据字段删除失败", e);
			throw new ServiceException("根据字段删除失败");
		}
	}
	
	@Override
	public TbMovie querySingleTbMovie(Integer id) {
		try {
			return tbMovieMapper.querySingleObject(id);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.querySingleTbMovie [ "+id+" ] 查询对象失败", e);
			throw new ServiceException("查询对象失败");
		}
	}
	
	@Override
	public TbMovie querySingleTbMovieForColumn(String columnName,Object columnValue) {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("columnName", columnName);
			map.put("columnValue", columnValue);
			return tbMovieMapper.querySingleObjectForMap(map);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.querySingleTbMovieForColumn [ columnName="+columnName+",columnValue="+columnValue+" ] 根据字段查询对象失败", e);
			throw new ServiceException("根据字段查询对象失败");
		}
	}
	
	@Override
	public Integer queryTbMoviePaginationCount(TbMovie tbMovie) {
		try {
			return tbMovieMapper.queryObjectPaginationCount(tbMovie);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.queryTbMovieCount [ "+tbMovie+" ] 查询条数失败", e);
			throw new ServiceException("查询条数失败");
		}
	}

	@Override
	public List<TbMovie> queryTbMoviePaginationList(TbMovie tbMovie) {
		try {
			return tbMovieMapper.queryObjectPaginationList(tbMovie);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.queryTbMovieList [ "+tbMovie+" ] 查询列表失败", e);
			throw new ServiceException("查询列表失败");
		}
	}
	
	@Override
	public Integer queryTbMovieCount() {
		try {
			return tbMovieMapper.queryObjectCount();
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.queryTbMovieCount 统计查询失败", e);
			throw new ServiceException("统计查询失败");
		}
	}
	
	@Override
	public List<TbMovie> queryTbMovieList() {
		try {
			return tbMovieMapper.queryObjectList();
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.queryTbMovieSelect 查询下拉框列表失败", e);
			throw new ServiceException("查询下拉框列表失败");
		}
	}
	
	@Override
	public Integer queryTbMovieCountForColumn(String columnName,Object columnValue) {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("columnName", columnName);
			map.put("columnValue", columnValue);
			return tbMovieMapper.queryObjectCountForMap(map);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.queryTbMovieCountForColumnName 根据字段统计查询失败", e);
			throw new ServiceException("根据字段统计查询失败");
		}
	}
	
	@Override
	public List<TbMovie> queryTbMovieListForColumn(String columnName,Object columnValue) {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("columnName", columnName);
			map.put("columnValue", columnValue);
			return tbMovieMapper.queryObjectListForMap(map);
		} catch (Exception e) {
			LOG.error("TbMovieServiceImpl.queryTbMovieListForColumnName 根据字段查询失败", e);
			throw new ServiceException("根据字段查询失败");
		}
	}

}
