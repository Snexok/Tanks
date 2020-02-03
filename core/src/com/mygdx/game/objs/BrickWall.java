package com.mygdx.game.objs;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BrickWall extends Wall {
    TextureRegion crashTexture;

    public BrickWall(float x, float y, TextureAtlas atlas) {
        super(x, y);
        texture = atlas.findRegion("brickWall");
        crashTexture = atlas.findRegion("brickWallCrashed");
        rect.height = texture.getRegionHeight();
        rect.width = texture.getRegionWidth();
        hpMAX = 4;
        hp = hpMAX;
    }

    public void takeDamage(float damage) {
        super.takeDamage(damage);
        if (hp <= hpMAX/2) {
            texture = crashTexture;
        }
    }
}
