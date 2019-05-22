package riley.rover.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class RileyRoverWS {
	// public static Logger LOGGER = LoggerFactory.getLogger(RileyRoverWS.class);
	private static String key = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
	public static final int PORT = 9000;
	private ServerSocket serversocket;
	private Socket client;
	private static Random rand = new Random();

	private InputStream inputstream;
	private OutputStream outputstream;

	public RileyRoverWS() throws IOException {
		System.out.println("Creation du serveur");
		// LOGGER.info("Creation du serveur");
		serversocket = new ServerSocket(PORT);
		System.out.println("Creation terminee");
		// LOGGER.info("Creation terminee");
	}

	private String encode(String key) throws Exception {
		byte[] bytes = MessageDigest.getInstance("SHA-1").digest(key.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(bytes);
	}

	public byte[] getState() {
		byte[] state = new byte[] { (byte) 0x81, 0x05, 0x48, 0x65, 0x6c, 0x6c, 0x6f };
		return state;
	}

	public void run() throws Exception {
		for (;;) {
			client = serversocket.accept();
			inputstream = client.getInputStream();
			outputstream = client.getOutputStream();
			// bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
			// printstream = new PrintStream(outputstream);
			System.out.println("Client connecte");
			// For the first connexion
			String data = new Scanner(inputstream, "UTF-8").useDelimiter("\\r\\n\\r\\n").next();

			Matcher get = Pattern.compile("^GET").matcher(data);

			if (get.find()) {
				Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
				match.find();
				byte[] response = ("HTTP/1.1 101 Switching Protocols\r\n" + "Connection: Upgrade\r\n"
						+ "Upgrade: websocket\r\n" + "Sec-WebSocket-Accept: "
						+ encode((match.group(1) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11")) + "\r\n\r\n")
								.getBytes("UTF-8");

				outputstream.write(response, 0, response.length);
				outputstream.flush();
			}

			System.out.println("Ecoute en cours...");
			// https://developer.mozilla.org/fr/docs/Web/API/WebSockets_API/Writing_a_WebSocket_server_in_Java

			int curByte = 0;
			int readBytes = 0;
			int frame = 0;
			int lenframe = 0;
			int[] keys = new int[4];
			String message;
			for (;;) {
				if (inputstream.read() != 129)
					continue;
				lenframe = inputstream.read() - 128;
				keys[0] = (byte) inputstream.read();
				keys[1] = (byte) inputstream.read();
				keys[2] = (byte) inputstream.read();
				keys[3] = (byte) inputstream.read();
				byte[] decoded = new byte[lenframe];
				for (int i = 0; i < lenframe; i++) {
					decoded[i] = (byte) (inputstream.read() ^ keys[i & 0x3]);
				}
				message = new String(decoded, StandardCharsets.UTF_8);
				System.out.println("Message = " + message);
				byte[] messageB = getState();
				outputstream.write(messageB, 0, messageB.length);

				/*
				 * curByte = inputstream.read(); if(curByte == -1) { continue; }
				 * System.out.println("byte = "+ curByte); if(curByte == 129) { lenframe =
				 * inputstream.read()-128; keys[0] = (byte) inputstream.read(); keys[1] = (byte)
				 * inputstream.read(); keys[2] = (byte) inputstream.read(); keys[3] = (byte)
				 * inputstream.read(); byte[] decoded = new byte[lenframe]; for (int i = 0; i <
				 * lenframe; i++) { decoded[i] = (byte) (inputstream.read() ^ keys[i & 0x3]); }
				 * message = new String(decoded, StandardCharsets.UTF_8);
				 * System.out.println("Message = " + message); }
				 */
			}
		}
	}
}
