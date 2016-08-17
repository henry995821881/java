package com.henry.test.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class testcsv {

	public static void main(String[] args) throws IOException {
		
		File file = new File("d:"+File.separator+"hello.csv");
		OutputStream out = new FileOutputStream(file, true);
		
		for(int i =0;i<1000000;i++){
			
			String string = i+","+"h"+i+","+"henry"+i+","+"hello"+i+"\r\n";
			
			byte[] bytes = string.getBytes();
			
			out.write(bytes);
			out.flush();
		}
		out.close();
		System.out.println("TTT.main()");
	}

}
