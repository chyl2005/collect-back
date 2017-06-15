package com.mm.back.interceptor;



import java.lang.reflect.Method;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.mm.back.entity.ModuleEntity;
import com.mm.back.service.ModuleService;
import com.mm.back.utils.UserUtils;

/**
 * 
 * @ClassName: CheckPermissionInterceptor
 * @Description: 权限校验拦截器 后于登录拦截器执行
 * @author chenyanlong
 * @date 2015年11月23日 下午5:37:47
 */
@Component
public class CheckPermissionInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckPermissionInterceptor.class);
	@Resource(name = "moduleService")
	private ModuleService moduleService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 获取当前登录用户信息
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		//Method method = handlerMethod.getMethod();
		// 方法名
		//String methodName = method.getName();
		RequestMapping requestMapping = handlerMethod.getBean().getClass().getAnnotation(RequestMapping.class);
		String[] actionPaths = requestMapping.value();
		// 当前请求 的controller requestMapping 中的值 也就是 Authority 表中 的 action_path
		String actionPath = actionPaths[0];
		// 查询是否有权限
		//去掉 mapping 前后"/"
		if (StringUtils.isNotBlank(actionPath)&&!actionPath.equals("/")) {
			actionPath = actionPath.startsWith("/") ? actionPath.substring(1, actionPath.length()) : actionPath;
			actionPath=actionPath.endsWith("/")?actionPath.substring(0, actionPath.length()-1) : actionPath;
		}
		ModuleEntity moduleEntity = this.moduleService.getModuleByPath(actionPath);
		if (null!=moduleEntity) {
			Integer moduleId = moduleEntity.getId();
			// 用户拥有权限的模块
			Set<Integer> moduleIds = UserUtils.getMenuIds();
			if (moduleIds.contains(moduleId)) {
				request.setAttribute("module", moduleEntity);
				return true;
			}
		}
		 request.getRequestDispatcher("/noPermissionPage").forward(request, response);  
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		MethodParameter returnType = handlerMethod.getReturnType();
		Method method = handlerMethod.getMethod();
		
		
		
	}

}
