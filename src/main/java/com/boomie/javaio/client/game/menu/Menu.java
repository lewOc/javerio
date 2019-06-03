package com.boomie.javaio.client.game.menu;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;

import java.awt.Graphics;

abstract public class Menu
{
    protected Game game;
    protected KeyHandler input;

    public Menu(Game game, KeyHandler input)
    {
        this.game = game;
        this.input = input;
    }

    abstract public void tick();
    abstract public void render(Graphics g);
}
