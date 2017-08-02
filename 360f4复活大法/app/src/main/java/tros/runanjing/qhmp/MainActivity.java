package tros.runanjing.qhmp;

import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
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
		} 
		TextView 型号 = (TextView) findViewById(R.id.型号);
		型号.setText("你的手机型号:" + android.os.Build.MODEL);
		final Button 数据=(Button)findViewById(R.id.数据);
		final Button 脚本=(Button)findViewById(R.id.脚本);
		Button 重启=(Button)findViewById(R.id.重启);
		Button 软重启=(Button)findViewById(R.id.软重启);
		Button TWRP=(Button)findViewById(R.id.TWRP);
		final TextView 数据_text=(TextView)findViewById(R.id.数据_text);
		final TextView 脚本_text=(TextView)findViewById(R.id.脚本_text);
		数据.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					Snackbar.make(v, "现在检验数据？", Snackbar.LENGTH_SHORT)
						.setAction("是啊", new View.OnClickListener(){
							@Override
							public void onClick(View v)
							{
								try
								{
									unzip.unzip(getAssets().open("360F4rec.zip"), "/mnt/sdcard");		
								}
								catch (Exception e)
								{
								}		
								File 数据_路径=new File("mnt/sdcard/.TWRP"); 
								if (数据_路径.exists())
								{
									数据.setVisibility(View.GONE);
									数据_text.setText("数据状态:检验成功");
								}
								else
								{
									数据_text.setText("数据状态:检验失败");
								}
							}
						}
					).show();
				}			
			}
		);
		脚本.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					File 数据_路径=new File("/mnt/sdcard/.TWRP"); 
					if (数据_路径.exists())
					{
						Snackbar.make(v, "现在开启脚本？", Snackbar.LENGTH_SHORT)
							.setAction("是啊", new View.OnClickListener(){

								@Override
								public void onClick(View p1)
								{
									try
									{
										Runtime.getRuntime().exec(new String[]{"su","-c","cp -Rf /sdcard/.TWRP/shell /data"});
										Runtime.getRuntime().exec(new String[]{"su","-c","chmod -R 777 /data/shell"});
									}
									catch (Exception e)
									{

									}
									File f1=new File("/data/shell/"); 
									if (f1.exists()) 
									{ 
										脚本.setVisibility(View.GONE);
										脚本_text.setText("脚本状态:开启成功");
									}
									else
									{
										脚本_text.setText("脚本状态:开启失败");
									}
								}
							}
						).show();	
					}		
					else
					{
						Snackbar.make(v, "你TM还没有检验数据吧（｀Δ´）ゞ", Snackbar.LENGTH_SHORT).show();
					}
				}
			}
		);
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
									Runtime.getRuntime().exec(new String[]{"su","-c","busybox killall system_server"});
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
									Runtime.getRuntime().exec(new String[]{"su","-c","busybox killall system_server"});
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
					File 数据_路径=new File("/mnt/sdcard/.TWRP"); 
					if (数据_路径.exists())
					{
						File 脚本_路径=new File("/data/shell/"); 
						if (脚本_路径.exists())
						{
							Snackbar.make(v, "确定进入TWRP？", Snackbar.LENGTH_SHORT)
								.setAction("是啊", new View.OnClickListener(){

									@Override
									public void onClick(View p1)
									{
										try
										{
											Runtime.getRuntime().exec(new String[]{"su","-c","nohup sh /data/shell/install+run_recovery.sh"});
										}
										catch (Exception e)
										{

										}
									}
								}
							).show();
						}
						else
						{
							Snackbar.make(v, "你TM还没有开启脚本吧（｀Δ´）ゞ", Snackbar.LENGTH_SHORT).show();
						}
					}
					else
					{
						Snackbar.make(v, "你TM还没有既没有检验数据也没有开启脚本吧（｀Δ´）ゞ", Snackbar.LENGTH_SHORT).show();
					}
				}
			}
		);
		File 数据_路径=new File("mnt/sdcard/.TWRP"); 
		if (数据_路径.exists())
		{
			数据_text.setText("数据状态:检验成功");
		}
		else
		{
			数据_text.setText("数据状态:未检验");
		}
		File 脚本_路径=new File("/data/shell"); 
		if (脚本_路径.exists())
		{
			脚本_text.setText("脚本状态:开启成功");
		}
		else
		{
			脚本_text.setText("脚本状态:未开启");
		}
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
}
