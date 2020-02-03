package com.mygdx.game.objs;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MetalWall extends Wall{
    TextureRegion crashTexture;

    public MetalWall(float x, float y, TextureAtlas atlas) {
        super(x, y);
        texture = atlas.findRegion("brickWall");
        crashTexture = atlas.findRegion("brickWallCrashed");
        rect.height = texture.getRegionHeight();
        rect.width = texture.getRegionWidth();
    }
}
