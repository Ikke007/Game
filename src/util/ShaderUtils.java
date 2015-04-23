package util;

import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.opengl.GL11;

public class ShaderUtils {

	private ShaderUtils() {
	}

	public static int load(String vertPath, String fragPath) {
		
		String vert = FileUtils.loadAsString(vertPath);
		String frag = FileUtils.loadAsString(fragPath);
		return create(vert, frag);
		
	}

	public static int create(String vert, String frag) {

		int program = glCreateProgram();
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(vertID, vert);
		glShaderSource(fragID, frag);
		
		glCompileShader(vertID);
		if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Failes to compile vertex shader!");
			System.err.println(glGetShaderInfoLog(vertID, 2048));
			return -1;
		}

		glCompileShader(fragID);
		if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Failes to compile fragment shader!");
			System.err.println(glGetShaderInfoLog(fragID, 2048));
			return -1;
		}

		glAttachShader(program, vertID);
		glAttachShader(program, fragID);
		glLinkProgram(program);
		glValidateProgram(program);

		glDeleteShader(fragID);
		glDeleteShader(vertID);

		return program;
	}

}
