package com.mygdx.game.objs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.Weapon;

public abstract class Tank extends Unit {
    Game game;

    Weapon weapon;
    float lastFire = 0;

    public Tank(Game game) {
        super();
        this.game = game;

        weapon = new Weapon();
    }

    public void takeDamage(float damage) {
        this.hp -= damage;
    }

    public void fire() {
        Vector2 center = new Vector2();
        rect.getCenter(center);
        if (lastFire >= weapon.getFireRate()){
            lastFire = 0;
            game.getBullets().activate(center.x + rect.width / 2 * direction.getVx(),
                                       center.y + rect.height / 2 * direction.getVy(),
                                           direction, weapon.getDamage());
        }
    }
}
