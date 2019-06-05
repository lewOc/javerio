package com.boomie.javaio.client.game.entity;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;
import com.boomie.javaio.client.game.handler.MouseHandler;
import com.boomie.javaio.client.game.overlay.PauseOverlay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Player extends Mob
{
    private Game game;
    private KeyHandler keyInput;
    private MouseHandler mouseInput;
    private List<Point> prevPoints;

    public Player(Game game, KeyHandler keyInput, MouseHandler mouseInput)
    {
        this.game = game;
        this.keyInput = keyInput;
        this.mouseInput = mouseInput;
        this.prevPoints = new ArrayList<>();
    }

    @Override
    public void tick()
    {
        super.tick();
        if(keyInput.getPause().isClicked())
        {
            game.setOverlay(new PauseOverlay(game, keyInput, mouseInput));
        }
        if(keyInput.getFast().isClicked())
        {
            this.prevPoints.clear();
        }

        this.x = mouseInput.getX() - 8;
        this.y = mouseInput.getY() - 8;

        Point curPoint = new Point(this.x, this.y);
        if(!prevPoints.contains(curPoint))
        {
            prevPoints.add(curPoint);
        }

        mouseInput.setPos(game.getWidth() / 2, game.getHeight() / 2);
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        for (Point point : prevPoints)
        {
            g.fillRect(point.x, point.y, 16, 16);
        }
    }
}
