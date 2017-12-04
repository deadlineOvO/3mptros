package me.funnypro.qkmptros;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

import android.util.Log;
import android.support.v7.widget.Toolbar;
public class MainActivity extends AppCompatActivity{
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
		
		Log.i("xx", "yy");
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Toolbar mToolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(mToolbar);
		//获取手机型号
		String 	m = android.os.Build.MODEL;
		if (m != "1051_M02" || m != "1051_A02"){
			Toast.makeText(this, "警告，你的设备不是 360frop4 系列，请谨慎使用\n顺便说下，出现任何问题后果自负", Toast.LENGTH_LONG);
		}
		
		
		//获取su版本
		try{

			TextView su版本=(TextView)findViewById(R.id.su版本);
			String out = ExecShell.execShell("su -v");

			if (out != "[not found]" || out != "错误"){
				su版本.setText("当前su二进制版本信息：" + out);
			}else{
				su版本.setText("你的手机好像没 root 权限的样子\n不信？那么 " + out + " 是什么情况？");
			}

		}catch (Throwable e){
			
		} 

		
		
		
		TextView 型号 = (TextView) findViewById(R.id.型号);
		型号.setText("你的手机型号：" + android.os.Build.MODEL);
		
		
		
		
		Button reBoot =(Button)findViewById(R.id.reBoot);
		reBoot.setOnClickListener(new View.OnClickListener(){
			
				@Override
				public void onClick(View reBootView){
					Snackbar.make(reBootView, "确定重启？", Snackbar.LENGTH_SHORT)
						.setAction("是啊", new View.OnClickListener(){

							@Override
							public void onClick(View p1){
								try{
									ExecShell.execShell("reboot");
								}catch(Exception e){

								}
							}
						}
					).show();
				}
			}
		);
		
		
		Button hotreBoot=(Button)findViewById(R.id.hotreBoot);
		hotreBoot.setOnClickListener(new View.OnClickListener(){
			
				@Override
				public void onClick(View hotrebootView){
					Snackbar.make(hotrebootView, "确定软重启？", Snackbar.LENGTH_SHORT)
						.setAction("是啊", new View.OnClickListener(){
							@Override
							public void onClick(View p1){
								try{
									ExecShell.execShell("busybox killall system_server");
								}catch (Exception e){

								}
							}
						}
					).show();
				}
			}
		);
		
		
		//进入TWRP时隐藏状态栏

		final RelativeLayout mR=(RelativeLayout)findViewById(R.id.RelativeLayout);
		
		Button openTWRP=(Button)findViewById(R.id.openTWRP);
		openTWRP.setOnClickListener(new View.OnClickListener(){
			
				@Override
				public void onClick(View openTWRPView){
					Snackbar.make(openTWRPView, "确定进入TWRP？", Snackbar.LENGTH_SHORT)
						.setAction("是啊", new View.OnClickListener(){

							@Override
							public void onClick(View p1){
								mR.setSystemUiVisibility(View.INVISIBLE);
								try{
									ExecShell.execShell("cp -Rf /data/data/tros.runanjing.qhmp/External_Recovery/shell /data");
									ExecShell.execShell("chmod -R 777 /data/shell");
									ExecShell.execShell("sh /data/shell/recovery.sh");
								}catch (InterruptedException e){
								}catch (IOException e){
								}
							}
						}
					).show();
				}
			}
		);
	}
	
	
	
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		
		Button exit=(Button)findViewById(R.id.exit);
        if (keyCode == KeyEvent.KEYCODE_BACK){
			Snackbar.make(exit, "确定退出？", Snackbar.LENGTH_SHORT)
				.setAction("对啊", new View.OnClickListener(){
					@Override
					public void onClick(View exitView){
						android.os.Process.killProcess(android.os.Process.myPid());
						System.exit(0); 
					}
				}
			) .show();
        }
        return false;	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.toolbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.about:
				Intent intent=new Intent(MainActivity.this, AboutActivity.class);
				startActivity(intent);
				break;
		}
		return true;
	}

}


