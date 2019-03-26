import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TurtleCanvas extends JPanel implements MouseListener {

    private Turtle turtle;
    private JTextField input;

    public TurtleCanvas() {
        JFrame frame = new JFrame("L-System Renderer");
        JPanel mainPanel = new JPanel();


        this.turtle = new Turtle(this,250,250);
        this.input = new JTextField();
        this.setPreferredSize(new Dimension(500,500));
        input.setPreferredSize(new Dimension(200,20));

        mainPanel.setPreferredSize(new Dimension(500,600));
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(this);
        mainPanel.add(new Label("dist;angle;iter;axiom;a:x"));
        mainPanel.add(input);


        this.addMouseListener(this);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public Turtle getTurtle() {
        return turtle;
    }

    public void drawLSystem(LSystem l,Graphics g) {
        for (char c : l.iterate().toCharArray()) {
            if (c == '+') {
                turtle.setHeading(turtle.getHeading()+l.getAngle());
            } else if (c == '-') {
                turtle.setHeading(turtle.getHeading()-l.getAngle());
            } else if (c == '[') {
                turtle.getStateStack().push(new TurtleState(turtle.getX(),turtle.getY(),turtle.getHeading()));
            } else if (c == ']') {
                TurtleState state = turtle.getStateStack().pop();
                turtle.setX(state.getX());
                turtle.setY(state.getY());
                turtle.setHeading(state.getHeading());
            } else if (c == 'X' || c== 'Y') {
                //do nothing
            } else {
                double x0 = turtle.getX();
                double y0 = turtle.getY();
                turtle.move(l.getDist(),turtle.getHeading());
                double x1 = turtle.getX();
                double y1 = turtle.getY();
                g.drawLine((int)x0,(int)y0,(int)x1,(int)y1);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLSystem(turtle.getlSystem(),g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!input.getText().equals("")) {
            turtle.setlSystem(LSystem.fromText(input.getText()));
        }
        turtle.setX(e.getX());
        turtle.setY(e.getY());
        turtle.setHeading(90);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
