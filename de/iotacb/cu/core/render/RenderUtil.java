package de.iotacb.cu.core.render;

import de.iotacb.cu.core.color.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtil {

    private static final Minecraft MC = Minecraft.getMinecraft();

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
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        color(Color.white);
    }

    public static final void polygonOffset(final float units) {
        GL11.glPolygonOffset(1.0F, units);
    }

    /**
     * Returns the interpolated value of current
     *
     * @param previous
     * @param current
     * @param partialTicks
     * @return
     */
    public static final float interpolate(final float previous, final float current, final float partialTicks) {
        return previous + (current - previous) * partialTicks;
    }

    /**
     * Sets the current color using rgb values
     *
     * @param color
     */
    public static final void color(final Color color) {
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
    public static final void drawRect(final float posX, final float posY, final float width, final float height, final Color color) {
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
     *
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     * @param color2
     */
    public static final void drawGradient(final float posX, final float posY, final float width, final float height, final Color color, final Color color2) {
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
     *
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     * @param color2
     */
    public static final void drawSidewaysGradient(final float posX, final float posY, final float width, final float height, final Color color, final Color color2) {
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
     *
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     * @param color2
     */
    public static final void drawDoubleGradient(final float posX, final float posY, final float width, final float height, final Color color, final Color color2, final Color color3) {
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
     *
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param color
     * @param color2
     */
    public static final void drawSidewaysDoubleGradient(final float posX, final float posY, final float width, final float height, final Color color, final Color color2, final Color color3) {
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
     *
     * @param imageLocation
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static final void drawImage(final ResourceLocation imageLocation, final float x, final float y, final float width, final float height) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        MC.getTextureManager().bindTexture(imageLocation);
        Gui.drawModalRectWithCustomSizedTexture((int) x, (int) y, 0, 0, (int) width, (int) height, (int) width, (int) height);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
    }


    /**
     * http://slabode.exofire.net/circle_draw.shtml
     *
     * @param xPos
     * @param yPos
     * @param radius
     * @param color
     */
    public static final void drawCircle(final float xPos, final float yPos, final float radius, final Color color) {
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
     *
     * @param xPos
     * @param yPos
     * @param radius
     */
    private static void drawCircle(final float xPos, final float yPos, final float radius) {
        final float theta = (float) (2 * Math.PI / 360.0F);
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
     *
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param thickness
     * @param color
     */
    public static final void drawLine(final float x, final float y, final float x2, final float y2, final float thickness, final Color color) {
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
     *
     * @param x
     * @param y
     * @param z
     * @param x2
     * @param y2
     * @param z2
     * @param thickness
     * @param color
     */
    public static final void drawLine(final float x, final float y, final float z, final float x2, final float y2, final float z2, final float thickness, final Color color) {
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
    public static final void prepareOutlinedBoundingBox(final AxisAlignedBB bb) {
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
    public static final void prepareFilledBoundingBox(final AxisAlignedBB bb) {
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
     * Draws a shaded bounding box
     *
     * @param axisAlignedBB
     * @param color
     * @param depth
     */
    public static final void prepareFilledShadedBoundingBox(final AxisAlignedBB axisAlignedBB, final Color color) {
        final Color alphaColor = ColorUtil.setAlpha(0, color);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);
        {
            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);

            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);

            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);

            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);

            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);

            color(color);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);

            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);

            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
            color(color);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);

            color(color);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);

            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);

            color(alphaColor);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);

            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
            GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
            GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        }
        GL11.glEnd();
        GL11.glShadeModel(GL11.GL_FLAT);
        GlStateManager.enableAlpha();
        GlStateManager.resetColor();
    }

    /**
     * Prepares a scissor box at the given location with the given size
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static final void scissor(float x, float y, float width, float height) {
        final ScaledResolution sr = new ScaledResolution(MC);
        final double scale = sr.getScaleFactor();

        y = sr.getScaledHeight() - y;

        x *= scale;
        y *= scale;
        width *= scale;
        height *= scale;

        GL11.glScissor((int) x, (int) (y - height), (int) width, (int) height);
    }

    /**
     * Draws a polygon using the given corner points
     *
     * @param points Corners of the polygon
     * @param color
     */
    public static final void drawFilledPolygon(final float offsetX, final float offsetY, final float[][] points, final Color color) {
        startRender();
        color(color);
        GlStateManager.disableCull();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            for (int i = 0; i < points.length; i++) {
                GL11.glVertex2d(offsetX + points[i][0], offsetY + points[i][1]);
            }
        }
        GL11.glEnd();
        GlStateManager.enableCull();
        stopRender();
    }

    /**
     * Draws a outlined polygon using the given corner points
     *
     * @param points       Corners of the polygon
     * @param autoComplete Will close the polygon if it is not closed
     * @param thickness
     * @param color
     */
    public static final void drawOutlinedPolygon(final float offsetX, final float offsetY, final float[][] points, final boolean autoComplete, final float thickness, final Color color) {
        startRender();
        color(color);
        GlStateManager.disableCull();
        GL11.glBegin(autoComplete ? GL11.GL_LINE_LOOP : GL11.GL_LINE_STRIP);
        {
            for (int i = 0; i < points.length; i++) {
                GL11.glVertex2d(offsetX + points[i][0], offsetY + points[i][1]);
            }
        }
        GL11.glEnd();
        GlStateManager.enableCull();
        stopRender();
    }

    /**
     * Draws the bounding box with a outlined box
     *
     * @param bb
     * @param color
     * @param thickness
     */
    public static final void drawOutlinedBoundingBox(final AxisAlignedBB bb, final Color color, final float thickness) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(thickness);
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        color(color);
        prepareOutlinedBoundingBox(bb);
        color(Color.white);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /**
     * Draws the entity bounding box with a outlined box
     *
     * @param entity
     * @param color
     * @param thickness
     */
    public static final void drawOutlinedBoundingBox(final Entity entity, final Color color, final float thickness) {
        final double x = RenderUtil.interpolate((float) entity.prevPosX, (float) entity.posX, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosX;
        final double y = RenderUtil.interpolate((float) entity.prevPosY, (float) entity.posY, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosY;
        final double z = RenderUtil.interpolate((float) entity.prevPosZ, (float) entity.posZ, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosZ;

        final AxisAlignedBB bb = entity.getEntityBoundingBox().offset(-entity.posX, -entity.posY, -entity.posZ).offset(x, y, z);

        drawOutlinedBoundingBox(new AxisAlignedBB(bb.minX - .2, bb.minY, bb.minZ - .2, bb.maxX + .2, bb.maxY + .2, bb.maxZ + .2), color, thickness);
    }

    /**
     * Draws the entity bounding box with a outlined box
     *
     * @param entity
     * @param color
     * @param thickness
     */
    public static final void drawOutlinedBoundingBox(final TileEntity entity, final Color color, final float thickness) {
        final double x = entity.getPos().getX() - MC.getRenderManager().renderPosX;
        final double y = entity.getPos().getY() - MC.getRenderManager().renderPosY;
        final double z = entity.getPos().getZ() - MC.getRenderManager().renderPosZ;

        drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1), color, thickness);
    }

    /**
     * Draws a bounding box with a filled box
     *
     * @param bb
     * @param color
     */
    public static final void drawFilledBoundingBox(final AxisAlignedBB bb, final Color color) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        color(color);
        prepareFilledBoundingBox(bb);
        color(Color.white);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /**
     * Draws the entity bounding box with a filled box
     *
     * @param entity
     * @param color
     */
    public static final void drawFilledBoundingBox(final Entity entity, final Color color) {
        final double x = RenderUtil.interpolate((float) entity.prevPosX, (float) entity.posX, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosX;
        final double y = RenderUtil.interpolate((float) entity.prevPosY, (float) entity.posY, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosY;
        final double z = RenderUtil.interpolate((float) entity.prevPosZ, (float) entity.posZ, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosZ;

        final AxisAlignedBB bb = entity.getEntityBoundingBox().offset(-entity.posX, -entity.posY, -entity.posZ).offset(x, y, z);

        drawFilledBoundingBox(new AxisAlignedBB(bb.minX - .2, bb.minY, bb.minZ - .2, bb.maxX + .2, bb.maxY + .2, bb.maxZ + .2), color);
    }

    /**
     * Draws the entity bounding box with a filled box
     *
     * @param entity
     * @param color
     */
    public static final void drawFilledBoundingBox(final TileEntity entity, final Color color) {
        final double x = entity.getPos().getX() - MC.getRenderManager().renderPosX;
        final double y = entity.getPos().getY() - MC.getRenderManager().renderPosY;
        final double z = entity.getPos().getZ() - MC.getRenderManager().renderPosZ;


        drawFilledBoundingBox(new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1), color);
    }

    /**
     * Draws a bounding box with a filled shaded box
     *
     * @param bb
     * @param color
     */
    public static final void drawFilledShadedBoundingBox(final AxisAlignedBB bb, final Color color) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        color(color);
        prepareFilledShadedBoundingBox(bb, color);
        color(Color.white);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /**
     * Draws the entity bounding box with a filled shaded box
     *
     * @param entity
     * @param color
     */
    public static final void drawFilledShadedBoundingBox(final Entity entity, final Color color) {
        final double x = RenderUtil.interpolate((float) entity.prevPosX, (float) entity.posX, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosX;
        final double y = RenderUtil.interpolate((float) entity.prevPosY, (float) entity.posY, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosY;
        final double z = RenderUtil.interpolate((float) entity.prevPosZ, (float) entity.posZ, MC.timer.renderPartialTicks) - MC.getRenderManager().renderPosZ;

        final AxisAlignedBB bb = entity.getEntityBoundingBox().offset(-entity.posX, -entity.posY, -entity.posZ).offset(x, y, z);

        drawFilledShadedBoundingBox(new AxisAlignedBB(bb.minX - .2, bb.minY, bb.minZ - .2, bb.maxX + .2, bb.maxY + .2, bb.maxZ + .2), color);
    }

    /**
     * Draws the entity bounding box with a filled shaded box
     *
     * @param entity
     * @param color
     */
    public static final void drawFilledShadedBoundingBox(final TileEntity entity, final Color color) {
        final double x = entity.getPos().getX() - MC.getRenderManager().renderPosX;
        final double y = entity.getPos().getY() - MC.getRenderManager().renderPosY;
        final double z = entity.getPos().getZ() - MC.getRenderManager().renderPosZ;

        drawFilledShadedBoundingBox(new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1), color);
    }

}
