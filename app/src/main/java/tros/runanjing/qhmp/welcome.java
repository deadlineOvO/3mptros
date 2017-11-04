package tros.runanjing.qhmp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import java.io.*;
public class welcome extends Activity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
	{	
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
		new Thread(new Runnable(){
				@Override
				public void run()
				{
					try
					{
						unzip.unzip(getAssets().open("External_Recovery.zip"), "/data/data/tros.runanjing.qhmp");
					}
					catch (IOException e)
					{}
					main();
				}
			}).start();
	}
	private void main()
	{
		runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					Intent intent=new Intent(welcome.this, MainActivity.class);
					intent.setClass(welcome.this, MainActivity.class);
					startActivity(intent);					
					finish();
				}
			});
	}
}


