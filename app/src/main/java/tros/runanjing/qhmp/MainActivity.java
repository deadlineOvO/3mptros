package tros.runanjing.qhmp;

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
public class MainActivity extends AppCompatActivity
{

	private shell shell;

	private TextView su_info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		Log.i("xx", "yy");
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);
		shell = new shell();
		//获取手机型号
		String 	m = android.os.Build.MODEL;
		if (m != "1051_M02" || m != "1051_A02")
		{
			Toast.makeText(this, "警告，你的设备不是 360frop4 系列，请谨慎使用\n顺便说下，出现任何问题后果自负", Toast.LENGTH_LONG);
		}

		//su返回信息
		su_info=(TextView)findViewById(R.id.su_info);
		//获取su版本
		try
		{

			TextView su版本=(TextView)findViewById(R.id.su版本);
			String out = shell.ExecShell("su -v", this);

			if (out != "[not found]")
			{
				su版本.setText("当前su二进制版本信息：" + out);
			}
			else
			{
				su版本.setText("你的手机好像没 root 权限的样子\n不信？那么 " + out + " 是什么情况？");
			}

		}
		catch (Throwable e)
		{} 

		//进入TWRP是隐藏状态栏所用的
		final RelativeLayout mR=(RelativeLayout)findViewById(R.id.RelativeLayout);

		//获取各种实例
		TextView 型号 = (TextView) findViewById(R.id.型号);
		型号.setText("你的手机型号：" + android.os.Build.MODEL);
		Button 重启=(Button)findViewById(R.id.重启);
		Button 软重启=(Button)findViewById(R.id.软重启);
		Button TWRP=(Button)findViewById(R.id.TWRP);
		重启.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v)
				{
					Snackbar.make(v, "确定重启？", Snackbar.LENGTH_SHORT)
						.setAction("是啊", new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								try
								{
									shell.ExecShell("reboot\n", MainActivity.this);
								}
								catch (Exception e)
								{

								}
							}
						}
					).show();
				}
			}
		);
		软重启.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v)
				{
					Snackbar.make(v, "确定软重启？", Snackbar.LENGTH_SHORT)
						.setAction("是啊", new View.OnClickListener(){
							@Override
							public void onClick(View p1)
							{
								try
								{
									shell.ExecShell("busybox killall system_server", MainActivity.this);
								}
								catch (Exception e)
								{

								}
							}
						}
					).show();
				}
			}
		);
		TWRP.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v)
				{
					Snackbar.make(v, "确定进入TWRP？", Snackbar.LENGTH_SHORT)
						.setAction("是啊", new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								mR.setSystemUiVisibility(View.INVISIBLE);
								try
								{
									shell.ExecShell("cp -Rf /data/data/tros.runanjing.qhmp/External_Recovery/shell /data", MainActivity.this);
									shell.ExecShell("chmod -R 777 /data/shell", MainActivity.this);
									String twrp= shell.ExecShell("sh /data/shell/recovery.sh", MainActivity.this);
									su_info.setText(twrp);
								}
								catch (InterruptedException e)
								{}
								catch (IOException e)
								{}
							}
						}
					).show();
				}
			}
		);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		Button 重启=(Button)findViewById(R.id.重启);
        if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Snackbar.make(重启, "确定退出？", Snackbar.LENGTH_SHORT)
				.setAction("对啊", new View.OnClickListener(){
					@Override
					public void onClick(View v)
					{
						android.os.Process.killProcess(android.os.Process.myPid());
						System.exit(0); 
					}
				}
			) .show();
        }
        return false;	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.toolbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.about:
				Intent intent=new Intent(MainActivity.this, about.class);
				startActivity(intent);
				break;
		}
		return true;
	}

}


