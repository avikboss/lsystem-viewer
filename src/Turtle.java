public class Turtle {

    private TurtleCanvas canvas;
    private double x;
    private double y;
    private double heading;
    private LSystem lSystem;

    public Turtle(TurtleCanvas canvas,double x, double y) {
        this.canvas = canvas;
        this.heading = 90;
        this.x = x;
        this.y = y;
        this.lSystem = new LSystem();
    }

    public void move(double dist, double angle) {
        setX(x+dist*Math.cos(Math.toRadians(angle)));
        setY(y-dist*Math.sin(Math.toRadians(angle)));
    }

    public TurtleCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(TurtleCanvas canvas) {
        this.canvas = canvas;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public LSystem getlSystem() {
        return lSystem;
    }

    public void setlSystem(LSystem lSystem) {
        this.lSystem = lSystem;
    }
}
