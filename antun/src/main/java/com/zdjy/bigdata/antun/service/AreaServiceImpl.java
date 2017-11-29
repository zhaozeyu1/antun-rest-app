package com.zdjy.bigdata.antun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.domain.AreaExample;
import com.zdjy.bigdata.antun.domain.AreaExample.Criteria;
import com.zdjy.bigdata.antun.mapper.AreaMapper;

/**
 * 地区业务类
 * @author zdjy
 *
 */
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaMapper areaMapper;

	public Area findById(Long town) {
		return areaMapper.selectByPrimaryKey(town);
	}

	public List<Area> findByParentId(Long parentId) {
		AreaExample areaExample = new AreaExample();
		Criteria createCriteria = areaExample.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		return areaMapper.selectByExample(areaExample);
	}
}