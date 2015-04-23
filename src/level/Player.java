package level;
import static org.lwjgl.glfw.GLFW.*;
import graphics.Shader;
import graphics.VertexArray;
import input.Input;
import math.Matrix4f;
import math.Vector3f;

public class Player {

	private VertexArray player;
	private float rotation;
	private Vector3f position;
	private boolean isJumping;
	
	public final float SIZE = 1;

	public Player() {

		rotation = 0;
		position = new Vector3f(0, 0, 0);
		isJumping = false;

		Shader.PLAYER.enable();
		Shader.PLAYER.setUniform3f("col", new Vector3f(0.8f, 0.5f, 0.2f));
		Shader.PLAYER.disable();

		float[] vertices = new float[] { 

				-SIZE / 2, -SIZE / 2, 0,
				-SIZE / 2,  SIZE / 2, 0,
				 SIZE / 2,  SIZE / 2, 0,
				 SIZE / 2, -SIZE / 2, 0
		};

		byte[] indices = new byte[] { 
				
				0, 1, 2,
				2, 3, 0
		};

		float[] tcs = new float[] { 
				
				0, 1,
				0, 0,
				1, 0,
				1, 1 
		};

		player = new VertexArray(vertices, indices, tcs);

	}

	public void render() {
		Shader.PLAYER.enable();
		Shader.PLAYER.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		player.render();
		Shader.PLAYER.disable();
	}
	public void update() {
		
		if (Input.isKeyDown(GLFW_KEY_LEFT)) {
			if (position.x > -10f + SIZE/2) {
				setPosition(new Vector3f(-0.1f, 0, 0));
			}
		}
		if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
			if (position.x <= 10 - SIZE/2) {
				setPosition(new Vector3f(0.1f, 0, 0));
			}

		}
		if (Input.isKeyDown(GLFW_KEY_DOWN)) {
			if (position.x <= 10 - SIZE/2) {
				setPosition(new Vector3f(0, -0.1f, 0));
			}

		}
		if (Input.isKeyDown(GLFW_KEY_UP)) {
			if (position.y < 10 - SIZE/2) {
				isJumping = true;
				setPosition(new Vector3f(0, 0.1f, 0));
			}
		}
		if (!Input.isKeyDown(GLFW_KEY_UP)) {
			isJumping = false;
		}

	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping() {
		isJumping = false;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation += rotation;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position.x += position.x;
		this.position.y += position.y;
		this.position.z += position.z;
	}

}
