<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String CategoryId = request.getParameter("CategoryId");
		String PageStart = request.getParameter("PageStart");
		String PageLimit = request.getParameter("PageLimit");
		String AppId = eDBManager.GetValue("SELECT appid FROM categorys WHERE categoryid = " + CategoryId + " LIMIT 1", "appid");

		String SQL = "SELECT * FROM expert"
			 + " WHERE  level = 1  AND appid = " + AppId
			 + " ORDER BY datetime DESC"
			 + " LIMIT " + PageStart + ", " + PageLimit;

		JSON = eDBManager.GetExpertList(SQL);
	} catch (Exception e) {
		System.out.println(e.getMessage());
	};

	if (request.getParameter("callback") != null) {
		response.setContentType("text/javascript;charset=UTF-8");
		response.getWriter().write(request.getParameter("callback") + "(" + JSON + ");");
	} else {
		response.setContentType("application/x-json;charset=UTF-8");
		response.getWriter().write(JSON);
	};

 %>