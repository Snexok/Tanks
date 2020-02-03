package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.objs.*;

public class Map {

    private int cells_height, cells_width;
    private Wall[][] walls;

    private static final int MIN_CELL_HEIGHT = 16;
    private static final int MIN_CELL_WIDTH = 16;

    public Map(TextureAtlas atlas) {
        cells_height = Gdx.graphics.getHeight() / MIN_CELL_HEIGHT;
        cells_width = Gdx.graphics.getWidth() / MIN_CELL_WIDTH;

        walls = new Wall[cells_height][cells_width];

        for (int i = 0; i < cells_height; i++) {
            for (int j = 0; j < cells_width; j++) {
                if(i==0 || i==cells_height-1 || j==cells_width-1 || j==0) {
                    walls[i][j] = new MetalWall(j*16, i*16, atlas);
                }
            }
        }
//        for (int i = 1; i < cells_height-1; i++) {
//            for (int j = 1; j < cells_width-1; j++) {
//                if((j==3 || j==4) && (i!=cells_height-2 && i!=cells_height-3)) {
//                    walls[i][j] = new BrickWall(j*16, i*16, atlas);
//                }
//            }
//        }
    }

    public boolean checkWallCollision(Active active, float dt) {
        for (int i = 0; i < 3; i++) {
            int cell_x = ((int)(active.getX() +
                            (active.getDirection().getVx()+1) / 2 * active.getWidth()/2 * i) / MIN_CELL_WIDTH);
            int cell_y = ((int)(active.getY() +
                            (active.getDirection().getVy()+1) / 2 * active.getHeight()/2 * i) / MIN_CELL_HEIGHT);

            if(walls[cell_y][cell_x] != null){
                active.preState(dt);
                return true;
            }
        }

        return false;
    }

    public boolean checkBulletWallCollision(Bullet bullet) {
        int cell_x = ((int)(bullet.getCenterX() + (bullet.getDirection().getVx()+1) / 2 * bullet.getWidth()/2) / MIN_CELL_WIDTH);
        int cell_y = ((int)(bullet.getCenterY() + (bullet.getDirection().getVy()+1) / 2 * bullet.getHeight()/2) / MIN_CELL_HEIGHT);

        if(walls[cell_y][cell_x] instanceof BrickWall) {
            walls[cell_y][cell_x].takeDamage(bullet.getDamage());
            if(walls[cell_y][cell_x].getHp() <= 0)
                walls[cell_y][cell_x] = null;
            return true;
        }
        else if(walls[cell_y][cell_x] instanceof MetalWall) {
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < cells_height; i++) {
            for (int j = 0; j < cells_width; j++) {
                if(walls[i][j] != null)
                    walls[i][j].render(batch);
            }
        }
    }
}
