package com.nd.pad.constPackage;

import java.io.File;

import android.os.Environment;

public class Constants {
	
	public static final int USER_EDIT_WORD_NUM = 300; //�_��������r����@ʾ�֔�
	
	/*1MB*/
	public static final int MB=1024*1024;
	/*���ػ����С*/
	public static final int CACHE_SIZE=100;
	/*��Ҫ����Ŀռ�*/
	public static final int FREE_SD_SPACE_NEEDED_TO_CACHE=CACHE_SIZE*MB;
	/*���؈DƬ����Ĵ洢λ��*/
	public static final String FILE_CACHE= Environment.getExternalStorageState()+ File.separator +"com.greenNet"+File.separator+ "images";
	
}
