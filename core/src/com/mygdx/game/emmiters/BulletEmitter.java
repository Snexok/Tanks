package com.mygdx.game.emmiters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.objs.Active;
import com.mygdx.game.objs.Bullet;
import com.mygdx.game.objs.Tank;
import com.mygdx.game.utils.Direction;

public class BulletEmitter {
    Bullet[] bullets;

    public BulletEmitter(int maxCount, TextureAtlas atlas) {
        bullets = new Bullet[maxCount];

        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet(atlas);
        }
    }

    public Bullet get(int i) {
        return bullets[i];
    }

    public int length() {
        return bullets.length;
    }


    public void activate (float x, float y, Direction direction, float damage) {
        for (int i = 0; i < bullets.length; i++) {
            if(!bullets[i].isActive()) {
                bullets[i].activate(x, y, direction, damage);
                break;
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < bullets.length; i++) {
            if(bullets[i].isActive()) {
                bullets[i].render(batch);
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i].isActive()) {
                bullets[i].update(dt);
            }
        }
    }

}
