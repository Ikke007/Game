package level;

import math.Vector3f;

public class Level {

	private Player player;
	private Floor floor;
	
	public Level() {
		
		player = new Player();
		floor = new Floor();
		
	}
	
	public boolean collision() {
		
		float playerX;
		float playerY;
		float floorY;
		float floorX;
		
		
		switch (floor.getMovingDirection()) {
		case 0:
			playerX = player.getPosition().x - player.getSize() / 2;
			playerY = player.getPosition().y - player.getSize() / 2;
			floorY = floor.getTopRight().y * 9 /16;
			floorX = floor.getTopRight().x;
			if(player.getPosition().y + player.getSize()  <= floorY)
				floor.setDirection();
			if(playerY < floorY && playerX <= floorX) {
				return true;
			}
			break;

		case 1:
			playerX = player.getPosition().x - player.getSize() / 2;
			playerY = player.getPosition().y + player.getSize() / 2;
			floorY = floor.getBottomRight().y * 9 /16;
			floorX = floor.getBottomRight().x;
			if(player.getPosition().x + player.getSize()  < floorX)
				floor.setDirection();
			if(playerX < floorX && playerY >= floorY){
				return true;
			}
			break;
			
		case 2:
			playerX = player.getPosition().x + player.getSize() / 2;
			playerY = player.getPosition().y + player.getSize() / 2;
			floorY = floor.getBottomLeft().y * 9 /16;
			floorX = floor.getBottomLeft().x;
			if(player.getPosition().y - player.getSize()  >= floorY)
				floor.setDirection();
			if(playerY >= floorY && playerX >= floorX)
				return true;
			break;
		
		case 3:
			playerX = player.getPosition().x + player.getSize() / 2;
			playerY = player.getPosition().y - player.getSize() / 2;
			floorY = floor.getTopLeft().y * 9 /16;
			floorX = floor.getTopLeft().x;
			if(player.getPosition().x - player.getSize()  >= floorX)
				floor.setDirection();
			if(playerY < floorY && playerX > floorX)
				return true;
			break;	
		}
		
		return false;
		
	}
	
	public void update() {
		
		switch (floor.getMovingDirection()) {
		case 0:
			player.setLeftKeyMovement(new Vector3f(-0.1f, 0, 0));
			player.setRightKeyMovement(new Vector3f(0.1f, 0, 0));
			player.setUpKeyMovement(new Vector3f(0, 0.1f, 0));
			player.setDownKeyMovement(new Vector3f(0, -0.1f, 0));
			break;
		case 1:
			player.setLeftKeyMovement(new Vector3f(0, 0.1f, 0));
			player.setRightKeyMovement(new Vector3f(0, -0.1f, 0));
			player.setUpKeyMovement(new Vector3f(0.1f, 0, 0));
			player.setDownKeyMovement(new Vector3f(-0.1f, 0, 0));
			break;
		case 2:
			player.setLeftKeyMovement(new Vector3f(0.1f, 0, 0));
			player.setRightKeyMovement(new Vector3f(-0.1f, 0, 0));
			player.setUpKeyMovement(new Vector3f(0, -0.1f, 0));
			player.setDownKeyMovement(new Vector3f(0, 0.1f, 0));
			break;
		case 3:
			player.setLeftKeyMovement(new Vector3f(0, -0.1f, 0));
			player.setRightKeyMovement(new Vector3f(0, 0.1f, 0));
			player.setUpKeyMovement(new Vector3f(-0.1f, 0, 0));
			player.setDownKeyMovement(new Vector3f(0.1f, 0, 0));
			break;
			
		}
		
		switch (floor.getMovingDirection()) {
		case 0:
			if(!player.isJumping() && !collision())
				player.addPosition(new Vector3f(0, -0.1f, 0));
			if(collision() && !player.isJumping())
				player.setYPosition(floor.getTopRight().y * 9/16 + player.getSize()/2);
			break;
			
		case 1:
 			if(!player.isJumping() && !collision())
				player.addPosition(new Vector3f(-0.1f, 0, 0));
			if(collision() && !player.isJumping())
				player.setXPosition(floor.getBottomRight().x + player.getSize()/2);
			break;
		
		case 2:
			if(!player.isJumping() && !collision())
				player.addPosition(new Vector3f(0, 0.1f, 0));
			if(collision() && !player.isJumping())
				player.setYPosition(floor.getBottomLeft().y * 9/16 - player.getSize()/2);
			break;
			
		case 3:
 			if(!player.isJumping() && !collision())
				player.addPosition(new Vector3f(0.1f, 0, 0));
			if(collision() && !player.isJumping())
				player.setXPosition(floor.getTopLeft().x - player.getSize()/2);
			break;
		}
		
		player.update();
		floor.update();
		
	}
	
	public void render() {
		
		player.render();
		floor.render();
		
	}
	
}
