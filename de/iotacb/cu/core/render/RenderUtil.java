package de.iotacb.cu.core.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.util.AxisAlignedBB;

public class RenderUtil {
	
	/**
	 * Sets up basic rendering parameters
	 */
	public static final void startRender() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	/**
	 * Resets the rendering parameters
	 */
	public static final void stopRender() {
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
	}
	
	/**
	 * Sets the current color using rgb values
	 * @param color
	 */
	public static final void color(final Color color) {
		GL11.glColor4d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
	}

	/**
	 * Draws a rectangle at the given position with the given size and color
	 * @param posX
	 * @param posY
	 * @param width
	 * @param height
	 * @param color
	 */
	public static final void drawRect(final double posX, final double posY, final double width, final double height, final Color color) {
		startRender();
		color(color);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2d(posX, posY);
			GL11.glVertex2d(posX + width, posY);
			GL11.glVertex2d(posX + width, posY + height);
			GL11.glVertex2d(posX, posY + height);
		}
		GL11.glEnd();
		stopRender();
	}
	
	public static final void drawOutlinedBoundingBox(final AxisAlignedBB bb) {
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBegin(GL11.GL_LINES);
		{
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
		}
		GL11.glEnd();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}
	
	public static final void drawFilledBoundingBox(final AxisAlignedBB bb) {
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
			
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
			GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
		}
		GL11.glEnd();
	}
	
}
