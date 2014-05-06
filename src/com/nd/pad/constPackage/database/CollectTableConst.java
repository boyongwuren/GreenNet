package com.nd.pad.constPackage.database;


/**
 * �ղر���
 * @author zmp
 *
 */
public class CollectTableConst
{

	/**
	 * �������ղء��ı�ı�����
	 */
	 public static final String TABLE_NAME = "MY_COLLECT";
	 
	 /**
	  * ��� �ֶμ�
	  */
	 public static final String TABLE_ID = "Table_Id";
	 
	 /**
	  * ͼƬ��ַ
	  */
	 public static final String PIC_URL = "picUrl";
	 
	 /**
	  * ��Ϣ�ı�
	  */
	 public static final String INFO_TXT = "infoTxt";
	 
	 /**
	  * ҳ���url��ַ
	  */
	 public static final String WEB_URL = "webUrl";
	 
	 /***
	  * �������ҵ��ղء��ı��sql���
	  */
	 public static final String CREATE_MYCOLLECT_TABLE = "CREATE TABLE " + CollectTableConst.TABLE_NAME + "( "+TABLE_ID+" INTEGER PRIMARY KEY, "
			 																							               +PIC_URL+" TEXT, "
			 																							               +INFO_TXT+" TEXT, "
			 																							               +WEB_URL+" TEXT" +
			 																							             ")" ;
	 
	 /**
	  * ɾ�����ҵ��ղء��ı��sql���
	  */
	 public static final String DELETE_MYCOLLECT_TABLE = "DROP TABLE IF EXISTS " + CollectTableConst.TABLE_NAME;
	 
}
