<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String NewsId = request.getParameter("NewsId");
		int SkimNum = Integer.parseInt(eDBManager.GetValue("SELECT skimnum FROM newscontent WHERE id = " + NewsId + " LIMIT 1", "skimnum"));

		String SQL = "UPDATE newscontent"
			 + " SET skimnum = " + (SkimNum + 1)
			 + " WHERE id = " + NewsId; ;

		eDBManager.UpdateValue(SQL);

		SQL = "SELECT * FROM newscontent"
			 + " WHERE level = 1"
			 + " AND id = " + NewsId
			 + " LIMIT 1";

		JSON = eDBManager.GetNewsDetail(SQL);
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