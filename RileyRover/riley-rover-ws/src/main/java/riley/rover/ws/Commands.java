package riley.rover.ws;

public final class Commands {
	private Commands() {}
	//Ensemble des commandes : 
	public final static byte CMD_KILL = (byte)           0b11111111; //255 0xFF
	public final static byte CMD_CLOSE = (byte)          0b11110000; //240 0xF0
	public final static byte CMD_MANUAL = (byte)         0b11100000; //224 0xE0
	public final static byte CMD_REMOTE = (byte)         0b11000000; //192 0xC0
	public final static byte CMD_NEUTRE = (byte)         0b00000000; //0   0x0
	public final static byte CMD_AVANCER = (byte)        0b00000011; //3   0x3
	public final static byte CMD_RECULER = (byte)        0b00001100; //12  0xC
	public final static byte CMD_GAUCHE_AVANCER = (byte) 0b00000001; //1   0x1
	public final static byte CMD_GAUCHE_RECULER = (byte) 0b00000100; //4   0x4
	public final static byte CMD_DROIT_AVANCER = (byte)  0b00000010; //2   0x2
	public final static byte CMD_DROIT_RECULER = (byte)  0b00001000; //8   0x8
	public final static byte CMD_PIVOT_GAUCHE = (byte)   0b00001001; //9   0x9
	public final static byte CMD_PIVOT_DROIT = (byte)    0b00000110; //6   0x6
	public final static byte CMD_STOP = (byte)           0b00010000; //16  0x10
}
