import java.awt.*;
import java.awt.geom.*;

public class Lollipop extends Area {

    private double stick_width;
    private double stick_height;
    private double pop_diameter;

    /**
     *  __
     * /  \
     *|    |
     * \  /
     *  ||
     *  ||
     *  ||
     * 
     * @param stick_width  stick width
     * @param stick_height stick height
     * @param pop_diameter diameter of lollipop circle
     *
     */
    Lollipop(double stick_width, double stick_height, double pop_diameter) {

        this.stick_width = stick_width;
        this.stick_height = stick_height;
        this.pop_diameter = pop_diameter;
        
        // stick
        double stick_x = -stick_width / 2;
        double stick_y = -stick_height / 2;
        Area stick = new Area(
                new Rectangle((int) stick_x, (int) (stick_y), (int) (stick_width), (int) stick_height));

        // pop
        double pop_x = stick_x - (pop_diameter / 2 - stick_width / 2);
        double pop_y = stick_y - pop_diameter;
        Area pop = new Area(new Ellipse2D.Double(pop_x, pop_y + 5,
                pop_diameter, pop_diameter));

        stick.add(pop);
        this.add(stick);

    }

    /**
     * @return width of the lollipop
     */
    public double getWidth() {
        if (stick_width < pop_diameter) {
            return pop_diameter;
        } else {
            return stick_width;
        }
    }

    /**
     * @return height of the lollipop
     */
    public double getHeight() {
        return stick_height + pop_diameter;
    }

    public Area getArea() {
        return (Area) this;
    }

    /**
     * @return pop diameter
     */
    public double getPop_diameter() {
        return pop_diameter;
    }

    /**
     * @return stick height
     */
    public double getStick_height() {
        return stick_height;
    }

    /**
     * @return stick width
     */
    public double getStick_width() {
        return stick_width;
    }

}
