package com.nd.pad.constPackage.database;

/**
 * �������ʵı�
 * @author zmp
 *
 */
public class OftenVisitTableConst
{
      public static final String TABLE_NAME = "oftenVisitTabel";
	 
      public static final String TABLE_ID = "tableID";

      public static final String PIC_URL = "picUrl";

      public static final String INFO_TXT = "infoTxt";

      public static final String WEB_URL = "webUrl";

      public static final String VISIT_COUNT = "visitCount";
      
      
      /***
 	  * �������������ʡ��ı��sql���
 	  */
 	 public static final String CREATE_OFTENVISIT_TABLE = "CREATE TABLE " + OftenVisitTableConst.TABLE_NAME + "( "+TABLE_ID+" INTEGER PRIMARY KEY, "
 			 																							               +PIC_URL+" TEXT, "
 			 																							               +INFO_TXT+" TEXT, "
 			 																							               +VISIT_COUNT+" INTEGER, "
 			 																							               +WEB_URL+" TEXT" +
 			 																							             ")" ;
 	 
 	 /**
 	  * ɾ�����ҵ��ղء��ı��sql���
 	  */
 	 public static final String DELETE_OFTENVISIT_TABLE = "DROP TABLE IF EXISTS " + OftenVisitTableConst.TABLE_NAME;
 	 
}
