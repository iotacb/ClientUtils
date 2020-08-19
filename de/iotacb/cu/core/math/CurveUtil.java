package de.iotacb.cu.core.math;

public class CurveUtil {

	public static final float lerp(final float a, final float b, final float t) {
		return a + (b - a) * t;
	}
	
	public static final Vector lerp(final Vector a, final Vector b, final float t) {
		final float aLerp = lerp(a.getXf(), b.getXf(), t);
		final float bLerp = lerp(a.getYf(), b.getYf(), t);
		return new Vector(aLerp, bLerp);
	}
	
	public static final Vector quadraticCurve(final Vector a, final Vector b, final Vector c, final float t) {
		final Vector aLerp = lerp(a, b, t);
		final Vector bLerp = lerp(b, c, t);
		return lerp(aLerp, bLerp, t);
	}
	
	public static final Vector cubicCurve(final Vector a, final Vector b, final Vector c, final Vector d, final float t) {
		final Vector aLerp = quadraticCurve(a, b, c, t);
		final Vector bLerp = quadraticCurve(b, c, d, t);
		return lerp(aLerp, bLerp, t);
	}
	
}
