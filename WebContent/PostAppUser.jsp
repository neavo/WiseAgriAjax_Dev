<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();	
		String IMSI = request.getParameter("IMSI");
		String AppId = request.getParameter("AppId");
		String RecordId = eDBManager.GetValue("SELECT id FROM appuser WHERE appid = " + AppId + " AND IMSI = " + IMSI + " AND DATE(datetime) = Date(Now()) LIMIT 1", "id");
		
		String SQL = "";
		if (RecordId.length() != 0){
			int RecordNum = Integer.parseInt(eDBManager.GetValue("SELECT num FROM appuser WHERE id = " + RecordId + " LIMIT 1", "num"));
			SQL = "UPDATE appuser SET num = " + (RecordNum+1) + " WHERE  id = " + RecordId + " LIMIT 1";
		} else {
			SQL = "INSERT INTO appuser (appid, IMSI, num) VALUES (\"" + AppId + "\", \"" + IMSI + "\", \"1\")";	
		};

		eDBManager.UpdateValue(SQL);
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