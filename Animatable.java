import java.awt.Graphics;

//Every object we want to animate on screen needs to know
//how to make one animation step and how to draw itself
//onto a Graphics object.
public interface Animatable {

    public void draw(Graphics g);
}