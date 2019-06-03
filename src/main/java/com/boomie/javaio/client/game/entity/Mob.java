package com.boomie.javaio.client.game.entity;

import java.awt.Graphics;

abstract public class Mob extends Entity
{
    @Override
    public void tick()
    {
        x += xVel;
        y += yVel;
    }

    @Override
    public void render(Graphics g)
    {
    }
}
