package com.tedu.cloudnote.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil implements Serializable {
	/**
	 * 密码加密处理(MD5)
	 * @param src原密码
	 * @return 加密后的内容
	 */
	public static String md5(String src){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());//加密处理
			//将加密结果output利用Base64转成字符串输出
			//引入jar包  commons-codec 
			String ret = Base64.encodeBase64String(output);
			return ret;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new NoteException("密码加密失败!",e);//必须RuntimeException事务 才会回滚
		}	
	}
	
	/**
	 * 利用uuid算法生成一个主键值
	 * @return 主键值
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(NoteUtil.md5("123"));
	}
}	
