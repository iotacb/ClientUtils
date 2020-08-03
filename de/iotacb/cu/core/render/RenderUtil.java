package de.iotacb.cu.core.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtil {

    private static final Minecraft MC = Minecraft.getMinecraft();
    public static final RenderUtil INSTANCE = new RenderUtil();

    /**
     * Sets up basic rendering parameters
     */
    public final void startRender() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    /**
     * Resets the rendering parameters
     */
    public final void stopRender() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public final void polygonOffset(final float units) {
    	GL11.glPolygonOffset(1.0F, units);
    }
    
    /**
     * Returns the interpolated value of current
     * @param previous
     * @param current
     * @param partialTicks
     * @return
     */
    public final float interpolate(final float previous, final float current, final float partialTicks) {
    	return previous + (current - previous) * partialTicks;
    }

    /**
     * Sets the current color using rgb values
     *
     * @param color
     */
    public final void color(final Color color) {
        GL11.glColor4d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }

    /**
     * Draws a rectangle at the given position with the given size and color
     *
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     */
    public final void drawRect(final float posX, final float posY, final float width, final float height, final Color color) {
        startRender();
        color(color);
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex2f(posX, posY);
            GL11.glVertex2f(posX + width, posY);
            GL11.glVertex2f(posX + width, posY + height);
            GL11.glVertex2f(posX, posY + height);
        }
        GL11.glEnd();
        stopRender();
    }
    
    /**
     * Draws a gradient from top to bottom at the given position with the given size and colors
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     * @param color2
     */
    public final void drawGradient(final float posX, final float posY, final float width, final float height, final Color color, final Color color2) {
        startRender();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color(color);
            GL11.glVertex2f(posX, posY);
            GL11.glVertex2f(posX + width, posY);
            color(color2);
            GL11.glVertex2f(posX + width, posY + height);
            GL11.glVertex2f(posX, posY + height);
        }
        GL11.glEnd();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        stopRender();
    }
    
    /**
     * Draws a sideways gradient from left to right at the given position with the given size and colors
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     * @param color2
     */
    public final void drawSidewaysGradient(final float posX, final float posY, final float width, final float height, final Color color, final Color color2) {
        startRender();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color(color);
            GL11.glVertex2f(posX, posY);
            color(color2);
            GL11.glVertex2f(posX + width, posY);
            GL11.glVertex2f(posX + width, posY + height);
            color(color);
            GL11.glVertex2f(posX, posY + height);
        }
        GL11.glEnd();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        stopRender();
    }
    
    /**
     * Draws a doubled gradient from top to center to bottom at the given position with the given size and colors
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     * @param color2
     */
    public final void drawDoubleGradient(final float posX, final float posY, final float width, final float height, final Color color, final Color color2, final Color color3) {
        startRender();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color(color);
            GL11.glVertex2f(posX, posY);
            GL11.glVertex2f(posX + width, posY);
            color(color2);
            GL11.glVertex2f(posX + width, posY + height / 2);
            GL11.glVertex2f(posX, posY + height / 2);
            GL11.glVertex2f(posX, posY + height / 2);
            GL11.glVertex2f(posX + width, posY + height / 2);
            color(color3);
            GL11.glVertex2f(posX + width, posY + height);
            GL11.glVertex2f(posX, posY + height);
        }
        GL11.glEnd();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        stopRender();
    }
    
    /**
     * Draws a doubled gradient from left to center to right at the given position with the given size and colors
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     * @param color2
     */
    public final void drawSidewaysDoubleGradient(final float posX, final float posY, final float width, final float height, final Color color, final Color color2, final Color color3) {
        startRender();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);
        {
        	color(color);
        	GL11.glVertex2d(posX, posY);
        	color(color2);
        	GL11.glVertex2d(posX + width / 2, posY);
        	GL11.glVertex2d(posX + width / 2, posY + height);
        	color(color);
        	GL11.glVertex2d(posX, posY + height);
        	color(color2);
        	GL11.glVertex2d(posX + width / 2, posY + height);
        	GL11.glVertex2d(posX + width / 2, posY);
        	color(color3);
        	GL11.glVertex2d(posX + width, posY);
        	GL11.glVertex2d(posX + width, posY + height);
        }
        GL11.glEnd();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        stopRender();
    }

    /**
     * Draws the given image at the given position with the given size
     * @param imageLocation
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public final void image(final ResourceLocation imageLocation, final float x, final float y, final float width, final float height) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        MC.getTextureManager().bindTexture(imageLocation);
        Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, 0, 0, (int)width, (int)height, (int)width, (int)height);
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
    }

    /**
     * http://slabode.exofire.net/circle_draw.shtml
     *
     * @param xPos
     * @param yPos
     * @param radius
     * @param color
     */
    public final void drawCircle(final float xPos, final float yPos, final float radius, final Color color) {
        startRender();
        color(color);
        GL11.glBegin(GL11.GL_POLYGON);
        {
            drawCircle(xPos, yPos, radius);
        }
        GL11.glEnd();

		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glLineWidth(2);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		{
			drawCircle(xPos, yPos, radius);
		}
    	GL11.glEnd();
        stopRender();
    }

    /**
     * From Stackoverlow, don't have the link anymore.
     * @param xPos
     * @param yPos
     * @param radius
     */
    private void drawCircle(final float xPos, final float yPos, final float radius) {
    	final float theta = (float) (2 * Math.PI / 360.0);
    	final float tangetial_factor = (float) Math.tan(theta); // calculate the tangential factor
    	final float radial_factor = (float) Math.cos(theta); // calculate the radial factor
    	float x = radius; // we start at angle = 0
    	float y = 0;
        for (int i = 0; i < 360; i++) {
            GL11.glVertex2f(x + xPos, y + yPos);

            // calculate the tangential vector
            // remember, the radial vector is (x, y)
            // to get the tangential vector we flip those coordinates and negate one of them

            final double tx = -y;
            final double ty = x;

            // add the tangential vector

            x += tx * tangetial_factor;
            y += ty * tangetial_factor;

            // correct using the radial factor
            x *= radial_factor;
            y *= radial_factor;
        }
    }
    
    /**
     * Draws a line from pos1 (x, y) to pos2 (x2, y2)
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param thickness
     * @param color
     */
    public final void drawLine(final float x, final float y, final float x2, final float y2, final float thickness, final Color color) {
    	startRender();
    	color(color);
    	GL11.glEnable(GL11.GL_LINE_SMOOTH);
    	GL11.glLineWidth(thickness);
    	GL11.glBegin(GL11.GL_LINE_LOOP);
    	{
    		GL11.glVertex2f(x, y);
    		GL11.glVertex2f(x2, y2);
    	}
    	GL11.glEnd();
    	GL11.glDisable(GL11.GL_LINE_SMOOTH);
    	stopRender();
    }
    
    /**
     * Draws a line from pos1 (x, y, z) to pos2 (x2, y2, z2)
     * @param x
     * @param y
     * @param z
     * @param x2
     * @param y2
     * @param z2
     * @param thickness
     * @param color
     */
    public final void drawLine(final float x, final float y, final float z, final float x2, final float y2, final float z2, final float thickness, final Color color) {
    	startRender();
    	color(color);
    	GL11.glLineWidth(thickness);
    	GL11.glBegin(GL11.GL_LINE_LOOP);
    	{
    		GL11.glVertex3f(x, y, z);
    		GL11.glVertex3f(x2, y2, z2);
    	}
    	GL11.glEnd();
    	stopRender();
    }

    /**
     * Sets up the vertices for drawing an outlined bounding box
     *
     * @param bb
     */
    public final void prepareOutlinedBoundingBox(final AxisAlignedBB bb) {
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

    /**
     * Sets up the vertices for a filled bounding box
     *
     * @param bb
     */
    public final void prepareFilledBoundingBox(final AxisAlignedBB bb) {
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
    
    /**
     * Prepares a scissor box at the given location with the given size
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public final void scissor(float x, float y, float width, float height) {
    	ScaledResolution sr = new ScaledResolution(MC);
    	final double scale = sr.getScaleFactor();
    	
    	y = sr.getScaledHeight() - y;
    	
    	x *= scale;
    	y *= scale;
    	width *= scale;
    	height *= scale;
    	
    	GL11.glScissor((int) x, (int) (y - height), (int) width, (int) height);
    }
    
    public final void drawDiamondField(final float xPos, final float yPos, final int xAmount, final int yAmount, final float diamondSize, final Color color) {
        RenderUtil.INSTANCE.startRender();
        RenderUtil.INSTANCE.color(color);
        GL11.glBegin(GL11.GL_QUADS);
        {
        	final float size = 10;
        	final int amount = 100;
        	for (int x = 0; x < xAmount; x++) {
        		for (int y = 0; y < yAmount; y++) {
        			GL11.glVertex2d(xPos + (x * size), yPos + (y * size) + size / 2);
        			GL11.glVertex2d(xPos + (x * size) + size / 2, yPos + (y * size));
        			GL11.glVertex2d(xPos + (x * size) + size, yPos + (y * size) + size / 2);
        			GL11.glVertex2d(xPos + (x * size) + size / 2, yPos + (y * size) + size);
        		}
        	}
        }
        GL11.glEnd();
        RenderUtil.INSTANCE.stopRender();
    }

}
