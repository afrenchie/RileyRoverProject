package riley.rover.ws;
import static riley.rover.ws.Commands.*;

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
		prev_cmd = CMD_NEUTRE;
	}
	
	public void ralentir() {
		mA.brake();
		mB.brake();
		prev_cmd = CMD_NEUTRE;
	}
	
	public void stopper() {
		mA.stop();
		mB.stop();
		prev_cmd = CMD_STOP;
	}
	
	public void avancer() {
		if (prev_cmd != CMD_AVANCER) {
			mA.forward();
			mB.forward();
			prev_cmd = CMD_AVANCER;
		}
	}
	
	public void reculer() {
		if (prev_cmd != CMD_RECULER) {
			mA.backward();
			mB.backward();
			prev_cmd = CMD_RECULER;
		}
	}
	
	public void gauche_avancer() {
		if (prev_cmd != CMD_GAUCHE_AVANCER) {
			if(prev_cmd == CMD_PIVOT_GAUCHE || prev_cmd == CMD_AVANCER) {
				mA.stop();
			}
			else {
				mA.stop();
				mB.forward();
			}
			prev_cmd = CMD_GAUCHE_AVANCER;
		}
	}
	
	public void gauche_reculer() {
		if (prev_cmd != CMD_GAUCHE_RECULER) {
			if(prev_cmd == CMD_RECULER || prev_cmd == CMD_PIVOT_DROIT) {
				mA.stop();
			}
			else {
				mA.stop();
				mB.backward();
			}
			prev_cmd = CMD_GAUCHE_RECULER;
		}
	}
	
	public void droit_avancer() {
		if (prev_cmd != CMD_DROIT_AVANCER) {
			if(prev_cmd == CMD_PIVOT_DROIT || prev_cmd == CMD_AVANCER) {
				mB.stop();
			}
			else {
				mB.stop();
				mA.forward();
			}
			prev_cmd = CMD_DROIT_AVANCER;
		}
	}

	public void droit_reculer() {
		if (prev_cmd != CMD_DROIT_RECULER) {
			if(prev_cmd == CMD_RECULER || prev_cmd == CMD_PIVOT_GAUCHE) {
				mB.stop();
			}
			else {
				mB.stop();
				mA.backward();
			}
			prev_cmd = CMD_DROIT_RECULER;
		}
	}
	
	public void pivot_gauche() {
		if (prev_cmd != CMD_PIVOT_GAUCHE) {
			if(prev_cmd == CMD_GAUCHE_AVANCER || prev_cmd == CMD_AVANCER) {
				mB.forward();
			}
			else {
				mB.forward();
				mA.backward();
			}
			prev_cmd = CMD_PIVOT_GAUCHE;
		}
	}
	
	public void pivot_droit() {
		if (prev_cmd != CMD_PIVOT_DROIT) {
			if(prev_cmd == CMD_DROIT_AVANCER || prev_cmd == CMD_AVANCER) {
				mA.forward();
			}
			else {
				mA.forward();
				mB.backward();
			}
			prev_cmd = CMD_PIVOT_DROIT;
		}
	}
}
