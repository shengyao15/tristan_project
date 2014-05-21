package s3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class IceAndFire5 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws Exception {

		String book = "http://book.kanunu.org/book3/8169/";
		String name = "魔龙的狂舞";
		
		HttpGet httpost = new HttpGet(book);
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();

		HttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(),"GBK"));
		String temp = "";
		
		Map<String,String> map = new HashMap<String,String>();
		
		while ((temp = br.readLine()) != null) {
			
			if(temp.contains("<td><a href=") || temp.contains("<td width=\"25%\"><a href=")){
				
				String key = filterChinese2(temp);
				
				int index = temp.indexOf("<a href") + 9;
				String value = temp.substring(index, index+11);
				
				map.put(key, value);
			}
			
		}
		
		for (String key : map.keySet()) {
			HttpGet httpost1 = new HttpGet(book+map.get(key));
			org.apache.http.client.HttpClient httpclient1 = new DefaultHttpClient();

			HttpResponse response1 = httpclient1.execute(httpost1);
			HttpEntity entity1 = response1.getEntity();
			BufferedReader br1 = new BufferedReader(new InputStreamReader(entity1.getContent(),"GBK"));
			String temp1 = "";
			
			boolean flag = false;
			StringBuffer sb1 = new StringBuffer();
			sb1.append(key + "\r\n");
			
			File fs2 = new File("D:/" + name+"/"+key);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fs2), "UTF-8"));
			
			
			
			while ((temp1 = br1.readLine()) != null) {
				
				
				if(temp1.contains("<p>")){
					flag = true;
				}
				
				if(flag && temp1.length()>8){
					
					temp1 = temp1.replaceAll("<br />", "");
					temp1 = temp1.replaceAll("&nbsp;", "");
					temp1 = temp1+"\r\n";
					sb1.append(temp1);
					
				}
				
				if(temp1.contains("</p>")){
					break;
				}
				
			}
			
			//System.out.println(sb1.toString());
			bw.write(sb1.toString());
			bw.close();
			
		}
	}

	
	static private  String filterChinese2(String name) throws Exception {  
			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < name.length(); i++) {  
	  
	            if (String.valueOf(name.charAt(i)).getBytes("UTF-8").length >= 3) {  
	            	sb.append(name.charAt(i));
	            }  
	        }  
	        
	        return sb.toString();
	    }  
}
