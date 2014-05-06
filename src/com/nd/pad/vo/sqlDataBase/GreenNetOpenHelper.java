package com.nd.pad.vo.sqlDataBase;

import com.nd.pad.constPackage.database.CategoryTableConst;
import com.nd.pad.constPackage.database.CollectTableConst;
import com.nd.pad.constPackage.database.DataBaseConst;
import com.nd.pad.constPackage.database.GuideDataTableConst;
import com.nd.pad.constPackage.database.KeyCallTableConst;
import com.nd.pad.constPackage.database.OftenVisitTableConst;
import com.nd.pad.constPackage.database.WhiteNetConst;
import com.nd.pad.GreenBrowser.util.SingleToolClass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 绿色上网数据库类
 * @author Administrator
 *
 */
public class GreenNetOpenHelper extends SQLiteOpenHelper {

	/**
	 * @param context
	 */
	public GreenNetOpenHelper(Context context)
	{
		super(context, DataBaseConst.DATABASE_NAME, null, DataBaseConst.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		//创建【我的收藏】表
		 db.execSQL(CategoryTableConst.CREATE_CATEGORY_TABLE);
		 db.execSQL(CollectTableConst.CREATE_MYCOLLECT_TABLE);
		 db.execSQL(GuideDataTableConst.CREATE_GUIDEDATA_TABLE);
		 db.execSQL(OftenVisitTableConst.CREATE_OFTENVISIT_TABLE);
		 db.execSQL(WhiteNetConst.CREATE_WHITENET_TABLE);
		 db.execSQL(KeyCallTableConst.CREATE_KEYCALL_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		  db.execSQL(CategoryTableConst.DELETE_CATEGORY_TABLE);
		  db.execSQL(CollectTableConst.DELETE_MYCOLLECT_TABLE);
		  db.execSQL(GuideDataTableConst.DELETE_GUIDEDATA_TABLE);
		  db.execSQL(OftenVisitTableConst.DELETE_OFTENVISIT_TABLE);
		  db.execSQL(WhiteNetConst.DELETE_WHITENET_TABLE);
		  db.execSQL(KeyCallTableConst.DELETE_KEYCALL_TABLE);
		  onCreate(db);
	}
	
	private static GreenNetOpenHelper greenNetOpenHelper;
	
	public static GreenNetOpenHelper getInstance()
	{
		if(greenNetOpenHelper == null)
		{
			greenNetOpenHelper = new GreenNetOpenHelper(SingleToolClass.content);
		}
		
		return greenNetOpenHelper;
	}

	
	public static SQLiteDatabase getSqlLiteDatabase()
	{
		return getInstance().getReadableDatabase();
	}
	
}
