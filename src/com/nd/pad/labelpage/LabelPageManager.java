package com.nd.pad.labelpage;

import java.util.Vector;

import android.graphics.Bitmap;

/**
 * 标签页管理器，代表标签页的数据层
 * @author yejinlong
 *
 */

public class LabelPageManager {
	
	static private LabelPageManager mInstance = null;
	private Vector<LabelPageItem> mLabelPageVec = new Vector<LabelPageItem>();	//所有标签页
	
	//单一实例
	private LabelPageManager() {}
	
	static public LabelPageManager instance() {
		if (mInstance == null) {
			mInstance = new LabelPageManager();
		}
		
		return mInstance;
	}	
	
	public class LabelPageItem {
		public Object mPageContentView;	//页面内容区域，唯一标识一个标签页
		public String mName;				//标签页名称
		public Bitmap mThumbBmp;		//标签页缩略图
		
		public LabelPageItem(Object pageContentView, String name, Bitmap thumbBmp) {
			mPageContentView = pageContentView;
			mName = name;
			mThumbBmp = thumbBmp;
		}
	}	
	
	/**
	 * 新增标签页，如果标签页已经存在更新它
	 * @param pageContentView 标签页内容View，唯一标识一个标签
	 * @param name	标签页名称
	 * @param thumbBmp	标签页缩略图
	 */
	public void add(Object pageContentView, String name, Bitmap thumbBmp) {
		if (pageContentView == null) {
			throw new IllegalArgumentException("page content view is null");
		}
		
		for (LabelPageItem item : mLabelPageVec) {
			if (item.mPageContentView == pageContentView) {  //已经存在更新它
				item.mPageContentView = pageContentView;
				item.mName = name;
				item.mThumbBmp = thumbBmp;
				return;
			}
		}
		
		//不存在，添加它
		mLabelPageVec.add(new LabelPageItem(pageContentView, name, thumbBmp));
	}
	
	/**
	 * 删除标签页
	 * @param pageContentView 标签页内容View，唯一标识一个标签
	 */
	public void remove(Object pageContentView) {
		for (LabelPageItem item : mLabelPageVec) {
			if (item.mPageContentView == pageContentView) { //存在，删之
				mLabelPageVec.remove(item);
				return;
			}
		}
	}
	
	/**
	 * 删除所有标签页
	 */
	public void removeAll() {
		mLabelPageVec.removeAllElements();
	}
	
	/**
	 * 获取标签页列表
	 * @return 标签页列表
	 */
	public Vector<LabelPageItem> get() {
		return mLabelPageVec;
	}
}
