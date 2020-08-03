package de.iotacb.cu.core.render;

import de.iotacb.cu.core.math.MathUtil;

public class AnimationUtil {

	public static final AnimationUtil INSTANCE = new AnimationUtil();
	
    public final float animate(final float target, float current, float speed) {
        final boolean larger = target > current;
        speed = MathUtil.clamp(speed, 0.0F, 1.0F);
        final float difference = Math.max(target, current) - Math.min(target, current);
        float factor = difference * speed;
        factor = MathUtil.clamp(factor, .1F, factor);
        current += (larger ? factor : -factor);
        return current;
    }
	
}
