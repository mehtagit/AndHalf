package com.gl.CEIR.FileProcess.validate;

import java.util.Arrays;
import java.util.List;

import com.gl.CEIR.FileProcess.exception.NullArgumentsNotAllowedException;

public interface Validator {

	public List<String> deviceTypes = Arrays.asList(new String[] {"Handheld", "Mobile Phone/Feature phone", "Vehicle", 
			"Portable(include PDA)", "Module", "Dongle", "WLAN Router", "Modem", "Smartphone", "Connected Computer", 
			"Tablet", "e-Book"});

	public List<String> deviceIdTypes = Arrays.asList(new String[] {"IMEI", "ESN", "MEID"});

	public List<String> yesNos = Arrays.asList(new String[] {"YES", "NO", "Y", "N"});

	public List<String> deviceStatus = Arrays.asList(new String[] {"New", "Used", "Refurbished"});

	public boolean isEmpty(String target) throws NullArgumentsNotAllowedException;

	public boolean isNull(String target);

	public boolean caseInsensitiveContains(List<String> pool, String target) throws NullArgumentsNotAllowedException;

	public boolean contains(List<Integer> pool, Integer target) throws NullArgumentsNotAllowedException;

	public boolean matchLength(String target, int length) throws NullArgumentsNotAllowedException;
	
	public boolean lengthRange(String target, int min, int max) throws NullArgumentsNotAllowedException;

	public boolean isNumeric(String target) throws NullArgumentsNotAllowedException;

	public boolean isAlphabetical(String target) throws NullArgumentsNotAllowedException;

	public boolean isAlphaNumeric(String target) throws NullArgumentsNotAllowedException;

}
