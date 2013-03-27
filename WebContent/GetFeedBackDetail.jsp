<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String CId = request.getParameter("CId");
		int SkimNum = Integer.parseInt(eDBManager.GetValue("SELECT skimnum FROM comment WHERE commentid = " + CId + " LIMIT 1", "skimnum"));

		String SQL = "UPDATE comment"
			 + " SET skimnum = " + (SkimNum + 1)
			 + " WHERE commentid = " + CId;

		eDBManager.UpdateValue(SQL);

		SQL = "SELECT * FROM feedback"
			 + " WHERE level = 1 AND commentid = " + CId
			 + " LIMIT 1";

		JSON = eDBManager.GetFeedBackDetail(SQL);		
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