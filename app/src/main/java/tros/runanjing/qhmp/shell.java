package tros.runanjing.qhmp;
import android.widget.*;
import java.io.*;
import java.util.*;
import android.content.*;

public class shell
{
	public String ExecShell(String shellString,Context context)throws IOException, java.lang.InterruptedException{	
		String shellOut;
		String shell=shellString+"\n";
		if(Runtime.getRuntime().exec(new String []{"sh", "-c","echo 'exit 0' | su"} ).waitFor() == 0){			
			java.lang.Process process = Runtime.getRuntime().exec("su");
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
		}
		else{
			try{
				throw new Exception();
			}
			catch(Exception RootError){
				shellOut = "错误";
				Toast.makeText(context, "你的设备好像没有 root 权限的样子", Toast.LENGTH_LONG);
			}
		}

	return shellOut;

	}
}
