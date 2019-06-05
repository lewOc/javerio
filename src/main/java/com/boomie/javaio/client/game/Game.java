package com.boomie.javaio.client.game;


import com.boomie.javaio.client.game.entity.EntityManager;
import com.boomie.javaio.client.game.entity.Player;
import com.boomie.javaio.client.game.handler.KeyHandler;
import com.boomie.javaio.client.game.handler.MouseHandler;
import com.boomie.javaio.client.game.menu.MainMenu;
import com.boomie.javaio.client.game.menu.Menu;
import com.boomie.javaio.client.game.overlay.Overlay;
import com.boomie.javaio.client.window.Window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.time.Duration;

public class Game extends Canvas implements Runnable
{
  public static final int HEIGHT = 600;
  public static final int WIDTH = HEIGHT * 16 / 9;
  /**
   * Amount of ticks passed in game. Updates every second.
   */
  private long tick = 0;
  /**
   * Current amount of frames drawn. Resets to 0 every second.
   */
  private long frames = 0;
  /**
   * The last time since a reset happened in ns.
   */
  private long lastTime = 0;
  /**
   * If the Game is running.
   */
  private boolean running = false;
  /**
   * How many ticks are performed every second.
   */
  private final double ticksPerSecond = 128.0;
  /**
   * How many nanoseconds between ticks
   */
  private final double nsPerTick = Duration.ofSeconds(1).toNanos() / ticksPerSecond;
  /**
   * The thread that this Game is run on.
   */
  private Thread gameThread;

  private EntityManager manager;

  private KeyHandler keyInput;

  private MouseHandler mouseInput;

  private Window window;

  private Menu menu;

  private Overlay overlay;

  public Game()
  {
    init();
  }

  public void init()
  {
    gameThread = new Thread(this);
    gameThread.setName("JavaIO main");

    keyInput = new KeyHandler();
    mouseInput = new MouseHandler();
    manager = new EntityManager(this);
    menu = new MainMenu(this, keyInput, mouseInput);
    manager.add(new Player(this, keyInput, mouseInput));
    window = new Window(WIDTH, HEIGHT, "Game", this);

    this.addKeyListener(keyInput);
    this.addMouseMotionListener(mouseInput);
    this.addMouseListener(mouseInput);
  }
  
  public synchronized void start()
  {
    if(!running)
    {
      running = true;
      gameThread.start();
    }
  }

  public synchronized void stop()
  {
    if(running)
    {
      running = false;
    }
  }

  private void tick()
  {
    keyInput.tick();
    mouseInput.tick();

    if(menu != null)
    {
      menu.tick();
    }
    else
    {
      if(overlay == null || !overlay.doesPauseGame())
      {
        manager.tick();
      }
      else
      {
        overlay.tick();
      }
    }

    ++tick;
  }

  @Override
  public void run()
  {
    lastTime = System.nanoTime();
    double unprocessed = 0.0;
    long lastTimer1 = System.currentTimeMillis();
    while(running)
    {
      long now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;
      while(unprocessed >= 1)
      {
        try {
          tick();
        } catch (Exception e) {
          e.printStackTrace();
        }
        --unprocessed;
      }

      render();

      // print out once per second
      if(System.currentTimeMillis() - lastTimer1 > 1000)
      {
        lastTimer1 += 1000;
        System.out.println(tick + "ticks " + frames + " fps");
        frames = 0;
        tick = 0;
      }
    }
  }

  private void render()
  {
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null)
    {
      this.createBufferStrategy(3);
      requestFocus();
      return;
    }

    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0,0, WIDTH, HEIGHT);

    if(menu != null)
    {
      menu.render(g);
    }
    else
    {
      manager.render(g);
    }

    if(overlay != null)
    {
      overlay.render(g);
    }

    g.dispose();
    bs.show();

    ++frames;
  }

  public void setMenu(Menu menu)
  {
    this.menu = menu;
  }

  public void setOverlay(Overlay overlay)
  {
    this.overlay = overlay;
  }

  public static void main(String[] args)
  {
    new Game();
  }

}
