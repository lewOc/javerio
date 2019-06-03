package com.boomie.javaio.client.game.entity;


import com.boomie.javaio.client.game.Game;

import java.awt.Graphics;
import java.util.LinkedList;

public class EntityManager
{
  private Game game;
  private LinkedList<Entity> entities;

  public EntityManager(Game game)
  {
    this.game = game;
    entities = new LinkedList<>();
  }

  public void tick()
  {
    entities.forEach(Entity::tick);
    for (Entity entity : entities)
    {
      entity.tick();

      if(entity.isRemoved)
      {
        entities.remove(entity);
      }
    }
  }

  public void render(Graphics g)
  {
    entities.forEach(item -> item.render(g));
  }

  public void add(Entity entity)
  {
    this.entities.add(entity);
  }
}
