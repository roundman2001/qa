package com.fallen.springboot; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView; 

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonInterceptor implements HandlerInterceptor {
	//在请求处理之前进行调用(Controller方法调用之前)
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.printf("preHandle被调用");
        //return true;    //如果false，停止流程，api被拦截
        
        log.info("------preHandle------");
        
        String requestUrl = httpServletRequest.getRequestURL().toString();

        log.info(requestUrl);
        
        //获取session
        HttpSession session = httpServletRequest.getSession(true);
        
        return true;
        
//        if (requestUrl.toLowerCase().contains("login"))
//        {
//        	return true;
//        }
//        else
//        {
//        	 //判断用户ID是否存在，不存在就跳转到登录界面
//            if(session.getAttribute("userId") == null){
//            	
//            	if (httpServletRequest.getMethod().toUpperCase().equals("POST"))
//            	{
//            		httpServletResponse.sendError(401);
//
//            		return false;
//            	}
//            	
//            	log.info("------:跳转到login页面！");
//            	httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login");
//                return false;
//            }else{
////                session.setAttribute("userId", session.getAttribute("userId"));
//                return true;
//            } 
//        } 
    }

    //请求处理之后进行调用，但是在视图被渲染之前(Controller方法调用之后)
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用");
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}
