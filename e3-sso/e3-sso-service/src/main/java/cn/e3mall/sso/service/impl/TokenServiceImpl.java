package cn.e3mall.sso.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;
import cn.e3mall.utils.E3Result;
import cn.e3mall.utils.JsonUtils;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	@Override
	public E3Result getUserByToken(String token) {
		// 根据token到redis中取用户信息
		//取不到用户信息，redis已过期，返回登陆过期
		//取到用户信息，更新token的过期时间
		//返回结果，E3Result中包含TbUser的信息
		String json = jedisClient.get("SESSION:"+token);
		if(StringUtils.isBlank(json)){
			return E3Result.build(201, "用户登陆已经过期");
		}	
		//取到用户信息，更新过期时间
		jedisClient.expire("SESSION:"+token, SESSION_EXPIRE);
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		
		return E3Result.ok(user);
		
	}

}
