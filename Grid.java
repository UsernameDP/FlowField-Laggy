import java.awt.*;
import java.util.HashMap;

public class Grid {
    public int width;
    public int height;
    public Graphics g;
    public Graphics2D g2;

    public LinkedListS<Particle> Particles = new LinkedListS<>();
    public HashMap<Point, Double> VectorField = new HashMap<>();

    public int cellSize;

    public double t = 0.5;
    public double tInc = 0.025;
    public double vectorAmp = 10;

    public int numberOfParticles = 100;

    public Grid(int width_, int height_, Graphics g_) {
        this.width = width_;
        this.height = height_;
        this.g = g_;
        this.g2 = (Graphics2D) g;

        this.cellSize = 20;
    }

    public void initParticles() {
        for (int i = 0; i < numberOfParticles; i++) {
            Particles.add(new Particle(this));
        }
    }

    public void initVectorField() {
        for (int xi = -cellSize; xi < width + cellSize; xi += cellSize) {
            for (int yi = -cellSize; yi < height + cellSize; yi += cellSize) {
                VectorField.put(new Point(xi, yi), 0.00);
            }
        }
    }

    public void updateVectorField() {
        for (int xi = -cellSize; xi < width + cellSize; xi += cellSize) {
            for (int yi = -cellSize; yi < height + cellSize; yi += cellSize) {
                VectorField.put(new Point(xi, yi), Noise.noise(xi, yi, t));
            }
        }
    }

    public void renderVectorField() {
        double currentAngle;

        g.setColor(Color.WHITE);
        for (int xi = -cellSize; xi < width + cellSize; xi += cellSize) {
            for (int yi = -cellSize; yi < height + cellSize; yi += cellSize) {
                currentAngle = this.VectorField.get(new Point(xi, yi));

                g2.setStroke(new BasicStroke(2));

                g.drawLine(xi, yi, (int) (xi + vectorAmp * Math.cos(currentAngle)),
                        (int) (yi + vectorAmp * Math.sin(currentAngle)));
            }
        }
    }

    public void renderParticles() {
        g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));

        LinkedListS.Node<Particle> currentParticle = Particles.getFirst();
        for (int i = 0; i < Particles.size; i++) {
            currentParticle.value.draw(g);
            currentParticle.value.update();

            currentParticle = currentParticle.next;
        }
    }

    public void incrementTime() {
        this.t += tInc;
    }

    public void render() {

        renderVectorField();

        renderParticles();

        incrementTime();

        updateVectorField();
    }

}
