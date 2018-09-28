package javapass;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private static SecretKeySpec secretkey;
	private static byte[] key;

	void setKey(String your_key) {
		MessageDigest sha = null;
		try {
			key = your_key.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretkey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	byte[] encrypt(String toEncrypt, String pass) {
		setKey(pass);
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);
			return cipher.doFinal(toEncrypt.getBytes("UTF-8"));
		} catch (Exception ex) {
			Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	String encode(byte[] b) {
		return Base64.getEncoder().encodeToString(b);
	}

	byte[] decode(String Base64String) {
		return Base64.getDecoder().decode(Base64String);
	}

	String decrypt(String Base64String, String key) {
		try {
			setKey(key);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretkey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(Base64String)));
		} catch (Exception ex) {
			Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
