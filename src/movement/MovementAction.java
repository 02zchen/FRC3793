package movement;
/**
 * abstract class that {@link Straight} and {@link Turn} implement
 * @author Warren Funk
 */
public abstract class MovementAction {
	protected long beginTime;
	protected boolean direction;
	protected float maxSpeed;
	protected double PID;
	protected float degrees;
	
	public MovementAction(int direction, float maxSpeed) {
		beginTime = System.currentTimeMillis();
		this.direction = direction > 0;
		this.maxSpeed = maxSpeed;
	}
	
	/*
	 * returns a speed object containing the speeds from -1 to 1 for each motor
	 */
	public abstract Speed getSpeed();
	public abstract boolean isComplete();
}
