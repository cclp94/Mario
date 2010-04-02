/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mario;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import mario.state.State;

/**
 *
 * @author danny
 */
public abstract class GameObject
{
    protected BufferedImage sprite;
    protected int x, y, width, height;
    protected HashMap<String, Rectangle> frames = new HashMap<String, Rectangle>();
    protected String[] animation;
    protected int animationFrame = 0;
    protected State state;

    public GameObject(int x, int y, int width, int height, String fileName)
    {
        this.x = x;
        this.y = y;
        sprite = loadImage(fileName);
    }

    private BufferedImage loadImage(String fileName)
    {
        URL imageUrl = Main.class.getResource(fileName);
        try
        {
            sprite = ImageIO.read(imageUrl);
        } catch (IOException e)
        {
        }
        return sprite;
    }

    public BufferedImage getSprite()
    {
        return sprite;
    }

    public BufferedImage getImage()
    {

        System.out.println(animationFrame + " : " + animation.length);
        if (animationFrame == animation.length)
        {
            animationFrame = 0;
        }
        BufferedImage crop = sprite.getSubimage((int) frames.get(animation[animationFrame]).getX(),
                (int) frames.get(animation[animationFrame]).getY(),
                (int) frames.get(animation[animationFrame]).getWidth(),
                (int) frames.get(animation[animationFrame]).getHeight());
        animationFrame++;
        return crop;
    }

    abstract public void draw(Graphics graphics);

    abstract public void doLoopAction();

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setAnimation(String[] animation)
    {
        this.animation = animation;
        animationFrame = 0;
    }

    public String[] getAnimation()
    {
        return animation;
    }
}
