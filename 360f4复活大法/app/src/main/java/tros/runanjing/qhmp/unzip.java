package tros.runanjing.qhmp;

import android.content.*;
import java.io.*;
import java.util.zip.*;  

public class unzip//文件解压
{
	// 解压zip

	public static void unzip (InputStream is, String dir) throws IOException  
	{  
		File dest = new File(dir);  
		if ( !dest.isDirectory())   
			throw new IOException("Invalid Unzip destination " + dest);  

		if(null == is){  
			throw new IOException("InputStream is null");  
		}  

		ZipInputStream zip = new ZipInputStream(is);  

		ZipEntry ze;  

		while ( (ze = zip.getNextEntry()) != null ) {  
			final String path = dest.getAbsolutePath()   
				+ File.separator + ze.getName();  

			// Create any entry folders  
			String zeName = ze.getName();  
			char cTail = zeName.charAt(zeName.length() - 1);  
			if ( cTail == File.separatorChar)   
			{     
				File file = new File(path);  
				if(!file.exists()){  
					if ( !file.mkdirs() ){  
						throw new IOException("Unable to create folder " + file);     
					}  
				}  
				continue;  
			}  

			FileOutputStream fout = new FileOutputStream(path);  
			byte[] bytes = new byte[1024];  
			int c;  
			while((c = zip.read(bytes)) != -1){  
				fout.write(bytes,0, c);    
			}  

			zip.closeEntry();  
			fout.close();              
		}  

	}  }

