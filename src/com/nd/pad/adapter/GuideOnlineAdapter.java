package com.nd.pad.adapter;

import java.util.ArrayList;
import com.nd.pad.view.guideOnlie.GuideOnlineLittle;
import com.nd.pad.vo.CategoryVo;
import com.nd.pad.GreenBrowser.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GuideOnlineAdapter extends BaseExpandableListAdapter {

	private Context context;
	
	private OnClickListener itemClickInterface;
	
	private ArrayList<CategoryVo> categoryList;
	
	public GuideOnlineAdapter(Context context , ArrayList<CategoryVo> groupTitle,OnClickListener itemClickInterface) 
	{
		this.context = context;
		this.itemClickInterface = itemClickInterface;
		this.categoryList = groupTitle;	
	}
	
	/**
	 * 得到大组成员的总数
	 */
	@Override
	public int getGroupCount() 
	{
		return categoryList.size();
	}

	/**
	 * 得到大组成员名称
	 */
	@Override
	public Object getGroup(int groupPosition) {
		return categoryList.get(groupPosition).name;
	}
	
	/**
	 * 得到大组成员id
	 */
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	
	/**
	 * 得到大组成员的view   
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) 
	{
		TextView titleTxtTextView;
		ImageView arrowImageView;
		ImageView bg;

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.guide_online_big_group, null);
			convertView.setBackgroundResource(android.R.color.transparent);
		}

		titleTxtTextView = (TextView) convertView.findViewById(R.id.titleTxt);
		arrowImageView = (ImageView) convertView.findViewById(R.id.arrowImage);
		bg = (ImageView) convertView.findViewById(R.id.bg);

		if (isExpanded) {
			// 大组展开
			arrowImageView.setImageResource(R.drawable.down_arrow);
			bg.setImageResource(R.drawable.guide_online_item_open);

		} else {
			arrowImageView.setImageResource(R.drawable.right_arrow);
			bg.setImageResource(R.drawable.guide_online_item_bg);
		}

		CategoryVo vo = categoryList.get(groupPosition);

		titleTxtTextView.setText(vo.name);

		return convertView;
	}
	//-----------------------------------------------------------------------------------------------
	
	@Override
	public Object getChild(int groupPosition, int childPosition) 
	{
		return null;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) 
	{
		return 1;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent) 
	{
		CategoryVo vo = categoryList.get(groupPosition);

		if (convertView == null) {
			convertView = new GuideOnlineLittle(context, vo.id,
					itemClickInterface);
		} else {
			((GuideOnlineLittle) convertView).updataData(vo.id);
		}

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) 
	{
		return true;
	}

}
