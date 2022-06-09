package com.linkface.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


// 고급 암호화 표준 256bit
public class Aes256 {

	private static String alg = "AES/CBC/PKCS5Padding";
    // 블록 사이즈에 맞출 임의의 값을 추가하기 위한 패딩
    private static String key = "pZm2d5iqQSGL4Alt62ETzaqKiYFM9nSq";
    // 32바이트(256bit)의 시크릿키
    private static String iv = key.substring(0, 16);
    // 평문과 최초로 XOR 연산을 할 블럭 
    
    public static String encrypt(String text) {

    	try {
    		
			Cipher cipher = Cipher.getInstance(alg);
		    // 패딩을 적용한 Cipher 객체 반환
		    SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		    // 바이트 배열로부터 AES 를 적용한 비밀키 생성
		    IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
		    // IV 블럭 생성
		    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
		    // 초기화
		    byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
		    // 문자열을 바이트로 바꾸고 암호화
		    return Base64.getEncoder().encodeToString(encrypted);
		    // 암호화된 바이트배열을 암호화된 문자열로 반환
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }

    // 위와 동일
    public static String decrypt(String cipherText) { 
    	
    	try {
    		
    		Cipher cipher = Cipher.getInstance(alg);
	        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
	        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
	        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
	        // 위와 동일
	        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
	        // 암호화된 문자열을 암호 바이트 배열로 변환
	        byte[] decrypted = cipher.doFinal(decodedBytes);
	        // 복호화해서 바이트배열로 변환
	        return new String(decrypted, "UTF-8");  
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    		  
    }
    

}
