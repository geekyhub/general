package com.geekylab.general.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class Utils {
	
	public static String sha1(String password, String salt){
		System.out.println("sha1 pwd:"+password);
		System.out.println("sha1 salt:"+salt);
		StringBuilder result = new StringBuilder();
		try{
			SecretKeySpec sha1Key = new SecretKeySpec(salt.toLowerCase().getBytes("UTF-8"),
					"HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(sha1Key);
			byte[] sigBytes = mac.doFinal(password.getBytes("UTF-8"));
			
			for (int i = 0; i < sigBytes.length; i++) {
				result.append(Integer.toString((sigBytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
		}catch (Exception e){
			//Log.d("sha1 failed");
		}
		return result.toString();
	}
	

	public static boolean isValidPhoneNumber(Object phoneNumber){
		if(phoneNumber==null || phoneNumber.toString().trim().length() <= 0){
			return false; 
		}
		if((phoneNumber.toString().substring(0, 1).equals("8") || 
			phoneNumber.toString().substring(0, 1).equals("9")) && 
			phoneNumber.toString().length() == 8){
			return true; 
		}
		return false;
	}
	
	
	public static boolean isValidEmail(Object email){
		if(email==null || email.toString().trim().length() <= 0){
			return false; 
		}
		String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		return pattern.matcher(email.toString()).matches();
	}
	
	public static boolean isEmpty(Object str){
		if(str==null) return true;
		return str.toString().trim().length() <= 0;
	}
	
	public static boolean isContainNumber(String str){
		return (str.matches(".*\\d.*"));
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("\\d+(\\.\\d+)?");  
	}
	
	
	
	public static int countUpperCase(String s){
		int caps = 0;
		for (int i=0; i<s.length(); i++){
			if (Character.isUpperCase(s.charAt(i)))
				caps++;
		}
		return caps;
	}
	
	public static int countNumeric(String s){
		int digits = 0;
		for (int i=0; i<s.length(); i++){
			if (Character.isDigit(s.charAt(i)))
				digits++;
		}
		return digits;
	}
	
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	
	public static String getRandomAlphaNum(int length){
		String alphanum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (length-- != 0) {
			int character = (int)(Math.random()*alphanum.length());
			builder.append(alphanum.charAt(character));
		}
		return builder.toString();

	}

	public static void hideKeyboard(Context context, View view){
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	
	public static void showToast(final Context context, String msg){
    	Toast toast= Toast.makeText(context, msg, Toast.LENGTH_SHORT);  
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
    }


	public static Bitmap getBitmap(Context c, String name) 
    {
		try{
			AssetManager asset = c.getAssets();
			InputStream is = asset.open(name);
			Bitmap bitmap = BitmapFactory.decodeStream(is);

			return bitmap;
		}catch(IOException e){
			
		}
		return null;
    }
	
	
	public static String getAppVersionName(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionName;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	
	public static String formatCreditCardNumber(String ccNumber){
		if(ccNumber!=null &&  ccNumber.length()>12){
			ccNumber = new StringBuilder(ccNumber).insert(4, " ").toString();
			ccNumber = new StringBuilder(ccNumber).insert(9, " ").toString();
			ccNumber = new StringBuilder(ccNumber).insert(14, " ").toString();
		}
		return ccNumber;
	}
	
	public static String formatCreditCardNumberForBilling(String ccNumber){
		if(ccNumber!=null && ccNumber.length()>12){
			return ccNumber.substring(0, 6) + "******" + ccNumber.substring(12);
		}
		return "";
	}
	
	public static String formatCreditCardMasked(String ccNumber){
		if(ccNumber!=null &&  ccNumber.length()>12){
			ccNumber = ccNumber.substring(0, 4) + "********" + ccNumber.substring(12);
			ccNumber = new StringBuilder(ccNumber).insert(4, " ").toString();
			ccNumber = new StringBuilder(ccNumber).insert(9, " ").toString();
			ccNumber = new StringBuilder(ccNumber).insert(14, " ").toString();
			
			
			
		}
		return ccNumber;
	}
	
	public static String formatExpiryDate(String expiryDate){
		if(expiryDate.length()==4)
			expiryDate = new StringBuilder(expiryDate).insert(2, "/").toString();
		return expiryDate;
	}
	
	
	public static String add(String val1, String val2){
		try{
			BigDecimal amount1 = new BigDecimal(val1);
			BigDecimal amount2 = new BigDecimal(val2);
			
			return amount1.add(amount2).toString();
		}catch(Exception e){
		}
		return "";
	}
	

    
    
}
