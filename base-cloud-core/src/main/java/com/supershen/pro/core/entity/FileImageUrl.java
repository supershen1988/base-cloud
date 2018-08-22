package com.supershen.pro.core.entity;
/**
 * 上传图片返回结果实体
 * @author gshen
 * @创建时间 2018-07-23 13:07
 * @copyright © 2018 
 */
public class FileImageUrl {
	/** 存放图片路径*/
	private String imageUrl;
	/** 存放缩略图路径*/
	private String thumbnailImageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}

	public void setThumbnailImageUrl(String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	@Override
	public String toString() {
		return "FileImageUrl [imageUrl=" + imageUrl + ", thumbnailImageUrl=" + thumbnailImageUrl + ", getImageUrl()="
				+ getImageUrl() + ", getThumbnailImageUrl()=" + getThumbnailImageUrl() + "]";
	}
	
	
}
