package level;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

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
	
	private BodyDef def;
	private PolygonShape shape;
	
	public Floor(){
		
		Shader.FLOOR.enable();
		Shader.FLOOR.setUniform3f("col", new Vector3f(0.2f, 0.5f, 0.8f));
		Shader.FLOOR.disable();
		
		bottomLeft = 	new Vector3f(-10, -10, 0);
		topLeft = 		new Vector3f(-10, -5 , 0); 
		bottomRight =	new Vector3f( 10, -10, 0);
		topRight = 		new Vector3f( 10, -5 , 0);
		
		
		position = new Vector3f(bottomLeft.x - bottomRight.x,bottomLeft.y - topLeft.y / 2,0);
		
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
		
		Vec2[] vertices1 = new Vec2[] {
				new Vec2(bottomLeft.x, bottomLeft.y	* 9.0f / 16.0f),
				new Vec2(topLeft.x, topLeft.y	* 9.0f / 16.0f),
				new Vec2(bottomRight.x, bottomRight.y	* 9.0f / 16.0f),
				new Vec2(topRight.x, topRight.y	* 9.0f / 16.0f),
			};
		
		
		def = new BodyDef();
		def.type = BodyType.STATIC;
//		def.position.set(0, );
		def.fixedRotation = true;
		
		shape = new PolygonShape();
		shape.set(vertices1, 4);
		
	}
	
	public PolygonShape getShape() {
		return shape;
	}
	
	public BodyDef getBody() {
		return def;
	}
	
//	private int floorDirection = 0;
//	private int movingDirection = 0;
//	private boolean changingDirection = false;
//	private int length = 1 * 60;
	
	public void update() {
		
//		if(changingDirection == false){
//			length--;
//			if(length == 0){
//				changingDirection = true;
//				
//			}
//		}
//			
//		if(changingDirection) {
//			switch (floorDirection % 4) {
//			case 0:
//				if(topRight.y <= 10) {
//					topLeft.y += 0.05f;
//					topRight.x -= 0.05f;
//					topRight.y += 0.05f;
//					bottomRight.x -= 0.05f;
//				}else{
//					changingDirection = false;
//					length = 2 * 60;
//					floorDirection++;
//				}
//				break;
//			case 1:
//				if(bottomRight.x <= 10) {
//					bottomLeft.y += 0.05f;
//					topRight.x += 0.05f;
//					bottomRight.x += 0.05f;
//					bottomRight.y += 0.05f;
//				}
//				else{
//					changingDirection = false;
//					length = 2 * 60;
//					floorDirection++;
//				}
//				break;
//			case 2:
//				if(bottomRight.y >= -10) {
//					topLeft.x += 0.05f;
//					bottomLeft.x += 0.05f;
//					bottomLeft.y -= 0.05f;
//					bottomRight.y -= 0.05f;
//				}
//				else{
//					changingDirection = false;
//					length = 2 * 60;
//					floorDirection++;
//				}
//				break;
//			case 3:
//				if(topLeft.x >= -10) {
//					topLeft.y -= 0.05f;
//					topLeft.x -= 0.05f;
//					topRight.y -= 0.05f;
//					bottomLeft.x -= 0.05f;
//				}
//				else{
//					changingDirection = false;
//					length = 2 * 60;
//					floorDirection++;
//				}
//				break;
//			}
//		}
//		
//		updateBuffer();
//		
	}
	
	public void render(){
		Shader.FLOOR.enable();
		floor.render();
		Shader.FLOOR.disable();
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
	
//	public void setDirection() {
//		movingDirection++;
//	}
//	
//	public int getMovingDirection() {
//		return movingDirection % 4;
//	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getTopRight() {
		return topRight;
	}

	public Vector3f getTopLeft() {
		return topLeft;
	}

	public Vector3f getBottomRight() {
		return bottomRight;
	}

	public Vector3f getBottomLeft() {
		return bottomLeft;
	}
}
