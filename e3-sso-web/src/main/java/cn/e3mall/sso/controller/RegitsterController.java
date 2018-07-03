package cn.e3mall.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.RegisterService;
import cn.e3mall.utils.E3Result;

/**
 * 注册功能
 * @author 文浩
 *
 */
@Controller
public class RegitsterController {

	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
     public E3Result checkData(@PathVariable String param,@PathVariable Integer type){
		
		E3Result e3Result = registerService.checkData(param, type);
		
		return e3Result;
		
	}
	
	@RequestMapping(value="/user/register",method = RequestMethod.POST)
	@ResponseBody
	public E3Result register(TbUser tbUser){
		E3Result result = registerService.register(tbUser);
		return result;
	}
	
}
