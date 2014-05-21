package picture;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageDemo {
	
	public static String parse(BufferedImage img){
		int width = img.getWidth();
		int height = img.getHeight();

		StringBuilder sb = new StringBuilder();

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				int RGB = img.getRGB(i, j);
				int red = (RGB >> 16) & 0xff;
				if (red < 100) {
					sb.append("1");
				} else {
					sb.append("0");
				}
			}
		}
		
		String s = sb.toString();
		String s2 = s;
//		System.out.println("图片的String码： " + s);
//		System.out.println("--------------");
//		for (int i = 0; i < height; i++) {
//			System.out.println(s2.substring(0, width));
//			s2 = s2.substring(width);
//		}
		
		return s;
	}
	
	public static void calResult(String s){
		if(s.indexOf(n0)>=0){
			System.out.print(" 0");
		}else if(s.indexOf(n1)>=0){
			System.out.print(" 1");
		}else if(s.indexOf(n2)>=0){
			System.out.print(" 2");
		}else if(s.indexOf(n3)>=0){
			System.out.print(" 3");
		}else if(s.indexOf(n4)>=0){
			System.out.print(" 4");
		}else if(s.indexOf(n5)>=0){
			System.out.print(" 5");
		}else if(s.indexOf(n6)>=0){
			System.out.print(" 6");
		}else if(s.indexOf(n7)>=0){
			System.out.print(" 7");
		}else if(s.indexOf(n8)>=0){
			System.out.print(" 8");
		}else if(s.indexOf(n9)>=0){
			System.out.print(" 9");
		}
	}
	
	public static void main(String[] args) throws Exception {

		//File file = new File("c:/image/num/nn" + n + ".jpg");
		File file = new File("gen/1.jpg");
		
		BufferedImage imgOrg = ImageIO.read(file);
		BufferedImage img = imgOrg.getSubimage(8, 0, 10, 20);
		BufferedImage img2 = imgOrg.getSubimage(20, 0, 10, 20);
		BufferedImage img3 = imgOrg.getSubimage(35, 0, 10, 20);
		BufferedImage img4 = imgOrg.getSubimage(45, 0, 10, 20);
		
		System.out.println("图片解析结果：");
		
		String s = parse(img);
		calResult(s);
		
		String s2 = parse(img2);
		calResult(s2);
		
		String s3 = parse(img3);
		calResult(s3);
		
		String s4 = parse(img4);
		calResult(s4);		
		
	}

	
	private static String n0 = "000000000000011110000011111100011100111001100001100110000110011000011001100001100110000110011000011001100001100111001110001111110000011110000000000000";
	private static String n1 = "000000000000000110000000111000000111100000110110000010011000000001100000000110000000011000000001100000000110000000011000000001100000000110000000000000";
	private static String n2 = "000000000000011110000011111100011100111001100001100000000110000000011000000011000000011000000011000000011000000011000000011111111001111111100000000000";
	private static String n3 = "000000000000011100000011111000011000110000000011000000001100000011100000001111000000000110000000011001100001100110000110001111110000011110000000000000";
	private static String n4 = "000000000000000110000000111000000011100000011110000011011000001101100001100110001100011000111111111011111111100000011000000001100000000110000000000000";
	private static String n5 = "000000000000111111000011111100001100000001100000000110111000011111110001100001100000000110000000011001100001100110000110001111110000011110000000000000";
	private static String n6 = "000000000000011110000011111100001100011001100000000110000000011011100001111111000111000110011000011001100001100011000110001111110000011110000000000000";
	private static String n7 = "000000000001111111100111111110000000010000000011000000011000000001100000001100000000110000000011000000001100000001100000000110000000011000000000000000";
	private static String n8 = "000000000000011110000011111100011000011001100001100110000110001111110000111111000110000110011000011001100001100110000110001111110000011110000000000000";
	private static String n9 = "000000000000011110000011111100011000011001100001100110000110011000111000111111100001110110000000011000000001100110001100001111110000011110000000000000";
	private static String n = "9";
}

