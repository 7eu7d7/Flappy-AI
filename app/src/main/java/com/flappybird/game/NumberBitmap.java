package com.flappybird.game;
import android.graphics.*;

public class NumberBitmap
{
	Bitmap[] bmp;
	Paint pai=new Paint();
	public NumberBitmap(Bitmap bit,int a)
	{
		bmp=BitmapRegion.split(bit,a,1);
	}
	public void draw(Canvas canvas,String a,float x,float y)
	{
		
		float p=x-((a.length()*bmp[0].getWidth())/2);
		for(int i=0;i<a.length();i++)
		{
			canvas.drawBitmap(bmp[a.charAt(i)-'0'],p,y,pai);
			p+=bmp[a.charAt(i)-'0'].getWidth();
		}
	}
}
