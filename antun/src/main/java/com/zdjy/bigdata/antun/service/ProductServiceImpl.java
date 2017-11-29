package com.zdjy.bigdata.antun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.domain.ProductExample;
import com.zdjy.bigdata.antun.domain.ProductExample.Criteria;
import com.zdjy.bigdata.antun.mapper.ProductMapper;

/**
 * 产品业务类
 * @author zdjy
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	public Product findByChannelCode(String productCode) {
		ProductExample productExample = new ProductExample();
		Criteria createCriteria = productExample.createCriteria();
		createCriteria.andCodeEqualTo(productCode);
		List<Product> selectByExample = productMapper.selectByExample(productExample);
		if(selectByExample.isEmpty())
			return null;
		return selectByExample.get(0);
	}
}