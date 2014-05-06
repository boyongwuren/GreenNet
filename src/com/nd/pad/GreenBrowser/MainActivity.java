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
	 * ��ǩ����
	 */
	private AbsoluteLayout titleContent;

	/**
	 * ��ӱ�ǩ�İ�ť
	 */
	private ImageButton addPagerBtn;

	/**
	 * ������
	 */
	private ProgressBar loadProgressBar;

	/**
	 * ҳ�������
	 */
	private FrameLayout greenNetPageContent;

	/**
	 * ��ǩ����ArrayList
	 */
	private ArrayList<TitleItem> titleTextViewArrayList;

	/**
	 * ҳ������ArrayList
	 */
	private ArrayList<ViewPagerContent> greenNetPageArrayList;

	/**
	 * ���½����ð�ť
	 */
	private ImageButton settingImageButton;

	/***
	 * �ײ���ť������
	 */
	private LinearLayout buttonContent;

	/**
	 * ���ư�ť�����õ�
	 */
	private RelativeLayout btnBgLayout;

	/**
	 * ���ð�ť�Ƿ��Ѿ�չ��
	 */
	private Boolean btnIsOpen = true;

	private WindowManager windowManager;

	/**
	 * ����Ĺ����ؼ�
	 */
	private HorizontalScrollView scrollView;

	/**
	 * ��ǰ����ʾҳ��
	 */
	private int curIndex = -1;

	/**
	 * ��ǰ����
	 */
	private TitleItem curTitleItem = null;

	/**
	 * ��ǰҳ��
	 */
	private ViewPagerContent curGreenNetView = null;

	/**
	 * ��ǩҳ�˵���
	 */
	private SlidingMenu labelPageMenu = null;

	/**
	 * �˵��б�����������
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

		// ��ʼ����ǩҳ�˵�
		initLabelPageMenu();

		titleTextViewArrayList = new ArrayList<TitleItem>();
		greenNetPageArrayList = new ArrayList<ViewPagerContent>();
		
		titleContent = (AbsoluteLayout) findViewById(R.id.titleContents);
		
		// ������
		loadProgressBar = (ProgressBar) findViewById(R.id.loadProgressBar);
		loadProgressBar.setVisibility(View.INVISIBLE);
		
		greenNetPageContent = (FrameLayout) findViewById(R.id.greenNetViewPager);
		btnBgLayout = (RelativeLayout) findViewById(R.id.btnContent);
		
		// ����Ĺ����ؼ�
		scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);

		// ��ӱ�ǩҳ��ť
		addPagerBtn = (ImageButton) findViewById(R.id.addPagerBtn);
		addPagerBtn.setOnClickListener(new AddButtonClickListener());
		
		settingImageButton = (ImageButton) findViewById(R.id.settingBtn);
		settingImageButton.setOnClickListener(new SettingOnClickListener());
		
		buttonContent = (LinearLayout) findViewById(R.id.settingsBtnContent);
		buttonContent.setOnClickListener(new BtnContentOnClickListener());
		
		// ��ҳ��ť
		homeBtn = (ImageButton) findViewById(R.id.home_btn);
		homeBtn.setOnClickListener(new BtnContentOnClickListener());
		homeBtn.setImageResource(R.drawable.home_btn_unable);
	
		// ˢ�°�ť
		flushBtn = (ImageButton) findViewById(R.id.flush_btn);
		flushBtn.setOnClickListener(new BtnContentOnClickListener());
		flushBtn.setImageResource(R.drawable.flush_btn_unable);
		 
		// �ղذ�ť
		collectBtn = (ImageButton) findViewById(R.id.collect_btn);
		collectBtn.setOnClickListener(new BtnContentOnClickListener());
		collectBtn.setImageResource(R.drawable.collect_btn_unable);

		// �ര�ڹ���
		mangerBtn = (ImageButton) findViewById(R.id.manager_btn);
		mangerBtn.setOnClickListener(new BtnContentOnClickListener());

		// ��һҳ��ť
		backBtn = (ImageButton) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new BtnContentOnClickListener());
		backBtn.setImageResource(R.drawable.back_btn_unable);

		// ��һҳ��ť
		nextBtn = (ImageButton) findViewById(R.id.next_btn);
		nextBtn.setOnClickListener(new BtnContentOnClickListener());
		nextBtn.setImageResource(R.drawable.next_btn_unable);
		
		titleNumTxt = (TextView) findViewById(R.id.titleNumTxt);
		TextPaint tp = titleNumTxt.getPaint();   
        tp.setFakeBoldText(true);  

		
		// Ĭ�����һҳ
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
		// ɾ�����б�ǩ����
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
		{ // �л���ǩҳ

			@Override
			public void onClick(View v) {
				labelPageMenu.toggle(true);  //���ر�ǩ�˵�

				final int destIndex = greenNetPageArrayList.indexOf(v.getTag());
				
				if (curIndex == destIndex) { // ������ǵ�ǰҳ
					return;
				}

				if (curIndex != -1) {
					onPageHide(greenNetPageArrayList.get(curIndex));
				}

				curIndex = destIndex;
				changeSelectItem();
				
				//�л�ҳ�棬���°�ť״̬
				curGreenNetView.updataBtnState();
				
			    scrollView.scrollTo(destIndex*ConstName.TITLE_WIDTH, 0);
				 
			}

		}, new OnClickListener()
		{ 
			// ɾ����ǩ
			@Override
			public void onClick(View v)
			{
				if (mMenuAdapter.getCount() <= 1) { // ���ر�ǩ�˵�
					labelPageMenu.toggle(true);
				}

				closePage(greenNetPageArrayList.indexOf(v.getTag()));
			}
		});
		
		((ListView) menuView.findViewById(R.id.label_page_listView)).setAdapter(mMenuAdapter);
		labelPageMenu.setMenu(menuView);
	}

	// �����ر�ҳ��
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
		// ����orientation|keyboard|screenSize������������仯�������activity

		// ���������²˵����
		labelPageMenu.setBehindWidthRes(R.dimen.sliding_menu_width);
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * ��Ӱ�ť������¼� ���һ����ǩҳ
	 * 
	 * @author Administrator
	 * 
	 */
	private class AddButtonClickListener implements OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			// ֪ͨ��ǰҳ�潫������
			if (curIndex != -1) {
				onPageHide(greenNetPageArrayList.get(curIndex));
			}
			
			addOnePager();			
		}
	}

	/**
	 * ���һ��ҳ��
	 */
	private void addOnePager()
	{
		
		if(greenNetPageArrayList != null && greenNetPageArrayList.size()>= ConstName.TITLE_BAR_TOTAL_NUM)
		{
			LogUtil.showTip("û�и��ര��");
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

		// �ӵ���ǩҳ��
		LabelPageManager.instance().add(greenNetView, "", null);
		mMenuAdapter.notifyDataSetChanged();
		
		titleNumTxt.setText(greenNetPageArrayList.size()+"");
	}
	
	private ChangeOftenVisitClass changeOftenVisitClass = new ChangeOftenVisitClass();
	
	/**
	 * ����ÿһ��table����� ��ҳ�ľ������ʽ���
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
	 * ����ر��¼�
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

		// ֪ͨ�����ر�ҳ��
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
	 * ���������ҳ���ı���������
	 * 
	 * @author zmp
	 * 
	 */
	private class ChangeTitleTxtClass implements ChangeTitleTxtInterface
	{
		@Override
		public void changeTitleTxt(String title, ViewPagerContent view)
		{
			System.out.println("����= " + title);
			int index = greenNetPageArrayList.indexOf(view);
			TitleItem titleItem = titleTextViewArrayList.get(index);
			if (title != null) {
				titleItem.setTitleTxt(title);
			} else {
				titleItem.setTitleTxt("�Ҳ�����ҳ");
			}

		}

		@Override
		public void changeTitleWebPic(Bitmap bmp)
		{
			curTitleItem.setWebPic(bmp);
		}

	}

	/**
	 * �������е�GreenNet
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
	 * �л�viewpager�Ĳ���
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
	 * ���ⱻ������¼�
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
	 * �ı�Ҫ��ʾ�Ľ���
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
	 * ����/��ʾ��ť������¼�
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
				// ���ذ�ť
				// animation = new TranslateAnimation(0, 0,
				// 0,btnBgLayout.getLayoutParams().height);
				// animation.setInterpolator(new AnticipateInterpolator());
				btnBgLayout.setBackgroundResource(R.drawable.bottom_bg_down);
				settingImageButton.setImageResource(R.drawable.setting_up);
				buttonContent.setVisibility(View.INVISIBLE);
			} else {
				// ��ʾ��ť
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
	 * ��ť�����������
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
				// home��ť�����
				case R.id.home_btn:
					curPageGoBackBackHome();
					break;

				// ˢ�°�ť�����
				case R.id.flush_btn:
					flushCurPage();
					break;

				// ҳ�����
				case R.id.back_btn:
					curPageGoBack();
					break;

				// ҳ��ǰ��
				case R.id.next_btn:
					curPageGoForword();
					break;

				// �ղ�
				case R.id.collect_btn:
					collectCurPage();
					break;

				// ��ǩ����
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
	 * home��ť����� ��ǰҳ���л���ҳ
	 */
	private void curPageGoBackBackHome()
	{
		if (curGreenNetView != null) {
			curGreenNetView.goBackHomePage();
		}
	}

	/**
	 * ˢ�µ�ǰҳ��
	 */
	private void flushCurPage()
	{
		if (curGreenNetView != null) {
			curGreenNetView.flushWebView();
		}
	}


	/**
	 * ��ǰҳ�� webView����
	 */
	private void curPageGoBack()
	{
		if (curGreenNetView != null) {
			curGreenNetView.goBack();
		}
	}

	/**
	 * ��ǰҳ�� webView����
	 */
	private void curPageGoForword()
	{
		if (curGreenNetView != null) {
			curGreenNetView.goForword();
		}
	}

	/**
	 * �ղص�ǰҳ��
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
				// ���ذ�ť
				buttonContent.setVisibility(View.INVISIBLE);
				FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) buttonContent.getLayoutParams();
				// layoutParams.setMargins(left, top, right, bottom);
				buttonContent.setLayoutParams(layoutParams);
			} else {
				// ��ʾ��ť��

			}
		}
	};
}
