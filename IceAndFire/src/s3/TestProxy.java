package s3;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.ProxyHost;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestProxy {
	public static void main(String[] args) throws Exception {
		  // 创建 HttpClient 的实例  
        HttpClient httpClient = new HttpClient();  
  
        // 代理的主机  
        ProxyHost proxy = new ProxyHost("98.126.62.146", 80);  
  
        // 使用代理  
       httpClient.getHostConfiguration().setProxyHost(proxy);  
        
        HttpMethod getMethod = new GetMethod("http://www.youtube.com");  
        
        int status = httpClient.executeMethod(getMethod);  
        
        System.out.println(status);
        
        String body = getMethod.getResponseBodyAsString();  
        System.out.println(body);
	}
}
