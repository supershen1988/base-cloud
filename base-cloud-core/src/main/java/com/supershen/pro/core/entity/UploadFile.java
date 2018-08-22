package com.supershen.pro.core.entity;
/**
 * 上传文件实体
 * @author gshen
 * @创建时间 2018-07-26 19:07
 * @copyright © 2018
 */
public class UploadFile {
	private String fileName;
	
	private String fileUrl;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public UploadFile(String fileName, String fileUrl) {
		super();
		this.fileName = fileName;
		this.fileUrl = fileUrl;
	}

	public UploadFile() {
		super();
	}
	
	
}
