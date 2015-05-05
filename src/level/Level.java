package level;

import input.Input;
import static org.lwjgl.glfw.GLFW.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import math.Vector3f;

public class Level {

	private Player player;
	private Floor floor;
	
	private Body pbody;
	private Body fbody;
	private World world;
	
	public Level() {
		
		player = new Player();
		floor = new Floor();
		
		world = new World(new Vec2(0, -2f), false);
		pbody = world.createBody(player.getBody());
		pbody.createFixture(player.getShape(), 1.0f);
		fbody = world.createBody(floor.getBody());
		fbody.createFixture(floor.getShape(), 0.0f);
		
//		pbody.getTransform().position
		
		
		
	}
	
//	public boolean collision() {
//		
//		float playerX;
//		float playerY;
//		float floorY;
//		float floorX;
//		
//		
//		switch (floor.getMovingDirection()) {
//		case 0:
//			playerX = player.getPosition().x - player.getSize() / 2;
//			playerY = player.getPosition().y - player.getSize() / 2;
//			floorY = floor.getTopRight().y * 9 /16;
//			floorX = floor.getTopRight().x;
//			if(player.getPosition().y + player.getSize()  <= floorY)
//				floor.setDirection();
//			if(playerY < floorY && playerX <= floorX) {
//				return true;
//			}
//			break;
//
//		case 1:
//			playerX = player.getPosition().x - player.getSize() / 2;
//			playerY = player.getPosition().y + player.getSize() / 2;
//			floorY = floor.getBottomRight().y * 9 /16;
//			floorX = floor.getBottomRight().x;
//			if(player.getPosition().x + player.getSize()  < floorX)
//				floor.setDirection();
//			if(playerX < floorX && playerY >= floorY){
//				return true;
//			}
//			break;
//			
//		case 2:
//			playerX = player.getPosition().x + player.getSize() / 2;
//			playerY = player.getPosition().y + player.getSize() / 2;
//			floorY = floor.getBottomLeft().y * 9 /16;
//			floorX = floor.getBottomLeft().x;
//			if(player.getPosition().y - player.getSize()  >= floorY)
//				floor.setDirection();
//			if(playerY >= floorY && playerX >= floorX)
//				return true;
//			break;
//		
//		case 3:
//			playerX = player.getPosition().x + player.getSize() / 2;
//			playerY = player.getPosition().y - player.getSize() / 2;
//			floorY = floor.getTopLeft().y * 9 /16;
//			floorX = floor.getTopLeft().x;
//			if(player.getPosition().x - player.getSize()  >= floorX)
//				floor.setDirection();
//			if(playerY < floorY && playerX > floorX)
//				return true;
//			break;	
//		}
//		
//		return false;
//		
//	}
	
	public void update() {
		
		world.step(1/60f, 6, 2);
		if (Input.isKeyDown(GLFW_KEY_UP)) {
			pbody.applyForce(new Vec2(0, 10.1f),pbody.getPosition());
//			pbody.applyLinearImpulse(new Vec2(0, 0.3f), pbody.getPosition());
		}
		if (Input.isKeyDown(GLFW_KEY_DOWN)) {
			pbody.applyForce(new Vec2(0, -10.1f),pbody.getPosition());
		}
		if (Input.isKeyDown(GLFW_KEY_LEFT)) {
			pbody.applyForce(new Vec2(-7, 0),pbody.getPosition());
		}
		if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
			pbody.applyForce(new Vec2(7, 0),pbody.getPosition());
		}
		if (Input.isKeyDown(GLFW_KEY_SPACE)) {
//			pbody.applyForce(new Vec2(7, 0),pbody.getPosition());
//			pbody.setTransform(new Vec2(0,3f), 0);
//			pbody.setLinearVelocity(new Vec2(0,0));
//			pbody.applyForce(new Vec2(0, 100.1f),pbody.getPosition());
		}
//		player.setXPosition(pbody.getPosition().x);
//		player.setYPosition(pbody.getPosition().y);
		
		player.pos(pbody.getTransform().position);
		System.out.print("Playerposition: ");
		System.out.println(pbody.getPosition().y - player.getSize() /2);
		System.out.println("topleft: " + floor.getTopLeft().y * 9.0f / 16.0f);
//		System.out.println(player.getPosition().y);
//		System.out.print(floor.getPosition().x);
//		System.out.println(floor.getPosition().y);
//		System.out.println(pbody.getPosition());
//		switch (floor.getMovingDirection()) {
//		case 0:
//			player.setLeftKeyMovement(new Vector3f(-0.1f, 0, 0));
//			player.setRightKeyMovement(new Vector3f(0.1f, 0, 0));
//			player.setUpKeyMovement(new Vector3f(0, 0.1f, 0));
//			player.setDownKeyMovement(new Vector3f(0, -0.1f, 0));
//			break;
//		case 1:
//			player.setLeftKeyMovement(new Vector3f(0, 0.1f, 0));
//			player.setRightKeyMovement(new Vector3f(0, -0.1f, 0));
//			player.setUpKeyMovement(new Vector3f(0.1f, 0, 0));
//			player.setDownKeyMovement(new Vector3f(-0.1f, 0, 0));
//			break;
//		case 2:
//			player.setLeftKeyMovement(new Vector3f(0.1f, 0, 0));
//			player.setRightKeyMovement(new Vector3f(-0.1f, 0, 0));
//			player.setUpKeyMovement(new Vector3f(0, -0.1f, 0));
//			player.setDownKeyMovement(new Vector3f(0, 0.1f, 0));
//			break;
//		case 3:
//			player.setLeftKeyMovement(new Vector3f(0, -0.1f, 0));
//			player.setRightKeyMovement(new Vector3f(0, 0.1f, 0));
//			player.setUpKeyMovement(new Vector3f(-0.1f, 0, 0));
//			player.setDownKeyMovement(new Vector3f(0.1f, 0, 0));
//			break;
//			
//		}
//		
//		switch (floor.getMovingDirection()) {
//		case 0:
//			if(!player.isJumping() && !collision())
//				player.addPosition(new Vector3f(0, -0.1f, 0));
//			if(collision() && !player.isJumping())
//				player.setYPosition(floor.getTopRight().y * 9/16 + player.getSize()/2);
//			break;
//			
//		case 1:
// 			if(!player.isJumping() && !collision())
//				player.addPosition(new Vector3f(-0.1f, 0, 0));
//			if(collision() && !player.isJumping())
//				player.setXPosition(floor.getBottomRight().x + player.getSize()/2);
//			break;
//		
//		case 2:
//			if(!player.isJumping() && !collision())
//				player.addPosition(new Vector3f(0, 0.1f, 0));
//			if(collision() && !player.isJumping())
//				player.setYPosition(floor.getBottomLeft().y * 9/16 - player.getSize()/2);
//			break;
//			
//		case 3:
// 			if(!player.isJumping() && !collision())
//				player.addPosition(new Vector3f(0.1f, 0, 0));
//			if(collision() && !player.isJumping())
//				player.setXPosition(floor.getTopLeft().x - player.getSize()/2);
//			break;
//		}
		
		player.update();
		floor.update();
		
	}
	
	public void render() {
		
		player.render();
		floor.render();
		
	}
	
}
