package cn.e3mall.order.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;
import cn.e3mall.utils.CookieUtils;
import cn.e3mall.utils.E3Result;
import cn.e3mall.utils.JsonUtils;

public class LoginInterceptor implements HandlerInterceptor{

	@Value("${SSO_URL}")
	private String SSO_URL;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//从cookies中取token，若没取到，未登陆状态，
		//跳转到sso的登陆页面，用户登陆成功后，跳转到当前请求的url
		String token = CookieUtils.getCookieValue(request, "token");
		if(StringUtils.isBlank(token)){
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			//拦截
			return false;
		}
		//token不为空，需要调用sso系统的服务，根据token取用户信息
		E3Result e3Result = tokenService.getUserByToken(token);
		if(e3Result.getStatus()!=200){
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			//拦截
			return false;
		}
		//取不到用户信息。说明用户信息已过期，需要登陆
		//取到用户信息，说明在登陆状态，把用户信息放入request
		TbUser user = (TbUser) e3Result.getData();
        request.setAttribute("user", user);
        //判断cookies中是否有够恶策数据，把它添加到服务端
       String json = CookieUtils.getCookieValue(request, "cart",true);
       if(StringUtils.isNotBlank(json)){
    	  cartService.mergeCart(user.getId(), JsonUtils.jsonToList(json, TbItem.class));
       }
		return true;
	}

}
