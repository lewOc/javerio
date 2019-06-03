package com.boomie.javaio.client.game.entity;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;
import com.boomie.javaio.client.game.handler.MouseHandler;
import com.boomie.javaio.client.game.overlay.PauseOverlay;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Mob
{
    private Game game;
    private KeyHandler keyInput;
    private MouseHandler mouseInput;

    public Player(Game game, KeyHandler keyInput, MouseHandler mouseInput)
    {
        this.game = game;
        this.keyInput = keyInput;
        this.mouseInput = mouseInput;
    }

    @Override
    public void tick()
    {
        super.tick();
        if(keyInput.getPause().isClicked())
        {
            game.setOverlay(new PauseOverlay(game, keyInput, mouseInput));
        }

        this.x = mouseInput.getX();
        this.y = mouseInput.getY();
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x, y, 16, 16);
    }
}
