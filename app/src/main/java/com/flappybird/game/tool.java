package com.flappybird.game;
import android.graphics.*;
import java.io.*;

public class tool
{
	public static Bitmap loadbitmap(String name)
	{
		Bitmap bmp=null;
		try
		{
			bmp=BitmapFactory.decodeStream(MainActivity.am.open(name));
		}
		catch (IOException e)
		{}
		return bmp;
	}
	public static Bitmap loadbitmap(String name,float w,float h)
	{
		Bitmap bmp=null;
		try
		{
			bmp=BitmapFactory.decodeStream(MainActivity.am.open(name));
		}
		catch (IOException e)
		{}
		return bigzd(bmp,w,h);
	}
	public static Bitmap bigzd(Bitmap bitmap,float na,float nb) {
		Matrix matrix = new Matrix();
		matrix.postScale(na/bitmap.getWidth(),nb/bitmap.getHeight()); 
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		//resizeBmp=big(resizeBmp,1080f/MainActivity.fblx);
		return resizeBmp;
	}
	public static float max(float a,float b)
	{
		if(a>b)return b;
		else return a;
	}
	public static float min(float a,float b)
	{
		if(a<b)return b;
		else return a;
	}
}
