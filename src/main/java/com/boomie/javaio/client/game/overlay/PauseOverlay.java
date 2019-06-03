package com.boomie.javaio.client.game.overlay;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;
import com.boomie.javaio.client.game.handler.MouseHandler;

import java.awt.Color;
import java.awt.Graphics;

public class PauseOverlay extends Overlay
{

    public PauseOverlay(Game game, KeyHandler keyInput, MouseHandler mouseInput)
    {
        super(game, keyInput, mouseInput);
        this.pauseGame = true;
    }

    @Override
    public void tick()
    {
        if(keyInput.getPause().isClicked())
        {
            game.setOverlay(null);
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.drawRect(50, 50, 500, 500);
        g.setColor(Color.RED);
        g.drawString("PAUSED", 300, 50);
    }
}
