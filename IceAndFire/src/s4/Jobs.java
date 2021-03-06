package s4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Jobs {

	
	public static void main(String[] args) throws Exception {
		
		
		String book = "http://book.kanunu.org/book/4340/";
		String name = "jobs";
	
		
		HttpGet httpost = new HttpGet(book);
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();

		HttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(),"GBK"));
		String temp = "";
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		int i = 1;
		while ((temp = br.readLine()) != null) {
			
			if(temp.contains("<td><a href=")){
				
				String key = filterChinese2(temp);
				
				int index = temp.indexOf("<a href") + 9;
				String value = temp.substring(index, index+10);
				
				map.put(StringUtils.leftPad(String.valueOf(i), 3, "0")+key, value);
				
				i++;
				
			}
			
		}
		
//		for (String key : map.keySet()) {
//			System.out.println(key + " : " + map.get(key));
//		}
		
		
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
			
			if(key==null || key.equals("")){
				key="x";
			}
			
			File fs2 = new File("D:/" + name+"/"+key);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fs2), "UTF-8"));
			
			
			
			while ((temp1 = br1.readLine()) != null) {
				
				if(temp1.contains("<p>")){
					flag = true;
				}
				
				if(flag && temp1.length()>8){
					
					temp1 = temp1.replaceAll("<br />", "");
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
