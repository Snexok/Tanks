package com.mygdx.game.objs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Direction;

public class Bullet extends Active{
    private float damage;

    private static final float SPEED = 320;

    public Bullet(TextureAtlas atlas) {
        super();
        texture = atlas.findRegion("bullet");
        rect.width = texture.getRegionWidth();
        rect.height = texture.getRegionHeight();
    }

    public float getDamage() {
        return damage;
    }

    public void activate(float x, float y, Direction direction, float damage) {
        this.rect.setCenter(x, y);
        this.direction = direction;
        this.damage = damage;
        this.speed = SPEED;
        this.active = true;
    }

    private void deactivate() {
        active = false;
    }

    private void checkCrashed() {
        if (rect.x + rect.width > Gdx.graphics.getWidth() ||
                rect.y + rect.height > Gdx.graphics.getHeight() ||
                rect.x < 0 ||
                rect.y < 0) {
            deactivate();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, rect.x, rect.y);
    }

    public void update(float dt) {
        super.update(dt);
        checkCrashed();
    }
}
