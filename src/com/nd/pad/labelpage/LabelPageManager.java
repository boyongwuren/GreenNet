package com.nd.pad.labelpage;

import java.util.Vector;

import android.graphics.Bitmap;

/**
 * ��ǩҳ�������������ǩҳ�����ݲ�
 * @author yejinlong
 *
 */

public class LabelPageManager {
	
	static private LabelPageManager mInstance = null;
	private Vector<LabelPageItem> mLabelPageVec = new Vector<LabelPageItem>();	//���б�ǩҳ
	
	//��һʵ��
	private LabelPageManager() {}
	
	static public LabelPageManager instance() {
		if (mInstance == null) {
			mInstance = new LabelPageManager();
		}
		
		return mInstance;
	}	
	
	public class LabelPageItem {
		public Object mPageContentView;	//ҳ����������Ψһ��ʶһ����ǩҳ
		public String mName;				//��ǩҳ����
		public Bitmap mThumbBmp;		//��ǩҳ����ͼ
		
		public LabelPageItem(Object pageContentView, String name, Bitmap thumbBmp) {
			mPageContentView = pageContentView;
			mName = name;
			mThumbBmp = thumbBmp;
		}
	}	
	
	/**
	 * ������ǩҳ�������ǩҳ�Ѿ����ڸ�����
	 * @param pageContentView ��ǩҳ����View��Ψһ��ʶһ����ǩ
	 * @param name	��ǩҳ����
	 * @param thumbBmp	��ǩҳ����ͼ
	 */
	public void add(Object pageContentView, String name, Bitmap thumbBmp) {
		if (pageContentView == null) {
			throw new IllegalArgumentException("page content view is null");
		}
		
		for (LabelPageItem item : mLabelPageVec) {
			if (item.mPageContentView == pageContentView) {  //�Ѿ����ڸ�����
				item.mPageContentView = pageContentView;
				item.mName = name;
				item.mThumbBmp = thumbBmp;
				return;
			}
		}
		
		//�����ڣ������
		mLabelPageVec.add(new LabelPageItem(pageContentView, name, thumbBmp));
	}
	
	/**
	 * ɾ����ǩҳ
	 * @param pageContentView ��ǩҳ����View��Ψһ��ʶһ����ǩ
	 */
	public void remove(Object pageContentView) {
		for (LabelPageItem item : mLabelPageVec) {
			if (item.mPageContentView == pageContentView) { //���ڣ�ɾ֮
				mLabelPageVec.remove(item);
				return;
			}
		}
	}
	
	/**
	 * ɾ�����б�ǩҳ
	 */
	public void removeAll() {
		mLabelPageVec.removeAllElements();
	}
	
	/**
	 * ��ȡ��ǩҳ�б�
	 * @return ��ǩҳ�б�
	 */
	public Vector<LabelPageItem> get() {
		return mLabelPageVec;
	}
}
