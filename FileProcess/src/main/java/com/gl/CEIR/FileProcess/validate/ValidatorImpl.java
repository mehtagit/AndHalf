package com.gl.CEIR.FileProcess.validate;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.exception.NullArgumentsNotAllowedException;
import com.gl.CEIR.FileProcess.regex.Regex;

@Component
public class ValidatorImpl implements Validator {
	
	@Override
	public boolean isNull(String target) {
		// TODO Auto-generated method stub
		return Objects.isNull(target);
	}
	
	@Override
	public boolean isEmpty(String target) throws NullArgumentsNotAllowedException {
		if(Objects.isNull(target)) {
			 throw new NullArgumentsNotAllowedException();
		}
		
		return target.isEmpty();
	}

	@Override
	public boolean caseInsensitiveContains(List<String> pool, String target) throws NullArgumentsNotAllowedException {
		
		if(Objects.isNull(pool) || Objects.isNull(target)) {
			 throw new NullArgumentsNotAllowedException();
		}
			
		for(String poolValue : pool) {
			if(poolValue.equalsIgnoreCase(target)) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}

	@Override
	public boolean contains(List<Integer> pool, Integer target) throws NullArgumentsNotAllowedException {
		
		if(Objects.isNull(pool) || Objects.isNull(target)) {
			 throw new NullArgumentsNotAllowedException();
		}
			
		for(Integer poolValue : pool) {
			if(poolValue == target) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}	

	@Override
	public boolean matchLength(String target, int length) throws NullArgumentsNotAllowedException {
		// TODO Auto-generated method stub
		
		if(Objects.isNull(target)) {
			 throw new NullArgumentsNotAllowedException();
		}
		
		return target.length() == length;
	}
	
	@Override
	public boolean lengthRange(String target, int min, int max) throws NullArgumentsNotAllowedException {
		if(Objects.isNull(target)) {
			 throw new NullArgumentsNotAllowedException();
		}
		return target.length() >= min && target.length() <= max;
	}

	@Override
	public boolean isNumeric(String target) throws NullArgumentsNotAllowedException {
		if(Objects.isNull(target)) {
			 throw new NullArgumentsNotAllowedException();
		}
		
		return target.matches(Regex.NUMERIC);
	}
	
	@Override
	public boolean isAlphabetical(String target) throws NullArgumentsNotAllowedException {
		if(Objects.isNull(target)) {
			 throw new NullArgumentsNotAllowedException();
		}
		
		return target.matches(Regex.ALPHABETICAL);
	}
	
	@Override
	public boolean isAlphaNumeric(String target) throws NullArgumentsNotAllowedException {
		if(Objects.isNull(target)) {
			 throw new NullArgumentsNotAllowedException();
		}
		
		return target.matches(Regex.ALPHA_NUMERIC);
	}
	
	public static void main(String[] args)
	{
		String s = "456";
		
		try {
			System.out.println("IsAlphaNumeric: " + new ValidatorImpl().isNumeric(s));
		} catch (NullArgumentsNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}