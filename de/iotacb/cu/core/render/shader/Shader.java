package de.iotacb.cu.core.render.shader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import net.minecraft.client.Minecraft;

public abstract class Shader {

	private int shaderProgramId;
	
	private Map<String, Integer> uniforms;
	
	private static final Minecraft MC = Minecraft.getMinecraft();
	
	/**
	 * http://wiki.lwjgl.org/wiki/GLSL_Shaders_with_LWJGL.html
	 * @param fragementShaderName
	 */
	
	public Shader(final String fragementShaderName) {
		try {
			final InputStream vertex = getClass().getResourceAsStream("/assets/minecraft/cu/shaders/base.vert");
			final int vertexId = createShaderId(IOUtils.toString(vertex), ARBVertexShader.GL_VERTEX_SHADER_ARB);
			IOUtils.closeQuietly(vertex);
			final InputStream fragment = getClass().getResourceAsStream("/assets/minecraft/cu/shaders/".concat(fragementShaderName).concat(".frag"));
			final int fragmentId = createShaderId(IOUtils.toString(fragment), ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
			IOUtils.closeQuietly(fragment);
			
			if (vertexId == 0 || fragmentId == 0) return;
			
			shaderProgramId = ARBShaderObjects.glCreateProgramObjectARB();
			
			if (shaderProgramId == 0) return;
			
			ARBShaderObjects.glAttachObjectARB(shaderProgramId, vertexId);
			ARBShaderObjects.glAttachObjectARB(shaderProgramId, fragmentId);
			
			ARBShaderObjects.glLinkProgramARB(shaderProgramId);
			ARBShaderObjects.glValidateProgramARB(shaderProgramId);
			
			uniforms = new HashMap<>();
			initUniforms();
			
			System.out.println("Successfully loaded the shader: " + fragementShaderName + "!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void initUniforms();
	public abstract void updateUniforms();
	
	public final void useShader() {
		GL20.glUseProgram(shaderProgramId); // Start using the shader
		updateUniforms();
	}
	
	public final void releaseShader() {
		GL20.glUseProgram(0); // Release the shader
	}
	
	public final int getShaderProgramId() {
		return shaderProgramId;
	}
	
	public final Map<String, Integer> getUniforms() {
		return uniforms;
	}
	
	public final int getUniform(final String uniformName) {
		return getUniforms().get(uniformName);
	}
	
	public final Minecraft getMc() {
		return MC;
	}
	
	public final void addUniform(final String uniformName) {
		getUniforms().put(uniformName, GL20.glGetUniformLocation(getShaderProgramId(), uniformName));
	}
	
	
	private int createShaderId(final String shaderCode, final int shaderType) {
		final int shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
		
		if (shader == 0) return 0;
		
		ARBShaderObjects.glShaderSourceARB(shader, shaderCode);
		ARBShaderObjects.glCompileShaderARB(shader);
		
		if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
			System.out.println("Error when creating: " + getLogInfo(shader));
			ARBShaderObjects.glDeleteObjectARB(shader);
			return shader;
		}
		return shader;
	}
	
    private String getLogInfo(int i) {
        return ARBShaderObjects.glGetInfoLogARB(i, ARBShaderObjects.glGetObjectParameteriARB(i, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
	
}
