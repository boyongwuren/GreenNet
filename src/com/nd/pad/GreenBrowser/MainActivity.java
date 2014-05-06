package com.nd.pad.GreenBrowser;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nd.pad.constPackage.ConstName;
import com.nd.pad.interfacePackage.ChangeOftenVisitInterface;
import com.nd.pad.interfacePackage.ChangeTitleTxtInterface;
import com.nd.pad.interfacePackage.HandlerBtnInterface;
import com.nd.pad.interfacePackage.ViewPageChangeInterface;
import com.nd.pad.labelpage.LabelPageManager;
import com.nd.pad.labelpage.LabelPageMenuAdapter;
import com.nd.pad.view.TitleItem;
import com.nd.pad.view.ViewPagerContent;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.nd.pad.GreenBrowser.util.LogUtil;
import com.nd.pad.GreenBrowser.util.SingleToolClass;

public class MainActivity extends Activity
{
	/**
	 * 标签容器
	 */
	private AbsoluteLayout titleContent;

	/**
	 * 添加标签的按钮
	 */
	private ImageButton addPagerBtn;

	/**
	 * 进度条
	 */
	private ProgressBar loadProgressBar;

	/**
	 * 页面的容器
	 */
	private FrameLayout greenNetPageContent;

	/**
	 * 标签容器ArrayList
	 */
	private ArrayList<TitleItem> titleTextViewArrayList;

	/**
	 * 页面容器ArrayList
	 */
	private ArrayList<ViewPagerContent> greenNetPageArrayList;

	/**
	 * 右下角设置按钮
	 */
	private ImageButton settingImageButton;

	/***
	 * 底部按钮的容器
	 */
	private LinearLayout buttonContent;

	/**
	 * 控制按钮背景用的
	 */
	private RelativeLayout btnBgLayout;

	/**
	 * 设置按钮是否已经展开
	 */
	private Boolean btnIsOpen = true;

	private WindowManager windowManager;

	/**
	 * 标题的滚动控件
	 */
	private HorizontalScrollView scrollView;

	/**
	 * 当前的显示页面
	 */
	private int curIndex = -1;

	/**
	 * 当前标题
	 */
	private TitleItem curTitleItem = null;

	/**
	 * 当前页面
	 */
	private ViewPagerContent curGreenNetView = null;

	/**
	 * 标签页菜单栏
	 */
	private SlidingMenu labelPageMenu = null;

	/**
	 * 菜单列表数据适配器
	 */
	private LabelPageMenuAdapter mMenuAdapter;
	
	private TextView titleNumTxt;

	ImageButton homeBtn;

	ImageButton flushBtn;

	ImageButton collectBtn;

	ImageButton mangerBtn;

	ImageButton backBtn;

	ImageButton nextBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		SingleToolClass.content = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// 初始化标签页菜单
		initLabelPageMenu();

		titleTextViewArrayList = new ArrayList<TitleItem>();
		greenNetPageArrayList = new ArrayList<ViewPagerContent>();
		
		titleContent = (AbsoluteLayout) findViewById(R.id.titleContents);
		
		// 进度条
		loadProgressBar = (ProgressBar) findViewById(R.id.loadProgressBar);
		loadProgressBar.setVisibility(View.INVISIBLE);
		
		greenNetPageContent = (FrameLayout) findViewById(R.id.greenNetViewPager);
		btnBgLayout = (RelativeLayout) findViewById(R.id.btnContent);
		
