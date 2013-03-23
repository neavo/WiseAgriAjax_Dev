<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String AppId = request.getParameter("AppId");

		String SQL = "SELECT apps.*, company.companyarea FROM apps, company"
			 + " WHERE apps.companyid = company.companyid AND apps.online = 1 AND apps.appid != 1 AND apps.appid != " + AppId
			 + " ORDER BY apps.appid ASC";

		JSON = eDBManager.GetAppList(SQL);
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