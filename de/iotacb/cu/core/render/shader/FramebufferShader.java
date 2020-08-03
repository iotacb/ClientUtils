package de.iotacb.cu.core.render.shader;

import java.awt.Color;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.shader.Framebuffer;

public abstract class FramebufferShader extends Shader {

	public FramebufferShader(final String fragmentShaderName) {
		super(fragmentShaderName);
	}
	
	private static Framebuffer framebuffer;
	
	private float[] colorArray = new float[] {1, 1, 1};
	
	public final void renderShader(final float partialTicks) {
		GlStateManager.enableAlpha();
		
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		
		makeFrameBuffer();
		framebuffer.framebufferClear();
		framebuffer.bindFramebuffer(true);
		getMc().entityRenderer.setupCameraTransform(partialTicks, 0);
	}
	
	public final void clearShader(final Color color) {
		
		colorArray[0] = color.getRed() / 255.0F;
		colorArray[1] = color.getGreen() / 255.0F;
		colorArray[2] = color.getBlue() / 255.0F;
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		getMc().getFramebuffer().bindFramebuffer(true);
		
		getMc().entityRenderer.disableLightmap();
		RenderHelper.disableStandardItemLighting();
		
		startShader();
		getMc().entityRenderer.setupOverlayRendering();
		drawFrameBuffer();
		stopShader();
		
		getMc().entityRenderer.disableLightmap();
		
		GlStateManager.popMatrix();
		GlStateManager.popAttrib();
	}
	
	public final void makeFrameBuffer() {
		if (framebuffer != null) framebuffer.deleteFramebuffer();
		
		framebuffer = new Framebuffer(getMc().displayWidth, getMc().displayHeight, true);
	}
	
	public final void drawFrameBuffer() {
		final ScaledResolution scaledResolution = new ScaledResolution(getMc());
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer.framebufferTexture);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, scaledResolution.getScaledHeight());
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(scaledResolution.getScaledWidth(), 0);
		}
		GL11.glEnd();
		GL20.glUseProgram(0);
	}
	
	public float[] getColorArray() {
		return colorArray;
	}
	
}