		// 标题的滚动控件
		scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);

		// 添加标签页按钮
		addPagerBtn = (ImageButton) findViewById(R.id.addPagerBtn);
		addPagerBtn.setOnClickListener(new AddButtonClickListener());
		
		settingImageButton = (ImageButton) findViewById(R.id.settingBtn);
		settingImageButton.setOnClickListener(new SettingOnClickListener());
		
		buttonContent = (LinearLayout) findViewById(R.id.settingsBtnContent);
		buttonContent.setOnClickListener(new BtnContentOnClickListener());
		
		// 首页按钮
		homeBtn = (ImageButton) findViewById(R.id.home_btn);
		homeBtn.setOnClickListener(new BtnContentOnClickListener());
		homeBtn.setImageResource(R.drawable.home_btn_unable);
	
		// 刷新按钮
		flushBtn = (ImageButton) findViewById(R.id.flush_btn);
		flushBtn.setOnClickListener(new BtnContentOnClickListener());
		flushBtn.setImageResource(R.drawable.flush_btn_unable);
		 
		// 收藏按钮
		collectBtn = (ImageButton) findViewById(R.id.collect_btn);
		collectBtn.setOnClickListener(new BtnContentOnClickListener());
		collectBtn.setImageResource(R.drawable.collect_btn_unable);

		// 多窗口管理
		mangerBtn = (ImageButton) findViewById(R.id.manager_btn);
		mangerBtn.setOnClickListener(new BtnContentOnClickListener());

		// 上一页按钮
		backBtn = (ImageButton) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new BtnContentOnClickListener());
		backBtn.setImageResource(R.drawable.back_btn_unable);

		// 下一页按钮
		nextBtn = (ImageButton) findViewById(R.id.next_btn);
		nextBtn.setOnClickListener(new BtnContentOnClickListener());
		nextBtn.setImageResource(R.drawable.next_btn_unable);
		
		titleNumTxt = (TextView) findViewById(R.id.titleNumTxt);
		TextPaint tp = titleNumTxt.getPaint();   
        tp.setFakeBoldText(true);  

		
		// 默认添加一页
		addOnePager();
	}
	
	
	private class HandlerBtnClass implements HandlerBtnInterface
	{
		@Override
		public void setHomeBtnEnable(boolean isEnalle)
		{
			homeBtn.setEnabled(isEnalle);
			if(isEnalle)
			{
				homeBtn.setImageResource(R.drawable.home_btn);
			}else {
				homeBtn.setImageResource(R.drawable.home_btn_unable);
			}
		}

		@Override
		public void setFlushBtnEnable(boolean isEnalle)
		{
			flushBtn.setEnabled(isEnalle);
			
			if(isEnalle)
			{
				flushBtn.setImageResource(R.drawable.flush_btn);
			}else {
				flushBtn.setImageResource(R.drawable.flush_btn_unable);
			}
		}

		@Override
		public void setCollectBtnEnable(boolean isEnalle)
		{
			collectBtn.setEnabled(isEnalle);
			
			if(isEnalle)
			{
				collectBtn.setImageResource(R.drawable.collect_btn);
			}else {
				collectBtn.setImageResource(R.drawable.collect_btn_unable);
			}
		}

		@Override
		public void setBackBtnEnable(boolean isEnalle)
		{
			backBtn.setEnabled(isEnalle);
			
			if(isEnalle)
			{
				backBtn.setImageResource(R.drawable.back_btn);
			}else 
			{
				backBtn.setImageResource(R.drawable.back_btn_unable);
			}
		}

		@Override
		public void setNextBtnEnable(boolean isEnalle)
		{
			nextBtn.setEnabled(isEnalle);
			
			if(isEnalle)
			{
				nextBtn.setImageResource(R.drawable.next_btn);
			}else {
				nextBtn.setImageResource(R.drawable.next_btn_unable);
			}
		}
		
	}

	private void initLabelPageMenu()
	{
		// 删除所有标签数据
		LabelPageManager.instance().removeAll();

		labelPageMenu = new SlidingMenu(this);
		labelPageMenu.setMode(SlidingMenu.LEFT);
		labelPageMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		// labelPageMenu.setShadowWidthRes(R.dimen.shadow_width);
		// labelPageMenu.setShadowDrawable(R.drawable.shadow);
		labelPageMenu.setBehindWidthRes(R.dimen.sliding_menu_width);
		labelPageMenu.setFadeDegree(0.35f);
		labelPageMenu.setOnOpenListener(new OnOpenListener()
		{
			@Override
			public void onOpen()
			{				
				LabelPageManager.instance().add(curGreenNetView, ((ViewPagerContent) curGreenNetView).getWebViewTitle(), getPageThumbPic(curGreenNetView));
				mMenuAdapter.setCurrentItem(curGreenNetView);
				mMenuAdapter.notifyDataSetChanged();
			}
		});
		labelPageMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		View menuView = LayoutInflater.from(this).inflate(R.layout.label_page_menu, null);
		mMenuAdapter = new LabelPageMenuAdapter(this);
		mMenuAdapter.setCallback(new OnClickListener()
		{ // 切换标签页

			@Override
			public void onClick(View v) {
				labelPageMenu.toggle(true);  //隐藏标签菜单

				final int destIndex = greenNetPageArrayList.indexOf(v.getTag());
				
				if (curIndex == destIndex) { // 点击就是当前页
					return;
				}

				if (curIndex != -1) {
					onPageHide(greenNetPageArrayList.get(curIndex));
				}

				curIndex = destIndex;
				changeSelectItem();
				
				//切换页面，更新按钮状态
				curGreenNetView.updataBtnState();
				
			    scrollView.scrollTo(destIndex*ConstName.TITLE_WIDTH, 0);
				 
			}

		}, new OnClickListener()
		{ 
			// 删除标签
			@Override
			public void onClick(View v)
			{
				if (mMenuAdapter.getCount() <= 1) { // 隐藏标签菜单
					labelPageMenu.toggle(true);
				}

				closePage(greenNetPageArrayList.indexOf(v.getTag()));
			}
		});
		
		((ListView) menuView.findViewById(R.id.label_page_listView)).setAdapter(mMenuAdapter);
		labelPageMenu.setMenu(menuView);
	}

	// 即将关闭页面
	protected void onClosePage(View pageContentview)
	{
		LabelPageManager.instance().remove(pageContentview);
		mMenuAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		// 监听orientation|keyboard|screenSize，否则横竖屏变化后会销毁activity

		// 重新设置下菜单宽度
		labelPageMenu.setBehindWidthRes(R.dimen.sliding_menu_width);
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * 添加按钮被点击事件 添加一个标签页
	 * 
	 * @author Administrator
	 * 
	 */
	private class AddButtonClickListener implements OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			// 通知当前页面将被隐藏
			if (curIndex != -1) {
				onPageHide(greenNetPageArrayList.get(curIndex));
			}
			
			addOnePager();			
		}
	}

	/**
	 * 添加一个页面
	 */
	private void addOnePager()
	{
		
		if(greenNetPageArrayList != null && greenNetPageArrayList.size()>= ConstName.TITLE_BAR_TOTAL_NUM)
		{
			LogUtil.showTip("没有更多窗口");
			return;
		}
		
		loadProgressBar.setVisibility(View.INVISIBLE);

		hideAllGreenNetPage();

		TitleItem titleItem = new TitleItem(this);
		titleItem.closeBtn.setOnClickListener(titleCloseOnClick);

		titleItem.setItemIsSelect(true);
		AbsoluteLayout.LayoutParams lp = new AbsoluteLayout.LayoutParams(android.widget.AbsoluteLayout.LayoutParams.WRAP_CONTENT, android.widget.AbsoluteLayout.LayoutParams.WRAP_CONTENT, 0, 0);

		lp.x = (titleTextViewArrayList.size()) * ConstName.TITLE_WIDTH;
		titleItem.setLayoutParams(lp);
		titleItem.setTitleTxt(ConstName.VIEW_PAGER_TITLE[0]);
		titleItem.setOnClickListener(new TitleClickListener(titleItem));

		titleTextViewArrayList.add(titleItem);
		titleContent.addView(titleItem);
		
		ViewPagerContent greenNetView = new ViewPagerContent(this, changeTitleTxtClass,handlerBtnClass);
		greenNetView.setViewPageChangeInf(new ViewPageChangeClass(titleItem));
		greenNetView.setChangeOftenInterface(changeOftenVisitClass);
		greenNetView.progressBar = loadProgressBar;
		greenNetPageArrayList.add(greenNetView);
		greenNetPageContent.addView(greenNetView);

		scrollView.post(new Runnable()
		{
			@Override
			public void run()
			{
				scrollView.fullScroll(ScrollView.FOCUS_RIGHT);
			}
		});

		curIndex = titleTextViewArrayList.size() - 1;
		curGreenNetView = greenNetView;
		curTitleItem = titleItem;

		// 加到标签页中
		LabelPageManager.instance().add(greenNetView, "", null);
		mMenuAdapter.notifyDataSetChanged();
		
		titleNumTxt.setText(greenNetPageArrayList.size()+"");
	}
	
	private ChangeOftenVisitClass changeOftenVisitClass = new ChangeOftenVisitClass();
	
	/**
	 * 更新每一个table下面的 首页的经常访问界面
	 * @author Administrator
	 *
	 */
	private class ChangeOftenVisitClass implements ChangeOftenVisitInterface
	{

		@Override
		public void changeOftenVisit()
		{
			for (int i = 0; i < greenNetPageArrayList.size(); i++) 
			{
				ViewPagerContent vpc = greenNetPageArrayList.get(i);
				vpc.handlerFirstOftenVisit();
			}
		}
		
	}
	
	private HandlerBtnClass handlerBtnClass = new HandlerBtnClass();

	private TitleCloseOnClick titleCloseOnClick = new TitleCloseOnClick();

	/**
	 * 标题关闭事件
	 * 
	 * @author zmp
	 * 
	 */
	private class TitleCloseOnClick implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			ViewGroup parent = (ViewGroup) v.getParent().getParent();
			int index = titleTextViewArrayList.indexOf(parent);
			closePage(index);
		}
	}

	private void closePage(int index)
	{
		if (titleTextViewArrayList == null || titleTextViewArrayList.size() == 0 || titleTextViewArrayList.size() == 1) {
			return;
		}

		TitleItem titleItem = titleTextViewArrayList.get(index);
		ViewPagerContent viewPageContent = greenNetPageArrayList.get(index);

		// 通知即将关闭页面
		onClosePage(viewPageContent);

		titleContent.removeView(titleItem);
		titleTextViewArrayList.remove(index);

		greenNetPageArrayList.remove(index);
		greenNetPageContent.removeView(viewPageContent);

		curIndex = titleTextViewArrayList.size() - 1;
		changeSelectItem();
		
		titleNumTxt.setText(greenNetPageArrayList.size()+"");
	}

	private ChangeTitleTxtClass changeTitleTxtClass = new ChangeTitleTxtClass();

	/**
	 * 点击打开了网页，改变标题的文字
	 * 
	 * @author zmp
	 * 
	 */
	private class ChangeTitleTxtClass implements ChangeTitleTxtInterface
	{
		@Override
		public void changeTitleTxt(String title, ViewPagerContent view)
		{
			System.out.println("标题= " + title);
			int index = greenNetPageArrayList.indexOf(view);
			TitleItem titleItem = titleTextViewArrayList.get(index);
			if (title != null) {
				titleItem.setTitleTxt(title);
			} else {
				titleItem.setTitleTxt("找不到网页");
			}

		}

		@Override
		public void changeTitleWebPic(Bitmap bmp)
		{
			curTitleItem.setWebPic(bmp);
		}

	}

	/**
	 * 隐藏所有的GreenNet
	 */
	private void hideAllGreenNetPage()
	{
		if (titleTextViewArrayList != null) {
			for (int i = 0; i < titleTextViewArrayList.size(); i++) {
				TitleItem titleItem = titleTextViewArrayList.get(i);
				android.widget.AbsoluteLayout.LayoutParams lp = (android.widget.AbsoluteLayout.LayoutParams) titleItem.getLayoutParams();
				lp.x = i * ConstName.TITLE_WIDTH;
				titleItem.setLayoutParams(lp);
				titleItem.setItemIsSelect(false);
			}
		}

		if (greenNetPageArrayList != null) {
			for (int i = 0; i < greenNetPageArrayList.size(); i++) {
				ViewPagerContent greenNetView = greenNetPageArrayList.get(i);
				greenNetView.setVisibility(View.INVISIBLE);
			}
		}
	}

	/**
	 * 切换viewpager的操作
	 * 
	 * @author Administrator
	 * 
	 */
	private class ViewPageChangeClass implements ViewPageChangeInterface
	{
		private TitleItem titleItem;

		public ViewPageChangeClass(TitleItem tempTitleItem)
		{
			titleItem = tempTitleItem;
		}

		@Override
		public void viewPageChange(int curPage)
		{
			loadProgressBar.setVisibility(View.INVISIBLE);
			titleItem.setTitleTxt(ConstName.VIEW_PAGER_TITLE[curPage]);
		}
	}

	/**
	 * 标题被点击的事件
	 * 
	 * @author Administrator
	 * 
	 */
	private class TitleClickListener implements OnClickListener
	{
		private TitleItem titleItem;

		public TitleClickListener(TitleItem tempTitleItem)
		{
			titleItem = tempTitleItem;
		}

		@Override
		public void onClick(View v)
		{
			int destIndex = titleTextViewArrayList.indexOf(titleItem);
			if (curIndex != -1 && curIndex != destIndex) {
				onPageHide(greenNetPageArrayList.get(curIndex));
			}

			curIndex = destIndex;
			changeSelectItem();
			
			curGreenNetView.updataBtnState();
		}
	}

	/**
	 * 改变要显示的界面
	 */
	private void changeSelectItem()
	{
		curTitleItem = titleTextViewArrayList.get(curIndex);
		curGreenNetView = greenNetPageArrayList.get(curIndex);

		hideAllGreenNetPage();
		curTitleItem.setItemIsSelect(true);

		loadProgressBar.setVisibility(View.INVISIBLE);
		
		if (curGreenNetView != null)
		{
			curGreenNetView.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 隐藏/显示按钮被点击事件
	 * 
	 * @author Administrator
	 */
	private class SettingOnClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			// TranslateAnimation animation;
			if (btnIsOpen == true) {
				// 隐藏按钮
				// animation = new TranslateAnimation(0, 0,
				// 0,btnBgLayout.getLayoutParams().height);
				// animation.setInterpolator(new AnticipateInterpolator());
				btnBgLayout.setBackgroundResource(R.drawable.bottom_bg_down);
				settingImageButton.setImageResource(R.drawable.setting_up);
				buttonContent.setVisibility(View.INVISIBLE);
			} else {
				// 显示按钮
				// animation = new TranslateAnimation(0,
				// 0,btnBgLayout.getLayoutParams().height, 0);
				// animation.setInterpolator(new BounceInterpolator());
				btnBgLayout.setBackgroundResource(R.drawable.bottom_bg);
				settingImageButton.setImageResource(R.drawable.setting_down);
				buttonContent.setVisibility(View.VISIBLE);
			}

			btnIsOpen = !btnIsOpen;

			// animation.setAnimationListener(new GreenNetAnimationListener());
			// animation.setFillAfter(true);
			// animation.setDuration(1000);
			// buttonContent.startAnimation(animation);
		}
	}

	/**
	 * 按钮容器被点击了
	 * 
	 * @author Administrator
	 */
	private class BtnContentOnClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
				// home按钮被点击
				case R.id.home_btn:
					curPageGoBackBackHome();
					break;

				// 刷新按钮被点击
				case R.id.flush_btn:
					flushCurPage();
					break;

				// 页面回退
				case R.id.back_btn:
					curPageGoBack();
					break;

				// 页面前进
				case R.id.next_btn:
					curPageGoForword();
					break;

				// 收藏
				case R.id.collect_btn:
					collectCurPage();
					break;

				// 标签管理
				case R.id.manager_btn:
					onLabelPageManagerBtn();
					break;

				default:
					break;
			}
		}
	}

	private void onLabelPageManagerBtn()
	{
		labelPageMenu.toggle(true);
	}

	private void onPageHide(View pageContentView)
	{
		LabelPageManager.instance().add(pageContentView, ((ViewPagerContent) pageContentView).getWebViewTitle(), getPageThumbPic(pageContentView));
		mMenuAdapter.notifyDataSetChanged();
	}

	private Bitmap getPageThumbPic(View pageContentView)
	{		
		try 
		{			
			Bitmap bitmap = Bitmap.createBitmap((int)getResources().getDimension(R.dimen.sliding_menu_thumbPic_width)*2, (int)getResources().getDimension(R.dimen.sliding_menu_thumbPic_height)*2, Bitmap.Config.ARGB_8888);
			pageContentView.draw(new Canvas(bitmap));
			return bitmap;
			
//			Bitmap thumbPic = ThumbnailUtils.extractThumbnail(bitmap, (int)getResources().getDimension(R.dimen.sliding_menu_thumbPic_width), (int)getResources().getDimension(R.dimen.sliding_menu_thumbPic_height));
//			if (!bitmap.isRecycled()) {
//				bitmap.recycle();
//				bitmap = null;
//			}			
//			
//			return thumbPic;
			
		} catch (Exception e) 
		{
			return null;
		}
	}

	/**
	 * home按钮被点击 当前页面切回首页
	 */
	private void curPageGoBackBackHome()
	{
		if (curGreenNetView != null) {
			curGreenNetView.goBackHomePage();
		}
	}

	/**
	 * 刷新当前页面
	 */
	private void flushCurPage()
	{
		if (curGreenNetView != null) {
			curGreenNetView.flushWebView();
		}
	}


	/**
	 * 当前页面 webView回退
	 */
	private void curPageGoBack()
	{
		if (curGreenNetView != null) {
			curGreenNetView.goBack();
		}
	}

	/**
	 * 当前页面 webView回退
	 */
	private void curPageGoForword()
	{
		if (curGreenNetView != null) {
			curGreenNetView.goForword();
		}
	}

	/**
	 * 收藏当前页面
	 */
	private void collectCurPage()
	{
		if (curGreenNetView != null) {
			curGreenNetView.collectCurPage();
		}
	}

	@Override
	public void onBackPressed()
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	private class GreenNetAnimationListener implements AnimationListener
	{
		@Override
		public void onAnimationStart(Animation animation)
		{
			if (btnIsOpen == true) {
				buttonContent.setVisibility(View.VISIBLE);
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation)
		{

		}

		@Override
		public void onAnimationEnd(Animation animation)
		{
			buttonContent.clearAnimation();
			if (btnIsOpen == false) {
				// 隐藏按钮
				buttonContent.setVisibility(View.INVISIBLE);
				FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) buttonContent.getLayoutParams();
				// layoutParams.setMargins(left, top, right, bottom);
				buttonContent.setLayoutParams(layoutParams);
			} else {
				// 显示按钮了

			}
		}
	};
}
