/**
 * Project Name:Test
 * File Name:NewImageUtils.java
 * Package Name:rsa
 * Date:2019年5月23日上午11:15:42
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.footprint.util;
/**
 * ClassName:NewImageUtils <br/>
 * Function: TODO 图片重叠. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年5月23日 上午11:15:42 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class NewImageUtils {
    /**
     * 
     * @Title: 构造图片
     * @Description: 生成水印并返回java.awt.image.BufferedImage
     * @param file
     *            源文件(图片)
     * @param waterFile
     *            水印文件(图片)
     * @param x
     *            距离右下角的X偏移量
     * @param y
     *            距离右下角的Y偏移量
     * @param alpha
     *            透明度, 选择值从0.0~1.0: 完全透明~完全不透明
     * @return BufferedImage
     * @throws IOException
     */
    public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
        // 获取底图
        BufferedImage buffImg = ImageIO.read(file);
        // 获取层图
        BufferedImage waterImg = ImageIO.read(waterFile);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
        int waterImgHeight = waterImg.getHeight();// 获取层图的高度
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        return buffImg;
    }

    /**
     * 输出水印图片
     * 
     * @param buffImg
     *            图像加水印之后的BufferedImage对象
     * @param savePath
     *            图像加水印之后的保存路径
     */
    private void generateWaterFile(BufferedImage buffImg, String savePath) {
        int temp = savePath.lastIndexOf(".") + 1;
        try {
            ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 
     * @param args
     * @throws IOException
     *             IO异常直接抛出了
     * @author bls
     */
    public static void main(String[] args) throws IOException {
        String sourceFilePath = "C:/Users/whater/Desktop/img/2.jpg";
        String waterFilePath = "C:/Users/whater/Desktop/img/4.jpg";
        String saveFilePath = "C:/Users/whater/Desktop/img/1.jpg";
        NewImageUtils newImageUtils = new NewImageUtils();
        // 构建叠加层
        BufferedImage buffImg = NewImageUtils.watermark(new File(sourceFilePath), new File(waterFilePath), 0, 0, 1.0f);
        // 输出水印图片
        newImageUtils.generateWaterFile(buffImg, saveFilePath);
        
        
        
        
    }
}
