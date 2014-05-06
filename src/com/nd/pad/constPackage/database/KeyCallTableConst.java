package com.nd.pad.constPackage.database;

public class KeyCallTableConst
{

	      public static final String TABLE_NAME = "keyCallTabel";
		 
	      public static final String TABLE_ID = "tableID";

	      public static final String PIC_URL = "picUrl";

	      public static final String INFO_TXT = "infoTxt";

	      public static final String WEB_URL = "webUrl";

	      public static final String ORDERING = "orderIng";
	      
	      
	      /***
	 	  * �������ص��Ƽ����ı��sql���
	 	  */
	 	 public static final String CREATE_KEYCALL_TABLE = "CREATE TABLE " + KeyCallTableConst.TABLE_NAME + "( "+TABLE_ID+" INTEGER PRIMARY KEY, "
	 			 																							               +PIC_URL+" TEXT, "
	 			 																							               +INFO_TXT+" TEXT, "
	 			 																							               +ORDERING+" INTEGER, "
	 			 																							               +WEB_URL+" TEXT" +
	 			 																							             ")" ;
	 	 
	 	 /**
	 	  * ɾ�����ص��Ƽ����ı��sql���
	 	  */
	 	 public static final String DELETE_KEYCALL_TABLE = "DROP TABLE IF EXISTS " + KeyCallTableConst.TABLE_NAME;
	 	 
}
