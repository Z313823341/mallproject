package cn.e3mall.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.LoginService;
import cn.e3mall.utils.E3Result;
import cn.e3mall.utils.JsonUtils;

/**
 * 1.判断用户名密码是否正确
 * 2.不正确 登陆失败
 * 3.正确 生成token
 * 4.把用户信息写入redis
 * 5.设置redis的过期时间
 * 6.把token返回给表现层
 * 7.
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	@Override
	public E3Result userLogin(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		//执行查询
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list==null || list.size()<0){
			return E3Result.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		//把密码再进行md5加密，再进行比对
		
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
			return E3Result.build(400, "用户名或密码错误");
		}
	    String token=UUID.randomUUID().toString();
	    user.setPassword(null);
	    jedisClient.set("SESSION:"+token,JsonUtils.objectToJson(user));
	    //设置session的过期时间
	    jedisClient.expire("SESSION:"+token,SESSION_EXPIRE );
	    
		return E3Result.ok(token);
	}

}
