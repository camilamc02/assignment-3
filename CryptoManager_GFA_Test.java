
public class CryptoManager_GFA_Test {

	public static void main(String[] args) {

		String str1 = "FAIL BECAUSE { IS OUTSIDE THE RANGE";
				// "\"THIS TEST SHOULD SUCCEED\"";
		String str4 = "CMSC207";
			//HELLO WORLD";
		String str5 = "MERRY CHRISTMAS";
				//IFMMP!XPSME";
		
		boolean good = CryptoManager.stringInBounds(str1);
		System.out.println(str1 + " Is it in bounds? " + good);
		if (good)
		{
		String c = CryptoManager.encryptCaesar(str5, 999);
		System.out.println(c);
		String d = CryptoManager.decryptCaesar(c, 999);
		System.out.println(d);
		String b = CryptoManager.encryptBellaso(str5, str4);
		System.out.println(b);
		String db = CryptoManager.decryptBellaso(b, str4);
		System.out.println(db);
		}
		
	}
}