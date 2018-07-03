package cn.e3mall.sso.service;

import cn.e3mall.utils.E3Result;

public interface LoginService {
	
	 //参数 用户名，密码
	//返回类型：
	/**
	 * 1.判断用户名密码是否正确
	 * 2.不正确 登陆失败
	 * 3.正确 生成token
	 * 4.把用户信息写入redis
	 * 5.设置redis的过期时间
	 * 6.把token返回给表现层
	 * 7.
	 */
	E3Result userLogin(String username,String password);

}
