package com.nd.pad.constPackage;

import java.io.File;

import android.os.Environment;

public class Constants {
	
	public static final int USER_EDIT_WORD_NUM = 300; //開箱文收起時最多顯示字數
	
	/*1MB*/
	public static final int MB=1024*1024;
	/*本地缓存大小*/
	public static final int CACHE_SIZE=100;
	/*需要缓存的空间*/
	public static final int FREE_SD_SPACE_NEEDED_TO_CACHE=CACHE_SIZE*MB;
	/*本地圖片缓存的存储位置*/
	public static final String FILE_CACHE= Environment.getExternalStorageState()+ File.separator +"com.greenNet"+File.separator+ "images";
	
}
