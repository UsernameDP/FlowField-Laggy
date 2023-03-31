import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Particle implements Animatable {
    // Constant Fields
    private final Grid grid;
    private final int MaxHistoryInit = 15;
    private final double vectorAmpInit;
    /*
     * the reason why low values of this leads to 'square-like' histories is because
     * it isn't reaching the next cell for a long period of time, when this happens,
     * they stay in the same vector for a long period of time, leading for there to
     * be a straight line though....this can be fixxed if i use dotproducts, and
     * base it off of the corner vectors as well as the particle's distance from it
     */

    /*
     * the field should move slow, the particles should move mildy fast, but the
     * particles must always move faster than the field
     * 
     */

    // Let Methods
    private int x;
    private int y;
    private int dx;
    private int dy;
    private double vectorAmp;

    private LinkedListS<Point> History = new LinkedListS<>();
    private int maxHistory;
    private boolean reset;

    public Particle(Grid grid_) {
        this.grid = grid_;

        this.vectorAmpInit = this.grid.vectorAmp;
        initParticle();
    }

    public void initParticle() {
        this.x = (int) (Math.random() * this.grid.width);
        this.y = (int) (Math.random() * this.grid.height);
        this.dx = (int) (Math.random() * (2 + 2) - 2);
        this.dy = (int) (Math.random() * (2 + 2) - 2);

        this.History.add(new Point(this.x, this.y));
        this.maxHistory = MaxHistoryInit + (int) randRange(0, 100);
        this.vectorAmp = vectorAmpInit + randRange(0, 0);

        reset = false;
    }

    public double randRange(int a, int b) {
        return (Math.random() * (b - a) + a);
    }

    public void draw(Graphics g) {
        LinkedListS.Node<Point> lastNode = this.History.getFirst();

        for (int i = 2; i < this.History.size; i++) { // 2 points create a line
            LinkedListS.Node<Point> currentNode = lastNode.next;
            g.drawLine(lastNode.value.x, lastNode.value.y, currentNode.value.x, currentNode.value.y);

            lastNode = currentNode;
        }
    }

    public void resetCheck() {
        if (this.x <= 0 || this.x >= grid.width || this.y <= 0 || this.y >= grid.height) {
            reset = true;
        }
    }

    public void update() {
        if (!reset) {
            int GridX = (int) (this.x / this.grid.cellSize) * this.grid.cellSize;
            int GridY = (int) (this.y / this.grid.cellSize) * this.grid.cellSize;

            double angle = this.grid.VectorField.get(new Point(GridX, GridY));

            this.dx = (int) (Math.cos(angle) * vectorAmp);
            this.dy = (int) (Math.sin(angle) * vectorAmp);

            // System.out.println("dx : " + dx);
            // System.out.println("dy : " + dy);

            this.x += this.dx;
            this.y += this.dy;

            this.History.add(new Point(this.x, this.y));

            if (this.History.size > this.maxHistory) {
                this.History.removeFirst();
            }

            resetCheck();
        } else {
            if (this.History.size > 0) {
                this.History.removeFirst();
            } else {
                initParticle();
            }
        }
    }
}
