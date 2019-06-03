package com.boomie.javaio.client.game.entity;


import java.awt.Graphics;

abstract public class Entity
{
  int x;
  int y;
  int xVel;
  int yVel;
  boolean isRemoved = false;

  abstract public void tick();
  abstract public void render(Graphics g);

  public void remove()
  {
    this.isRemoved = true;
  }
}
