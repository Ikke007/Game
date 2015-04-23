import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import graphics.Shader;
import input.Input;
import level.Floor;
import level.Player;
import math.Matrix4f;
import math.Vector3f;

public class Main implements Runnable {

	private Window window;
	private Thread thread;

	private Player player;
	private Floor floor;
	private Matrix4f pr_matrix;
	private Matrix4f vw_matrix;
	
	private int direction = 1;
	
	public void init() {

		window = new Window("GAME", 900, 1600);

		System.out.println("OpenGL-Version: " + glGetString(GL_VERSION));

		glClearColor(1.0f, 1.0f, 0f, 1.0f);

		Shader.loadAll();

		pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
		
		Shader.PLAYER.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.FLOOR.setUniformMat4f("pr_matrix", pr_matrix);

		player = new Player();
		floor = new Floor();

	}

	public void run() {
		
		init();

		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (!window.closed()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1.0){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			
//			int i = glGetError();
//			if (i != GL_NO_ERROR)
//				System.out.println(i);
		}

	}
	
	private void update() {
		glfwPollEvents();
//		player.setPosition(new Vector3f(0, floor.getPosition().y+2, 0));
//		if(player.getPosition().y >= floor.getPosition().y /*&& player.isJumping() == false*/){
//			player.setJumping();
//			Shader.PLAYER.setUniformMat4f("ml_matrix", Matrix4f.translate(player.getPosition()));
//			player.setPosition(new Vector3f(0, -0.1f, 0));
//		}
		player.update();
		floor.update();
		
		if(Input.isKeyDown(GLFW_KEY_ESCAPE))
			glfwSetWindowShouldClose(window.getWindow(), GL_TRUE);
		
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		player.render();
		floor.render();
		glfwSwapBuffers(window.getWindow());
	}

	public void start() {
		thread = new Thread(this, "Game");
		thread.start();
	}

	public static void main(String[] args) {
		new Main().start();
	}

}