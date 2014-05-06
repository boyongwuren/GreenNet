package com.nd.pad.vo.sqlDataBase;

import java.util.ArrayList;

import com.nd.pad.constPackage.database.CollectTableConst;
import com.nd.pad.interfacePackage.ShowCloseBtnInterface;
import com.nd.pad.vo.CollectVo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 处理 收藏表
 * @author zmp
 *
 */
public class HandlerCollectTable
{

	public static ShowCloseBtnInterface showCloseBtnInterface;
	
	/**
	 * 插入数据到收藏表
	 * @param infoTxt
	 * @param picUrl
	 * @param webUrl
	 */
	public static void insertDataToTable(String infoTxt,String picUrl,String webUrl)
	{
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getInstance().getReadableDatabase();
		
		Cursor cursor = sqLiteDatabase.query(CollectTableConst.TABLE_NAME, new String[]{CollectTableConst.INFO_TXT}, CollectTableConst.WEB_URL+"=?", new String[]{webUrl}, null, null, null);
		
		if(cursor.moveToNext())
		{
			//已经存在这个搜藏网址了
		}else 
		{
			ContentValues cv = new ContentValues();
			cv.put(CollectTableConst.INFO_TXT, infoTxt);
			cv.put(CollectTableConst.PIC_URL, picUrl);
			cv.put(CollectTableConst.WEB_URL, webUrl);
			
			sqLiteDatabase.insert(CollectTableConst.TABLE_NAME, null, cv);
		}
		
		sqLiteDatabase.close();
		
		if(showCloseBtnInterface != null)
		{
			showCloseBtnInterface.updataData();
		}
	}
	
	/**
	 * 删除数据
	 * @param id
	 */
	public static void deleteDataFromTable(int id)
	{
		String sql = "delete from "+CollectTableConst.TABLE_NAME +" where "+CollectTableConst.TABLE_ID +" = " + id;
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getSqlLiteDatabase();
		sqLiteDatabase.execSQL(sql);
    	
		sqLiteDatabase.close();
		
		if(showCloseBtnInterface != null)
		{
			showCloseBtnInterface.updataData();
		}
	}
	
	/**
	 * 获取收藏数据
	 * @return
	 */
	public static ArrayList<CollectVo> getDataFromTable()
	{
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getSqlLiteDatabase();
        Cursor cursor = sqLiteDatabase.query(CollectTableConst.TABLE_NAME, null, null, null, null, null, null);
        
        ArrayList<CollectVo> cVos = new ArrayList<CollectVo>();
        while (cursor.moveToNext()) 
        {
        	 CollectVo vo = new CollectVo();
			 int id = cursor.getInt(cursor.getColumnIndex(CollectTableConst.TABLE_ID));
			 String picUrl = cursor.getString(cursor.getColumnIndex(CollectTableConst.PIC_URL));
			 String infoTxt = cursor.getString(cursor.getColumnIndex(CollectTableConst.INFO_TXT));
			 String webUrl = cursor.getString(cursor.getColumnIndex(CollectTableConst.WEB_URL));
			 vo.id = id;
			 vo.picUrl = picUrl;
			 vo.infoString = infoTxt;
			 vo.webUrl = webUrl;
			 
			 cVos.add(vo);
		}
        
        cursor.close();
        sqLiteDatabase.close();
        
        return cVos;
	}

}
