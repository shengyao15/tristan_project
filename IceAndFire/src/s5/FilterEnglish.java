package s5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FilterEnglish {
	public static void main(String[] args) throws Exception {
		String fileName = "D:\\tristan\\ice-文本\\列王的纷争";
		File file = new File(fileName);
		File[] files = file.listFiles();

		// 数组也可以用增加型的for 来迭代
		for (File fs : files) {
			System.out.println();
			System.out.println("----------" + fs.getName() + "----------");
			// 解决乱码问题
			// File -- FileInputStream -- InputStreamReader -- BufferedReader --
			// readLine()
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					fs.getAbsoluteFile()), "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			String temp = "";
			int i = 0;
			while ((temp = br.readLine()) != null) {
				filterEnglish(temp);
			}
		}
	}

	private static void filterEnglish(String name) throws Exception {
		for (int i = 0; i < name.length(); i++) {

			if (String.valueOf(name.charAt(i)).getBytes("UTF-8").length < 3) {
				char s = name.charAt(i);
				System.out.print(s);
				if (i + 1 < name.length()
						&& String.valueOf(name.charAt(i + 1)).getBytes("UTF-8").length >= 3) {
					System.out.println();
				}
			}
		}
	}
}
