package com.nd.pad.adapter;

import java.util.ArrayList;

import com.nd.pad.vo.BasePageVo;
import com.nd.pad.vo.KeyCallItemVo;
import com.nd.pad.GreenBrowser.R;
import com.nd.pad.GreenBrowser.util.ImageDownloader;
import com.nd.pad.GreenBrowser.util.SingleToolClass;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.TextView;

/**
 * 重点推荐的数据
 * @author Administrator
 *
 */
public class KeyCallGridViewAdapter extends BaseAdapter 
{

	/***
	 * 重点推荐的数据数组
	 */
	private BasePageVo[] keyCallItemVos;
	
	private Context context;

	private OnClickListener itemClickInterface;
	
	public KeyCallGridViewAdapter(Context context,BasePageVo[] keyCallItemVos,OnClickListener itemClickInterface)
	{
		// TODO Auto-generated constructor stub
		this.keyCallItemVos = keyCallItemVos;
		this.context = context;
		
		this.itemClickInterface = itemClickInterface;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(keyCallItemVos != null)
		{
			return keyCallItemVos.length;
		}else 
		{
			return 0;
		}
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		if(keyCallItemVos != null)
		{
			return keyCallItemVos[position];
		}else 
		{
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		if(convertView == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.key_call_item, null);
		}
		
		TextView textView = (TextView)convertView.findViewById(R.id.txt);
		TextView titleTxt = (TextView)convertView.findViewById(R.id.titleTxt);
		ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
		
		BasePageVo vo = keyCallItemVos[position];
		
		titleTxt.setText(vo.infoString);
		textView.setText(vo.webUrl);
		 
		SingleToolClass.imageDownloader.download(vo.picUrl, imageView, R.drawable.loading_drawable, R.drawable.fail_drawable, true);
		
		convertView.setTag(vo);
		convertView.setOnClickListener(itemClickInterface);
		
		return convertView;
	}
	
}
