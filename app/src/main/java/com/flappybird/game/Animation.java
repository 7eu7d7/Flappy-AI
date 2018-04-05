package com.flappybird.game;

import android.graphics.*;

public class Animation
{
	float x,y,w,h,ztime=0,intime=0;
	float vx,vy;
	Bitmap[] bmp;
	Paint paint;
	int time;
	boolean flag=true,isloop;
	public Animation(float x,float y,float w,float h)
	{
		this.x=x;this.y=y;this.w=w;this.h=h;
		paint=new Paint();
	}
	public void inct(int t)
	{
		if(flag)
		{
			intime+=t;
			if(intime>=ztime)
			{
				if(isloop)
				intime%=ztime;
				else
				flag=false;
			}
		}
	}
	public void setloop(boolean p){isloop=p;}
	public void setT(int t){ztime=t;}
	public void setbitmaps(Bitmap[] bit){bmp=bit;}
	public void start(){intime=0;flag=true;}
	public void stop(){flag=false;}
	public void setposition(float x,float y)
	{
		this.x=x;this.y=y;
	}
	public void setposition_center(float x,float y)
	{
		this.x=x-w/2;this.y=y-h/2;
	}
	public void setbitmaps(Bitmap[] bit,int start,int end){
		bmp=new Bitmap[end-start];
		for(int i=start;i<end;i++)bmp[i-start]=bit[i];
	}
	
	public void draw(Canvas canvas)
	{
		if(flag){
		canvas.drawBitmap(bmp[(int)(bmp.length*(intime/ztime))],new Rect(0,0,bmp[0].getWidth(),bmp[0].getHeight()),new RectF(x,y,w+x,h+y),paint);
		if(time>0)
		{
			x+=vx;y+=vy;
			time-=20;
		}
		}
	}
	public void moveto(int dx,int dy,int time)
	{
		vx=(dx-x)/(time/20f);
		vy=(dy-y)/(time/20f);
		this.time=time;
	}
}
