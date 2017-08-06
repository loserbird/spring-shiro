package com.loserbird.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
	 	@RequestMapping("/hello1")
	    public String hello1() {
	        SecurityUtils.getSubject().checkRole("admin");
	        return "success";
	    }

	    @RequiresRoles("admin")
	    @RequestMapping("/hello2")
	    public String hello2() {
	        return "success";
	    }
	    
	    @RequestMapping(value="/login",method=RequestMethod.POST)
	    public void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
	    	  String error = null;
	    	  String username = req.getParameter("username");
	          String password = req.getParameter("password");
	          String rememberMe = req.getParameter("rememberMe");
	          System.out.println(rememberMe);
	          System.out.println(username+password);
	          Subject subject = SecurityUtils.getSubject();
	          UsernamePasswordToken token = new UsernamePasswordToken(username,password);
	          try {
	        	  if(rememberMe != null && "true".equals(rememberMe)){
	        		  token.setRememberMe(true);//记住我，自动登录
	        	  }
	        	  subject.login(token);
			} catch (UnknownAccountException e) {
				error = "用户名/密码错误";
			}catch (IncorrectCredentialsException e) {
				error = "用户名/密码错误";
			}catch (AuthenticationException e) {
				error = "其他错误";
			}
	          
	          if(error != null){
	        	  System.out.println("error---"+error);
	        	  req.setAttribute("error", error);
	        	  req.getRequestDispatcher("/login.jsp").forward(req, resp);
	          }else{
	        	  req.getRequestDispatcher("/success.jsp").forward(req, resp);
	          }
	         
	    }
}
