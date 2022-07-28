import java.awt.*;
import java.awt.geom.*;

public class Pattern {

     /**
      * Draws the n-th cirle with lollipops around it
      * 
      * @param center_x        x coordinate of first circle
      * @param center_y        y coordinate of the first circle
      * @param center_diameter diameter of the first circle
      * @param n               n-th circle to be drawn
      * @param g2              drawing pen
      */
     public static void drawPattern(double center_x, double center_y, double center_diameter, int n, Graphics2D g2) {

          // the first circle has no lollipops and it is colored inside
          if (n == 0) {
               Area center_circle = new Area(
                         new Ellipse2D.Double(center_x, center_y, center_diameter, center_diameter));
               g2.fill(center_circle);
          } else {

               // lollipop parametres
               double stick_width = 0.4 * center_diameter;
               double stick_height = 1.5 * center_diameter;
               double pop_diameter = 0.9 * center_diameter;

               // Circle
               double circle_radius = Math.abs(center_x - 0.8 * center_diameter)
                         + (n - 1) * (stick_height + pop_diameter + 10);
               double circle_x = center_x - circle_radius;
               double circle_y = center_y - circle_radius;
               double circle_width = 2 * (Math.abs(circle_x));
               Area circle = new Area(new Ellipse2D.Double(circle_x, circle_y, circle_width, circle_width));

               // Lollipop
               Lollipop lollipop = new Lollipop(stick_width, stick_height, pop_diameter);
               Rectangle lollipop_bounds = lollipop.getBounds();
               // raise the lollipop to the top of the circle
               AffineTransform move = new AffineTransform();
               move.translate(0,
                         -(circle_radius - center_x - Math.abs(lollipop_bounds.getY()) + lollipop_bounds.getHeight()));
               lollipop.transform(move);

               // rotoate angle for the f(n) lollipop
               AffineTransform r = new AffineTransform();
               int n0 = f(n);
               r.rotate(2 * Math.PI / n0);

               // Drawing with g2
               Area lollipopArea = lollipop.getArea();
               g2.draw(circle);
               for (int i = 0; i < n0; i++) {
                    lollipopArea = lollipopArea.createTransformedArea(r);
                    g2.fill(lollipopArea);
               }
          }
     }

     /**
      * @param n n-th circle of the pattern
      * @return number of lollipops around the circle
      */
     private static int f(int n) {
          return (n + 1) * 8;
     }
}