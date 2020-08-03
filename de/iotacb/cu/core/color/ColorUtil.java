package de.iotacb.cu.core.color;

import java.awt.Color;

import de.iotacb.cu.core.math.MathUtil;

public class ColorUtil {
	
	/**
	 * Adds color values to a color
	 * The values will be clamped in the valid rgb color space (0-255)
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 * @param color
	 * @return
	 */
	public static final Color addColor(final int red, final int green, final int blue, final int alpha, final Color color) {
		final int newRed = MathUtil.clamp(color.getRed() + red, 0, 255);
		final int newGreen = MathUtil.clamp(color.getGreen() + green, 0, 255);
		final int newBlue = MathUtil.clamp(color.getBlue() + blue, 0, 255);
		final int newAlpha = MathUtil.clamp(color.getAlpha() + alpha, 0, 255);
		return new Color(newRed, newGreen, newBlue, newAlpha);
	}
	
	/**
	 * Adds color values to a color
	 * The values will be clamped in the valid rgb color space (0-255)
	 * @param red
	 * @param green
	 * @param blue
	 * @param color
	 * @return
	 */
	public static final Color addColor(final int red, final int green, final int blue, final Color color) {
		final int newRed = MathUtil.clamp(color.getRed() + red, 0, 255);
		final int newGreen = MathUtil.clamp(color.getGreen() + green, 0, 255);
		final int newBlue = MathUtil.clamp(color.getBlue() + blue, 0, 255);
		return new Color(newRed, newGreen, newBlue, color.getAlpha());
	}
	
	/**
	 * Sets the alpha value of a color
	 * The value will be clamped in the valid rgb color space (0-255)
	 * @param alpha
	 * @param color
	 * @return
	 */
	public static final Color setAlpha(final int alpha, final Color color) {
		final int newAlpha = MathUtil.clamp(alpha, 0, 255);
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), newAlpha);
	}
	
	/**
	 * Returns the rgb color of a hue
	 * @param value
	 * @return
	 */
	public static final Color getColorOfHue(final int value) {
        final float hue = (1.0F - value / 360.0F);
        final int color = Color.HSBtoRGB(hue, 1.0F, 1.0F);
        return new Color(color);
	}

}
