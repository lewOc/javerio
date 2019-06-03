package com.boomie.javaio.client.game.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener
{
    public class Key
    {
        private int presses;
        private int absorbs;
        private boolean down;
        private boolean clicked;

        public Key()
        {
            keys.add(this);
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

    private List<Key> keys =  new ArrayList<>();
    private Key up = new Key();
    private Key down = new Key();
    private Key left = new Key();
    private Key right = new Key();
    private Key fast = new Key();
    private Key pause = new Key();
    private Key enter = new Key();

    public Key getUp()
    {
        return up;
    }

    public Key getDown()
    {
        return down;
    }

    public Key getLeft()
    {
        return left;
    }

    public Key getRight()
    {
        return right;
    }

    public Key getFast()
    {
        return fast;
    }

    public Key getPause()
    {
        return pause;
    }

    public Key getEnter()
    {
        return enter;
    }

    public KeyHandler()
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        toggle(e, false);
    }

    public void releaseAll()
    {
        for(Key key : keys)
        {
            key.down = false;
        }
    }

    public void tick()
    {
        for(Key key : keys)
        {
            key.tick();
        }
    }

    private void toggle(KeyEvent e, boolean pressed)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up.toggle(pressed);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down.toggle(pressed);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left.toggle(pressed);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right.toggle(pressed);
                break;
            case KeyEvent.VK_ESCAPE:
                pause.toggle(pressed);
                break;
            case KeyEvent.VK_SHIFT:
                fast.toggle(pressed);
                break;
            case KeyEvent.VK_ENTER:
                enter.toggle(pressed);
                break;
        }
    }
}
