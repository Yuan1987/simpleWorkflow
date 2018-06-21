package com.dynastech.base.file;

import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yuanhb
 *
 */
public class FileServiceImpl implements IFileSystemService {

	private String baseUrl;
	
	private String basePath;
	
	public String locate(String path){
		
		String absolutePath = FilenameUtils.normalize(basePath+"/"+path, true);
		
		return absolutePath;
	}
	@Override
	public String getAbsoluteAccessUrl(String path) {	
		String accessUrl =baseUrl+"/"+path;
		return accessUrl;
	}
	@Override
	public void save(File sourceFile, String path) throws IOException {
		
		String absolutePath = locate(path);
				
		File destFile = new File(absolutePath);
		
		File p=destFile.getParentFile();
		if(!p.exists()){
			p.mkdirs();
		}
		FileUtils.moveFile(sourceFile, destFile);
			
	}
	@Override
	public void save(MultipartFile sourceFile, String path) throws IOException {
		
		String absolutePath = locate(path);
				
		File destFile = new File(absolutePath);
		
		if(!destFile.exists()){
			destFile.mkdirs();
		}
		
		sourceFile.transferTo(destFile);
		
	}
	@Override
	public boolean delete(String path) {
		
		boolean deleted = false;
		
		String absolutePath = locate(path);
		
		File destFile = new File(absolutePath);
		
		if(destFile.exists()){
			
			deleted=FileUtils.deleteQuietly(destFile);
		}
			
		return deleted;
	}
	
	public File getDownloadFile(String path){
		String absolutePath = locate(path);
		
		File file = new File(absolutePath);
		return file;
	}
	
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	@Override
	public String getAbsoluteAccessPath(String path) {		
		return locate(path);
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	@Override
	public void save(byte[] sourceFile, String path){
		
		FileImageOutputStream imageOutput=null;
		try {
			String absolutePath = locate(path);
			
			File destFile = new File(absolutePath);
			
			File p=destFile.getParentFile();
			
			if(!p.exists()){
				p.mkdirs();
			}
			imageOutput = new FileImageOutputStream(destFile);
			imageOutput.write(sourceFile, 0, sourceFile.length);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				imageOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
