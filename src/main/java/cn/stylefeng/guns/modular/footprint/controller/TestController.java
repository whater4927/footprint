/**
 * Project Name:Guns-Medical-master
 * File Name:QueryController.java
 * Package Name:cn.stylefeng.guns.modular.gs.controller
 * Date:2019年4月17日下午3:34:36
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.footprint.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.modular.footprint.util.ImageDemo;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * ClassName:QueryController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2019年4月17日 下午3:34:36 <br/>
 * 
 * @author whater
 * @version
 * @since JDK 1.8
 * @see
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
	private String PREFIX = "/footprint/test/";
	@Autowired
	private INoService noService ;
	@Autowired
    private GunsProperties gunsProperties;
	@GetMapping("/test1")
	public String test1() {
		return PREFIX + "test1.html";
	}

	@GetMapping("/test2")
	public String test2() {
		return PREFIX + "test2.html";
	}
	@GetMapping("/test3")
	public String test3() {
		return PREFIX + "test3.html";
	}
	
	@GetMapping("/test4")
	public String test4() {
		return PREFIX + "test4.html";
	}
	
	
	@PostMapping(value = "/get1",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage() throws IOException {
        return ImageIO.read(new FileInputStream(new File("C:\\Users\\mayn\\git\\footprint\\src\\main\\webapp\\static\\footprint-img\\4\\K2301020000222015050082.jpg")));
    }
	
	@GetMapping(value = "/get2",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage2() throws IOException {
	    File file = new File("C:\\Users\\mayn\\git\\footprint\\src\\main\\webapp\\static\\footprint-img\\4\\K2301020000222015050082.jpg");
	    FileInputStream inputStream = new FileInputStream(file);
	    byte[] bytes = new byte[inputStream.available()];
	    inputStream.read(bytes, 0, inputStream.available());
	    return bytes;
	}
	
	
	@RequestMapping(value = "/get3",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage3(@RequestBody String url) throws IOException {
	    File file = new File("C:\\Users\\mayn\\git\\footprint\\src\\main\\webapp\\static\\footprint-img\\4\\K2301020000222015050082.jpg");
	    FileInputStream inputStream = new FileInputStream(file);
	    byte[] bytes = new byte[inputStream.available()];
	    inputStream.read(bytes, 0, inputStream.available());
	    return bytes;
	}
	@GetMapping(value = "/get4",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage4() throws IOException {
	    File file = new File("C:\\Users\\mayn\\git\\footprint\\src\\main\\webapp\\static\\footprint-img\\4\\K2301020000222015050082.jpg");
	    FileInputStream inputStream = new FileInputStream(file);
	    byte[] bytes = new byte[inputStream.available()];
	    inputStream.read(bytes, 0, inputStream.available());
	    return bytes;
	}

	@PostMapping(value = "/binaryImage")
	public String binaryImage(@RequestParam("url") String url) throws IOException {
//		ImageDemo.binaryImage(url);
	    return ImageDemo.binaryImage(gunsProperties.getFileUploadPath(),url);
	}
	
}
