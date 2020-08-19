package de.iotacb.cu.core.math;

public class IntersectionUtil {

	/**
	 * Returns true if the given x and y are coordinates are contained in the given bounds
	 * @param mouseX
	 * @param mouseY
	 * @param posX
	 * @param posY
	 * @param width
	 * @param height
	 * @return
	 */
	public static final boolean inBounds(final int x, final int y, final float posX, final float posY, final float width, final float height) {
		return (x > posX && x < posX + width) && (y > posY && y < posY + height);
	}
	
	/**
	 * Returns true if the given x and y coordinates are lying in the a given range
	 * @param mouseX
	 * @param mouseY
	 * @param posX
	 * @param posY
	 * @param range
	 * @return
	 */
	public static final boolean inBounds(final int x, final int y, final float posX, final float posY, final float range) {
		final double distX = x - posX;
		final double distY = y - posY;
		return Math.sqrt(distX * distX + distY * distY) < range;
	}
	
	/**
	 * Returns true if the given x and y coordinates are lying in the given polygon
	 * @param x
	 * @param y
	 * @param polygon
	 * @return
	 */
	public static boolean inBounds(final int x, final int y, final float[][] polygon) {
		boolean isInside = false;
	    double minX = polygon[0][0], maxX = polygon[0][0];
	    double minY = polygon[0][0], maxY = polygon[0][0];
	    for (int n = 1; n < polygon.length; n++) {
	        final float[] q = polygon[n];
	        minX = Math.min(q[0], minX);
	        maxX = Math.max(q[0], maxX);
	        minY = Math.min(q[1], minY);
	        maxY = Math.max(q[1], maxY);
	    }
	    
	    if (x < minX || x > maxX || y < minY || y > maxY) return false;

		for (int i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
			if ((polygon[i][1] > y) != (polygon[j][1] > y) && x < (polygon[j][0] - polygon[i][0]) * (y - polygon[i][1]) / (polygon[j][1] - polygon[i][1]) + polygon[i][0]) {
				isInside = !isInside;
			}
	    }
		return isInside;
	}
	
}
