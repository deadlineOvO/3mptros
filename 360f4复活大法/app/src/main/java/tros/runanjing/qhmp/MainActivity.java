package tros.runanjing.qhmp;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import java.io.*;

import android.support.v7.widget.Toolbar;
import java.lang.Process;
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
		if (android.os.Build.MODEL == "1051_M02")
		{}
		else if (android.os.Build.MODEL == "1051_A02")
		{}
		else
		{
			Toast.makeText(this, "警告，你的设备不是360f4系列，请谨慎使用", Toast.LENGTH_LONG);
		}
		TextView textView = (TextView) findViewById(R.id.text);
		textView.setText("你的手机型号:" + android.os.Build.MODEL);
		Button 数据=(Button)findViewById(R.id.数据);
		Button 脚本=(Button)findViewById(R.id.脚本);
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
    }  
}
