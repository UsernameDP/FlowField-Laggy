import javax.swing.*;

public class PerlinNoiseDriver {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Flow Field");
        frame.setSize(1000, 600); // This time we're going for a 500x500 panel
        frame.setLocation(20, 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new PerlinNoisePanel(1000, 600));
        frame.setVisible(true);
    }
}