package riley.rover.ws;

// TODO: Auto-generated Javadoc
/**
 * The Class Commands
 * See https://dzone.com/articles/constants-in-java-the-anti-pattern-1
 */
public final class Commands {
	
	/**
	 * Instantiates a new commands.
	 */
	private Commands() {}
	
	/** The Constant CMD_KILL. 
	 *  Equivalent in decimal : 255
	 *  	Equivalent in hexadecimal : 0xFF
	 */
	public final static byte CMD_KILL = (byte) 0b11111111;
	
	/** The Constant CMD_CLOSE. 
	 *  Equivalent in decimal : 240
	 *  	Equivalent in hexadecimal : 0xF0
	 */
	public final static byte CMD_CLOSE = (byte) 0b11110000;
	
	/** The Constant CMD_MANUAL. 
	 *  Equivalent in decimal : 224
	 *  	Equivalent in hexadecimal : 0xE0
	 */
	public final static byte CMD_MANUAL = (byte) 0b11100000;
	
	/** The Constant CMD_REMOTE. 
	 *  Equivalent in decimal : 192
	 *  	Equivalent in hexadecimal : 0xC0
	 */
	public final static byte CMD_REMOTE = (byte) 0b11000000;
	
	/** The Constant CMD_NEUTRE. 
	 *  Equivalent in decimal : 0
	 *  	Equivalent in hexadecimal : 0x0
	 */
	public final static byte CMD_NEUTRE = (byte) 0b00000000;
	
	/** The Constant CMD_AVANCER. 
	 *  Equivalent in decimal : 3
	 *  	Equivalent in hexadecimal : 0x3
	 */
	public final static byte CMD_AVANCER = (byte) 0b00000011;
	
	/** The Constant CMD_RECULER. 
	 *  Equivalent in decimal : 12
	 *  	Equivalent in hexadecimal : 0xC
	 */
	public final static byte CMD_RECULER = (byte) 0b00001100;
	
	/** The Constant CMD_GAUCHE_AVANCER. 
	 *  Equivalent in decimal : 1
	 *  	Equivalent in hexadecimal : 0x1
	 */
	public final static byte CMD_GAUCHE_AVANCER = (byte) 0b00000001;
	
	/** The Constant CMD_GAUCHE_RECULER. 
	 *  Equivalent in decimal : 4
	 *  	Equivalent in hexadecimal : 0x4
	 */
	public final static byte CMD_GAUCHE_RECULER = (byte) 0b00000100;
	
	/** The Constant CMD_DROIT_AVANCER. 
	 *  Equivalent in decimal : 2
	 *  	Equivalent in hexadecimal : 0x2
	 */
	public final static byte CMD_DROIT_AVANCER = (byte) 0b00000010;
	
	/** The Constant CMD_DROIT_RECULER. 
	 *  Equivalent in decimal : 8
	 *  	Equivalent in hexadecimal : 0x8
	 */
	public final static byte CMD_DROIT_RECULER = (byte) 0b00001000;
	
	/** The Constant CMD_PIVOT_GAUCHE. 
	 *  Equivalent in decimal : 9
	 *  	Equivalent in hexadecimal : 0x9
	 */
	public final static byte CMD_PIVOT_GAUCHE = (byte) 0b00001001;
	
	/** The Constant CMD_PIVOT_DROIT. 
	 *  Equivalent in decimal : 6
	 *  	Equivalent in hexadecimal : 0x6
	 */
	public final static byte CMD_PIVOT_DROIT = (byte) 0b00000110;
	
	/** The Constant CMD_STOP. 
	 *  Equivalent in decimal : 16
	 *  	Equivalent in hexadecimal : 0x10
	 */
	public final static byte CMD_STOP = (byte) 0b00010000;
}
