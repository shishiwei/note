package com.tedu.cloudnote.resolver;

import org.apache.log4j.Logger;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

public class MobileExceptionResolver implements HandlerExceptionResolver {


	final Logger logger = Logger.getLogger(this.getClass());
	

	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request,
										 HttpServletResponse response, Object handler, Exception ex) {

		logger.debug(request.getRequestURI());

		ex.printStackTrace();

		
		//进入异常action，进行异常处理
		return new ModelAndView("redirect:/note/exception.do");
	}
	
	/**
	@Deprecated
	private Throwable getRootCause(Throwable ex) {
		if (ex.getCause() == null) {
			return ex;
		} else {
			getRootCause(ex.getCause());
		}
		return ex;
	}
	**/

	/**
	 * @method: getStackTrace
	 * @tags: @param t
	 * @tags: @return
	 * @author：hwang@3ti.us
	 * @date: 2015年7月1日 下午12:18:00
	 * @Descripton:
	 * 获取异常的堆栈信息 
	 */
	@Deprecated
	private static String getStackTrace(Throwable ex)  {  
		StringWriter sw = new StringWriter();  
		PrintWriter pw = new PrintWriter(sw);  
		try{
			ex.printStackTrace(pw);  
			return sw.toString();  
		}finally{  
			pw.close();  
		}
	}
}
