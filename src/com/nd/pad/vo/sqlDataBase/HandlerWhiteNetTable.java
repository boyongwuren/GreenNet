package com.nd.pad.vo.sqlDataBase;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nd.pad.constPackage.database.WhiteNetConst;

/**
 * ������������
 * @author zmp
 *
 */
public class HandlerWhiteNetTable
{
   /**
    * �������ݵ�������
	* @param webUrl
	*/
	public static void insertDataToTable(String webUrl)
	{
	    ContentValues cv = new ContentValues();
		cv.put(WhiteNetConst.WEB_URL, webUrl);
		
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getInstance().getReadableDatabase();
		
		sqLiteDatabase.insert(WhiteNetConst.TABLE_NAME, null, cv);
		
		sqLiteDatabase.close();
	}

	/**
	 * �������ݵ�������
	 * @param webUrl
	 */
	public static void insertDataToTable(ArrayList<String> whites)
	{
		for (int i = 0; i < whites.size(); i++) 
		{
			insertDataToTable(whites.get(i));
		}
	}
   
   /**
    * �Ӱ�������ȡ����
	* @return
	*/
	public static ArrayList<String> getDataFromTable()
   {
	   SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getSqlLiteDatabase();
       Cursor cursor = sqLiteDatabase.query(WhiteNetConst.TABLE_NAME, null, null, null, null, null, null);
       
       ArrayList<String> cVos = new ArrayList<String>();
       while (cursor.moveToNext()) 
       {
			 String webUrl = cursor.getString(cursor.getColumnIndex(WhiteNetConst.WEB_URL));
			 cVos.add(webUrl);
		}
       
       cursor.close();
       sqLiteDatabase.close();
       
       return cVos;
   }
	 
}
