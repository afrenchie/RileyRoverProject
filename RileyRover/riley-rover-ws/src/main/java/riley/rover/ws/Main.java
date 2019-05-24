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
	}

}
