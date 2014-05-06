package com.nd.pad.constPackage.database;

/**
 * 导航下面的分类
 * @author zmp
 *
 */
public class GuideDataTableConst
{

	public static final String TABLE_NAME = "guideDataTable";

	public static final String TABLE_ID = "tableID";

	public static final String PIC_URL = "picUrl";

	public static final String INFO_TXT = "infoTxt";

	public static final String WEB_URL = "webUrl";

	public static final String CATEGORY_ID = "categoryId";

	public static final String ORDERING = "ordering";

	/***
	 * 创建【导航下面的分类】的表的sql语句
	 */
	public static final String CREATE_GUIDEDATA_TABLE = "CREATE TABLE " + GuideDataTableConst.TABLE_NAME + "( " + TABLE_ID + " INTEGER PRIMARY KEY, " + 
																												  PIC_URL + " TEXT, " + 
																												  INFO_TXT + " TEXT, " + 
																												  CATEGORY_ID + " INTEGER, " + 
																												  ORDERING + " INTEGER, " + 
																												  WEB_URL + " TEXT" + 
																											")";

	/**
	 * 删除【导航下面的分类】的表的sql语句
	 */
	public static final String DELETE_GUIDEDATA_TABLE = "DROP TABLE IF EXISTS " + GuideDataTableConst.TABLE_NAME;

}
