package ict.monitor.datamining;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
 
/**
 * @author 码农小江
 * H20121012.java
 * 2012-10-12下午11:40:21
 */
public class data_list {
    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static ArrayList <data_contrast> readTxtFile(String filePath){
    	ArrayList <data_contrast> list =new ArrayList();
    	
        try {
                
        		String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	String[] str = lineTxt.split(",");
                    	data_contrast d=new data_contrast();//创建一个比较的对象
                    	d.setCompent(str[0].trim());//将数据添加到对象中
                    	d.setUptime(str[1].trim());//将数据添加到对象中
                    	d.setTranscationId(str[2].trim());//将数据添加到对象中
                    	d.setStartTime(str[3].trim());//将数据添加到对象中
                    	list.add(d);
                    //	System.out.println(lineTxt);
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }
    
//    public static void main(String argv[]){
////      "res/";
//        ArrayList<data_contrast> list =new ArrayList();
//        list=readTxtFile("C:\\data\\dataset.txt");
//        data_contrast d=new data_contrast();
//        for(int i = 0; i<list.size(); i++)
//        {    
//        	d=list.get(i);
//        	System.out.println(d.getCompent()+d.getStartTime()+d.getTranscationId()+d.getUptime());
//        }
//    }
    
    public data_contrast search(String uptime,String compent)
    {
    	data_contrast d=new data_contrast();
    	ArrayList<data_contrast> list =new ArrayList();
    	list=readTxtFile("C:\\data\\dataset.txt");
        for(int i = 0; i<list.size(); i++)
        {    
        	d=list.get(i);
        	if(d.getCompent().equals(compent)&&d.getUptime().equals(uptime))
        		return d;
        }
    	return null;
    }
}