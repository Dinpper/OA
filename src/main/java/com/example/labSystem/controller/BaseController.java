package com.example.labSystem.controller;


import com.example.labSystem.dto.JsonResultDto;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.common.OpenApiConstant;
import com.example.labSystem.utils.GsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;
import java.io.PrintWriter;


public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	public void BackJsonResult(HttpServletResponse response, JsonResultDto jsonResultDto) throws IOException {
		String msg = "";
		PrintWriter out = null;
		response.setContentType("application/json;charset=UTF-8");
		out = response.getWriter();
		msg = jsonResultDto.toString();

		if (log.isDebugEnabled()) {
			log.debug("backResult:{}", msg);
		}
		if (out != null) {
			out.write(msg);
			out.flush();
		}
	}

	public void BackJsonResult(HttpServletResponse response, Object object) throws IOException {
		String msg = "";
		PrintWriter out = null;
		response.setContentType("application/json;charset=UTF-8");
		out = response.getWriter();
		msg = GsonUtil.ObjectToJson(object);

		if (log.isDebugEnabled()) {
			log.debug("backResult:{}", msg);
		}
		if (out != null) {
			out.write(msg);
			out.flush();
		}
	}

	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler(Exception.class)
	public void exception(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws IOException, ServletException {

		JsonResultDto jsonResultDto = new JsonResultDto();
		boolean hasCode = false;
		if (ex instanceof BusinessException) {
			Integer code = ((BusinessException) ex).getCode();
			if (null != code) {
				jsonResultDto.setCode(String.valueOf(code));
				jsonResultDto.setMessage(ex.getMessage());
				hasCode = true;
			}
			log.error("exception", ex);
		}	
		if (ex instanceof BindException) {
			String message = ex.getMessage();
			String[] split = message.split(OpenApiConstant.FIELD);
			String[] split2 = split[1].split(OpenApiConstant.SYMBOL_COLON);
			String[] split3 = split2[1].split(OpenApiConstant.VALUE);
			String[] split4 = split3[1].split(OpenApiConstant.SYMBOL_SEMICOLON);
			jsonResultDto.setCode("502");
			jsonResultDto.setMessage("参数封装有误 : "+split2[0]+" = "+split4[0]);
			hasCode = true;
			log.error("exception", ex);
		}
		if (!hasCode) {
			jsonResultDto.setCode(JsonResultDto.CODE_ERROR);
			jsonResultDto.setMessage(ex.getMessage());
			log.error("exception", ex);
		}

		BackJsonResult(response, jsonResultDto);
	}

	public boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("x-requested-with");
		if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
	
	/*public void checkOrInsertRequestCount(String userCode, String method) throws Exception {
		//从redis中获取，有的话，报请求多次
		boolean lockCheck = redisClient.lockCheck(OpenApiConstant.MANAGER_CheckMethod+userCode+":"+ method, 3);
		if(!lockCheck){
			throw new BusinessException(398);
		}
	}*/
}
