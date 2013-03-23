<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String ParentId = request.getParameter("ParentId");
		String PageStart = request.getParameter("PageStart");
		String PageLimit = request.getParameter("PageLimit");

		String SQL = "SELECT * FROM categorys "
			 + " WHERE parentid = " + ParentId
			 + " ORDER BY categoryid ASC"
			 + " LIMIT " + PageStart + ", " + PageLimit;

		JSON = eDBManager.GetChildCategory(SQL);
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