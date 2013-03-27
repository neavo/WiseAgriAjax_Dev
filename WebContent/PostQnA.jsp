<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String QnAPublisher = request.getParameter("QnAPublisher");
		String QnAPhone = request.getParameter("QnAPhone");
		String QnAContent = request.getParameter("QnAContent");
		String CategoryId = request.getParameter("CategoryId");
		String AppId = eDBManager.GetValue("SELECT appid FROM categorys WHERE categoryid = " + CategoryId + " LIMIT 1", "appid");

		String SQL = "INSERT INTO question"
			 + " ("
			 + " publisher, telephone, question,"
			 + " categoryid, appid,"
			 + " expertid, questiontype, publishtime"
			 + " )"
			 + " VALUE"
			 + " ("
			 + " \"" + QnAPublisher + "\", \"" + QnAPhone + "\", \"" + QnAContent + "\","
			 + " \"" + CategoryId + "\", \"" + AppId + "\","
			 + " \"0\", \"1\", Now()"
			 + " )";

		int Record = eDBManager.UpdateValue(SQL);
		if (Record == 1) {
			JSON = "{\"success\": true}";
		} else if (Record == 0) {
			JSON = "{\"success\": false}";
		};

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