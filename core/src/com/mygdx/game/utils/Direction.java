package com.mygdx.game.utils;

import com.badlogic.gdx.math.MathUtils;

public enum Direction {
    UP(0,1, 90), DOWN(0,-1, 270), LEFT(-1,0, 180), RIGHT(1, 0, 0);
    private int vx, vy;
    private float angel;

    Direction(int vx, int vy, float angel) {
        this.vx = vx;
        this.vy = vy;
        this.angel = angel;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public float getAngel() {
        return angel;
    }

    public Direction reverse() {
        if(this == Direction.UP) {
           return Direction.DOWN;
        }
        else if(this == Direction.DOWN) {
            return Direction.UP;
        }
        else if(this == Direction.LEFT) {
            return Direction.RIGHT;
        }
        else if(this == Direction.RIGHT) {
            return Direction.LEFT;
        }
        return Direction.DOWN;
    }

    public static Direction random() {
        return Direction.values()[MathUtils.random(Direction.values().length-1)];
    }
}
