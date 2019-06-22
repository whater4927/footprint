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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.modular.footprint.service.IFootprintService;
import cn.stylefeng.guns.modular.footprint.util.ImageDemo;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;

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
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	private String PREFIX = "/footprint/test/";
	@Autowired
	private INoService noService ;
	@Autowired
    private GunsProperties gunsProperties;
	
	@RequestMapping("/test1")
	public String test1() {
		return PREFIX + "test1.html";
//		return "/footprint/shoesDemo/shoesDemo.html";
	}

	@GetMapping("/test2")
	public String test2() {
		return PREFIX + "test2.html";
	}
	@GetMapping("/test3")
	public String test3() {
		return PREFIX + "test3.html";
	}
	@Autowired
    private IFootprintService footprintService;
	@GetMapping("/test4")
	public String test4() {
		/*File file = new File("C:\\Users\\mayn\\git\\footprint\\doc\\足迹照片");
		String targetPatn = "D:/tmp/" ; 
		
		File[] files = file.listFiles();
		for (File file2 : files) {
			File[] images = file2.listFiles() ;
			for (File image : images) {
				
				String pictureName = CommonUtil.UUID() + "." + ToolUtil.getFileSuffix(image.getName());
				try {
					FileUtil.copy(image, new File(targetPatn+pictureName));
					Footprint footprint = new Footprint();
					footprint.setFpNo(noService.busiNo("F"));
					footprint.setOriginalImg(pictureName);
			    	EntityUtils.setCreateInfo(footprint);
			        footprintService.insert(footprint);
			        System.out.println(image.getName());
				} catch (Exception e) {
					System.err.println(image.getName());
					e.printStackTrace();
				}
			}
		}*/
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
	
	@PostMapping("/imageshangchuan")
	public String imageshangchuan(@RequestPart("xxx") MultipartFile multipartFile, Model model, HttpServletRequest request) {
		String url = request.getParameter("url");
        if (!multipartFile.getContentType().contains("image/")) {
            model.addAttribute("err", "只能是图片文件！");
            return "/inputfile";
        }
        if (multipartFile.getSize() > 1024 * 1024 * 5) {
            model.addAttribute("err", "只能是5M以下！");
            return "/inputfile";
        }
        //取得相对路径
        String path = gunsProperties.getFileUploadPath();
//        String pictureName = CommonUtil.UUID() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
//            String rekativePath = makeImagePath(path, multipartFile.getOriginalFilename());
            File file = new File(path+url);
            file.delete();
//          file.getParentFile().mkdir();
            multipartFile.transferTo(file);
        } catch (IOException e) {
            model.addAttribute("err", "上传失败，请重试");
            return "/inputfile";
        }
        return "success";
     }
        private String makeImagePath (String basePath, String fileName){
            Date date = new Date();
            //String[] filename = simpleFile(fileName);
            return String.format("%s%s%s%supload_%s_%s.%s",
                    basePath,
                    File.separator,
                    new SimpleDateFormat("yyyyMMdd").format(date),
                    File.separator,
                    "11",
                    new SimpleDateFormat("hhmmss").format(date),
                    "jpg"
            );
        }
        private String[] simpleFile (String file){
            int sum = file.lastIndexOf(".");
            return new String[]{
                    file.substring(0, sum),
                    file.substring(sum + 1)
            };
        }
	
}
