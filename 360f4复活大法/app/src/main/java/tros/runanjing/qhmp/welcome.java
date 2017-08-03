package tros.runanjing.qhmp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import android.transition.*;

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
					Runtime.getRuntime().exec(new String[]{"su","-c","chmod -R 777 /data/shell"});
					sleep(2500);
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
			
