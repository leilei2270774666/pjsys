package com.shketai.util;
import java.security.*;


public class Md5 {
  public final static String MD5(String s) {
    try {
      byte[] strTemp = s.getBytes();
      MessageDigest mdTemp = MessageDigest.getInstance("MD5");
      mdTemp.update(strTemp);
      byte[] md = mdTemp.digest();
      return byte2hex(md);
    }
    catch (Exception e) {
      return null;
    }
  }
  public final static String byte2hex(byte[] b) {
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      }
      else {
        hs = hs + stmp;
      }
    }
    return hs.toUpperCase();
  }
  public final static boolean isEquale(String pass, String key) { 
    try {
      byte[] strTemp = pass.getBytes();
      MessageDigest mdTemp = MessageDigest.getInstance("MD5"); 
      mdTemp.update(strTemp);
      byte[] md = mdTemp.digest();
      String str = byte2hex(md);
      return key.equals(str);
    }
    catch (Exception e) {
      return false;
    }
  }
  public static void main(String[] args) {
    System.out.println(Md5.MD5("123456"));
    //827CCB0EEA8A706C4C34A16891F84E7B
    //E10ADC3949BA59ABBE56E057F20F883E

  }
}
