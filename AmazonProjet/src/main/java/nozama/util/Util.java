package nozama.util;

public final class Util {

	public static boolean convertToInt(String text) {

		try {
			Integer.parseInt(text);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public static boolean convertToShort(String sYearBirth) {
		try{
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

}
