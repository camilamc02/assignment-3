/*
 * Class: CMSC203 24307
 * Instructor: Ping-Wei Tsai
 * Description: Ask user what they want to encrypt and what key they want, and then use those to encrypt using either the caesar
 * or bellaso method
 *  * Due: 10/10/2021
 * Platform/compiler:Eclipse
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Camila Chombo
*/
public class CryptoManager {

	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;



	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds(String plainText) {
		boolean temporary = true;
		for (int index = 0; index < plainText.length(); index++)
		{
			//if the string is out bounds then temporary will be false
			if ((int)plainText.charAt(index) < (int)LOWER_BOUND || (int)plainText.charAt(index) > (int)UPPER_BOUND)
			{
				temporary = false;
			}
		}
		//if the string is in bounds then temporary will remain the same since it is already true
		return temporary;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		//declare temporary string that will return value
		String ceasarCipher = "";

		for (int index = 0; index < plainText.length(); index++)
		{
			//declared cNum as the ascii value
			int cNum=0;
			
			//if plaintext at index position plus key is less than the lower bound or is higher than upper bound
			if (((int)plainText.charAt(index) + key) < (int)LOWER_BOUND || ((int)plainText.charAt(index) + key) > (int)UPPER_BOUND)
			{ 
				//take the modulus of the index position plus the key and assign cNum
				cNum = (((int)plainText.charAt(index)+ key) %RANGE);
				//if cNum is less than the lower bound, add the range value to cNum
				if(cNum<LOWER_BOUND)
					cNum=cNum+RANGE;
				//add the char of cNum value to the declared string
				ceasarCipher += ((char)cNum);
			}
			else
			{
				//if in bounds, only add the key to plaintext at position index
				cNum = ((int)plainText.charAt(index) + key);
				//use (char) to convert the value of the cNum to its char
				ceasarCipher += ((char)cNum);
			}
		}
		return ceasarCipher;

	}
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String bellasoCipher = "";
		int y = 0;
		int bNum = 0;

		for (int index = 0; index < plainText.length(); index++)
		{
			//if y becomes bigger or equal to the length of the bellaso key, reset y to 0
			if (y >= bellasoStr.length())
			{
				y = 0;
			}
			if (((int)plainText.charAt(index) + (int)bellasoStr.charAt(y) - RANGE) < (int)LOWER_BOUND || ((int)plainText.charAt(index) + (int)bellasoStr.charAt(y) - RANGE) > (int)UPPER_BOUND)
			{
				
				bNum = ((((int)plainText.charAt(index) + (int)bellasoStr.charAt(y)) - RANGE));
				
				//if bNum is higher than the upper bound, subtract range twice
				if(bNum>(int)UPPER_BOUND)
					bNum=((((int)plainText.charAt(index) + (int)bellasoStr.charAt(y)) - RANGE)-RANGE);
				
				if(bNum<(int)LOWER_BOUND)
					bNum=((int)plainText.charAt(index) + (int)bellasoStr.charAt(y));
				
				bellasoCipher += ((char)(bNum));
			}
			else
			{
				bNum = (((int)plainText.charAt(index) + (int)bellasoStr.charAt(y)) - RANGE);
				bellasoCipher += ((char)(bNum));
			}
			y++;
		}

		return bellasoCipher;
	}

	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String dCaesar = "";
	     for (int index = 0; index < encryptedText.length(); index++)
         {
	    	 //declare cChar assigned to the encrypted text chat at position index 
               char cChar = encryptedText.charAt(index);
               //subtract key from the integer of cChar
               int cNum =  ((int)cChar-key);
         
               while (cNum < LOWER_BOUND) {
            	   //adds RANGE to cNum
                       cNum += RANGE;
               }
                     dCaesar += (char)cNum;
        }
        return dCaesar;
	}	

	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String dBellaso = "";
		int y = 0;
		int bNum = 0;


		for (int index = 0; index < encryptedText.length(); index++)
		{
			if (y >= bellasoStr.length())
			{
				y = 0;
			}
			//If the result of the cipher is less than the lower bound or higher than the upper bound
			if (((int)encryptedText.charAt(index) - (int)bellasoStr.charAt(y) + RANGE) < (int)LOWER_BOUND || ((int)encryptedText.charAt(index) - (int)bellasoStr.charAt(y) + RANGE) > (int)UPPER_BOUND)
			{		
				bNum=((int)encryptedText.charAt(index) - (int)bellasoStr.charAt(y)+RANGE);
				//if the result of the cipher is more than the upper bound, subtract range from bNum
				if(bNum>(int)UPPER_BOUND)
					bNum=bNum-RANGE;
				
				//if the result of the cipher is more than the upper bound, add range from bNum
				if(bNum<(int)LOWER_BOUND)
					bNum=(bNum+RANGE);
				dBellaso += ((char)(bNum));
			}
			else
			{
				//if the result is in bounds, only add the range
				bNum = (((int)encryptedText.charAt(index) - (int)bellasoStr.charAt(y)) + RANGE);
				dBellaso += ((char)(bNum));
			}
			y++;
		}

		return dBellaso;
	}
}
