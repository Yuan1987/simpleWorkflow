package com.dynastech.base.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传：fastFs实现
 * 
 * @author yuan
 *
 */
public class FileUploadUtil {
	private static Logger logger = Logger.getLogger(FileUploadUtil.class);
	
	private static TrackerClient tracker = null;
	private static TrackerServer trackerServer = null;
	private static StorageServer storageServer = null;
	private static StorageClient1 client = null;

	static {
		ClassPathResource cpr = new ClassPathResource("fdfs_client.conf");
		try {
			ClientGlobal.init(cpr.getClassLoader().getResource("fdfs_client.conf").getPath());
			 tracker = new TrackerClient();
			 trackerServer = tracker.getConnection();
			 storageServer = null;
			 client = new StorageClient1(trackerServer, storageServer);
		} catch (Exception e) {
			logger.error("ClientGlobal init error:", e);
			throw new RuntimeException("ClientGlobal init error: ", e);
		}
	}

	/**
	 * 上传多个文件，返回文件名称和服务器存储路径列表
	 * 
	 * 分布式文件上传
	 * 
	 * @param files
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	public static Map<String, String> upload(MultipartFile... files) throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue;
			}
			String tempFileName = file.getOriginalFilename();
			String fileExtName = tempFileName.substring(tempFileName.lastIndexOf(".") + 1);
			String fileId = upload(file.getBytes(), tempFileName, fileExtName, String.valueOf(file.getSize()));
			result.put(file.getOriginalFilename(), fileId);
			logger.info("upload file " + tempFileName + " finished!!!");
		}
		return result;
	}

	/**
	 * 上传多个文件，返回文件名称和服务器存储路径列表
	 * 
	 * @param files
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	public static Map<String, String> uploadLoaclFile(String localFileName, String fileExtName, NameValuePair[] nvp)
			throws IOException, MyException {
		Map<String, String> result = new HashMap<String, String>();
		String fileId = client.upload_file1(localFileName, fileExtName, nvp);
		result.put(localFileName, fileId);
		return result;
	}

	/**
	 * 
	 * @param fileBuff
	 * @param tempFileName
	 * @param fileExtName
	 *            :jpg/png等，注意不要带点
	 * @param size
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	public static String upload(byte[] fileBuff, String tempFileName, String fileExtName, String size){
		// 设置元信息
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", tempFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength", size);
		// 上传文件
		String fileId = "";
		try {
			fileId = client.upload_file1(fileBuff, fileExtName, metaList);
		} catch (IOException e) {
			logger.error("e2", e);
		} catch (MyException e) {
			logger.error("e3", e);
		}
		return fileId;
	}

	public static String getImageServerUrl(String path) {
		String accessUrl = ConfigurationUtil.getResourcesValue("fastdfs.file.baseUrl") + "/" + path;
		return accessUrl;
	}

}
