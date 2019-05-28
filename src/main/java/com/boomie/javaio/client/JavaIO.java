package com.boomie.javaio.client;

import com.boomie.javaio.client.frame.Frame;

public class JavaIO
{
    public static final String GAME_NAME = "javaio";

    public static void main(String args[])
    {
        try
        {
            Frame gameFrame = new Frame();
        }
        catch(Exception e)
        {
            // TODO log it
            e.printStackTrace();
        }
    }
}
