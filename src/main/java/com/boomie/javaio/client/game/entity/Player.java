package com.boomie.javaio.client.game.entity;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Mob
{
    private Game game;
    private KeyHandler input;
    private int fastMod = 1;

    public Player(Game game, KeyHandler input)
    {
        this.game = game;
        this.input = input;
    }

    @Override
    public void tick()
    {
        super.tick();
        yVel = 0;
        xVel = 0;
        fastMod = 1;
        if(input.getFast().isDown())
        {
            fastMod = 3;
        }
        if(input.getUp().isDown())
        {
            yVel = -1 * fastMod;
        }
        if(input.getDown().isDown())
        {
            yVel = 1 * fastMod;
        }
        if(input.getLeft().isDown())
        {
            xVel = -1 * fastMod;
        }
        if(input.getRight().isDown())
        {
            xVel = 1 * fastMod;
        }

    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x, y, 16, 16);
    }
}
