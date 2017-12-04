package me.funnypro.qkmptros;

import android.widget.Toast;
import java.io.*;
import java.util.ArrayList;
import android.content.*;
import java.lang.InterruptedException;
import java.lang.Process;


public class ExecShell{
	static public String execShell(String shellString)throws IOException, InterruptedException{
		
		String shellOut;
		String shell=shellString + "\n";
		
		if(Runtime.getRuntime().exec(new String []{"sh", "-c","echo 'exit 0' | su"} ).waitFor() == 0){			
			Process process = Runtime.getRuntime().exec("su");
			OutputStream outputStream = process.getOutputStream();
			outputStream.write(shell.getBytes());
			outputStream.write("exit\n".getBytes());
			outputStream.flush();
			InputStreamReader ir = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			ArrayList<String> strList = new ArrayList<String>();
			process.waitFor();
			while ((line = input.readLine()) != null){
				strList.add(line);
			}
			String AshellOut = strList.toString();
			int beginIndex = 1;
			int endIndex = AshellOut.length() - 1;
			shellOut = AshellOut.substring(beginIndex , endIndex);		
		}else{
			try{
				throw new Exception();
			}catch(Exception RootError){
				shellOut = "错误";
				Context context = null;
				Toast.makeText(context, "你的设备看起来不是没有 root 权限就是拒绝了本软件的申请\n或者是授权软件起不来导致授权异常", Toast.LENGTH_LONG);
			}
		}

	return shellOut;

	}
}
