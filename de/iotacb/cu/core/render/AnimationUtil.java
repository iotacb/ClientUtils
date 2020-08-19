package de.iotacb.cu.core.render;

import de.iotacb.cu.core.math.MathUtil;

public class AnimationUtil {

    public static final float animate(final float target, float current, float speed) {
        final boolean larger = target > current;
        speed = MathUtil.clamp(speed, 0F, 1F);
        final float difference = Math.max(target, current) - Math.min(target, current);
        float factor = difference * speed;
        factor = MathUtil.clamp(factor, .1F, factor);
        current += (larger ? factor : -factor);
        return current;
    }
	
}
