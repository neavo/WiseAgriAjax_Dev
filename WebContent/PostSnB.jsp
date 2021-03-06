<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding = "utf-8" import = "com.whut.WiseAgriAjax.DBManager" %>
<%

	String JSON = "";
	try {
		DBManager eDBManager = new DBManager();
		String SnBTitle = request.getParameter("SnBTitle");
		String SnBPrice = request.getParameter("SnBPrice");
		String SnBArea = request.getParameter("SnBArea");
		String SnBPublisher = request.getParameter("SnBPublisher");
		String SnBPhone = request.getParameter("SnBPhone");
		String SnBContent = request.getParameter("SnBContent");
		String SnBCategoryId = request.getParameter("SnBCategoryId");
		String AppId = eDBManager.GetValue("SELECT appid FROM categorys WHERE categoryid = " + SnBCategoryId + " LIMIT 1", "appid");
		int SnBType = Integer.parseInt(request.getParameter("SnBType"));
		String CategoryId = eDBManager.GetValue("SELECT categoryid FROM categorys WHERE parentid != 0 AND flag = " + (SnBType+1) + " AND appid = " + AppId + " LIMIT 1", "categoryid");
		
		String SQL = "INSERT INTO publish"
			 + " ("
			 + " title, price, area,"
			 + " publisher, telephone, content,"
			 + " appid, category, categoryid,"
			 + " typeid, publishtime"
			 + " )"
			 + " VALUE"
			 + " ("
			 + " \"" + SnBTitle + "\", \"" + SnBPrice + "\", \"" + SnBArea + "\","
			 + " \"" + SnBPublisher + "\", \"" + SnBPhone + "\", \"" + SnBContent + "\","
			 + " \"" + AppId + "\", \"" + SnBType + "\", \"" + CategoryId + "\","
			 + " \"1\", Now()"
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