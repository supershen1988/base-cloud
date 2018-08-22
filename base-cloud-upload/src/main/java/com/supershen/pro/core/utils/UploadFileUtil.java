package com.supershen.pro.core.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 上传工具
 * @author gshen
 * @创建时间 2018-07-23 13:07
 * @copyright © 2018 
 */
public class UploadFileUtil {

	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;

	/**
	 * 保存文件，直接以multipartFile形式
	 * 
	 * @param multipartFile
	 * @param path
	 *            文件保存绝对路径
	 * @param returnPath
	 *            文件保存绝对路径【不包含项目头】
	 * @return 返回文件绝对路径
	 */
	public static String save(MultipartFile multipartFile, String path, String returnPath) throws IOException {
		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
		String fileName = getFileName(multipartFile.getOriginalFilename());
		// String fileName = Ids.uuid2() + ".png";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + "/" + fileName));

		byte[] bs = new byte[1024];
		int len;
		while ((len = fileInputStream.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}

		bos.flush();
		bos.close();
		// System.out.println("图片绝对路径：" + returnPath + File.separator +
		// fileName);
		return returnPath + "/" + fileName;
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成新的文件名
	 * 
	 * @param fileOriginName
	 *            源文件名
	 */
	public static String getFileName(String fileOriginName) {
		return Ids.uuid2() + UploadFileUtil.getSuffix(fileOriginName);
	}

	/**
	 * 生成缩略图方法
	 * 
	 * @param file
	 * @param uploadPath
	 *            相对路径 存在哪个文件夹下 eg： images
	 * @param realUploadPath
	 *            实际路径 eg：E:/images/
	 * @return
	 */
	public static String thumbnail(MultipartFile file, String uploadPath, String realUploadPath) {
		String fileNamePath = "/thum_" + getFileName(file.getOriginalFilename());
		try {
			String des = realUploadPath + fileNamePath;
			Thumbnails.of(file.getInputStream()).size(WIDTH, HEIGHT).toFile(des);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadPath + fileNamePath;
	}

}
