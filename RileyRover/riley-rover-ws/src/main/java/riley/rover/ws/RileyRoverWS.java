package riley.rover.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RileyRoverWS {
	public static Logger LOGGER = LoggerFactory.getLogger(RileyRoverWS.class);
	private static String HANDSHAKEKEY = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
	public static final int PORT = 9000;
	//Timeout de 30 secondes
	public static final int READ_TIMEOUT = 30000;
	private ServerSocket serversocket;
	private Socket client;
	private InputStream inputstream;
	private OutputStream outputstream;

	private final byte CMD_KILL = (byte)           0b11111111; //255 0xFF
	private final byte CMD_CLOSE = (byte)          0b11110000; //240 0xF0
	private final byte CMD_MANUAL = (byte)         0b11100000; //224 0xE0
	private final byte CMD_REMOTE = (byte)         0b11000000; //192 0xC0
	private final byte CMD_STOP = (byte)           0b00000000; //0   0x0
	private final byte CMD_AVANCER = (byte)        0b00000011; //3   0x3
	private final byte CMD_RECULER = (byte)        0b00001100; //12  0xC
	private final byte CMD_GAUCHE_AVANCER = (byte) 0b00000001; //1   0x1
	private final byte CMD_GAUCHE_RECULER = (byte) 0b00000100; //4   0x4
	private final byte CMD_DROIT_AVANCER = (byte)  0b00000010; //2   0x2
	private final byte CMD_DROIT_RECULER = (byte)  0b00001000; //8   0x8
	private final byte CMD_PIVOT_GAUCHE = (byte)   0b00001001; //9   0x9
	private final byte CMD_PIVOT_DROIT = (byte)    0b00000110; //6   0x6
	
	
	
	public RileyRoverWS() throws IOException {
		System.out.println("Creation du serveur");
		LOGGER.info("Creation du serveur");
		serversocket = new ServerSocket(PORT);
		System.out.println("Creation terminee");
		LOGGER.info("Creation terminee");
	}

	private String encode(String key) throws Exception {
		byte[] bytes = MessageDigest.getInstance("SHA-1").digest(key.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	private byte[] customMessage(int instruction) {
		byte[] msg = new byte[] { (byte) 0x82, 0x04, (byte) (instruction << 24), (byte) (instruction << 16), (byte) (instruction << 8), (byte) instruction };
		return msg;
	}
	
	private byte[] customMessage(String message) {
		return customMessage(message, 1);
	}
	
	private byte[] customMessage(String message, int mode) {
		int messagelen = message.length();
		byte[] msg = new byte[messagelen+2];
		
		msg[0] = (byte) (0x80 + mode);
		msg[1] = (byte) messagelen;
		byte[] messagebytes;
		try {
			messagebytes = message.getBytes("UTF-8"); 
			for (int i = 0; i < messagelen; i++)
				msg[i] = messagebytes[i];
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	private byte[] byteMsgMsg() {
		return new byte[] { (byte) 0x81, 0x03, 0x4d, 0x73, 0x67};
	}
	
	private byte[] byteCmdMsg() {
		return new byte[] { (byte) 0x81, 0x03, 0x43, 0x6d, 0x64};
	}

	@SuppressWarnings("resource")
	private void handshake() {
		String data = new Scanner(inputstream, "UTF-8").useDelimiter("\\r\\n\\r\\n").next();
		Matcher get = Pattern.compile("^GET").matcher(data);

		if (get.find()) {
			Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
			match.find();
			try {
			byte[] response = ("HTTP/1.1 101 Switching Protocols\r\n" 
					+ "Connection: Upgrade\r\n"
					+ "Upgrade: websocket\r\n" 
					+ "Sec-WebSocket-Accept: "
					+ encode((match.group(1) + HANDSHAKEKEY)) + "\r\n\r\n")
							.getBytes("UTF-8");
				outputstream.write(response, 0, response.length);
				outputstream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private byte[] readBytes() throws IOException {
		int lenframe = 0;
		int[] keys = new int[4];
		byte[] decoded = null;

		lenframe = inputstream.read() - 128;
		decoded = new byte[lenframe];
		keys[0] = (byte) inputstream.read();
		keys[1] = (byte) inputstream.read();
		keys[2] = (byte) inputstream.read();
		keys[3] = (byte) inputstream.read();
		for (int i = 0; i < lenframe; i++) {
			decoded[i] = (byte) (inputstream.read() ^ keys[i & 0x3]);
		}
		
		return decoded;
	}
	
	private void executeCommand(byte[] decoded) throws IOException {
		byte cmd = decoded[0];
		switch(cmd) {
			case CMD_KILL:
				System.out.println("CMD_KILL");
				break;
			case CMD_CLOSE:
				System.out.println("CMD_CLOSE");
				close();
				break;
			case CMD_STOP:
				System.out.println("CMD_STOP");
				break;
			case CMD_MANUAL:
				System.out.println("CMD_MANUAL");
				break;
			case CMD_REMOTE:
				System.out.println("CMD_REMOTE");
				break;
			case CMD_AVANCER:
				System.out.println("CMD_AVANCER");
				break;
			case CMD_RECULER:
				System.out.println("CMD_RECULER");
				break;
			case CMD_GAUCHE_AVANCER:
				System.out.println("CMD_GAUCHE_AVANCER");
				break;
			case CMD_GAUCHE_RECULER:
				System.out.println("CMD_GAUCHE_RECULER");
				break;
			case CMD_DROIT_AVANCER:
				System.out.println("CMD_DROIT_AVANCER");
				break;
			case CMD_DROIT_RECULER:
				System.out.println("CMD_DROIT_RECULER");
				break;
			case CMD_PIVOT_GAUCHE:
				System.out.println("CMD_PIVOT_GAUCHE");
				break;
			case CMD_PIVOT_DROIT:
				System.out.println("CMD_PIVOT_DROIT");
				break;
			default:
				break;
		}
	}
	
	private void close() throws IOException {
		outputstream.close();
		inputstream.close();
		client.close();
		System.out.println("Client déconnecté.");
	}
	
	public void run() throws Exception {
		for (;;) {
			try {
				client = serversocket.accept();
				System.out.println("Tentative de connection.");
				inputstream = client.getInputStream();
				outputstream = client.getOutputStream();
				handshake();
				System.out.println("Télécommande connectée.");
				System.out.println("Ecoute en cours...");
				// https://developer.mozilla.org/fr/docs/Web/API/WebSockets_API/Writing_a_WebSocket_server_in_Java
				client.setSoTimeout(READ_TIMEOUT);
				long lastCommand = 0;
				boolean cmd = false;
				byte[] decoded = null;
				int curByte = 0;
				while (true) {
					curByte = inputstream.read();
					if (curByte == 129)
						cmd = false;
					else if (curByte == 130)
						cmd = true;
					else
						continue;
					lastCommand = System.currentTimeMillis();
					System.out.println("lastCommand " + lastCommand);
					decoded = readBytes();
					
					if (! cmd) {
						System.out.println("Message = " + new String(decoded, StandardCharsets.UTF_8));
						outputstream.write(byteMsgMsg(), 0, 5);
						outputstream.flush();
					}
					else {
						executeCommand(decoded);
						outputstream.write(byteCmdMsg(), 0, 5);
						outputstream.flush();
					}
					
				}
			} catch(IOException e) {
				e.printStackTrace();
				close();
			}  catch(Exception e) {
				e.printStackTrace();
				close();
			}
			
		}
	}
}
