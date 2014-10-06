package com.tristan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class CoreServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(CoreServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戮
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			logger.info("wechat validate");
			out.print(echostr);
		}

		out.close();
		out = null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("CoreServlet start...");
		InputStream is = request.getInputStream();
		// 取HTTP请求流长度
		int size = request.getContentLength();
		// 用于缓存每次读取的数据
		byte[] buffer = new byte[size];
		// 用于存放结果的数组
		byte[] xmldataByte = new byte[size];
		int count = 0;
		int rbyte = 0;
		// 循环读取
		while (count < size) {
			// 每次实际读取长度存于rbyte中
			rbyte = is.read(buffer);
			for (int i = 0; i < rbyte; i++) {
				xmldataByte[count + i] = buffer[i];
			}
			count += rbyte;
		}
		is.close();
		String requestStr = new String(xmldataByte, "UTF-8");

		logger.info(requestStr);
		
		try{  
            manageMessage(requestStr,request,response);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        
		logger.info("CoreServlet end");
	}

	private void manageMessage(String requestStr, HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		logger.info("manageMessage start...");
		String responseStr ="";

		try {
			XMLSerializer xmlSerializer = new XMLSerializer();
			JSONObject jsonObject = (JSONObject) xmlSerializer.read(requestStr);
			String msgtype = jsonObject.getString("MsgType");
			jsonObject.put("Content", "welcome to send us question: " + jsonObject.getString("Content") + ". We will deal with later");

			responseStr = creatRevertText(jsonObject);// 创建XML
			logger.info("responseStr:" + responseStr);
			OutputStream os = response.getOutputStream();
			os.write(responseStr.getBytes("UTF-8"));
			logger.info("manageMessage end");
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}

	}

	private String creatRevertText(JSONObject jsonObject) {
		String fromUserName = (String) jsonObject.get("FromUserName");
		String toUserName = (String) jsonObject.get("ToUserName");
		String content = (String) jsonObject.get("Content");
		
		logger.info("Content: " + content);
		
		StringBuffer revert = new StringBuffer();
		revert.append("<xml>");
		revert.append("<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>");
		revert.append("<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>");
		long d1 = (new Date()).getTime(); //1412585103538
		revert.append("<CreateTime>" + String.valueOf(d1).substring(0, 10) + "</CreateTime>");
		revert.append("<MsgType><![CDATA[text]]></MsgType>");
		revert.append("<Content><![CDATA[" + content + "]]></Content>");
		revert.append("</xml>");
		return revert.toString();
	}
	
	public static void main(String[] args) {
		String s ="<xml><URL><![CDATA[http://tristan.duapp.com/CoreServlet]]></URL><ToUserName><![CDATA[abc]]></ToUserName><FromUserName><![CDATA[abc]]></FromUserName><CreateTime>111</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[1111]]></Content><MsgId>1111</MsgId></xml>";
		XMLSerializer xmlSerializer=new XMLSerializer();  
        JSONObject jsonObject =(JSONObject) xmlSerializer.read(s);  
        logger.info(jsonObject.getString("Content"));
        logger.info(jsonObject.getString("ToUserName"));
        logger.info(jsonObject.containsKey("Event"));
        //logger.info(jsonObject.getString("Event"));
	}
}
