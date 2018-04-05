package com.flappybird.game;

import android.graphics.*;
import android.view.*;

public class Image
{
	float x,y,w,h,alpha=255;
	int time,type;
	float vx,vy,dalpha;
	Paint paint;
	Bitmap bit;
	ClickListener cl=null;
	boolean isclick;
	public Image(){
		paint=new Paint();
	}
	public Image(float x,float y,float w,float h)
	{
		this.x=x;this.y=y;this.w=w;this.h=h;
		paint=new Paint();
	}
	public void settype(int t)
	{type=t;}
	public void setbitmap_fit(Bitmap bit)
	{
		this.bit=bit;
		this.w=bit.getWidth();
		this.h=bit.getHeight();
	}
	public void setbitmap(Bitmap bit)
	{
		this.bit=big(bit,w/bit.getWidth(),h/bit.getHeight());
	}
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(bit,x,y,paint);
		if(time>0)
		{
			x+=vx;y+=vy;
			alpha-=dalpha;
			paint.setAlpha((int)alpha);
			time-=20;
		}
	}
	public void moveto(float dx,float dy,int time)
	{
		vx=(dx-x)/(time/20f);
		vy=(dy-y)/(time/20f);
		dalpha=255f/(time/20f);
		this.time=time;
	}
	public void setclick(ClickListener c)
	{cl=c;}
	interface ClickListener
	{public void onclick()}
	public void click(MotionEvent event,int u)
	{
		//if(event.getAction()==MotionEvent.ACTION_DOWN)
		if(碰撞(event.getX(u)/(MainActivity.fblx/1920f),event.getY(u)/(MainActivity.fbly/1080f),x,y,x+w,y+h))
		{isclick=true;}
	}
	public void click(float a,float b)
	{
		//if(event.getAction()==MotionEvent.ACTION_DOWN)
		if(碰撞(a,b,x,y,x+w,y+h))
		{isclick=true;}
	}
	public Bitmap big(Bitmap bitmap,float na,float nb) {
		Matrix matrix = new Matrix();
		matrix.postScale(na,nb); 
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		return resizeBmp;
	}
	public void setposition(float x,float y)
	{
		this.x=x;this.y=y;
	}
	public void setposition_center(float x,float y)
	{
		this.x=x-w/2;this.y=y-h/2;
	}
	public boolean 碰撞(float ox,float oy,float px,float py,float px1,float py1)
	{

		if(ox<px)
			return false;
		else if(ox>px1)
			return false;
		else if(oy<py)
			return false;
		else if(oy>py1)
			return false;
		else
			return true;
	}
	public boolean 碰撞(float ox,float oy,int r)
	{

		if(ox+r<x)
			return false;
		else if(ox-r>x+w)
			return false;
		else if(oy+r<y)
			return false;
		else if(oy-r>y+h)
			return false;
		else
			return true;
	}
}
