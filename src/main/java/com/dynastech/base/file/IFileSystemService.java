package com.dynastech.base.file;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public interface IFileSystemService {

	public String getAbsoluteAccessUrl(String path);
	
	public String getAbsoluteAccessPath(String path);
	
	public boolean delete(String path);
	
	public void save(File sourceFile,String path) throws IOException;
	
	public void save(MultipartFile sourceFile,String path) throws IOException;
	
	public void save(byte[] sourceFile,String path) throws IOException;
	
}
