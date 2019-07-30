package com.ffs.api.filter;

import com.alibaba.fastjson.JSONObject;
import com.ffs.service.impl.PowerServiceImpl;
import com.ffs.service.jwt.TokenManage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Enumeration;

/**
 * 身份验证过滤器，拥有有效token与有效权限的用户才可以访问。
 * 注意，这里拦截所有请求，要设置排除静态资源
 * @author hp
 *
 */
@WebFilter(filterName="authFilter", urlPatterns={ "/*" })
public class AuthenticationFilter implements Filter {

	@Autowired
	private TokenManage tokenManage;
	@Autowired
	private PowerServiceImpl powerService;

	public Long userId;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//得到访问的url
		String currentPath = ((HttpServletRequest)request).getRequestURI();
		System.out.println("current path:"+currentPath);
		
		//排除所有静态资源
		if(currentPath.startsWith("/resources/")){
			chain.doFilter(request, response);
			return;
		}
		
		//排除登录请求
		if (currentPath.endsWith("/login") ) {
			chain.doFilter(request, response);
			return;
		}

		//排除注册
		if (currentPath.endsWith("/register") ) {
			chain.doFilter(request, response);
			return;
		}

		//获取请求头中的token
		String token= ((HttpServletRequest) request).getHeader("Authorization");
		//token分段
		String[] jwt=tokenManage.subsection(token);
		//没有token
		if(token==null||token.equals("")){
			//返回错误码
			((HttpServletResponse)response).sendError(500);
			return;
		}
		//假token
		else if(!tokenManage.examineToken(jwt[0],jwt[1],jwt[2])){
			//返回错误码
			((HttpServletResponse)response).sendError(500);
			return;
		}
		//不空不假
		else {
			//解析token
			JSONObject js = tokenManage.fetchToken(jwt[1]);
			Long time = (Long) js.get("expires");
			String id;
			id=js.get("userId").toString();
			if (time < Instant.now().toEpochMilli()) {
				//token过期,返回错误码
				((HttpServletResponse) response).sendError(401);
			}
			else {
				//权限判断

				if(powerService.authorizationDecide(Long.valueOf(id),currentPath)){
					//有权限
					((HttpServletRequest) request).setAttribute("id", id);
					((HttpServletRequest) request).setAttribute("type", js.get("type"));
					chain.doFilter(request, response);
					return;
				}
				else {
					//权限不足
					((HttpServletResponse) response).sendError(403);
					return;
				}
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
