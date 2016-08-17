package com.henry.test.io;
import java.awt.image.AreaAveragingScaleFilter;
import java.beans.DefaultPersistenceDelegate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Iotext {

	public static void main(String[] args) throws IOException {
		
		
		
		
		
		
	/*	
		File folder = new File("d:"+File.separator+"aa"+File.separator+"bb");
		
		if(!folder.exists()){
			
			folder.mkdirs();
			
					
		}
		
		
		File file = new File(folder.getAbsolutePath()+File.separator+"abc.txt");*/
		
	
	/*	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
		
		for(int i =0;i<1000;i++){
			
			bw.write("henry"+i);
			bw.flush();
		}
		bw.close();*/
		
		/*FileOutputStream fStream = new FileOutputStream(file,true);
	    for(int i =0;i<1000;i++){
			
	    	fStream.write(("henry"+i).getBytes("UTF-8"));
	    	fStream.flush();
		}
	    fStream.close();*/
		
	/*	FileInputStream input = new FileInputStream(file);
		FileOutputStream out = new FileOutputStream(new File("d:"+File.separator+"aaa.txt"));
		byte[] buff = new byte[1024];
		int len = -1;
		while((len = input.read(buff)) != -1){

			out.write(buff, 0, len);
			out.flush();
		}
		out.close();
		input.close();*/
		
	/*	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		
		String string = null;
		while((string = br.readLine()) !=null){
			System.out.println(string+"\n\r");
		}
		br.close();*/
		
		/*if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("createNewFile");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		File file = new File("d:"+File.separator+"abc");
		long a = System.currentTimeMillis();

		delete(file);
		long b = System.currentTimeMillis();
		System.out.println((b-a)/1000);

	}

	private static void delete(File file) {
		
		
		if(file.isFile()||file.listFiles().length ==0){
			file.delete();
			System.out.println(file.getName());
		}else{
			
			File[] listFiles = file.listFiles();
			
			for (File file2 : listFiles) {
				
				
				 delete(file2);
				 
				
			}
			
			file.delete();
		}
		
	}

}
