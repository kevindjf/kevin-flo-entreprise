package fr.RivaMedia.AnnoncesAutoGenerique.net.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import android.text.format.DateFormat;

public class MD5 {

	public static final String md5(final String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
	public static final String getDateFormatee(){
		Date now = new Date();
		return DateFormat.format("yyyy-MM-dd", now).toString();
	}
	
	public static final String getDateFormateeJMHMS(){
		Date now = new Date();
		return DateFormat.format("ddMMhhmmss", now).toString();
	}
	
	public static final String getDateFormateeMD5(){
		return md5(getDateFormatee());
	}
	
	public static final String getDateFormateeJMHMSMD5(){
		return md5(getDateFormateeJMHMS());
	}
	
}
