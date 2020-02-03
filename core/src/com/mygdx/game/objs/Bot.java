package com.mygdx.game.objs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.Weapon;
import com.mygdx.game.utils.Direction;

public class Bot extends Tank {
    private float aiTimer = 0, aiTimerTo;

    private static final float HP_MAX = 3;
    private static final float SPEED = 100;

    public Bot(Game game, TextureAtlas atlas) {
        super(game);
        texture = atlas.findRegion("enemy");
        rect.height = texture.getRegionHeight();
        rect.width = texture.getRegionWidth();

        hpMAX = HP_MAX;
        hp = hpMAX;

        aiTimerTo = 3;

    }

    public void activate(float x, float y) {
        this.hp = this.hpMAX;
        this.rect.setPosition(x, y);
        this.direction = Direction.UP;
        this.angel = direction.getAngel();
        this.speed = SPEED;
        this.active = true;
    }

    private void letsMove() {
        if(aiTimer >= aiTimerTo) {
            aiTimer = 0;
            aiTimerTo = MathUtils.random(2.5f, 5.0f);
            direction = Direction.random();
        }
    }

    private void notGoOverFrame() {
        if(rect.x + rect.width >= Gdx.graphics.getWidth() ||
                rect.x <= 0 ||
                rect.y + rect.height >= Gdx.graphics.getHeight() ||
                rect.y <= 0) {
            direction = direction.reverse();
        }
    }

    public void update(float dt) {
        if (hp <= 0)
            destroy();
        aiTimer += dt;
        letsMove();
        notGoOverFrame();
        super.update(dt);
    }
}
