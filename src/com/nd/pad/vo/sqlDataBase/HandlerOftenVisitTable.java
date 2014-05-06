package com.nd.pad.vo.sqlDataBase;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nd.pad.constPackage.database.OftenVisitTableConst;
import com.nd.pad.vo.OftenVisitVo;

/**
 * @author 处理经常访问表
 *
 */
public class HandlerOftenVisitTable
{
	/**
	 * 插入数据到经常访问表
	 * @param infoTxt
	 * @param picUrl
	 * @param webUrl
	 */
	public static void insertDataToTable(String infoTxt,String picUrl,String webUrl)
	{
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getInstance().getReadableDatabase();
		
		Cursor cursor = sqLiteDatabase.query(OftenVisitTableConst.TABLE_NAME, new String[]{OftenVisitTableConst.VISIT_COUNT}, OftenVisitTableConst.WEB_URL+"=?", new String[]{webUrl}, null, null, null);
		
		if (cursor.moveToNext())
		{
			//有这条记录
			int visitCount = cursor.getInt(0);
			visitCount++;
			ContentValues cv = new ContentValues();
			cv.put(OftenVisitTableConst.VISIT_COUNT, visitCount);
			sqLiteDatabase.update(OftenVisitTableConst.TABLE_NAME, cv, OftenVisitTableConst.WEB_URL+"=?", new String[]{webUrl});
		}else 
		{
			//没有这条记录 
			//插入
			ContentValues cv = new ContentValues();
			cv.put(OftenVisitTableConst.INFO_TXT, infoTxt);
			cv.put(OftenVisitTableConst.PIC_URL, picUrl);
			cv.put(OftenVisitTableConst.WEB_URL, webUrl);
			
			sqLiteDatabase.insert(OftenVisitTableConst.TABLE_NAME, null, cv);
		}

		sqLiteDatabase.close();
	}
	
	/**
	 * 获取经常访问的数据
	 * @return
	 */
	public static ArrayList<OftenVisitVo> getDataFromTable()
	{
		SQLiteDatabase sqLiteDatabase = GreenNetOpenHelper.getSqlLiteDatabase();
        Cursor cursor = sqLiteDatabase.query(OftenVisitTableConst.TABLE_NAME, null, null, null,  null, null,OftenVisitTableConst.VISIT_COUNT +" DESC");
        
        ArrayList<OftenVisitVo> cVos = new ArrayList<OftenVisitVo>();
        while (cursor.moveToNext()) 
        {
        	 OftenVisitVo vo = new OftenVisitVo();
			 int id = cursor.getInt(cursor.getColumnIndex(OftenVisitTableConst.TABLE_ID));
			 int visitCount = cursor.getInt(cursor.getColumnIndex(OftenVisitTableConst.VISIT_COUNT));
			 String picUrl = cursor.getString(cursor.getColumnIndex(OftenVisitTableConst.PIC_URL));
			 String infoTxt = cursor.getString(cursor.getColumnIndex(OftenVisitTableConst.INFO_TXT));
			 String webUrl = cursor.getString(cursor.getColumnIndex(OftenVisitTableConst.WEB_URL));
			 vo.id = id;
			 vo.picUrl = picUrl;
			 vo.infoString = infoTxt;
			 vo.webUrl = webUrl;
			 vo.visitNum = visitCount;
			 cVos.add(vo);
		}
        
        cursor.close();
        sqLiteDatabase.close();
        
        return cVos;
	}
}
