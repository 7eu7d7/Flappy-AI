package com.flappybird.game;

import android.graphics.*;
import android.view.*;
import java.io.*;
import java.util.*;

public class di1//614
{
	int angle=0;
	final int v=4;
	float vy=0;
	float x=400,y=960,aiw=150,aih=150;
	final float[] points={41-aiw/2,16-aih/2,98-aiw/2,16-aih/2,120-aiw/2,150-aih/2,24-aiw/2,150-aih/2};
	Animation an;
	Vector<point> ve=new Vector<point>();
	Bitmap hj,qb,jz;
	Paint paint;
	NumberBitmap nb;
	int fs;
	boolean start;
	Image iq=new Image();
	
	public di1()
	{
		paint=new Paint();
		paint.setColor(0xff66ccff);
		paint.setTextSize(80);
		Bitmap dh = null,num=null;
		dh=tool.loadbitmap("25.png",1024,1024);
		hj=tool.loadbitmap("ai.png",aiw,aih);
		qb=tool.loadbitmap("qb.png",200,1920);
		iq.setbitmap_fit(tool.loadbitmap("IQ-1.png",279,100));
		jz=BitmapRegion.region(tool.loadbitmap("IQ-1.png",279,100),);
		num=tool.loadbitmap("number.png");
		an=new Animation(300, 400, 256, 256);
		an.setbitmaps(BitmapRegion.split(dh,4,4));
		an.setT(640);
		an.stop();
		
		nb=new NumberBitmap(num,11);
		ve.add(new point(500+600,(int)(Math.random()*1400)));
		ve.add(new point(500+1200,(int)(Math.random()*1400)));
		ve.add(new point(500+1800,(int)(Math.random()*1400)));
	}
	public void reset(){
		angle=0;vy=0;
		x=400;y=960;
		fs=0;
		start=false;
		ve=new Vector<point>();
		ve.add(new point(500+600,(int)(Math.random()*1400)));
		ve.add(new point(500+1200,(int)(Math.random()*1400)));
		ve.add(new point(500+1800,(int)(Math.random()*1400)));
	}
	
	public void draw(Canvas canvas)
	{
		for(int i=0;i<ve.size();i++)
		{
			canvas.drawBitmap(qb,ve.get(i).x,ve.get(i).y-1920,paint);
			canvas.drawBitmap(qb,ve.get(i).x,ve.get(i).y+400,paint);
		}
		canvas.save();
		canvas.rotate(angle,x,y);
		canvas.drawBitmap(hj,x-aiw/2,y-aih/2,paint);
		canvas.restore();
		
		an.draw(canvas);
		if(iq.time>0)iq.draw(canvas);
		nb.draw(canvas,""+fs,540,200);
		if(!start)
		canvas.drawText("点击开始",500,1000,paint);
	}
	public boolean touch(MotionEvent event)
	{
		if(event.getAction()==MotionEvent.ACTION_DOWN)
		{
			if(!start)start=true;
			vy=-16;
			return true;
		}
		return false;
	}
	public void poi()
	{
		angle+=2;
		if(!start)return;
		vy+=0.7f;
		y+=vy;
		vy=tool.max(vy,30);
		if(y<-aih/2)y=MainActivity.fbly-aih/2;
		if(y>MainActivity.fbly-aih/2)y=-aih/2;
		an.inct(20);
		for(int i=0;i<ve.size();i++)
		{
			point pp=ve.get(i);
			float[] vex=roate(0,0,points.clone(),(float)Math.toRadians(angle));
			for(int u=0;u<vex.length;u+=2)
			if(碰撞(vex[u]+x,vex[u+1]+y,pp.x,0,pp.x+150,pp.y)||碰撞(vex[u]+x,vex[u+1]+y,pp.x,pp.y+400,pp.x+150,1920))
			{
				FlappyBirdView.f=false;
				FlappyBirdView.lost.start();
				FlappyBirdView.playsound(FlappyBirdView.alost);
				return;
			}
		}
		for(int i=0;i<ve.size();i++)ve.get(i).x-=v;
		if(ve.get(0).x<=-200)
		{
			ve.remove(0);
			ve.add(new point(1600,(int)(Math.random()*1400)));
		}
		if(ve.get(0).x<=200&&!ve.get(0).flag)
		{
			an.setposition_center(x,y);
			an.start();
			iq.alpha=255;
			iq.setposition_center(x,y);
			iq.moveto(iq.x,iq.y-200,1000);
			FlappyBirdView.playsound(Math.random()>1/3d?FlappyBirdView.faq:FlappyBirdView.faaaq);
			ve.get(0).flag=true;
			fs++;
		}
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
	public float[] roate(float cx,float cy,float[] vertex,float angle){
		float[] mat={(float)Math.cos(angle),-(float)Math.sin(angle),
					 (float)Math.sin(angle),(float)Math.cos(angle)};
		for(int i=0;i<vertex.length;i+=2){
			vertex[i]-=cx;vertex[i+1]-=cy;
			vertex[i]=mat[0]*vertex[i]+mat[1]*vertex[i+1];
			vertex[i+1]=mat[2]*vertex[i]+mat[3]*vertex[i+1];
			vertex[i]+=cx;vertex[i+1]+=cy;
		}
		return vertex;
	}
}
class point
{
	int x,y;
	boolean flag;
	public point(int x,int y){this.x=x;this.y=y;}
}
