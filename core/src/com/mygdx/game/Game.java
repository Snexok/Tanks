package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.emmiters.BotEmmiter;
import com.mygdx.game.emmiters.BulletEmitter;
import com.mygdx.game.objs.Active;
import com.mygdx.game.objs.Bullet;
import com.mygdx.game.objs.Player;
import com.mygdx.game.objs.Tank;

public class Game extends ApplicationAdapter {
	private float dt;
	private SpriteBatch batch;
	private Player player;
	private BotEmmiter bots;
	private BulletEmitter bullets;
	private Map map;
	private Active[] actives;

	TextureAtlas atlas;

	private float botTimer, botTimerTo;

	public BulletEmitter getBullets() {
		return bullets;
	}
	
	@Override
	public void create () {
		atlas = new TextureAtlas("game.pack.atlas");
		batch = new SpriteBatch();
		player = new Player(this, atlas);
		bots = new BotEmmiter(10, this, atlas);
		bullets = new BulletEmitter(10, atlas);
		map = new Map(atlas);

		botTimer = 0;
		botTimerTo = 3;
	}

	@Override
	public void render () {
		dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch);
		player.render(batch);
		bots.render(batch);
		bullets.render(batch);
		batch.end();
	}

	public void update(float dt) {
//		botTimer += dt;
//		if (botTimer >= botTimerTo) {
//			botTimer = 0;
//			bots.activate(MathUtils.random(32, Gdx.graphics.getWidth()) - 32, MathUtils.random(32, Gdx.graphics.getHeight() - 32));
//		}

		player.update(dt);
		checkPlayerCollision();
		bots.update(dt);
		checkBotsCollision();
		bullets.update(dt);
		checkBulletsCollision();
	}

	private void checkPlayerCollision() {
		if (map.checkWallCollision(player, dt)) {
			return;
		}
		for (int i = 0; i < bots.length(); i++) {
			if (bots.get(i).isActive())
				if(player.collision(bots.get(i).getRect())) {
					player.preState(dt);
					break;
				}
		}
	}

	private void checkBotsCollision() {
		for (int i = 0; i < bots.length(); i++) {
			if (bots.get(i).isActive()) {
				if (map.checkWallCollision(bots.get(i), dt)) {
					continue;
				}
				for (int j = 0; j < bots.length(); j++) {
					if (i == j)
						continue;
					if (bots.get(j).isActive()) {
						if (bots.get(i).collision(bots.get(j).getRect())) {
							bots.get(i).preState(dt);
							break;
						}
					}
				}
				if (bots.get(i).collision(player.getRect())) {
					bots.get(i).preState(dt);
					continue;
				}
			}
		}
	}

	private void checkBulletsCollision() {
		for (int i = 0; i < bullets.length(); i++) {
			if (bullets.get(i).isActive()) {
				if (map.checkBulletWallCollision(bullets.get(i))) {
					bullets.get(i).destroy();
					continue;
				}
				for (int j = 0; j < bots.length(); j++) {
					if (bots.get(j).isActive()) {
						if (bullets.get(i).collision(bots.get(j).getRect())) {
							bots.get(j).takeDamage(bullets.get(i).getDamage());
							bullets.get(i).destroy();
							break;
						}
					}
				}
				if (bullets.get(i).collision(player.getRect())) {
					player.takeDamage(bullets.get(i).getDamage());
					bullets.get(i).destroy();
					continue;
				}
			}
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
