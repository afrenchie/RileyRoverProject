package riley.rover.ws;

//import java.nio.charset.StandardCharsets;

public class Main {

	public static void main(final String[] args) {
		System.out.println("Demarrage ...");

		RileyRoverWS s;
		try {
			s = new RileyRoverWS();
			s.run();
		} catch (Exception e) {
			System.out.println(e);
		}

		/*
		 * System.out.println("Test lecture bytes -> abcdef"); byte[] decoded = new
		 * byte[6]; byte[] encoded = new byte[] { (byte) 198, (byte) 131, (byte) 130,
		 * (byte) 182, (byte) 194, (byte) 135 }; byte[] key = new byte[] { (byte) 167,
		 * (byte) 225, (byte) 225, (byte) 210 }; for (int i = 0; i < encoded.length;
		 * i++) { decoded[i] = (byte) (encoded[i] ^ key[i & 0x3]); }
		 * System.out.println("Decoded bytes = " + decoded);
		 * 
		 * System.out.println("Decoded = " + new String(decoded,
		 * StandardCharsets.UTF_8));
		 */
		/*
		 * 
		 */
	}

}