package com.gl.ceir.gsma_thirdparty.service;

import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class EncriptonService {
	static Cipher cipher;

	static String APIKey = "A459000091616";
	static String Password = "7v50d1501";
	static String Salt_String = "DCDW";
	static String Organization_Id = "505";
	static String Secretkey = "GSMAESencryption";

	public static void main(String[] args) {
		String deviceId = "869187035312995";
		String abc = getSHA(APIKey + Password + deviceId);
		String auth = encrypt(Salt_String + Organization_Id + "=" + abc, Secretkey);
		System.out.println("the auth key is =" + auth);
	}

	public static String getSHA(String stringToHash) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(stringToHash.getBytes("UTF-8"), 0, stringToHash.length());

			byte byteData[] = md.digest();

			StringBuffer hashValue = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hashValue.append('0');
				hashValue.append(hex);
			}

			return hashValue.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encrypt(String stringToEncrypt, String secretkey) {
		try {
			String algorithm = "AES";// AES encrption
			String algorithm_mode_padding = "AES/ECB/PKCS5Padding"; // algorithm_mode_padding
			String encryptedValue = encrypt(stringToEncrypt, secretkey, algorithm, algorithm_mode_padding);
			return encryptedValue;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	public static String encrypt(String stringToEncrypt, String secretkey, String algorithm,
			String algorithm_mode_padding) throws Exception {
		SecretKeySpec secret = new SecretKeySpec(secretkey.getBytes("UTF-8"), algorithm);
		if (cipher == null) {
			cipher = Cipher.getInstance(algorithm_mode_padding);// Algorithm/mode/padding
		}
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		String encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes()));

		return encryptedString;
	}
}
