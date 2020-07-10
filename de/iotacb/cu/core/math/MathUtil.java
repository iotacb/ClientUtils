package de.iotacb.cu.core.math;

public class MathUtil {

	/**
	 * Will clamp the given value between a min and max value
	 * @param current
	 * @param min
	 * @param max
	 * @return
	 */
	public static final int clamp(final int current, final int min, final int max) {
		return (current < min ? min : current > max ? max : current);
	}
	
	/**
	 * Will clamp the given value between a min and max value
	 * @param current
	 * @param min
	 * @param max
	 * @return
	 */
	public static final double clamp(final double current, final double min, final double max) {
		return (current < min ? min : current > max ? max : current);
	}
	
	/**
	 * Will clamp the given value between a min and max value
	 * @param current
	 * @param min
	 * @param max
	 * @return
	 */
	public static final float clamp(final float current, final float min, final float max) {
		return (current < min ? min : current > max ? max : current);
	}
	
	/**
	 * Will clamp the given value between a min and max value
	 * @param current
	 * @param min
	 * @param max
	 * @return
	 */
	public static final long clamp(final long current, final long min, final long max) {
		return (current < min ? min : current > max ? max : current);
	}
	
}
