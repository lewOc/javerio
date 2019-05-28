package com.boomie.javaio.client.frame;

import com.boomie.dev.Boomie;
import com.boomie.javaio.client.JavaIO;
import com.boomie.javaio.client.game.Game;
import com.boomie.versioning.Version;

import javax.swing.JFrame;
import java.awt.*;

public class Frame extends JFrame
{
  public static final int DEFAULT_WIDTH = 500;
  public static final int DEFAULT_HEIGHT = 500;

  private int width = DEFAULT_WIDTH;
  private int height = DEFAULT_HEIGHT;
  private Game gamePanel;

  public Frame() throws Exception
  {
    this.setup();
  }

  private void setup() throws Exception
  {
    gamePanel = new Game();
    this.setTitle(JavaIO.GAME_NAME + " " + Version.getVersion() + " - by " + Boomie.companyName);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setAlwaysOnTop(true);
    this.setSize(this.width, this.height);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.getContentPane().add(gamePanel, BorderLayout.CENTER);

    gamePanel.start();
  }
}
