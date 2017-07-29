package tros.runanjing.qhmp;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
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
		if (android.os.Build.MODEL == "1051_M02")
		{}
		else if (android.os.Build.MODEL == "1051_A02")
		{}
		else
		{
			Toast.makeText(this, "警告，你的设备不是360f4系列，请谨慎使用", Toast.LENGTH_LONG);
		}
		TextView textView = (TextView) findViewById(R.id.text);
		textView.setText("你的手机型号:" + android.os.Build.MODEL);}



	public class CopyFileFromAssets{
		/**
		 　　*
		 　　* @param myContext
		 　　* @param ASSETS_NAME 要复制的文件名
		 　　* @param savePath 要保存的路径
		 　　* @param saveName 复制后的文件名
		 　　* testCopy(Context context)是一个测试例子。
		 　　*/
		public  void copy(Context myContext, String ASSETS_NAME, String savePath, String saveName)
		{
			String filename = "mnt/sdcard/TWRP" + "/" + "rec.zip";
			File dir = new File(filename);
			// 如果目录不中存在，创建这个目录
			if (!dir.exists())
				dir.mkdir();
			try
			{
				if (!(new File(filename)).exists())
				{
					InputStream is = myContext.getResources().getAssets()
						.open("360F4rec_V3.zip");
					FileOutputStream fos = new FileOutputStream(filename);
					byte[] buffer = new byte[7168];
					int count = 0;
					while ((count = is.read(buffer)) > 0)
					{
						fos.write(buffer, 0, count);
					}
					fos.close();
					is.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
