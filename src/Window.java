import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import input.Input;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GLContext;

public class Window {

	private int height, width;
	private long window;
	private String title;
	private GLFWKeyCallback keyCallback;
	
	public Window(String title, int height, int width) {
		this.title = title;
		this.height = height;
		this.width = width;
		if (!initWindow()) {
			glfwTerminate();
		}
		initOpenGL();
	}

	private boolean initWindow() {
		
		if (glfwInit() == GL_FALSE) {
			System.out.println("GLFW konnte nicht initialisiert werden!");
			return false;
		}
		
		// Window Hints
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
//		glfwWindowHint(GLFW_SAMPLES, 4);
		
		// Create Window
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if (window == NULL) {
			System.out.println("Fenster konnte nicht erzeugt werden!");
			return false;
		}
		
		// Create Context
		glfwMakeContextCurrent(window);
		
		// Set Callbacks
		glfwSetWindowSizeCallback(window, new WindowResizeCallback());
		
		
		glfwShowWindow(window);
		
		return true;
		
	}
	
	public long getWindow(){
		return window;
	}
	
	public GLFWKeyCallback getKeyCallback(){
		return keyCallback;
	}
	
	public void initOpenGL(){
		
		GLContext.createFromCurrent();
		glEnable(GL_DEPTH_TEST);
		
	}
		
	public class WindowResizeCallback extends GLFWWindowSizeCallback{
		public void invoke(long window, int width, int height) {
			glViewport(0, 0, width, height);
		}
	}
	
	public boolean closed() {
		if (glfwWindowShouldClose(window) == GL_FALSE) {
			return false;
		}
		return true;
	}
}
