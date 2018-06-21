package com.dynastech.base.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成 工具类
 * 
 * @author yuan
 * 
 */
public class QrCodeUtil {

	public static final String QRCODE_DEFAULT_CHARSET = "UTF-8";
	public static final int QRCODE_DEFAULT_HEIGHT = 300;
	public static final int QRCODE_DEFAULT_WIDTH = 300;
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private static final String base64Logo = "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";

	/**
	 * 创建不带logo的二维码
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] createQRCode(String data, String title) {
		return createQRCode(data, QRCODE_DEFAULT_WIDTH, QRCODE_DEFAULT_HEIGHT,
				title);
	}

	/**
	 * 创建不带logo的二维码
	 * 
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 */
	public static byte[] createQRCode(String data, int width, int height,
			String title) {

		BufferedImage image = enCode(data, width, height, title);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] b = out.toByteArray();

		return b;
	}

	/**
	 * 创建带logo的二维码
	 * 
	 * @param data
	 * @param logoFile
	 * @return
	 */
	public static byte[] createQRCodeWithLogo(String data, String title) {

		Base64 d = new Base64();
		byte[] logoFile = d.decode(base64Logo);

		return createQRCodeWithLogo(data, QRCODE_DEFAULT_WIDTH,
				QRCODE_DEFAULT_HEIGHT, logoFile, title);
	}

	/**
	 * 创建带logo的二维码
	 * 
	 * @param data
	 * @param width
	 * @param height
	 * @param logoFile
	 * @return
	 */
	public static byte[] createQRCodeWithLogo(String data, int width,
			int height, byte[] logoFile, String titie) {
		ByteArrayInputStream logoFileIn = new ByteArrayInputStream(logoFile);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			BufferedImage qrcode = enCode(data, width, height, titie);

			BufferedImage logo = ImageIO.read(logoFileIn);
			int deltaHeight = height - logo.getHeight();
			int deltaWidth = width - logo.getWidth();
			BufferedImage combined = new BufferedImage(height, width,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) combined.getGraphics();
			g.drawImage(qrcode, 0, 0, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					1f));

			// 增加logo
			g.drawImage(logo, (int) Math.round(deltaWidth / 2),
					(int) Math.round(deltaHeight / 2), null);
			
			ImageIO.write(combined, "png", out);
			byte[] b = out.toByteArray();

			return b;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				logoFileIn.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static BufferedImage enCode(String data, int width, int height,
			String title) {

		BitMatrix matrix;

		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hint.put(EncodeHintType.CHARACTER_SET,
				QrCodeUtil.QRCODE_DEFAULT_CHARSET);
		try {
			matrix = new MultiFormatWriter().encode(
					new String(data.getBytes(QRCODE_DEFAULT_CHARSET),
							QRCODE_DEFAULT_CHARSET), BarcodeFormat.QR_CODE,
					width, height, hint);

			BufferedImage image = toBufferedImage(matrix);

			Graphics2D g = (Graphics2D) image.getGraphics();
			g.setColor(Color.red);
			Font font = new Font("宋体", Font.PLAIN, 20);
			g.setFont(font);
			// 获取title值的宽度 用于title居中
			int titleWidth = g.getFontMetrics().stringWidth(title);

			g.drawString(title, (int) Math.round((width - titleWidth) / 2), 20);

			return image;
		} catch (WriterException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 生成活动图片,(带主题)，暂无用 2016-12-14
	 * 
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 */
	public static byte[] createImagerForTitle(String data, String title, String timeName, String sTime ) {

		Base64 d = new Base64();
		byte[] logoFile = d.decode(data);

		ByteArrayInputStream logoFileIn = new ByteArrayInputStream(logoFile);

		BufferedImage logo;
		try {

			logo = ImageIO.read(logoFileIn);

			int height_1 = logo.getHeight();
			int width_1 = logo.getWidth();

			BufferedImage combined = new BufferedImage(width_1, height_1,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) combined.getGraphics();

			g.drawImage(logo, 0, 0, null);
		 

			g.setColor(Color.white);
			Font font = new Font("", Font.BOLD, 25);
			g.setFont(font);
			// 获取title值的宽度 用于title居中
			int titleWidth = g.getFontMetrics().stringWidth(title);
			int titleHeight = g.getFontMetrics().getHeight();// 文字高度

			int xname = (int) Math.round(width_1 - titleWidth - (width_1 - titleWidth) / 4);// 名称的x坐标
			int yname = (int) Math.round(height_1 - titleHeight - (height_1 - titleHeight) / 4);// 名称的y坐标
			
			int xname_1=(int) Math.round(titleWidth/ 10);
			
			g.drawString(title,	xname,yname);

			g.setColor(Color.WHITE);
			
			Stroke stroke = g.getStroke(); //得到当前的画刷
			float thick = 2f; //设置画刷的粗细为 0.5
			g.setStroke(new BasicStroke(thick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)); //设置新的画刷
		 
			g.drawRect(xname - xname_1,yname - titleHeight, titleWidth + xname_1*2, titleHeight + 10); // 边框，（坐标，宽和高）

			g.setStroke(stroke); //将画刷复原
			
			// 设置活动标题
			font = new Font("宋体", Font.BOLD, 15);
			g.setFont(font);
			// 获取title值的宽度 用于title居中
			titleWidth = g.getFontMetrics().stringWidth(timeName);
			titleHeight = g.getFontMetrics().getHeight();// 文字高度
			int xtname = xname + (int) Math.round((titleWidth + 20) / 2);// 时间主题开始位置
			g.drawString(timeName, xtname, (int) Math.round(height_1 / 5));

			// 设置活动时间
			font = new Font("宋体", Font.BOLD, 12);
			g.setFont(font);
			// 获取title值的宽度 用于title居中
			titleWidth = g.getFontMetrics().stringWidth(sTime);
			titleHeight = g.getFontMetrics().getHeight();// 文字高度
			int xtime = xname ;// 时间开始位置
			g.drawString(sTime, xtime, (int) Math.round(height_1 / 5+titleHeight));

			g.dispose();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(combined, "png", out);
			byte[] b = out.toByteArray();
			return b;

		} catch (IOException e) {
			logoFile = d.decode("");
			e.printStackTrace();
		}
		return logoFile;

	}
	
    
}
