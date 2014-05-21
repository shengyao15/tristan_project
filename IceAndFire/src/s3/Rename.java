package s3;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Rename {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		String name = "权力的游戏";
		String name = "列王的纷争";
//		String name = "冰雨的风暴";
//		String name = "群鸦的盛宴";
//		String name = "魔龙的狂舞";
		
		File f = new File("D:/"+name);
		File[] fs = f.listFiles();
		
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < fs.length; i++) {
			set.add(fs[i].getName());
		}
		
		String name1 = "C:\\Users\\tristan\\"+name+"\\";
		File f1 = new File(name1);
		File[] fs1 = f1.listFiles();
		for (int i = 0; i < fs1.length; i++) {
			String fname = fs1[i].getName();
			int index = fname.indexOf(".");
			fname = fname.substring(0, index);
			if(fname.trim().equals("")){
				continue;
			}
			System.out.println(fname);
			for (String s : set) {
				if(s.startsWith(fname)){
					fs1[i].renameTo(new File(name1 + s + ".mp3"));
				}
			}
			
			
		}
		
		
	}

}
