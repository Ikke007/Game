package graphics;
import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

import math.*;
import util.ShaderUtils;

public class Shader {

	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORD_ATTRIB = 1;
	
	public static Shader PLAYER;
	public static Shader FLOOR;
	
	private boolean enabled = false;
	
	private final int ID;
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();
	
	public Shader(String vertex, String fragment) {
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	public static void loadAll(){
		PLAYER 	= new Shader("shaders/shader.vert", "shaders/shader.frag");
		FLOOR 	= new Shader("shaders/floor.vert", "shaders/floor.frag");
	}
	
	public int getUniform(String name) {
		if (locationCache.containsKey(name))
			return locationCache.get(name);
					
		int result = glGetUniformLocation(ID, name);
		if (result == -1)
			System.err.println("Could not find uniform variable '" + name + "'!");
		else
			locationCache.put(name, result);
		return result;
		
	}
	
	public void setUniform1i(String name, int value) {
		if (!enabled) enable();
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform1f(String name, int value) {
		if (!enabled) enable();
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name, Vector2f vec) {
		if (!enabled) enable();
		glUniform2f(getUniform(name), vec.x, vec.y);
	}
	
	public void setUniform3f(String name, Vector3f vec) {
		if (!enabled) enable();
		glUniform3f(getUniform(name), vec.x, vec.y, vec.z);
	}
	
	public void setUniformMat4f(String name, Matrix4f mat) {
		if (!enabled) enable();
		glUniformMatrix4(getUniform(name), false, mat.toFloatBuffer());
	}
	
	public void enable() {
		glUseProgram(ID);
		enabled = true;
	}
	
	public void disable() {
		glUseProgram(0);
		enabled = false;
	}
	
	
}
