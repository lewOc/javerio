package com.boomie.javaio.client.game.overlay;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;
import com.boomie.javaio.client.game.handler.MouseHandler;

import java.awt.Graphics;

abstract public class Overlay
{
    protected boolean pauseGame = false;
    protected KeyHandler keyInput;
    protected MouseHandler mouseInput;
    protected Game game;

    abstract public void tick();
    abstract public void render(Graphics g);

    public Overlay(Game game, KeyHandler keyInput, MouseHandler mouseInput)
    {
        this.keyInput = keyInput;
        this.mouseInput = mouseInput;
        this.game = game;
    }

    public boolean doesPauseGame()
    {
        return pauseGame;
    }
}
