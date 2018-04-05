package com.flappybird.game;

import android.app.*;
import android.content.res.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity 
{
	public static int fblx,fbly;
	public static AssetManager am;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		WindowManager windowManager = getWindowManager();  
        Display display = windowManager.getDefaultDisplay();  
        fblx = display.getWidth();  
      	fbly = display.getHeight();
		am=getAssets();
        setContentView(new FlappyBirdView(this));
    }
}
