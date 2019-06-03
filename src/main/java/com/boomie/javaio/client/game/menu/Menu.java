package com.boomie.javaio.client.game.menu;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;
import com.boomie.javaio.client.game.handler.MouseHandler;

import java.awt.Graphics;

abstract public class Menu
{
    protected Game game;
    protected KeyHandler keyInput;
    protected MouseHandler mouseInput;

    public Menu(Game game, KeyHandler keyInput, MouseHandler mouseInput)
    {
        this.game = game;
        this.keyInput = keyInput;
        this.mouseInput = mouseInput;
    }

    abstract public void tick();
    abstract public void render(Graphics g);
}
