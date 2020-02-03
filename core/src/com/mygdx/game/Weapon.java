package com.mygdx.game;

public class Weapon {
    private float damage;
    private float fireRate;

    public Weapon() {
        damage = 1;
        fireRate = 0.4f;
    }

    public float getFireRate() {
        return fireRate;
    }

    public float getDamage() {
        return damage;
    }
}
