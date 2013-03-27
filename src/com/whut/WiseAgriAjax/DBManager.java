package com.whut.WiseAgriAjax;

import java.sql.* ;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;

import net.sf.json.JSONArray;

public class DBManager {

	// JDBC连接数据库
	public Connection GetConnection() {
		Connection eConnection = null;
		try {
			String DBName = "zhnydb";
			String UserName = "root";
			String PassWord = "lxt";
			String Url = "jdbc:mysql://localhost/" + DBName + "?user=" + UserName + "&password=" + PassWord + "&characterEncoding=utf8";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			eConnection = DriverManager.getConnection(Url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return eConnection;
	}

	// HashMap插入数据
	public Map<String, String> DoInsert(Map<String, String> eMap, String key, String value) {
		if (value != null) {
			eMap.put(key, value);
		} else {
			eMap.put(key, "");
		};
		return eMap;
	}

	// 获得字段数据
	public String GetValue(String SQL, String Key) {
		String Value = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Value = Value + eResultSet.getString(Key);
			}
			Value = Value.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return Value;
	}
	
	// 执行数据库更新
	public int UpdateValue(String SQL) {
		int Record = 0;
		Connection eConnection = null;
		Statement eStatement = null;
		
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			Record = eStatement.executeUpdate(SQL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return Record;
	}

	// 获取建议反馈列表
	public String GetFeedBackList(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();

		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "CId", eResultSet.getString("commentid"));
				DoInsert(eMap, "CContent", eResultSet.getString("comment"));
				DoInsert(eMap, "CImageUrl", eResultSet.getString("imageurl1"));
				DoInsert(eMap, "CPublisher", eResultSet.getString("publisher"));
				DoInsert(eMap, "CDateTime", eResultSet.getString("publishtime"));
				DoInsert(eMap, "CSkimNum", eResultSet.getString("skimnum"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}
	
	// 获取建议反馈详细信息
	public String GetFeedBackDetail(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();

		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
						
				DoInsert(eMap, "FContent", eResultSet.getString("content"));
				DoInsert(eMap, "FImageUrl", eResultSet.getString("imageurl1"));
				DoInsert(eMap, "FPublisher", eResultSet.getString("publisher"));
				DoInsert(eMap, "FDateTime", eResultSet.getString("publishtime"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}
	
	// 获取客户端列表
	public String GetAppList(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();

		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "AppId", eResultSet.getString("appid"));
				DoInsert(eMap, "AppName", eResultSet.getString("appname"));
				DoInsert(eMap, "AppIconUrl", eResultSet.getString("imageurl1"));
				DoInsert(eMap, "AppLocation", eResultSet.getString("companyarea"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}

	// 获取一级频道列表
	public String GetRootCategory(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();

		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "CategoryId", eResultSet.getString("categoryid"));
				DoInsert(eMap, "CategoryName", eResultSet.getString("categoryname"));
				DoInsert(eMap, "CategoryIconUrl", eResultSet.getString("categoryimageurl"));
				
				if (eResultSet.getString("parentid").equals("0")) {
					DoInsert(eMap, "CategoryType", "ParentCategory");
				} else {
					String CategoryType = eResultSet.getString("flag");
					if (CategoryType.equals("0")) {
						DoInsert(eMap, "CategoryType", "NewsCategory");
					} else if (CategoryType.equals("1") || CategoryType.equals("2")) {
						DoInsert(eMap, "CategoryType", "SnBCategory");
					} else if (CategoryType.equals("3")) {
						DoInsert(eMap, "CategoryType", "QnACategory");
					} else if (CategoryType.equals("4")) {
						DoInsert(eMap, "CategoryType", "ExpertCategory");
					} else if (CategoryType.equals("7")) {
						DoInsert(eMap, "CategoryType", "DoSnB");
					} else if (CategoryType.equals("8")) {
						DoInsert(eMap, "CategoryType", "DoQnA");
					} else
						DoInsert(eMap, "CategoryType", "");
				}
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}
	
	// 获取二级频道列表
	public String GetChildCategory(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();

		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "CategoryId", eResultSet.getString("categoryid"));
				DoInsert(eMap, "CategoryName", eResultSet.getString("categoryname"));
				DoInsert(eMap, "CategoryIconUrl", eResultSet.getString("categoryimageurl"));
				
				if (eResultSet.getString("parentid").equals("0")) {
					DoInsert(eMap, "CategoryType", "ParentCategory");
				} else {
					String CategoryType = eResultSet.getString("flag");
					if (CategoryType.equals("0")) {
						DoInsert(eMap, "CategoryType", "NewsCategory");
					} else if (CategoryType.equals("1") || CategoryType.equals("2")) {
						DoInsert(eMap, "CategoryType", "SnBCategory");
					} else if (CategoryType.equals("3")) {
						DoInsert(eMap, "CategoryType", "QnACategory");
					} else if (CategoryType.equals("4")) {
						DoInsert(eMap, "CategoryType", "ExpertCategory");
					} else if (CategoryType.equals("7")) {
						DoInsert(eMap, "CategoryType", "PostSnB");
					} else if (CategoryType.equals("8")) {
						DoInsert(eMap, "CategoryType", "PostQnA");
					} else
						DoInsert(eMap, "CategoryType", "");
				}
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}
	
	// 获取新闻列表信息
	public String GetNewsList(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();
		          
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "NewsId", eResultSet.getString("id"));
				DoInsert(eMap, "NewsTitle", eResultSet.getString("title"));
				DoInsert(eMap, "NewsIconUrl", eResultSet.getString("imageurl1"));
				DoInsert(eMap, "NewsDateTime", eResultSet.getString("datetime"));
				DoInsert(eMap, "NewsPublisher", eResultSet.getString("publisher"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}
	
	// 获取新闻详细信息
	public String GetNewsDetail(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();
		          
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "NewsTitle", eResultSet.getString("title"));
				DoInsert(eMap, "NewsContent", eResultSet.getString("textcontent"));
				DoInsert(eMap, "NewsSkimNum", eResultSet.getString("skimnum"));
				DoInsert(eMap, "NewsImageUrl", eResultSet.getString("imageurlall"));
				DoInsert(eMap, "NewsVideoUrl", eResultSet.getString("videourl"));
				DoInsert(eMap, "NewsDateTime", eResultSet.getString("datetime"));
				DoInsert(eMap, "NewsPublisher", eResultSet.getString("publisher"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}

	// 获取供求列表信息
	public String GetSnBList(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();
		          
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "SnBId", eResultSet.getString("id"));
				DoInsert(eMap, "SnBTitle", eResultSet.getString("title"));
				DoInsert(eMap, "SnBIconUrl", eResultSet.getString("imageurl1"));
				DoInsert(eMap, "SnBPublisher", eResultSet.getString("publisher"));
				DoInsert(eMap, "SnBDateTime", eResultSet.getString("publishtime"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}

	// 获取供求详细信息
	public String GetSnBDetail(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();
		          
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "SnBArea", eResultSet.getString("area"));
				DoInsert(eMap, "SnBTitle", eResultSet.getString("title"));
				DoInsert(eMap, "SnBPrice", eResultSet.getString("price"));
				DoInsert(eMap, "SnBPhone", eResultSet.getString("telephone"));
				DoInsert(eMap, "SnBImageUrl", eResultSet.getString("imageurl1"));
				DoInsert(eMap, "SnBContent", eResultSet.getString("content"));
				DoInsert(eMap, "SnBPublisher", eResultSet.getString("publisher"));
				DoInsert(eMap, "SnBDateTime", eResultSet.getString("publishtime"));
				DoInsert(eMap, "SnBSkimNum", eResultSet.getString("skimnum"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}

	// 获取问答列表信息
	public String GetQnAList(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();
		          
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "QId", eResultSet.getString("questionid"));
				DoInsert(eMap, "QContent", eResultSet.getString("question"));
				DoInsert(eMap, "QImageUrl", eResultSet.getString("imageurl1"));
				DoInsert(eMap, "QPublisher", eResultSet.getString("publisher"));
				DoInsert(eMap, "QDateTime", eResultSet.getString("publishtime"));
				DoInsert(eMap, "QSkimNum", eResultSet.getString("skimnum"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}
	
	// 获取问答详细信息
	public String GetQnADetail(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();
		          
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "AContent", eResultSet.getString("answer"));
				DoInsert(eMap, "AImageUrl", eResultSet.getString("imageurl1"));
				DoInsert(eMap, "APublisher", eResultSet.getString("publisher"));
				DoInsert(eMap, "ADateTime", eResultSet.getString("publishtime"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}
	
	// 获取专家列表信息
	public String GetExpertList(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();
		          
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "ExpertId", eResultSet.getString("id"));
				DoInsert(eMap, "ExpertName", eResultSet.getString("name"));
				DoInsert(eMap, "ExpertTitle", eResultSet.getString("title"));
				DoInsert(eMap, "ExpertPosition", eResultSet.getString("position"));
				DoInsert(eMap, "ExpertIconUrl", eResultSet.getString("imageurl"));
				
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}

	// 获取专家详细信息
	public String GetExpertDetail(String SQL) {
		String JSON = "";
		Connection eConnection = null;
		Statement eStatement = null;
		ResultSet eResultSet = null;
		List <Map<String, String>> eList = new ArrayList <Map<String, String>>();
		          
		try {
			eConnection = GetConnection();
			eStatement = eConnection.createStatement();
			eResultSet = eStatement.executeQuery(SQL);
			while (eResultSet.next()) {
				Map<String, String> eMap = new HashMap<String, String>(); 
				
				DoInsert(eMap, "ExpertName", eResultSet.getString("name"));
				DoInsert(eMap, "ExpertTitle", eResultSet.getString("title"));
				DoInsert(eMap, "ExpertPhone", eResultSet.getString("telephone"));
				DoInsert(eMap, "ExpertContent", eResultSet.getString("content"));
				DoInsert(eMap, "ExpertSkimNum", eResultSet.getString("skimnum"));
				DoInsert(eMap, "ExpertPosition", eResultSet.getString("position"));
				DoInsert(eMap, "ExpertImageUrl", eResultSet.getString("imageurl"));
					
				eList.add(eMap);
			}
			JSON = JSON + JSONArray.fromObject(eList).toString();
			JSON = JSON.replace("null", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DbUtils.closeQuietly(eResultSet);
			DbUtils.closeQuietly(eStatement);
			DbUtils.closeQuietly(eConnection);
		}
		return JSON;
	}
	
}
