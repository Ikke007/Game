package level;
import static org.lwjgl.glfw.GLFW.*;
import graphics.Shader;
import graphics.VertexArray;
import input.Input;
import math.Matrix4f;
import math.Vector3f;

public class Player {

	private VertexArray player;
	private Vector3f position;
	private boolean isJumping;
	
	public final float SIZE = 1;

	public Player() {

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
	
	private Vector3f leftKeyMovement;
	private Vector3f rightKeyMovement;
	private Vector3f upKeyMovement;
	private Vector3f downKeyMovement;
	
	public void update() {
		
		if (Input.isKeyDown(GLFW_KEY_LEFT))
			addPosition(leftKeyMovement);
		
		if (Input.isKeyDown(GLFW_KEY_RIGHT))
			addPosition(rightKeyMovement);
		
		if (Input.isKeyDown(GLFW_KEY_DOWN))
			addPosition(downKeyMovement);
		
		if (Input.isKeyDown(GLFW_KEY_UP)) {
			isJumping = true;
			addPosition(upKeyMovement);
		}
		
		if (!Input.isKeyDown(GLFW_KEY_UP))
			isJumping = false;

	}

	public float getSize() {
		return SIZE;
	}
	
	public boolean isJumping() {
		return isJumping;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void addPosition(Vector3f position) {
		this.position.x += position.x;
		this.position.y += position.y;
		this.position.z += position.z;
	}
	
	public void setYPosition(float y) {
		position.y = y;
	}
	
	public void setXPosition(float x) {
		position.x = x;
	}

	public void setLeftKeyMovement(Vector3f leftKeyMovement){
		this.leftKeyMovement = leftKeyMovement;
	}

	public void setRightKeyMovement(Vector3f rightKeyMovement) {
		this.rightKeyMovement = rightKeyMovement;
	}

	public void setUpKeyMovement(Vector3f upKeyMovement) {
		this.upKeyMovement = upKeyMovement;
	}
	
	public void setDownKeyMovement(Vector3f downKeyMovement) {
		this.downKeyMovement = downKeyMovement;
	}
}
