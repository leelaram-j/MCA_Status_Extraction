package com.txt.write;

import java.io.FileOutputStream;
import java.io.IOException;

public class Txt_write
{
	public static void write2Txt(String opFileName, String inName, String CIN,String mcaName, String status) throws IOException
	    {
	       
	        String str = inName+"\t"+CIN+"\t"+mcaName+"\t"+status+"\n";
	        FileOutputStream fos = new FileOutputStream(opFileName,true);
	        byte[] strToBytes = str.getBytes();
	        fos.write(strToBytes);
	        fos.close();
	    }
}
