<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String QId = request.getParameter("QId");
		int SkimNum = Integer.parseInt(eDBManager.GetValue("SELECT skimnum FROM question WHERE questionid = " + QId + " LIMIT 1", "skimnum"));

		String SQL = "UPDATE question"
			 + " SET skimnum = " + (SkimNum + 1)
			 + " WHERE questionid = " + QId;

		eDBManager.UpdateValue(SQL);

		SQL = "SELECT * FROM answer"
			 + " WHERE level = 1 AND questionid = " + QId
			 + " LIMIT 1";

		JSON = eDBManager.GetQnADetail(SQL);		
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