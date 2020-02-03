package com.mygdx.game.objs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Wall extends Block{
    float hp, hpMAX = 4;

    public Wall(float x, float y) {
        super();
        this.rect.setPosition(x, y);
        this.rect.height = texture.getRegionHeight();
        this.rect.width = texture.getRegionWidth();
    }

    public float getHp() {
        return hp;
    }

    public void takeDamage(float damage) {
        hp -= damage;
    }
}
