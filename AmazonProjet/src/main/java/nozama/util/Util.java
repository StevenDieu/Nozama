package nozama.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Util {

  public static boolean checkConvertToInt(String text) {

    try {
      Integer.parseInt(text);
    } catch (Exception e) {
      return false;
    }
    return true;

  }

  public static boolean checkConvertToFloat(String text) {

    try {
      Float.parseFloat(text);
    } catch (Exception e) {
      return false;
    }
    return true;

  }

  public static boolean checkConvertToShort(String sYearBirth) {
    try {
      Short.parseShort(sYearBirth);
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  public static String ConvertStringToNull(String string) {
    if (string == null || string.equals("") || string.equals("null")) {
      return null;
    }
    return string;
  }

  /**
   * Check if the price is compatible
   * 
   * @param email
   * @return
   */
  public static boolean isPrice(String price) {
    Pattern p = Pattern.compile("^[0-9]{1,}(,[0-9]{1,2}|[.][0-9]{1,2}){0,1}$");
    Matcher m = p.matcher(price);
    return m.matches();
  }

  public static Map<String, Object> returnMessage(String message) {
    Map<String, Object> redirect = new HashMap<String, Object>();
    redirect.put("message", message);
    return redirect;
  }


}
