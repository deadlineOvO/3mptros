package me.funnypro.qkmptros;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.WindowManager;
import java.io.*;


public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
		new Thread(new Runnable(){
				@Override
				public void run(){
					try{
						Unzip.unzip(getAssets().open("External_Recovery.zip"), "/data/data/net.funnypro.3mptros");
					}catch (IOException e){
						
					}
					main();
				}
			}).start();
	}
	private void main(){
		runOnUiThread(new Runnable(){

				@Override
				public void run(){
					Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
					intent.setClass(WelcomeActivity.this, MainActivity.class);
					startActivity(intent);					
					finish();
				}
			});
	}
}


