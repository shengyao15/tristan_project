package z;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		System.out.println("xxxxxxxxxx2");
		return true;
	}

	public void validate(Object target, Errors errors) {
		System.out.println("xxxxxxxxxxx");
	}
}
