package level;
import graphics.Shader;
import graphics.VertexArray;
import math.Vector3f;

public class Floor {
	
	private VertexArray floor;
	private Vector3f position;
	private Vector3f topRight, topLeft, bottomRight, bottomLeft;
	
	private float[] vertices;
	private byte[] indices;
	private float[] tcs;
	
	public Floor(){
		
		Shader.FLOOR.enable();
		Shader.FLOOR.setUniform3f("col", new Vector3f(0.2f, 0.5f, 0.8f));
		Shader.FLOOR.disable();
		
		bottomLeft = 	new Vector3f(-10, -10, 0);
		topLeft = 		new Vector3f(-10, -6 , 0); 
		bottomRight =	new Vector3f( 10, -10, 0);
		topRight = 		new Vector3f( 10, -6 , 0);
		
		
		position = new Vector3f(0,-5,0);
		
		vertices = new float[] {
				bottomLeft.x, 	bottomLeft.y	* 9.0f / 16.0f, 0,
				topLeft.x, 		topLeft.y  		* 9.0f / 16.0f, 0,
				bottomRight.x, 	bottomRight.y 	* 9.0f / 16.0f, 0,
				topRight.x, 	topRight.y  	* 9.0f / 16.0f, 0
			};
		
		indices = new byte[] {
				0, 1, 2,
				1, 3, 2
		};
		
		tcs = new float[] {
				0, 1,
				0, 0,
				1, 0,
				1, 1
		};
		
		floor = new VertexArray(vertices, indices, tcs);
		
	}
	
	private int direction = 0;
	private boolean changingDirection = false;
	private int length = 1 * 60;
	
	public void update() {
		
		if(changingDirection == false){
			length--;
			if(length == 0){
				changingDirection = true;
				direction++;
			}
		}
			
		if(changingDirection) {
			switch (direction % 4) {
			case 1:
				if(topRight.y <= 10) {
					topLeft.y += 0.05f;
					topRight.x -= 0.05f;
					topRight.y += 0.05f;
					bottomRight.x -= 0.05f;
				}else{
					changingDirection = false;
					length = 1 * 60;
				}
				break;
			case 2:
				if(bottomRight.x <= 10) {
					bottomLeft.y += 0.05f;
					topRight.x += 0.05f;
					bottomRight.x += 0.05f;
					bottomRight.y += 0.05f;
					
				}
				else{
					changingDirection = false;
					length = 1 * 60;
				}
				break;
			case 3:
				if(bottomRight.y >= -10) {
					topLeft.x += 0.05f;
					bottomLeft.x += 0.05f;
					bottomLeft.y -= 0.05f;
					bottomRight.y -= 0.05f;
				}
				else{
					changingDirection = false;
					length = 1 * 60;
				}
				break;
			case 0:
				if(topLeft.x >= -10) {
					topLeft.y -= 0.05f;
					topLeft.x -= 0.05f;
					topRight.y -= 0.05f;
					bottomLeft.x -= 0.05f;
				}
				else{
					changingDirection = false;
					length = 1 * 60;
				}
				break;
			}
		}
		
		updateBuffer();
		position.y = topRight.y;
		
	}
	
	public void render(){
		Shader.FLOOR.enable();
		floor.render();
		Shader.FLOOR.disable();
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	private void updateBuffer() {
		
		vertices = new float[] {
				bottomLeft.x, 	bottomLeft.y	* 9.0f / 16.0f, 0,
				topLeft.x, 		topLeft.y  		* 9.0f / 16.0f, 0,
				bottomRight.x, 	bottomRight.y 	* 9.0f / 16.0f, 0,
				topRight.x, 	topRight.y  	* 9.0f / 16.0f, 0
			};
		
		floor = new VertexArray(vertices, indices, tcs);
		
	}
}
