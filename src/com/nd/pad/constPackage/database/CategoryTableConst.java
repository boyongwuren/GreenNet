package com.nd.pad.constPackage.database;

public class CategoryTableConst
{
	 public static final String TABLE_NAME = "cateogryTable";
	 
     public static final String TABLE_ID = "tableID";

     public static final String PARENT_ID = "parentID";

     public static final String NAME = "name";

     public static final String PIC_URL = "picUrl";

     public static final String ORDER = "ordering";

     public static final String DESCRIPTION = "description";

     

     
     
     /***
	  * ������������𡿵ı��sql���
	  */
	 public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE " + CategoryTableConst.TABLE_NAME + "( "+TABLE_ID+" INTEGER PRIMARY KEY, "
			 																							               +PARENT_ID+" INTEGER, "
			 																							               +NAME+" TEXT, "
			 																							               +PIC_URL+" TEXT, "
			 																							               +ORDER+" INTEGER, "
			 																							               +DESCRIPTION+" TEXT" +
			 																							             ")" ;
	 
	 /**
	  * ɾ����������𡿵ı��sql���
	  */
	 public static final String DELETE_CATEGORY_TABLE = "DROP TABLE IF EXISTS " + CategoryTableConst.TABLE_NAME;
	 
}
