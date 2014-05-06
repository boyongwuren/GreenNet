package com.nd.pad.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class KeyCallItemVo extends BasePageVo implements Parcelable
{	
	//Ë³Ðò
	public int ordering = 0;

	public KeyCallItemVo()
	{
		
	}	
	
	public KeyCallItemVo(int id, int ordering, String picUrl,
			String infoString, String webUrl) {
		super();
		this.id = id;
		this.ordering = ordering;
		this.picUrl = picUrl;
		this.infoString = infoString;
		this.webUrl = webUrl;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getInfoString() {
		return infoString;
	}

	public void setInfoString(String infoString) {
		this.infoString = infoString;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(picUrl);  
        dest.writeString(infoString);  
        dest.writeString(webUrl);        
        dest.writeString(String.valueOf(id));
        dest.writeString(String.valueOf(ordering));
	}
	
	public static final Parcelable.Creator<KeyCallItemVo> CREATOR  = new Creator<KeyCallItemVo>()
	{           
        @Override  
        public KeyCallItemVo[] newArray(int size) {  
            return new KeyCallItemVo[size];  
        }
		@Override
		public KeyCallItemVo createFromParcel(Parcel source) {
			KeyCallItemVo vo  = new KeyCallItemVo();  
			vo.picUrl = source.readString();  
			vo.infoString= source.readString();  
			vo.webUrl = source.readString();
			vo.id =Integer.parseInt( source.readString());
			vo.ordering = Integer.parseInt(source.readString());
            return vo;  
		}  
    }; 
	
	public String toString()
	{
		return "id = "+id+" picUrl= "+picUrl+" infoString = "+infoString+" webUrl= "+webUrl;
	}

}
