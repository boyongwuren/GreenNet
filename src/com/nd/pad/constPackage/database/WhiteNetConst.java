package com.nd.pad.constPackage.database;

/**
 * 白名单数据库
 * @author zmp
 *
 */
public class WhiteNetConst
{
	  public static final String TABLE_NAME = "whiteNetTable";
		 
      public static final String TABLE_ID = "tableID";

      public static final String WEB_URL = "webUrl";
      
      
      /***
 	  * 创建【白名单数据表】的表的sql语句
 	  */
 	 public static final String CREATE_WHITENET_TABLE = "CREATE TABLE " + WhiteNetConst.TABLE_NAME + "( "+TABLE_ID+" INTEGER PRIMARY KEY, "
 			 																							               +WEB_URL+" TEXT" +
 			 																							             ")" ;
 	 
 	 /**
 	  * 删除【白名单数据表】的表的sql语句
 	  */
 	 public static final String DELETE_WHITENET_TABLE = "DROP TABLE IF EXISTS " + WhiteNetConst.TABLE_NAME;
 	 
}
