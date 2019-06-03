package com.boomie.javaio.client.game.menu;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MainMenu extends Menu
{
    private enum MenuItem
    {
        Play,
        Help,
        Exit
    }

    private Font titleFont;
    private Font itemFont;
    private Font bottomFont;

    private MenuItem selected = MenuItem.Play;

    public MainMenu(Game game, KeyHandler input)
    {
        super(game, input);

        titleFont = new Font("Arial", Font.BOLD, 50);
        itemFont = new Font("Arial", Font.BOLD, 30);
        bottomFont = new Font("Arial", Font.BOLD, 15);
    }

    @Override
    public void tick()
    {
        if(input.getDown().isClicked())
        {
            down();
        }
        if(input.getUp().isClicked())
        {
            up();
        }
        if(input.getEnter().isClicked())
        {
            action();
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        g.drawString("JavaIO",
                     (game.getWidth() / 2) - (g.getFontMetrics().stringWidth("JavaIO") / 2),
                     100);
        drawMenuItems(g);
        drawBottom(g);
    }

    private void up()
    {
        switch(selected)
        {
            case Help:
                selected = MenuItem.Play;
                break;
            case Exit:
                selected = MenuItem.Help;
                break;
            default:
                break;
        }
    }

    private void down()
    {
        switch(selected)
        {
            case Play:
                selected = MenuItem.Help;
                break;
            case Help:
                selected = MenuItem.Exit;
                break;
            default:
                break;
        }
    }

    private void drawMenuItems(Graphics g)
    {
        g.setFont(itemFont);
        for(int ii = 0; ii < MenuItem.values().length; ++ii)
        {
            MenuItem item = MenuItem.values()[ii];
            if(selected == item)
            {
                g.setColor(Color.LIGHT_GRAY);
            }
            else
            {
                g.setColor(Color.BLACK);
            }

            g.drawString(item.name(), 100, (ii * 100) + 200);
        }
    }

    private void drawBottom(Graphics g)
    {
        g.setFont(bottomFont);
        g.setColor(Color.BLACK);

        g.drawString("A boomie production!",
                     (game.getWidth() / 2) - (g.getFontMetrics().stringWidth("A boomie production!") / 2),
                     game.getHeight() - g.getFontMetrics().getHeight());
    }

    private void action()
    {
        if(selected == MenuItem.Play)
        {
            game.setMenu(null);
        }
        else if(selected == MenuItem.Help)
        {
            // TODO create the help menu
        }
        else if (selected == MenuItem.Exit)
        {
            game.stop();
        }
    }
}
