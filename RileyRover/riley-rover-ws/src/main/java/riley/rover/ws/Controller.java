package riley.rover.ws;

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Controller {
	private EV3LargeRegulatedMotor mA;
	private EV3LargeRegulatedMotor mB;
	private int prev_cmd;
	
	public void init() {
		mA = new EV3LargeRegulatedMotor(MotorPort.A);
		mB = new EV3LargeRegulatedMotor(MotorPort.B);
		mA.setSpeed(200);
		mA.brake();
		mB.setSpeed(200);
		mB.brake();	
		prev_cmd = 16;
		//System.out.println("init");
	}
	
	public void ralentir() {
		mA.brake();
		mB.brake();
		prev_cmd = 16;
		//System.out.println("ralentir");
	}
	
	public void stopper() {
		mA.stop();
		mB.stop();
		prev_cmd = 0;
		//System.out.println("stopper");
	}
	
	public void avancer() {
		if (prev_cmd != 3) {
			stopper();
			mA.forward();
			mB.forward();
			prev_cmd = 3;
		}
		//System.out.println("avancer");
	}
	
	public void reculer() {
		if (prev_cmd != 12) {
			stopper();
			mA.backward();
			mB.backward();
			prev_cmd = 12;
		}
		//System.out.println("reculer");
	}
	
	public void gauche_avancer() {
		if (prev_cmd != 1) {
			if(prev_cmd == 9 || prev_cmd == 3) {
				mA.stop();
			}
			else {
				mA.stop();
				mB.forward();
			}
		}
	}
	
	public void gauche_reculer() {
		if (prev_cmd != 4) {
			if(prev_cmd == 12 || prev_cmd == 6) {
				mA.stop();
			}
			else {
				mA.stop();
				mB.backward();
			}
		}
	}
	
	public void droit_avancer() {
		if (prev_cmd != 2) {
			if(prev_cmd == 6 || prev_cmd == 3) {
				mB.stop();
			}
			else {
				mB.stop();
				mA.forward();
			}
		}
	}

	public void droit_reculer() {
		if (prev_cmd != 8) {
			if(prev_cmd == 12 || prev_cmd == 9) {
				mB.stop();
			}
			else {
				mB.stop();
				mA.backward();
			}
		}
	}
	
	public void pivot_gauche() {
		if (prev_cmd != 9) {
			if(prev_cmd == 1 || prev_cmd == 3) {
				mB.forward();
			}
			else {
				mB.forward();
				mA.backward();
			}
		}
	}
	
	public void pivot_droit() {
		if (prev_cmd != 6) {
			if(prev_cmd == 2 || prev_cmd == 3) {
				mA.forward();
			}
			else {
				mA.forward();
				mB.backward();
			}
		}
	}

	/*
	var cmd_gauche_avancer = 1; // 0b00000001 1   0x1
	var cmd_gauche_reculer = 4; // 0b00000100 4   0x4
	var cmd_droit_avancer = 2;  // 0b00000010 2   0x2
	var cmd_droit_reculer = 8;  // 0b00001000 8   0x8
	var cmd_pivot_gauche = 9;   // 0b00001001 9   0x9
	var cmd_pivot_droit = 6;    // 0b00000110 6   0x6
	*/
}
