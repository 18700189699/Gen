package com.uxsino.Netty.ServerNetty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 将获取到的文件添加到本地文件中
 * 获取本地文件中的内容并将内筒销毁
 * @author admin
 *
 */

public class GetFile {

	static String dirName = "D:\\123.txt";
	/**
	 * 将文件内容添加到本地
	 * @param str
	 */
	public static void writeDiskInfo(String str){
		 try  
		    {  
			// 创建文件对象  
	        File fileText = new File(dirName);  
	        // 向文件写入对象写入信息  
	        FileWriter fileWriter = new FileWriter(fileText); 
	        // 写文件        
	        fileWriter.write(str);
	        fileWriter.flush();
				System.out.println("_____________________");
	        // 关闭
	        fileWriter.close(); 
	    }  
	    catch (IOException e)  
	    {  
	      e.printStackTrace();  
	    }  
	}
	
	/**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static String readTxtFile(){
    	String lineTxt = null;
    	try {
                String encoding="UTF-8";
                File file=new File(dirName);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    
                    while((lineTxt = bufferedReader.readLine()) != null){
                        System.out.println("read==================="+lineTxt);
						return lineTxt;
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
		System.out.println("++++++++++"+lineTxt);
        return lineTxt;
      
    }
	
	
	/*public static void main(String[] args) {
		GetFile.writeDiskInfo("11");
		System.out.println("结束");
	}*/
}
