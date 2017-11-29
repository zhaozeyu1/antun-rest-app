package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.service.AreaService;
import com.zdjy.bigdata.antun.service.ChannelService;
import com.zdjy.bigdata.antun.service.ProductService;
import com.zdjy.bigdata.antun.service.UserService;
import com.zdjy.bigdata.antun.web.model.UserAdd;
import com.zdjy.bigdata.antun.web.model.UserUpdate;

@Component
public class UserValidation {
	@Autowired
	private UserService userService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ProductService productService;

	/**
	 * 新增验证
	 * 
	 * @param userAdd
	 * @return
	 */
	public String addUserValidation(UserAdd userAdd) {
		// 字段验证
		// 验证参数格式
		if (StringUtils.isBlank(userAdd.getName()))
			return empty("姓名");
		if (!isName(userAdd.getName()))
			return "名字格式不正确，只能是2-5位中文";
		if (StringUtils.isBlank(userAdd.getPhone()))
			return empty("手机号");
		if (!isPhone(userAdd.getPhone()))
			return empty("手机号格式不正确，只能是11位数字");
		if (userAdd.getSex() == null)
			return empty("性别");
		if (userAdd.getSex() != 1 && userAdd.getSex() != 0)
			return "性别格式不正确，只能是0或者1";
		if (StringUtils.isBlank(userAdd.getBirth()))
			return empty("生日");
		if (!isBirth(userAdd.getBirth()))
			return "出生日期格式不正确，正确的例子是：1991-04-17";
		if (StringUtils.isBlank(userAdd.getIdNo()))
			return empty("身份证号");
		if (!isIdNo(userAdd.getIdNo()))
			return empty("身份证号格式不正确，前17位只能是数字，最后一位可以是数字和x和X");
		if (userAdd.getProvince() == null)
			return empty("省份");
		if (userAdd.getCity() == null)
			return empty("城市");
		if (userAdd.getTown() == null)
			return empty("区县");
		if (StringUtils.isBlank(userAdd.getChannelCode()))
			return empty("渠道码");
		if (StringUtils.isBlank(userAdd.getProductCode()))
			return empty("产品码");
		
		
		//省市县验证
		//从小到大的顺序
		//1、通过town查询数据
		Area area=areaService.findById(userAdd.getTown());
		if(area==null)
			return "区县不存在";
		//2、根据town查出来的数据比对city
		if(area.getParentId().longValue()!=userAdd.getCity().longValue())
			return "区县和城市不匹配";
		//3、通过city查询数据
		Area area2=areaService.findById(userAdd.getCity());
		if(area2==null)
			return "城市不存在";
		//4、根据city查出来的数据比对province
		if(area2.getParentId().longValue()!=userAdd.getProvince().longValue())
			return "城市和省份不匹配";
		//渠道验证
		Channel channel=channelService.findByChannelCode(userAdd.getChannelCode());
		if(channel==null)
			return "渠道不存在";
		if(channel.getStatus()!=1)
			return "渠道已禁用";
		//产品验证
		Product product=productService.findByChannelCode(userAdd.getProductCode());
		if(product==null)
			return "产品不存在";
		if(product.getStatus()!=1)
			return "产品已下线";
		// 数据去重--手机号码去重
		User user = userService.findByPhone(userAdd.getPhone());
		if (user != null)
			return "手机号重复";
		return null;
	}

	private String empty(String string) {
		return string + "为空";
	}

	/**
	 * 修改验证
	 * 
	 * @param userUpdate
	 * @return
	 */
	public String updateUserValidation(UserUpdate userUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String NAME_PATTERN = "[\\u4e00-\\u9fa5]{2,5}";

	/**
	 * 验证是否是姓名
	 * 
	 * @param name
	 * @return
	 */
	public boolean isName(String name) {
		return name.matches(NAME_PATTERN);
	}

	private static final String PHONE_PATTERN = "\\d{11}";

	/**
	 * 验证是否是手机号
	 * 
	 * @param phone
	 * @return
	 */
	public boolean isPhone(String phone) {
		return phone.matches(PHONE_PATTERN);
	}

	private static final String BIRTH_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

	/**
	 * 验证是否是生日
	 * 
	 * @param idNo
	 * @return
	 */
	public boolean isBirth(String birth) {
		return birth.matches(BIRTH_PATTERN);
	}

	private static final String IDNO_PATTERN = "\\d{17}[0-9xX]";

	/**
	 * 验证是否是身份证号
	 * 
	 * @param idNo
	 * @return
	 */
	public boolean isIdNo(String idNo) {
		return idNo.matches(IDNO_PATTERN);
	}

}