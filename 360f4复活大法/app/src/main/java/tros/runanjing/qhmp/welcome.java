package tros.runanjing.qhmp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;

public class welcome extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {      
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
		new Thread(){
			public void run()
			{
				try
				{				
					sleep(3000);
				}
				catch (Exception e)
				{}
				Intent intent=new Intent(welcome.this, MainActivity.class);
				intent.setClass(welcome.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}.start();
	}
}
			
