package z;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class MyTest {
	@Test  
	public void testStringToPhoneNumberConvert() {  
	    DefaultConversionService conversionService = new DefaultConversionService();  
	    conversionService.addConverter(new StringToPhoneNumberConverter());  
	      
	    String phoneNumberStr = "010-12345678";  
	    PhoneNumberModel phoneNumber = conversionService.convert(phoneNumberStr, PhoneNumberModel.class);  
	          
	    Assert.assertEquals("010", phoneNumber.getAreaCode());  
	}  
	
	@Test  
	public void testOtherConvert() {  
	    DefaultConversionService conversionService = new DefaultConversionService();  
	      
	    //"1"--->true（字符串“1”可以转换为布尔值true）  
	    Assert.assertEquals(Boolean.valueOf(true), conversionService.convert("1", Boolean.class));  
	      
	    //"1,2,3,4"--->List（转换完毕的集合大小为4）  
	    Assert.assertEquals(4, conversionService.convert("1,2,3,4", List.class).size());  
	}  
}
