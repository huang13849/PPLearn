package com.hqb.pplearn.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Md5Util {	

	private static Logger LOG = LoggerFactory.getLogger(Md5Util.class);
		public static  String getMD5(String text)
		{
			if(text==null)
				return null;
			else
				return makeMD5(text);
		}
		
	
		private static String makeMD5(String text)
		{
			MessageDigest md = null;
			byte[] encryptMsg = null;

			try {
				md = MessageDigest.getInstance( "MD5" );		// getting a 'MD5-Instance'
				encryptMsg = md.digest(text.getBytes());		// solving the MD5-Hash
			}catch (NoSuchAlgorithmException e) {
				String message = "No MD5 Algorithm Exception!";
				LOG.error(message);
				throw new RuntimeException(message);
			}
				
			String swap="";										// swap-string for the result
			String byteStr="";									// swap-string for current hex-value of byte
			StringBuffer strBuf = new StringBuffer();
			
			for(int i=0; i<=encryptMsg.length-1; i++) {
				
				byteStr = Integer.toHexString(encryptMsg[i]);	// swap-string for current hex-value of byte
				
				switch(byteStr.length()) {
				case 1:											// if hex-number length is 1, add a '0' before
					swap = "0"+Integer.toHexString(encryptMsg[i]);
					break;
					
				case 2:											// correct hex-letter
					swap = Integer.toHexString(encryptMsg[i]);
					break;
					
				case 8:											// get the correct substring
					swap = (Integer.toHexString(encryptMsg[i])).substring(6,8);
					break;
				}
				strBuf.append(swap);							// appending swap to get complete hash-key
			}
			return strBuf.toString();							// String with the MD5-Hash
		}
		
		
		public static void main(String[] args)
		{
			System.out.println(getMD5("雷锋"));
			System.out.println(getMD5("雷锋"));
			System.out.println(getMD5("123456"));
			System.out.println(getMD5("123456"));
			System.out.println(getMD5("ABCDefg"));
			System.out.println(getMD5("ABCDefg"));
		}
}