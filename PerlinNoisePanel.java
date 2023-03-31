import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class PerlinNoisePanel extends JPanel {
    public static int FrameW;
    public static int FrameH;
    public int FRAMELENGTH;

    private BufferedImage myImage;
    private Graphics myBuffer;

    private Timer t;

    private Grid grid;

    public PerlinNoisePanel(int FrameW_, int FrameH_) {
        PerlinNoisePanel.FrameW = FrameW_;
        PerlinNoisePanel.FrameH = FrameH_;
        myImage = new BufferedImage(PerlinNoisePanel.FrameW, PerlinNoisePanel.FrameH, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.createGraphics();
        myBuffer.setColor(Color.BLACK);
        myBuffer.fillRect(0, 0, PerlinNoisePanel.FrameW, PerlinNoisePanel.FrameH);

        grid = new Grid(PerlinNoisePanel.FrameW, PerlinNoisePanel.FrameH, myBuffer);
        grid.initParticles();
        grid.initVectorField();
        grid.updateVectorField();

        t = new Timer(5, new AnimationListener());
        t.start();
    }

    public void paintComponent(Graphics g) { // Graphics2D can not drawimage apparently
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    public void animate() {

        myBuffer.setColor(Color.BLACK);
        myBuffer.fillRect(0, 0, PerlinNoisePanel.FrameW, PerlinNoisePanel.FrameH);

        grid.render();

        repaint();
    }

    private class AnimationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            animate();
        }
    }
}
