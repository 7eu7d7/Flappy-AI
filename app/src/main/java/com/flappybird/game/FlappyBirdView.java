package com.flappybird.game;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.os.*;
import android.view.*;
import android.view.SurfaceHolder.*;
import java.io.*;
import android.util.*;
public class FlappyBirdView extends SurfaceView implements Callback,Runnable
{
	public static boolean f=true,rand;
	private SurfaceHolder sfh;
	public static int screen=0;
	private Canvas canvas;
	public static SoundPool sp;
	di1 d1=new di1();
	public static Animation lost;
	
	public static int faq,faaaq,alost;
	
	public FlappyBirdView(Context context)
	{
		super(context);
		sfh=this.getHolder();
		sfh.addCallback(this);
		setFocusableInTouchMode(true);
		//setLongClickable(true); 
		setFocusable(true);
		lost=new Animation(0,0,MainActivity.fblx,MainActivity.fbly);
		lost.setbitmaps(BitmapRegion.split(tool.loadbitmap("lost.png"),4,8),1,30);
		lost.setT(1000);
		lost.stop();
		sp=new SoundPool(6,AudioManager.STREAM_MUSIC,100);
		try
		{
			faq=sp.load(MainActivity.am.openFd("sound/faq.wav"),1);
			faaaq=sp.load(MainActivity.am.openFd("sound/faaaq.wav"),1);
			alost=sp.load(MainActivity.am.openFd("sound/lost.wav"),1);
		}catch (IOException e){}
	}
	@Override
	public void surfaceCreated(SurfaceHolder p1)
	{
		new Thread(this).start();
		rand=true;
		// TODO: Implement this method
	}
	public void Draw()
	{

		try{
			canvas=sfh.lockCanvas();
			if(canvas!=null)
			{
				canvas.drawColor(Color.BLACK);
				canvas.save();
				canvas.scale((MainActivity.fblx/1080f),(MainActivity.fbly/1920f));
				d1.draw(canvas);
				lost.draw(canvas);
				lost.inct(20);
				canvas.restore();
			}
		}
		catch(Exception e){}//canvas.drawColor(Color.GREEN);e.printStackTrace();}
		finally
		{
			if(canvas!=null)
			{
				sfh.unlockCanvasAndPost(canvas);
			}
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return d1.touch(event);
	}
	@Override
	public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4)
	{
		// TODO: Implement this method
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder p1)
	{
		rand=false;
	}

	@Override
	public void run()
	{
		while(true)
		{
			while(f)
			{
				if(!rand)continue;
				long 开始=System.currentTimeMillis();
				poi();
				Draw();
				long 结束=System.currentTimeMillis();
				try
				{
					if(结束-开始<20)
						Thread.sleep(20-(结束-开始));
				}
				catch (InterruptedException e)
				{}
			}
			d1.reset();
			f=true;
		}
	}
	public void poi()
	{
		d1.poi();
	}
	public static void playsound(int id){
		sp.play(id,1,1,0,0,1);
	}
}
