package riley.rover.ws;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		System.out.println("Demarrage ...");

		RileyRoverWS s;
		try {
			s = new RileyRoverWS(9000);
			s.run();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
