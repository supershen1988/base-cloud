package com.supershen.pro.web;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.WriterException;
import com.supershen.pro.core.api.BackResult;
import com.supershen.pro.core.entity.FileImageUrl;
import com.supershen.pro.core.entity.UploadFile;
import com.supershen.pro.core.utils.DateUtils;
import com.supershen.pro.core.utils.QRcodeUtil;
import com.supershen.pro.core.utils.UploadFileUtil;

/**
 * 微服务上传公共类
 * 
 * @author gshen
 * @创建时间 2018-07-21 15:07
 * @copyright © 2018
 */
@Controller
public class FileUploadController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 在配置文件中配置的文件保存路径
	 * 
	 */
	@Value("${base.cloud.upload.path}")
	private String location;

	/**
	 * 上传图片
	 * 
	 * @param multipartFile
	 *            图片文件
	 */
	@RequestMapping(value = "/img/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public BackResult<FileImageUrl> uploadImg(
			@RequestParam(value = "file", required = true) MultipartFile multipartFile) {
		if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
			return BackResult.result(false);
		}
		String contentType = multipartFile.getContentType();
		if (!contentType.contains("")) {
			return BackResult.result(false);
		}

		String root_fileName = multipartFile.getOriginalFilename();
		logger.info("上传图片:name={},type={}", root_fileName, contentType);
		// 处理图片
		// User currentUser = userService.getCurrentUser();
		// 获取路径
		// String return_path = ImageUtil.getFilePath(currentUser);
		String date = DateUtils.toString(new Date());
		String returnPath = "images/" + date;

		String filePath = location + returnPath;
		logger.info("图片保存路径={}", filePath);
		// 相对路径
		String path = "imageShow/";
		String fileName = null;
		try {
			// 保存图片
			fileName = UploadFileUtil.save(multipartFile, filePath, returnPath);
			// 保存缩略图
			String thumbnailImageUrl = UploadFileUtil.thumbnail(multipartFile, returnPath, filePath);
			// BackResult backResult = new BackResult();
			FileImageUrl fiu = new FileImageUrl();
			fiu.setImageUrl(path + fileName);
			fiu.setThumbnailImageUrl(path + thumbnailImageUrl);
			logger.info("返回值：{}", fiu.toString());
			if (StringUtils.isBlank(fileName)) {
				logger.info("图片返回值为空");
				return BackResult.result(false);
			}
			return BackResult.success(fiu);
		} catch (IOException e) {
			return BackResult.result(false);
		}

	}

	/**
	 * 上传文件
	 * 
	 * @param multipartFile
	 *            文件
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public BackResult<UploadFile> fileUpload(
			@RequestParam(value = "file", required = true) MultipartFile multipartFile) {
		if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
			return BackResult.result(false);
		}
		String contentType = multipartFile.getContentType();
		if (!contentType.contains("")) {
			return BackResult.result(false);
		}

		String root_fileName = multipartFile.getOriginalFilename();
		logger.info("上传文件:name={},type={}", root_fileName, contentType);

		String date = DateUtils.toString(new Date());
		String returnPath = "file/" + date;

		String filePath = location + returnPath;
		logger.info("文件保存路径={}", filePath);
		// 相对路径
		String path = "fileShow/";
		String fileName = null;
		try {
			// 保存
			fileName = UploadFileUtil.save(multipartFile, filePath, returnPath);
			// 拼接绝对路径
			String fileUrl = path + fileName;
			UploadFile uf = new UploadFile();
			uf.setFileName(root_fileName);
			uf.setFileUrl(fileUrl);

			logger.info("返回值：{}", fileUrl.toString());
			if (StringUtils.isBlank(fileName)) {
				logger.info("文件返回值为空");
				return BackResult.result(false);
			}
			return BackResult.success(uf);
		} catch (IOException e) {
			return BackResult.result(false);
		}
	}

	/**
	 * 生成二维码并返回保存地址
	 * 
	 * @param multipartFile
	 *            文件
	 */
	@RequestMapping(value = "/qrcode/{content}", method = RequestMethod.GET)
	@ResponseBody
	public BackResult<UploadFile> getQrcode(@PathVariable("content") String content) {

		String date = DateUtils.toString(new Date());
		String returnPath = "qrcode/" + date;

		String filePath = location + returnPath;
		logger.info("文件保存路径={}", filePath);
		// 相对路径
		String path = "imageShow/";
		String fileName = null;

		try {
			fileName = QRcodeUtil.createQrCode(filePath, content, 900, "JPEG", returnPath);

			// 拼接回显的绝对路径
			String fileUrl = path + fileName;
			UploadFile uf = new UploadFile();
			uf.setFileName("qrcode");
			uf.setFileUrl(fileUrl);

			logger.info("返回值：{}", fileUrl.toString());
			if (StringUtils.isBlank(fileName)) {
				logger.info("文件返回值为空");
				return BackResult.result(false);
			}
			return BackResult.success(uf);
		} catch (WriterException e) {
			return BackResult.result(false);
		} catch (IOException e) {
			return BackResult.result(false);
		}
	}

}
