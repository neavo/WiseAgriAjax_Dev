<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String ExpertId = request.getParameter("ExpertId");
		int SkimNum = Integer.parseInt(eDBManager.GetValue("SELECT skimnum FROM expert WHERE id = " + ExpertId + " LIMIT 1", "skimnum"));

		String SQL = "UPDATE expert"
			 + " SET skimnum = " + (SkimNum + 1)
			 + " WHERE id = " + ExpertId;

		eDBManager.UpdateValue(SQL);

		SQL = "SELECT * FROM expert"
			 + " WHERE level = 1"
			 + " AND id = " + ExpertId
			 + " LIMIT 1";

		JSON = eDBManager.GetExpertDetail(SQL);
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