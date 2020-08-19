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
	 * Returns the absolute of the given number
	 * @param num
	 * @return
	 */
	public static final float abs(final float num) {
		return (num < 0) ? 0 - num : num;
	}

	/**
	 * Returns the absolute of the given number
	 * @param num
	 * @return
	 */
	public static final double abs(final double num) {
		return (num < 0) ? 0 - num : num;
	}

	/**
	 * Returns the negative absolute of the given number
	 * @param num
	 * @return
	 */
	public static final float nabs(final float num) {
		return (abs(num) * -1.0F);
	}

	/**
	 * Returns the negative absolute of the given number
	 * @param num
	 * @return
	 */
	public static final double nabs(final double num) {
		return (abs(num) * -1.0);
	}
	
	/**
	 * Will return the interpolated value of t
	 * @param num
	 * @return
	 */
	public static final float interpolate(final float t) {
		return -2 * t * t * t + 3 * t * t;
	}
	
	/**
	 * Will return a value between 0 and 1 based on the given time
	 * Code taken from: https://stackoverflow.com/questions/3018550/how-to-create-pulsating-value-from-0-1-0-1-0-etc-for-a-given-duration
	 * @param time
	 * @return
	 */
    public static final float pulse(final float time) {
        final float pi = (float) Math.PI;
        final float frequency = 10; // Frequency in Hz
        return (float) (0.5F * (1 + Math.sin(2 * pi * frequency * (time / 100F))));
    }
    
    /**
     * Returns the point where to lines intersect
     * @param s1
     * @param s2
     * @param d1
     * @param d2
     * @return
     */
    public static Vector getLineInterception(Vector s1, Vector s2, Vector d1, Vector d2) {
        double a1 = s2.y - s1.y;
        double b1 = s1.x - s2.x;
        double c1 = a1 * s1.x + b1 * s1.y;

        double a2 = d2.y - d1.y;
        double b2 = d1.x - d2.x;
        double c2 = a2 * d1.x + b2 * d1.y;

        double delta = a1 * b2 - a2 * b1;
        return new Vector(((b2 * c1 - b1 * c2) / delta), ((a1 * c2 - a2 * c1) / delta));
    }
    
    public static float[] getLineInterception(final float x, final float y, final float x2, final float y2, final float x3, final float y3, final float x4, final float y4) {
    	final Vector res = getLineInterception(new Vector(x, y), new Vector(x2, y2), new Vector(x3, y3), new Vector(x4, y4));
    	return new float[] {res.getXf(), res.getYf()};
    }
	
}
