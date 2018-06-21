package com.dynastech.base.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class ImageCut {
	private static Logger logger = Logger.getLogger(ImageCut.class);
	/**
	 * 图片切割
	 * @param x 目标切片坐标 X轴起点
	 * @param y 目标切片坐标 Y轴起点
	 * @param w 目标切片 宽度
	 * @param h 目标切片 高度
	 * @throws Exception
	 */
	public static String cutImage(BufferedImage bi, int x, int y, int w, int h,
			int rotate) throws Exception {
		BufferedImage cutedImage = null;
		ImageFilter cropFilter;
		Image img;
		String base64Str = "";
		ByteArrayOutputStream out = null;
		try {
			int srcWidth=0;
			int srcHeight=0;
			if(rotate!=0){//用户旋转了图片
				bi = rotate(bi, rotate);
			}
			srcWidth = bi.getWidth();
			srcHeight = bi.getHeight();
			Image image = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);
			cropFilter = new CropImageFilter(x, y, w, h);
			img = Toolkit.getDefaultToolkit().createImage(
					new FilteredImageSource(image.getSource(), cropFilter));
			
			cutedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics g = cutedImage.getGraphics();
			g.setColor(Color.WHITE);//设置笔刷白色
			g.fillRect(0,0,x+w,y+h);//填充整个屏幕  
			g.drawImage(img, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			out = new ByteArrayOutputStream();
			ImageIO.write(cutedImage, "jpg", out);
			byte[] b = out.toByteArray();
			
			Base64 base64 = new Base64();
			base64Str =base64.encodeAsString(b);
		} catch (IOException e) {
			logger.error("",e);
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		return base64Str;
	}

	/**
	 * @param pic 图片的byte数组
	 * @param x
	 * @param y
	 * @param w 宽
	 * @param h 高
	 * @param rotate 旋转角度
	 * @return
	 * @throws Exception
	 */
	public static String cutByteImage(byte[] pic, int x, int y, int w, int h,
			int rotate) throws Exception {
		ByteArrayInputStream logoFileIn = new ByteArrayInputStream(pic);
		BufferedImage bi = ImageIO.read(logoFileIn);

		return cutImage(bi, x, y, w, h, rotate);
	}

	/**
	 * 图片旋转
	 * @param bufferedimage
	 * @param angle 角度，图片旋转角度
	 * @return
	 */
    public static BufferedImage rotate( BufferedImage bufferedimage,int angle){
        int width = bufferedimage.getWidth();
        int height = bufferedimage.getHeight();
        int new_w = 0, new_h = 0;
        int new_radian = angle;
        if (angle <= 90) {
            new_w = (int) (width * Math.cos(Math.toRadians(new_radian)) + height * Math.sin(Math.toRadians(new_radian)));
            new_h = (int) (height * Math.cos(Math.toRadians(new_radian)) + width * Math.sin(Math.toRadians(new_radian)));
        } else if (angle <= 180) {
            new_radian = angle - 90;
            new_w = (int) (height * Math.cos(Math.toRadians(new_radian)) + width * Math.sin(Math.toRadians(new_radian)));
            new_h = (int) (width * Math.cos(Math.toRadians(new_radian)) + height * Math.sin(Math.toRadians(new_radian)));
        } else if (angle <= 270) {
            new_radian = angle - 180;
            new_w = (int) (width * Math.cos(Math.toRadians(new_radian)) + height * Math.sin(Math.toRadians(new_radian)));
            new_h = (int) (height * Math.cos(Math.toRadians(new_radian)) + width * Math.sin(Math.toRadians(new_radian)));
        } else {
            new_radian = angle - 270;
            new_w = (int) (height * Math.cos(Math.toRadians(new_radian)) + width * Math.sin(Math.toRadians(new_radian)));
            new_h = (int) (width * Math.cos(Math.toRadians(new_radian)) + height * Math.sin(Math.toRadians(new_radian)));
        }
        BufferedImage toStore = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = toStore.createGraphics();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(angle), width / 2, height / 2);
        if (angle != 180) {
            AffineTransform translationTransform = findTranslation(affineTransform, bufferedimage, angle);
            affineTransform.preConcatenate(translationTransform);
        }
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, new_w, new_h);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawRenderedImage(bufferedimage, affineTransform);
        g.dispose();
        bufferedimage = toStore;
 
        return bufferedimage;
    }
    
    private static AffineTransform findTranslation(AffineTransform at, BufferedImage bi, int angle) {
        Point2D p2din, p2dout;
        double ytrans = 0.0, xtrans = 0.0;
        if (angle <= 90) {
            p2din = new Point2D.Double(0.0, 0.0);
            p2dout = at.transform(p2din, null);
            ytrans = p2dout.getY();
 
            p2din = new Point2D.Double(0, bi.getHeight());
            p2dout = at.transform(p2din, null);
            xtrans = p2dout.getX();
        }
        else if (angle <= 180) {
            p2din = new Point2D.Double(0.0, bi.getHeight());
            p2dout = at.transform(p2din, null);
            ytrans = p2dout.getY();
 
            p2din = new Point2D.Double(bi.getWidth(), bi.getHeight());
            p2dout = at.transform(p2din, null);
            xtrans = p2dout.getX();
 
        }
        else if (angle <= 270) {
            p2din = new Point2D.Double(bi.getWidth(), bi.getHeight());
            p2dout = at.transform(p2din, null);
            ytrans = p2dout.getY();
 
            p2din = new Point2D.Double(bi.getWidth(), 0.0);
            p2dout = at.transform(p2din, null);
            xtrans = p2dout.getX();
 
        } else {
            p2din = new Point2D.Double(bi.getWidth(), 0.0);
            p2dout = at.transform(p2din, null);
            ytrans = p2dout.getY();
            p2din = new Point2D.Double(0.0, 0.0);
            p2dout = at.transform(p2din, null);
            xtrans = p2dout.getX();
 
        }
        AffineTransform tat = new AffineTransform();
        tat.translate(-xtrans, -ytrans);
        return tat;
    }
    
     

 
    
    
    
}
