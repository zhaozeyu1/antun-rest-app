package com.zdjy.bigdata.antun.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.service.UserService;
@Component
public class PingAnJieKouSender {
	private Logger logger=LoggerFactory.getLogger(PingAnJieKouSender.class);
	@Autowired
	private UserService userService;
	private RestTemplate restTemplate = new RestTemplate();
	public int send(User user) {
		PingAnJieKouResponse forObject;
		try {
			StringBuilder builder = new StringBuilder("http://47.94.250.65/pinganjiekou/user/add?");
			builder.append("userName="+user.getName()+"&");
			builder.append("userSex="+user.getSex()+"&");
			builder.append("userBirth="+user.getBirth()+"&");
			builder.append("userPhone="+user.getPhone()+"&");
			builder.append("userIdNo="+user.getIdNo()+"&");
			builder.append("province="+user.getProvince()+"&");
			builder.append("city="+user.getCity()+"&");
			builder.append("town="+user.getTown()+"&");
			builder.append("channelCode=afangti_huhuabo_001&");
			builder.append("channelToken=-67-75-49-31-96-89-29-1191749116-528-4926-43&");
			builder.append("productCode="+user.getProductCode());
			logger.info("【send】"+builder.toString());
			forObject = restTemplate.getForObject(builder.toString(), PingAnJieKouResponse.class);
			logger.info("【response】"+new Gson().toJson(forObject));
			if (forObject.getCode() == 200) {
				user.setStutus(2);
			} else {
				user.setStutus(3);
			}
			user.setSendCode(forObject.getCode());
			user.setSendResult(forObject.getResult());
		} catch (RestClientException e) {
			e.printStackTrace();
			user.setStutus(1);
		}
		int i=userService.updateUser(user);
		return i;
	}
}