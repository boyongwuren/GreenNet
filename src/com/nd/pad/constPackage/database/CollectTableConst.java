package com.nd.pad.constPackage.database;


/**
 * 收藏表常量
 * @author zmp
 *
 */
public class CollectTableConst
{

	/**
	 * 【我们收藏】的表的表名称
	 */
	 public static final String TABLE_NAME = "MY_COLLECT";
	 
	 /**
	  * 表的 字段键
	  */
	 public static final String TABLE_ID = "Table_Id";
	 
	 /**
	  * 图片地址
	  */
	 public static final String PIC_URL = "picUrl";
	 
	 /**
	  * 信息文本
	  */
	 public static final String INFO_TXT = "infoTxt";
	 
	 /**
	  * 页面的url地址
	  */
	 public static final String WEB_URL = "webUrl";
	 
	 /***
	  * 创建【我的收藏】的表的sql语句
	  */
	 public static final String CREATE_MYCOLLECT_TABLE = "CREATE TABLE " + CollectTableConst.TABLE_NAME + "( "+TABLE_ID+" INTEGER PRIMARY KEY, "
			 																							               +PIC_URL+" TEXT, "
			 																							               +INFO_TXT+" TEXT, "
			 																							               +WEB_URL+" TEXT" +
			 																							             ")" ;
	 
	 /**
	  * 删除【我的收藏】的表的sql语句
	  */
	 public static final String DELETE_MYCOLLECT_TABLE = "DROP TABLE IF EXISTS " + CollectTableConst.TABLE_NAME;
	 
}
