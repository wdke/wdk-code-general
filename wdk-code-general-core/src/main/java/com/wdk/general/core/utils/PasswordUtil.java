package com.wdk.general.core.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordUtil {
	/**
	 * 密码加密处理（MD5）
	 * @param src 原密码
	 * @return 加密后的内容
	 */
	public static String md5(String src){
		try{//采用MD5处理
			MessageDigest md =
					MessageDigest.getInstance("MD5");
			byte[] output = md.digest(
					src.getBytes());//加密处理
			//将加密结果output利用Base64转成字符串输出
			String ret =
					Base64.encodeBase64String(output);
			return ret;
		}catch(Exception e){
			return "";
		}
	}

	/**
	 * 加密
	 *
	 * @param content
	 *            待加密内容
	 * @param key
	 *            加密的密钥
	 * @return
	 */
	public static String encrypt(String content, String key){
		try{
			KeyGenerator kgen= KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
			secureRandom.setSeed(key.getBytes());
			kgen.init(128,secureRandom);
			SecretKey secretKey=kgen.generateKey();
			byte[] enCodeFormat=secretKey.getEncoded();
			SecretKeySpec secretKeySpec=new SecretKeySpec(enCodeFormat,"AES");
			Cipher cipher= Cipher.getInstance("AES");
			byte[] byteContent=content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
			byte[] byteRresult=cipher.doFinal(byteContent);
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<byteRresult.length;i++){
				String hex= Integer.toHexString(byteRresult[i]&0xFF);
				if(hex.length()==1){
					hex='0'+hex;
				}
				sb.append(hex.toUpperCase());
			}
			return sb.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 *
	 * @param content
	 *            待解密内容
	 * @param key
	 *            解密的密钥
	 * @return
	 */
	public static String decrypt(String content, String key){
		if(content.length()<1)
			return null;
		byte[] byteRresult=new byte[content.length()/2];
		for(int i=0;i<content.length()/2;i++){
			int high= Integer.parseInt(content.substring(i*2,i*2+1),16);
			int low= Integer.parseInt(content.substring(i*2+1,i*2+2),16);
			byteRresult[i]=(byte)(high*16+low);
		}
		try{
			KeyGenerator kgen= KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
			secureRandom.setSeed(key.getBytes());
			kgen.init(128,secureRandom);
			SecretKey secretKey=kgen.generateKey();
			byte[] enCodeFormat=secretKey.getEncoded();
			SecretKeySpec secretKeySpec=new SecretKeySpec(enCodeFormat,"AES");
			Cipher cipher= Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
			byte[] result=cipher.doFinal(byteRresult);
			return new String(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

