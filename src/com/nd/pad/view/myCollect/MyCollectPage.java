package com.nd.pad.view.myCollect;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nd.pad.adapter.CollectGridViewAdapter;
import com.nd.pad.interfacePackage.OpenWebViewInterface;
import com.nd.pad.interfacePackage.ShowCloseBtnInterface;
import com.nd.pad.vo.CollectVo;
import com.nd.pad.vo.sqlDataBase.HandlerCollectTable;
import com.nd.pad.GreenBrowser.R;

/**
 * 我的收藏页面
 * @author Administrator
 *
 */
public class MyCollectPage extends LinearLayout 
{
	private GridView gridView;
	
	private ArrayList<CollectVo> cVos;
	
	private RelativeLayout noCollectContent;
	
	private ShowCloseBtnClass showCloseBtnClass;
	
	private OpenWebViewInterface itemClickInterface;
	
	public MyCollectPage(Context context,OpenWebViewInterface itemClickInterface)
	{
		super(context);
		LayoutInflater.from(context).inflate(R.layout.my_collect, this,true);
		
		this.itemClickInterface = itemClickInterface;
		
		gridView = (GridView)findViewById(R.id.gridView);
		noCollectContent = (RelativeLayout) findViewById(R.id.noCollectPic);
		
		cVos = HandlerCollectTable.getDataFromTable();
		
		if(cVos == null || cVos.size() == 0)
		{
			noCollectContent.setVisibility(View.VISIBLE);
			gridView.setVisibility(View.INVISIBLE);
		}else 
		{
			noCollectContent.setVisibility(View.INVISIBLE);
			gridView.setVisibility(View.VISIBLE);
		}
		
		showCloseBtnClass = new ShowCloseBtnClass();
		
		gridView.setAdapter(new CollectGridViewAdapter(cVos,showCloseBtnClass,itemClickInterface));
		
		HandlerCollectTable.showCloseBtnInterface = showCloseBtnClass;
		
		gridView.setOnTouchListener(new ThisOnClickListener());
		
	}
	
	private class ThisOnClickListener implements OnTouchListener
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			if(closeBtnShow)
			{
				closeBtnShow = false;
				int len = gridView.getChildCount();
				for (int i = 0; i < len; i++) 
				{
					CollectItemView collectItemView = (CollectItemView) gridView.getChildAt(i);
					collectItemView.setIsShowCloseBtn(false);
					
					collectItemView.clearAnimation();
				}
			} 
			return false;
		}
		
	}
	

	public MyCollectPage(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
	}

	public MyCollectPage(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	private boolean closeBtnShow = false;
	/**
	 * 显示所有的 collectItemView关闭按钮
	 * @author zmp
	 *
	 */
	private class ShowCloseBtnClass implements ShowCloseBtnInterface
	{

		@Override
		public void showCloseBtn()
		{
			// TODO Auto-generated method stub
			int len = gridView.getChildCount();
			for (int i = 0; i < len; i++) 
			{
				closeBtnShow = true;
				CollectItemView collectItemView = (CollectItemView) gridView.getChildAt(i);
				collectItemView.setIsShowCloseBtn(true);
				
				
				
				final TranslateAnimation anim = new TranslateAnimation(0, 2, 0, 0);
				anim.setInterpolator(new CycleInterpolator(50000f));
				anim.setDuration(300000);
				
				collectItemView.startAnimation(anim);
			}
		}

		@Override
		public void updataData()
		{
			cVos = HandlerCollectTable.getDataFromTable();
			if(cVos == null || cVos.size() == 0)
			{
				noCollectContent.setVisibility(View.VISIBLE);
				gridView.setVisibility(View.INVISIBLE);
			}else 
			{
				noCollectContent.setVisibility(View.INVISIBLE);
				gridView.setVisibility(View.VISIBLE);
			}
			
			//更新数据
			gridView.setAdapter(new CollectGridViewAdapter(cVos,showCloseBtnClass,itemClickInterface));
		}
		
	}

}
