package tristan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.StringUtils;

import com.tristan.web.po.User;

//省略import
public class UserConverter implements Converter<String, User> {

	@Override
	public User convert(String source) {
		
		System.out.println("user convert-------------");
		return new User();
	}
	
}


