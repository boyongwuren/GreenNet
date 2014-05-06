package com.nd.pad.view.myCollect;

import com.nd.pad.interfacePackage.OpenWebViewInterface;
import com.nd.pad.interfacePackage.ShowCloseBtnInterface;
import com.nd.pad.vo.CollectVo;
import com.nd.pad.vo.sqlDataBase.HandlerCollectTable;
import com.nd.pad.GreenBrowser.R;
import com.nd.pad.GreenBrowser.util.SingleToolClass;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 收藏页面的 每一个item
 * @author zmp
 *
 */
public class CollectItemView extends LinearLayout
{
	private ImageButton closeBtn;
	
	private ImageView webPic;
	
	private TextView urlTxt;
	
	private TextView infoTxt;
	
	private GestureDetector gestureDetector;
	
	private ShowCloseBtnInterface showCloseBtnInterface;
	
	private OpenWebViewInterface onClickListener;
	
	private CollectVo vo;
	
	public CollectItemView(Context context,ShowCloseBtnInterface showCloseBtnInterface,OpenWebViewInterface itemClickInterface)
	{
		super(context);
   
		LayoutInflater.from(context).inflate(R.layout.collect_item, this, true);
 
		this.onClickListener = itemClickInterface;
		
		closeBtn = (ImageButton) findViewById(R.id.closeBtn);
		webPic = (ImageView) findViewById(R.id.webPic);
		urlTxt = (TextView) findViewById(R.id.urlTxt);
		infoTxt = (TextView) findViewById(R.id.infoTxt);
		
		closeBtn.setVisibility(View.INVISIBLE);
		closeBtn.setOnClickListener(new CloseBtnClickClass());
		
		gestureDetector = new GestureDetector(context, new OnGestureClass());
		
		this.setOnTouchListener(new OnTouchClass());
		
		this.showCloseBtnInterface = showCloseBtnInterface;
	}
	
	public CollectItemView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CollectItemView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 设置收藏item的数据
	 * @param vo
	 */
	public void setCollectVo(CollectVo vo)
	{
		this.vo = vo;
		urlTxt.setText(vo.webUrl);
		infoTxt.setText(vo.infoString);

		SingleToolClass.imageDownloader.download(vo.picUrl, webPic, R.drawable.fail_drawable, R.drawable.fail_drawable, true);
	}

	/**
	 * 设置关闭按钮是否显示
	 * @param b
	 */
	public void setIsShowCloseBtn(boolean b)
	{
		if(b)
		{
			closeBtn.setVisibility(View.VISIBLE);
		}else
		{
			closeBtn.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * 关闭按钮
	 * @author Administrator
	 *
	 */
	private class CloseBtnClickClass implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			HandlerCollectTable.deleteDataFromTable(vo.id);
		}
	}
	
	/**
	 * 触摸事件
	 * @author zmp
	 *
	 */
	private class OnTouchClass implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			gestureDetector.onTouchEvent(event);
			
			return true;
		}
		
	}
	
	/**
	 * 手势
	 * @author zmp
	 *
	 */
	private class OnGestureClass implements OnGestureListener
	{

		@Override
		public boolean onDown(MotionEvent e)
		{
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e)
		{
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e)
		{
			onClickListener.openWebView(vo);
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
		{
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e)
		{
			 if(showCloseBtnInterface != null&&closeBtn.getVisibility() == View.INVISIBLE)
			 {
				 showCloseBtnInterface.showCloseBtn();
			 }
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
		{
			return false;
		}
		
	}
}
