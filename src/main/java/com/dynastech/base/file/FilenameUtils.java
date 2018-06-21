package com.dynastech.base.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;


public class FilenameUtils extends org.apache.commons.io.FilenameUtils{

	
	public static String createUniqueFilename(String suffix){
		
		String name = UUID.randomUUID().toString();
		
		if(!StringUtils.isEmpty(suffix)){
			name=name+"."+suffix;
		}
		return name;
			
	}

    /**
     * 获取文件扩展名
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    public static List<String> getFileName(File f){
		
		List <String> list=new ArrayList<String>();
		
        if(!f.isDirectory()){
            System.out.println("你输入的不是一个文件夹，请检查路径是否有误！！");
        }else{
            File[] t = f.listFiles();
            for(int i=0;i<t.length;i++){
                if(t[i].isDirectory()){
                	list.add(t[i].getName());
                }
            }
        }
        return list;
    }
}
