/**
 * Project Name:footprint
 * File Name:ImageDemo.java
 * Package Name:cn.stylefeng.guns.modular.footprint.util
 * Date:2019年5月24日下午1:41:16
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.footprint.util;
/**
 * ClassName:ImageDemo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年5月24日 下午1:41:16 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageDemo {

	public void binaryImage() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/main/java/1.jpg");
		BufferedImage image = ImageIO.read(file);
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
			}
		}
		File newFile = new File(System.getProperty("user.dir") + "/src/main/java/2.jpg");
		ImageIO.write(grayImage, "jpg", newFile);
	}

	public void grayImage() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/main/java/3.jpg");
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
			}
		}

		File newFile = new File(System.getProperty("user.dir") + "/src/main/java/4.jpg");
		ImageIO.write(grayImage, "jpg", newFile);
	}

	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		ImageDemo demo = new ImageDemo();
		demo.binaryImage();
		demo.grayImage();
	}

}
