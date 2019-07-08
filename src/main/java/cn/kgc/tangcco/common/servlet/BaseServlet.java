package cn.kgc.tangcco.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Optional;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 4188888927963760241L;
	private String methodName;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		methodName = Optional.ofNullable(request.getParameter("methodName")).orElse("");
		if (StringUtils.isNotEmpty(getContentType(request))) {
			try {
				if (StringUtils.isNotEmpty(methodName)) {
					if (getContentType(request).contains("application/json")
							|| getContentType(request).contains("text/plain")) {
						// ajax方式
						BufferedReader br = new BufferedReader(
								new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
						StringBuffer sb = new StringBuffer();
						String temp;
						while ((temp = br.readLine()) != null) {
							sb.append(temp);
						}
						br.close();
						Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
								HttpServletResponse.class, String.class);
						method.setAccessible(true);
						method.invoke(this, request, response, sb.toString());
					} else {
						// 传统表单方式
						Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
								HttpServletResponse.class);
						method.invoke(this, request, response);
						method.setAccessible(true);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String getContentType(HttpServletRequest request) {
		Optional<String> ofNullable = Optional.ofNullable(request.getHeader("content-type"));
		return ofNullable.orElse("");
	}

	public void print(HttpServletRequest request, HttpServletResponse response, String json) {
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			printWriter.flush();
			printWriter.close();
		}
	}

	public String prefix() {
		return "/WEB-INF/jsp/";
	}

	public String suffix() {
		return ".jsp";
	}

	public void forward(HttpServletRequest request, HttpServletResponse response, String forward)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(this.prefix());
		sb.append(forward);
		sb.append(suffix());
		request.getRequestDispatcher(sb.toString()).forward(request, response);
	}
}
