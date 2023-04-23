package com.bridgelabz.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FileModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nameImage;
	private String imageType;
	@Lob
	private byte[]data;
	
	public FileModel() {
		super();
	}

	public FileModel( String nameImage, String imageType, byte[] data) {
		super();
		this.nameImage = nameImage;
		this.imageType = imageType;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameImage() {
		return nameImage;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FileModel [id=" + id + ", nameImage=" + nameImage + ", imageType=" + imageType + ", data="
				+ Arrays.toString(data) + "]";
	}
	
}
