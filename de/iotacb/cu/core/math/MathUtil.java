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

	/**
	 * Absolute and negative absolute of n
	 * @param num
	 * @return
	 */
	public static float abs(final float num) {
		return (num < 0) ? 0 - num : num;
	}

	public static double abs(final double num) {
		return (num < 0) ? 0 - num : num;
	}

	public static float nabs(final float num) {
		return (abs(num) * -1.0F);
	}

	public static double nabs(final double num) {
		return (abs(num) * -1.0);
	}

	/**
	 * Sin function for circle
	 * @param num
	 * @param radius
	 * @return
	 */

	public static double sinForCircle(float num, int radius) {
		return Math.sin(num * Math.PI / 180) * radius;
	}

	/**
	 * Cosine function for circle
	 * @param num
	 * @param radius
	 * @return
	 */

	public static double cosForCircle(float num, int radius) {
		return Math.cos(num * Math.PI / 180) * radius;
	}
	
}
