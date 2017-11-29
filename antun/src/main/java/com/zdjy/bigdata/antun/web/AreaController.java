package com.zdjy.bigdata.antun.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.service.AreaService;
import com.zdjy.bigdata.antun.web.response.BaseResponse;

@RestController
@RequestMapping("/areas")
public class AreaController extends BaseResponse{
	@Autowired
	private AreaService areaService;
	/**
	 * 省市联动接口
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/{parentId}",method=RequestMethod.GET)
	public BaseResponse findByParentId(@PathVariable Long parentId){
		List<Area> areas=areaService.findByParentId(parentId);
		return successResponse("查询成功").data(areas);
	}
}