package level;

import math.Vector3f;

public class Level {

	private Player player;
	private Floor floor;
	
	public Level() {
		
		player = new Player();
		floor = new Floor();
		
	}
	
	public void update() {
		
		player.update();
		floor.update();
		
//		player.setPosition(new Vector3f(0, floor.getPosition().y+2, 0));
//		if(player.getPosition().y >= floor.getPosition().y /*&& player.isJumping() == false*/){
//			player.setJumping();
//			Shader.PLAYER.setUniformMat4f("ml_matrix", Matrix4f.translate(player.getPosition()));
//			player.setPosition(new Vector3f(0, -0.1f, 0));
//		}
		
	}
	
	public void render() {
		
		player.render();
		floor.render();
		
	}
	
}
