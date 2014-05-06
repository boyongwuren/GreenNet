package com.nd.pad.labelpage;

import com.nd.pad.labelpage.LabelPageManager.LabelPageItem;
import com.nd.pad.GreenBrowser.R;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LabelPageMenuAdapter extends BaseAdapter {
	
	private Context mContext;
	private OnClickListener mThumbPicClick;
	private OnClickListener mDeleteBtnClick;
	private Object mCurrentItem;
	
	public LabelPageMenuAdapter(Context ctx) {
		mContext = ctx;
	}
	
	public void setCallback(OnClickListener thumbPicClick, OnClickListener delBtnClick) {
		mThumbPicClick = thumbPicClick;
		mDeleteBtnClick = delBtnClick;
	}
	
	public void setCurrentItem(Object obj) {
		if (mCurrentItem != obj) {
			mCurrentItem = obj;
			notifyDataSetChanged();
		}
	}
	
	@Override
	public int getCount() {
		return LabelPageManager.instance().get().size();		
	}
	
	@Override
	public Object getItem(int position) {
		return LabelPageManager.instance().get().get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.label_page_menu_item, null);
		}
		
		LabelPageItem item = (LabelPageItem)getItem(position);
		
		ImageView thumbPic = (ImageView)convertView.findViewById(R.id.page_thumb_imageView);
		if(item.mThumbBmp != null)
		{
			thumbPic.setImageBitmap(item.mThumbBmp);
		}else 
		{
			thumbPic.setImageResource(R.drawable.fail_drawable);
		}
		thumbPic.setOnClickListener(mThumbPicClick);
		thumbPic.setTag(item.mPageContentView);
		
		ImageView delBtn = (ImageView)convertView.findViewById(R.id.page_delete_imageView);
		delBtn.setOnClickListener(mDeleteBtnClick);
		delBtn.setTag(item.mPageContentView);
		
		TextView name = (TextView)convertView.findViewById(R.id.label_page_name_textView);
		name.setText(item.mName);
		
		ImageView boundImageView = (ImageView)convertView.findViewById(R.id.thumbKK);
		if (mCurrentItem == item.mPageContentView) {
			boundImageView.setVisibility(View.VISIBLE);
		}
		else {
			boundImageView.setVisibility(View.GONE);
		}
		
		return convertView;
	}	
}
