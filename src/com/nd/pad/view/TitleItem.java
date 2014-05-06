package com.nd.pad.view;

import com.nd.pad.GreenBrowser.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleItem extends RelativeLayout
{

	/**
	 * 背景。选中未选中切换
	 */
	private ImageView titleBg;
	
	private ImageView webPic;

	/**
	 * 文本
	 */
	private TextView txt;

	/**
	 * 关闭按钮
	 */
	public ImageButton closeBtn;

    
	
	public TitleItem(Context context)
	{
		super(context);

		LayoutInflater.from(context).inflate(R.layout.title_item, this, true);
		
		titleBg = (ImageView) findViewById(R.id.titleBg);
		webPic = (ImageView) findViewById(R.id.webPic);
		txt = (TextView) findViewById(R.id.txt);
		closeBtn = (ImageButton) findViewById(R.id.closeBtn);
		
		setItemIsSelect(false);
	}

	public TitleItem(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TitleItem(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置title是否被选中
	 * 
	 * @param isSelect
	 */
	public void setItemIsSelect(Boolean isSelect)
	{
		titleBg.setSelected(isSelect);
		if (isSelect)
		{
			// 选中
			closeBtn.setVisibility(View.VISIBLE);
			 
		} else
		{
			// 未选中
			closeBtn.setVisibility(View.INVISIBLE);
		}
	}


	/**
	 * 设置标题的文字
	 * 
	 * @param txtString
	 */
	public void setTitleTxt(String txtString)
	{
		txt.setText(txtString);
	}
	
	/**
	 * 设置网页的图标
	 * @param icon
	 */
	public void setWebPic(Bitmap icon)
	{
		webPic.setImageBitmap(icon);
	}

}
