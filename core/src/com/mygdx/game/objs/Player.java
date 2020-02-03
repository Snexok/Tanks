package com.mygdx.game.objs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.Weapon;
import com.mygdx.game.utils.Direction;

public class Player extends Tank{

    public Player(Game game, TextureAtlas atlas) {
        super(game);
        rect.setPosition(100, 50);
        texture = atlas.findRegion("player");
        rect.height = texture.getRegionHeight();
        rect.width = texture.getRegionWidth();

        direction = Direction.RIGHT;
        angel = direction.getAngel();
        speed = 100;

        hpMAX = 10;
        hp = hpMAX;

        active = true;
    }

    private boolean checkKeys() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = Direction.LEFT;
            return true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = Direction.RIGHT;
            return true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = Direction.UP;
            return true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = Direction.DOWN;
            return true;
        }
        return false;
    }

    private void checkMovement(float dt) {
        if(checkKeys())
            super.update(dt);
    }

    public void update(float dt) {
        lastFire += dt;
        checkMovement(dt);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fire();
        }
    }
}
