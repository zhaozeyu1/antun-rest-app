package com.zdjy.bigdata.antun.service;

import java.util.List;

import com.zdjy.bigdata.antun.domain.Area;

public interface AreaService {

	Area findById(Long town);
	
	List<Area> findByParentId(Long parentId);
}
