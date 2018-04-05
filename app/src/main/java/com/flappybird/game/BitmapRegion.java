package com.flappybird.game;
import android.graphics.*;

public class BitmapRegion
{
	public static Bitmap region(Bitmap bit,int X,int Y,int W,int H)
	{
		return Bitmap.createBitmap(bit,X,Y,W,H,null,false);
	}
	public static Bitmap region(Bitmap bit,int W,int H)
	{
		return Bitmap.createBitmap(bit,0,0,W,H,null,false);
	}
	public static Bitmap[] split(Bitmap bit,int W,int H)
	{
		int p=0,w=bit.getWidth()/W,h=bit.getHeight()/H;
		Bitmap[] re=new Bitmap[W*H];
		for(int i=0;i<H;i++)
		{
			for(int u=0;u<W;u++)
			{
				re[p]=region(bit,u*w,i*h,w,h);
				p++;
			}
		}
		return re;
		//return Bitmap.createBitmap(bit,0,0,W,H,null,false);
	}
}
