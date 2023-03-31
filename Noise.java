public class Noise {
    private final static double angleAmp = 0.3;
    private final static double curve = 0.44;

    public static double noise(double x, double y, double t) {
        return (Math.cos((x + t) * angleAmp) + Math.sin((y + t) * angleAmp)) * curve;
    }
}