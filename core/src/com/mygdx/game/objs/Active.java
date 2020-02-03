package com.mygdx.game.objs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Direction;
import sun.java2d.loops.FillRect;

public abstract class Active extends Obj{
    Direction direction;
    float speed, angel;
    boolean active;

    public Active() {
        super();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, rect.x, rect.y,
                rect.height / 2, rect.width / 2,
                rect.width, rect.height,
                1, 1, angel);
    }

    public void update(float dt) {
        rect.x += speed * direction.getVx() * dt;
        rect.y += speed * direction.getVy() * dt;
        angel = direction.getAngel();
    }

    public Direction getDirection() {
        return direction;
    }

    public void randomDirection() {
        direction = direction.random();
    }

    public void preState(float dt) {
        rect.x -= speed * direction.getVx() * dt;
        rect.y -= speed * direction.getVy() * dt;
    }

    public boolean isActive() {
        return active;
    }
    public void destroy() {
        active = false;
    }


    public boolean collision(Rectangle rect) {
        Vector2[] points = new Vector2[2];
        points[0] = new Vector2();
        points[1] = new Vector2();

        if (direction == Direction.UP) {
            points[0].set(this.rect.x, this.rect.y + this.rect.height);
            points[1].set(this.rect.x + this.rect.width, this.rect.y + this.rect.height);
        }
        else if (direction == Direction.RIGHT) {
            points[0].set(this.rect.x + this.rect.width, this.rect.y);
            points[1].set(this.rect.x + this.rect.width, this.rect.y + this.rect.height);
        }
        else if (direction == Direction.DOWN) {
            points[0].set(this.rect.x, this.rect.y);
            points[1].set(this.rect.x + this.rect.width, this.rect.y);
        }
        else if (direction == Direction.LEFT) {
            points[0].set(this.rect.x, this.rect.y);
            points[1].set(this.rect.x, this.rect.y + this.rect.height);
        }

        return rect.contains(points[0]) ||
                rect.contains(points[1]);
    }

}
