package com.nd.pad.constPackage.database;

/**
 * ���������ݿ�
 * @author zmp
 *
 */
public class WhiteNetConst
{
	  public static final String TABLE_NAME = "whiteNetTable";
		 
      public static final String TABLE_ID = "tableID";

      public static final String WEB_URL = "webUrl";
      
      
      /***
 	  * ���������������ݱ��ı��sql���
 	  */
 	 public static final String CREATE_WHITENET_TABLE = "CREATE TABLE " + WhiteNetConst.TABLE_NAME + "( "+TABLE_ID+" INTEGER PRIMARY KEY, "
 			 																							               +WEB_URL+" TEXT" +
 			 																							             ")" ;
 	 
 	 /**
 	  * ɾ�������������ݱ��ı��sql���
 	  */
 	 public static final String DELETE_WHITENET_TABLE = "DROP TABLE IF EXISTS " + WhiteNetConst.TABLE_NAME;
 	 
}
