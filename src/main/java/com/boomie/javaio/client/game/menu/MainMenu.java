package com.boomie.javaio.client.game.menu;

import com.boomie.javaio.client.game.Game;
import com.boomie.javaio.client.game.handler.KeyHandler;
import com.boomie.javaio.client.game.handler.MouseHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

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

    private Rectangle[] menuItemBoxes;

    public MainMenu(Game game, KeyHandler keyInput, MouseHandler mouseInput)
    {
        super(game, keyInput, mouseInput);

        titleFont = new Font("Arial", Font.BOLD, 50);
        itemFont = new Font("Arial", Font.BOLD, 30);
        bottomFont = new Font("Arial", Font.BOLD, 15);
    }

    @Override
    public void tick()
    {
        if(keyInput.getDown().isClicked())
        {
            down();
        }
        if(keyInput.getUp().isClicked())
        {
            up();
        }
        if(keyInput.getEnter().isClicked())
        {
            action();
        }

        if(mouseInput.isMoving() || mouseInput.getLeftClick().isClicked())
        {
            for (int ii = 0; ii < menuItemBoxes.length; ++ii)
            {
                Rectangle itemBox = menuItemBoxes[ii];
                if (mouseInput.getX() > itemBox.x && mouseInput.getX() < itemBox.x + itemBox.width)
                {
                    if (mouseInput.getY() > itemBox.y && mouseInput.getY() < itemBox.y + itemBox.height)
                    {
                        selected = MenuItem.values()[ii];
                        if (mouseInput.getLeftClick().isClicked())
                        {
                            action();
                        }
                    }
                }
            }
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
        menuItemBoxes = new Rectangle[MenuItem.values().length];
        for(int ii = 0; ii < MenuItem.values().length; ++ii)
        {
            MenuItem item = MenuItem.values()[ii];
            if(selected == item)
            {
                g.setColor(Color.BLUE);
            }
            else
            {
                g.setColor(Color.BLACK);
            }

            g.drawString(item.name(), 100, (ii * 100) + 200);
            menuItemBoxes[ii] = new Rectangle(
                    100,
                    (ii * 100) + 200 - g.getFontMetrics().getHeight(),
                    g.getFontMetrics().stringWidth(item.name()),
                    g.getFontMetrics().getHeight());
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
            // TODO close the game properly
        }
    }
}
