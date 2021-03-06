package movement;

import org.usfirst.frc.team3793.robot.Sensors;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Class to cause a desired stationary turn to a specified number of degrees
 * @author Warren Funk
 *
 */
public class Turn extends MovementAction implements PIDOutput{
	PIDController turnController;
	
	final static float kP = 0.03f;
	final static float kI = 0.0002f;
	final static float kD = 0.0f;
	final static float kF = 0.0f;
	final static float kTolerance = 2;
	static float targetTime = 0;
	
	public Turn(float degrees, float maxSpeed) {
		super((int)Math.signum(degrees), maxSpeed);
		this.degrees = Sensors.navX.getYaw()+degrees;
		
		turnController = new PIDController(kP, kI, kD, kF, Sensors.navX, this);
	    turnController.setInputRange(-180.0f,  180.0f);
	    turnController.setOutputRange(-1.0, 1.0);
	    turnController.setAbsoluteTolerance(kTolerance);
	    turnController.setContinuous(true);
	    turnController.setSetpoint(degrees);
	    turnController.enable();
	}
	
	/**
	 * @return required {@link Speed} to turn correctly
	 */
	public Speed getSpeed() {
		double pidOut = -turnController.get();
		System.out.println(Sensors.navX.getYaw());
		if(direction) return new Speed(-maxSpeed*pidOut, maxSpeed*pidOut);
			
		return new Speed(maxSpeed*pidOut, -maxSpeed*pidOut);
		
	}
	
	/**
	 * @return whether or not the turn is within tolerance according to the PID controller
	 */
	public boolean isComplete() {
		System.out.println(Sensors.navX.getYaw());
		return turnController.onTarget();
	}

	@Override
	public void pidWrite(double output) {
		PID = output;
	}
}
