package utilities;

import java.util.Date;

public class CommonUtils {
	// Method to generate new Emails
	public static String generateEmailWithTimestamp() {
		return new Date().toString().replaceAll("\\s","").replaceAll("\\:","")+"@gmail.com";
	}

}
