package cn.e3mall.cart.interceptor;

/**
 * 用户登陆处理拦截器
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;
import cn.e3mall.utils.CookieUtils;
import cn.e3mall.utils.E3Result;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//前处理，执行handler之前执行之方法
		//返回true，放行，返回false 就拦截 
		//1.从cookie中取token
		//2.如果没取到，未登录，直接放行
		//3.取到了，有可能为登陆状态，调用sso服务，根据token去获取用户信息
		//4.没有取到用户信息，redis信息过期了，直接放行
		//5.取到用户信息，登陆状态
		//6.把用户信息放到request中，在controller中判断request是否包含用户信息，
		//即可判断是否在登陆状态
		//7.不管处于什么状态，都要放行，return true
		// 前处理，执行handler之前执行此方法。
				//返回true，放行	false：拦截
				//1.从cookie中取token
				String token = CookieUtils.getCookieValue(request, "token");
				//2.如果没有token，未登录状态，直接放行
				if (StringUtils.isBlank(token)) {
					return true;
				}
				//3.取到token，需要调用sso系统的服务，根据token取用户信息
				E3Result e3Result = tokenService.getUserByToken(token);
				//4.没有取到用户信息。登录过期，直接放行。
				if (e3Result.getStatus() != 200) {
					return true;
				}
				//5.取到用户信息。登录状态。
				TbUser user = (TbUser) e3Result.getData();
				//6.把用户信息放到request中。只需要在Controller中判断request中是否包含user信息。放行
				request.setAttribute("user", user);
				return true;
			}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {
		// handle执行之后，返回ModelAndView之前

	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		//返回ModelAndView后，处理异常

	}



	
}
