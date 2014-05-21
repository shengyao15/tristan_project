package s1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//下载天风证券的信息
public class TFStock {

	public static int startPage = 1;
	public static int endPage = 14;
	
	public static void main(String[] args) throws Exception {
		m5();
	}
	
	public static void m5() throws Exception {
		for(int i=startPage; i<=endPage; i++){
			System.out.println(i+"----------------------");
			m4(i);
		}
	}
	
	public static void m4(int page) throws Exception {
		String temp = "";
		List<News> list = new ArrayList<News>();
		HttpURLConnection conn = (HttpURLConnection) new URL(v1+page).openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((temp = br.readLine()) != null) {
			if (temp.contains("<li><a href=\"/detail.aspx")) {

				News news = new News();
				String t1 = temp.trim();
				String date = "";
				String name = "";
				String url = "";
				// <li><a href="/detail.aspx?cid=24435">城农商行扎堆冲锋上市 贷款成本风险较高成障碍
				// </a><span>2012-01-06</span></li>
				for (int i = 0; i < t1.length() - 5; i++) {
					if ((t1.charAt(i) == '<' && t1.charAt(i + 1) == '/')
							&& (t1.charAt(i + 2) == 's' && t1.charAt(i + 3) == 'p'
									&& t1.charAt(i + 4) == 'a' && t1.charAt(i + 5) == 'n')) {
						date = t1.substring(i - 10, i);
						news.setDate(date);
					}
					// </a>
					if ((t1.charAt(i) == '<' && t1.charAt(i + 1) == '/')
							&& (t1.charAt(i + 2) == 'a' && t1.charAt(i + 3) == '>')) {
						name = t1.substring(37, i);
						name = name.replace("\\", "");
						name = name.replace("\"", "");
						name = name.replace(":", " ");
						name = name.replace("：", " ");
						name = name.replace("“", " ");
						name = name.replace("”", " ");
						name = name.replace("?", " ");
						name = name.replace("<", " ");
						name = name.replace(">", " ");
						name = name.replace("*", " ");
						name = name.replaceAll("","");
						news.setName(name);
					}
					// ">
					if ((t1.charAt(i) == '"' && t1.charAt(i + 1) == '>')) {
						url = t1.substring(14, i);
						news.setUrl(url);
					}
				}
				list.add(news);
//				if (list.size() > 0) {
//					break;
//				}
			}
		}
		
		for (News news : list) {
			String dateFolder = news.getDate().substring(0,7);
			File existFolder =  new File("stockNews/"+dateFolder);
			if(!existFolder.isDirectory()){
				existFolder.mkdirs();
			}
			
			File fs2 = new File("stockNews/"+dateFolder+"/"+news.getDate()+"      "+news.getName()+".txt");
			BufferedWriter bw = null;
			try{
				 bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fs2),
				"UTF-8"));
			}catch(FileNotFoundException e){
				File fs3 = new File("stockNews/"+news.getDate()+"      XXX.txt");
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fs3),
				"UTF-8"));
				e.printStackTrace();
			}
			
			
			HttpURLConnection conn2 = (HttpURLConnection) new URL(v2 + news.getUrl())
					.openConnection();
			BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
			String temp2 = "";
			boolean b = false;
			String content = "";
			while ((temp2 = br2.readLine()) != null) {
				if (temp2.contains("<div class=\"footer\">")) {
					break;
				}
				if (temp2.contains("<!--<p></p>")) {
					break;
				}
				if (b) {
					content = temp2;
					Pattern p = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
					Matcher m = p.matcher(content);
					content = m.replaceAll("");
					
					content = content.replace("&nbsp;", "");
					content = content.replace("	", "");
					content = content.replace("　　", "");
					if(content != null && !"".equals(content)){
						bw.write(content);
						bw.write("\r\n");
						
					}
				}
				if (temp2.contains("<div class=\"m_right_content\">")) {
					b = true;
				}
			}
			bw.close();
		}
	}


	public static String v1 = "http://www.tfzq.com/ContentList.aspx?nodeid=43&page=";
	public static String v2 = "http://www.tfzq.com/";
	public static Pattern p = Pattern.compile("<[^>]+>([^<]*)</[^>]+>");
}

