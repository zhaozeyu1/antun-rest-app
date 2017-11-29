package com.zdjy.bigdata.antun.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.domain.UserExample;
import com.zdjy.bigdata.antun.domain.UserExample.Criteria;
import com.zdjy.bigdata.antun.mapper.UserMapper;
import com.zdjy.bigdata.antun.util.TransferUtil;
import com.zdjy.bigdata.antun.web.model.UserAdd;
import com.zdjy.bigdata.antun.web.model.UserUpdate;

/**
 * 用户数据业务类
 * @author zdjy
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	public List<User> findAll() {
		return userMapper.selectByExample(null);
	}


	public int addUser(UserAdd userAdd) {
		User transfer = TransferUtil.transfer(userAdd, User.class);
		transfer.setGmtCreate(new Date());
		return userMapper.insertSelective(transfer);
	}

	 
	public User getUser(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	 
	public int updateUser(Long id, UserUpdate userUpdate) {
		User user = TransferUtil.transfer(userUpdate, User.class);
		user.setId(id);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	 
	public int deleteUser(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	 
	public User findByPhone(String phone) {
		//创建查询类对象
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		//拼接查询参数
		createCriteria.andPhoneEqualTo(phone);
		List<User> selectByExample = userMapper.selectByExample(userExample);
		if(selectByExample.isEmpty())
			return null;
		return selectByExample.get(0);
	}

	 
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	 
	public List<User> findByStatus(int i) {
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		createCriteria.andStutusEqualTo(i);
		return userMapper.selectByExample(userExample);
	}
}