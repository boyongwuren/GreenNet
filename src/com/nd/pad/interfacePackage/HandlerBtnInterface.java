package com.nd.pad.interfacePackage;

public interface HandlerBtnInterface
{
	/**
	 * 主页按钮是否可用
	 * @param isEnalle
	 */
       public void setHomeBtnEnable(boolean isEnalle);

       /**
        * 刷新按钮是否可用
        * @param isEnalle
        */
       public void setFlushBtnEnable(boolean isEnalle);
       

       /**
        * 收藏按钮是否可用
        * @param isEnalle
        */
       public void setCollectBtnEnable(boolean isEnalle);

       /**
        * 返回按钮是否可用
        * @param isEnalle
        */
       public void setBackBtnEnable(boolean isEnalle);

       /**
        * 前进按钮是否可用
        * @param isEnalle
        */
       public void setNextBtnEnable(boolean isEnalle);
       
       
}
