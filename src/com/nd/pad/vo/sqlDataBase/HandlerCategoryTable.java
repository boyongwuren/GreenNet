package com.nd.pad.vo.sqlDataBase;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nd.pad.constPackage.database.CategoryTableConst;
import com.nd.pad.vo.CategoryVo;

/**
 * 操作导航分类表
 * @author Administrator
 * 
 */
public class HandlerCategoryTable
{

	public static void insertDataToTable(int id,String description,String name,int order,int parentId,String picUrl)
	{
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getInstance().getReadableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(CategoryTableConst.TABLE_ID, id);
		cv.put(CategoryTableConst.DESCRIPTION, description);
		cv.put(CategoryTableConst.NAME, name);
		cv.put(CategoryTableConst.ORDER, order);
		cv.put(CategoryTableConst.PARENT_ID, parentId);
		cv.put(CategoryTableConst.PIC_URL, picUrl);

		sqLiteDatabase.insert(CategoryTableConst.TABLE_NAME, null, cv);

		sqLiteDatabase.close();
	}
	
	public static void insertDataToTable(ArrayList<CategoryVo> cVos)
	{
		for (int i = 0; i < cVos.size(); i++) 
		{
			insertDataToTabel(cVos.get(i));
		}
	}
	
	public static void insertDataToTabel(CategoryVo vo)
	{
		insertDataToTable(vo.id,vo.description, vo.name, vo.order, vo.parentId, vo.iconUrl);
	}
	
	private static ArrayList<CategoryVo> cVos;
	
	public static ArrayList<CategoryVo> getDataFromTable()
	{
		if(HandlerCategoryTable.cVos != null)
		{
			return HandlerCategoryTable.cVos;
		}
		
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getSqlLiteDatabase();
        Cursor cursor = sqLiteDatabase.query(CategoryTableConst.TABLE_NAME, null, null, null, null, null, null);
        
        ArrayList<CategoryVo> cVos = new ArrayList<CategoryVo>();
        while (cursor.moveToNext()) 
        {
        	 CategoryVo vo = new CategoryVo();
			 String desc = cursor.getString(cursor.getColumnIndex(CategoryTableConst.DESCRIPTION));
			 String name = cursor.getString(cursor.getColumnIndex(CategoryTableConst.NAME));
			 int id = cursor.getInt(cursor.getColumnIndex(CategoryTableConst.TABLE_ID));
			 int order = cursor.getInt(cursor.getColumnIndex(CategoryTableConst.ORDER));
			 int parentId = cursor.getInt(cursor.getColumnIndex(CategoryTableConst.PARENT_ID));
			 
			 String picUrl = cursor.getString(cursor.getColumnIndex(CategoryTableConst.PIC_URL));
			 
			 vo.id = id;
			 vo.description = desc;
			 vo.iconUrl = picUrl;
			 vo.name = name;
			 vo.order = order;
			 vo.parentId = parentId;
			 
			 cVos.add(vo);
		}
        
        cursor.close();
        sqLiteDatabase.close();
        
        if(cVos.size()>0)
        {
        	HandlerCategoryTable.cVos = cVos;
        }

        return cVos;
	}

}
