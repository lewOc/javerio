package com.boomie.javaio.client.game;

import javax.swing.*;

import java.awt.*;

import static com.boomie.javaio.client.game.State.*;

public class Game extends JPanel implements Runnable
{
  /**
   * Amount of ticks passed in game. Updates every second.
   */
  private long tick = 0;
  /**
   * Current amount of frames drawn. Resets to 0 every second.
   */
  private long frames = 0;
  /**
   * Amount of frames drawn in the last second.
   */
  private long fps = 0;
  /**
   * The last time since a reset happened in ms.
   */
  private long lastTime = 0;
  /**
   * If the Game is running.
   */
  private boolean running = false;
  /**
   * If we are limiting fps. Could be an option somewhere later.
   */
  private boolean limitFps = false;
  /**
   * The fps limit.
   */
  private final double fpsLimit = 60.0;
  /**
   * How many ticks are performed every second.
   */
  private final long ticksPerSecond = 100;
  /**
   * Current amount of ticks. Resets to 0 every second.
   */
  private double ticks = 0.0;
  /**
   * The thread that this Game is run on.
   */
  private Thread gameThread;
  /**
   * What the current game state is.
   */
  private State gameState = MENU;

  public Game()
  {
    gameThread = new Thread(this);
    gameThread.setName("GAME THREAD");
  }
  
  public synchronized void start()
  {
    if(!running)
    {
      running = true;
      gameThread.start();
    }
  }

  private void tick()
  {
    if(ticks < ticksPerSecond)
    {
      ++ticks;
    }

  }

  @Override
  public void run()
  {
    lastTime = System.currentTimeMillis();
    while(running)
    {
      tick();
      render();

      // print out once per second
      if(System.currentTimeMillis() - lastTime > 1000)
      {
        lastTime = System.currentTimeMillis();
        fps = frames;
        tick += ticks;
//        System.out.println("tick #" + tick);
//        System.out.println(fps + "fps");
        frames = 0;
        ticks = 0;
      }
    }
  }

  private void render()
  {
    if(!(limitFps) || (frames < fpsLimit))
    {
      this.repaint();
    }
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    drawDebug(g2d);


    ++frames;
  }

  private void drawDebug(Graphics2D g2d)
  {
    int maxStrWidth = -1;
    String[] debugInfo = {
            fps + " FPS",
            "tick #" + tick
    };

    for(int ii = 0; ii < debugInfo.length; ++ii)
    {
      g2d.drawString(debugInfo[ii], 0, (ii + 1) * g2d.getFontMetrics(g2d.getFont()).getHeight());
      int curWidth = g2d.getFontMetrics(g2d.getFont()).stringWidth(debugInfo[ii]);
      if(curWidth > maxStrWidth)
      {
        maxStrWidth = curWidth;
      }
    }

    g2d.drawRect(0, 0, maxStrWidth, (debugInfo.length + 1) * g2d.getFontMetrics(g2d.getFont()).getHeight());
  }
}
