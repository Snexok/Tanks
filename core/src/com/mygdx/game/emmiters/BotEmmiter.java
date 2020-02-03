package com.mygdx.game.emmiters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Game;
import com.mygdx.game.objs.Active;
import com.mygdx.game.objs.Bot;

public class BotEmmiter {
    Bot[] bots;

    public BotEmmiter(int maxCount, Game game, TextureAtlas atlas) {
        bots = new Bot[maxCount];

        for (int i = 0; i < bots.length; i++) {
            bots[i] = new Bot(game, atlas);
        }
    }

    public Bot get(int i) {
        return bots[i];
    }

    public int length() {
        return bots.length;
    }

    public void activate (float x, float y) {
        for (int i = 0; i < bots.length; i++) {
            if(!bots[i].isActive()) {
                bots[i].activate(x, y);
                break;
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < bots.length; i++) {
            if(bots[i].isActive()) {
                bots[i].render(batch);
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < bots.length; i++) {
            if (bots[i].isActive()) {
                bots[i].update(dt);
            }
        }
    }
}
