package com.boomie.javaio.client.game.handler;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;

public class MouseHandler implements MouseInputListener
{
    public class Button
    {
        private int presses;
        private int absorbs;
        private boolean down;
        private boolean clicked;

        public Button()
        {
            buttons.add(this);
        }

        void toggle(boolean pressed)
        {
            if(pressed != down)
            {
                down = pressed;
            }
            if(pressed)
            {
                ++presses;
            }
        }

        void tick()
        {
            if(absorbs < presses)
            {
                ++absorbs;
                clicked = true;
            }
            else
            {
                clicked = false;
            }
        }

        public boolean isDown()
        {
            return down;
        }

        public boolean isClicked()
        {
            return clicked;
        }
    }

    private int x;
    private int y;
    private int nextX;
    private int nextY;

    private boolean moving;

    private List<Button> buttons = new ArrayList<>();
    private Button leftClick = new Button();
    private Button rightClick = new Button();

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isMoving()
    {
        return moving;
    }

    public Button getLeftClick()
    {
        return leftClick;
    }

    public Button getRightClick()
    {
        return rightClick;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        nextX = e.getX();
        nextY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        toggle(e, true);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        toggle(e,false);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    public void releaseAll()
    {
        for(Button button : buttons)
        {
            button.down = false;
        }
    }

    public void tick()
    {
        if(this.x == this.nextX || this.y == this.nextY)
        {
            moving = false;
        }
        else
        {
            moving = true;
        }

        this.x = this.nextX;
        this.y = this.nextY;

        for(Button button : buttons)
        {
            button.tick();
        }
    }

    private void toggle(MouseEvent e, boolean pressed)
    {
        switch(e.getButton())
        {
            case BUTTON1:
                leftClick.toggle(pressed);
                break;
            case BUTTON3:
                rightClick.toggle(pressed);
                break;
        }
    }
}
