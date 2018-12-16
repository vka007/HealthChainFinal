package healthchain;

import java.security.MessageDigest;

import com.google.gson.GsonBuilder;
import java.security.MessageDigest;

public class StrOpr {
	
		
		public static String applySha256(String input)
		{
			//Apply POW algo i.e. sha256
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        
				//Applies sha256 to input, 
				byte[] hash = digest.digest(input.getBytes("UTF-8"));
		        
				StringBuffer hexString = new StringBuffer(); //hash as hexidecimal
				for (int i = 0; i < hash.length; i++) {
					String hex = Integer.toHexString(0xff & hash[i]);
					if(hex.length() == 1) hexString.append('0');
					hexString.append(hex);
				}
				return hexString.toString();
			}
			catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		//Convert object to Json string
		public static String getJson(Object o) 
		{
			return new GsonBuilder().setPrettyPrinting().create().toJson(o);
		}
		
		
		public static String getDificultyString(int difficulty)
		{
			//Return the Difficulty level in Blockchain 
			return new String(new char[difficulty]).replace('\0', '0');
		}
		


}
