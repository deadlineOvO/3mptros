package tros.runanjing.qhmp;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;

import android.support.v7.widget.Toolbar;
public class MainActivity extends AppCompatActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);   
		//获取手机型号
		String 	m=android.os.Build.MODEL;
		if (m == "1051_M02")
		{}
		else if (m == "1051_A02")
		{}
		else
		{
			Toast.makeText(this, "警告，你的设备不是360f4系列，请谨慎使用", Toast.LENGTH_LONG);
		}
		//获取su版本
		try
		{
			java.lang.Process process = Runtime.getRuntime().exec("su -v");
			OutputStream outputStream = process.getOutputStream();
			outputStream.write("ls &\n".getBytes());
			outputStream.write("exit".getBytes());
			outputStream.flush();
			InputStream inputStream = process.getInputStream();
			byte[] b = new byte[1024];
			int l = -1;
			StringBuffer buffer = new StringBuffer();
			while ((l = inputStream.read(b)) != -1)
			{
                buffer.append(new String(b, 0, l));
                buffer.append("\n");
            }
			String out = buffer.toString();
			inputStream.close();
			process.waitFor();
			process.destroy();
			TextView su版本=(TextView)findViewById(R.id.su版本);
			su版本.setText("当前su二进制:" + out);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		} 
		TextView 型号 = (TextView) findViewById(R.id.型号);
		型号.setText("你的手机型号:" + android.os.Build.MODEL);
		Button 数据=(Button)findViewById(R.id.数据);
		Button 脚本=(Button)findViewById(R.id.脚本);
		Button 重启=(Button)findViewById(R.id.重启);
		Button 软重启=(Button)findViewById(R.id.软重启);
		Button TWRP=(Button)findViewById(R.id.TWRP);
		数据.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					try
					{
						InputStream in=getAssets().open("360F4rec.zip");
						byte[] buff=new byte[in.available()];
						in.read(buff);
						FileOutputStream ou=new FileOutputStream("/mnt/sdcard/rec.zip");
						ou.write(buff);	
						ou.close();	
					}
					catch (Exception e)
					{
					}			
				}			
			}
		);
		脚本.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					try
					{
						Runtime.getRuntime().exec(new String[]{"su","-c","id"});
					}
					catch (Exception e)
					{

					}
				}
			}
		);
		重启.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v)
				{
					try
					{
						Runtime.getRuntime().exec(new String[]{"su","-c","reboot"});
					}
					catch (Exception e)
					{

					}
				}
			}
		);
		软重启.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v)
				{
					try
					{
						Runtime.getRuntime().exec(new String[]{"su","-c","busybox killall system_server"});
					}
					catch (Exception e)
					{

					}
				}
			}
		);
		TWRP.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v)
				{
					new Thread(){
						public void run()
						{
							try
							{
								sleep(3000);
							}
							catch (Exception e)
							{
							}
							try
							{			
								Runtime.getRuntime().exec(new String[]{"su","-c","nohup sh /data/shell/install+run_recovery.sh"});
							}
							catch (Exception e)
							{
							}
						}  
					};
				}
			}
		);
	}
}
