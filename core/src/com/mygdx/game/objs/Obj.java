package com.mygdx.game.objs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Obj {
    TextureRegion texture;
    Rectangle rect;

    public Obj() {
        texture = new TextureRegion();
        rect = new Rectangle();
    }

    public float getWidth() {
        return rect.width;
    }
    public float getHeight() {
        return rect.height;
    }

    public float getX() {
        return rect.getX();
    }
    public float getY() {
        return rect.getY();
    }

    public float getCenterX() {
        return rect.getX() + rect.getWidth() / 2;
    }
    public float getCenterY() {
        return rect.getY() + rect.getHeight() / 2;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, rect.x, rect.y);
    };

    public Rectangle getRect() {
        return rect;
    }


}
