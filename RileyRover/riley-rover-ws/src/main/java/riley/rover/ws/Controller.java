package riley.rover.ws;
import static riley.rover.ws.Commands.*;

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
public class Controller {
	
	/** The left motor */
	private EV3LargeRegulatedMotor mA;
	
	/** The right motor */
	private EV3LargeRegulatedMotor mB;
	
	/** The prev cmd. */
	private int prev_cmd;
	
	/**
	 * Inits the.
	 */
	public void init() {
		mA = new EV3LargeRegulatedMotor(MotorPort.A);
		mB = new EV3LargeRegulatedMotor(MotorPort.B);
		mA.setSpeed(200);
		mA.brake();
		mB.setSpeed(200);
		mB.brake();	
		prev_cmd = CMD_NEUTRE;
	}
	
	/**
	 * Ralentir.
	 */
	public void ralentir() {
		mA.brake();
		mB.brake();
		prev_cmd = CMD_NEUTRE;
	}
	
	/**
	 * Stopper.
	 */
	public void stopper() {
		mA.stop();
		mB.stop();
		prev_cmd = CMD_STOP;
	}
	
	/**
	 * Avancer.
	 */
	public void avancer() {
		if (prev_cmd != CMD_AVANCER) {
			mA.forward();
			mB.forward();
			prev_cmd = CMD_AVANCER;
		}
	}
	
	/**
	 * Reculer.
	 */
	public void reculer() {
		if (prev_cmd != CMD_RECULER) {
			mA.backward();
			mB.backward();
			prev_cmd = CMD_RECULER;
		}
	}
	
	/**
	 * Gauche avancer.
	 */
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
	
	/**
	 * Gauche reculer.
	 */
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
	
	/**
	 * Droit avancer.
	 */
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

	/**
	 * Droit reculer.
	 */
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
	
	/**
	 * Pivot gauche.
	 */
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
	
	/**
	 * Pivot droit.
	 */
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
