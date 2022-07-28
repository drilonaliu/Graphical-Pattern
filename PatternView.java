import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PatternView extends JPanel {

     int frame_height;
     int frame_width;
     int circles = 3;

     PatternView(int frame_width, int frame_height) {
          this.frame_height = frame_height;
          this.frame_width = frame_width;

          // Frame
          JFrame frame = new JFrame();
          frame.setSize(frame_width, frame_height);
          frame.setDefaultCloseOperation(3);
          frame.add(this);
          this.setBackground(new Color(229, 222, 206, 255));

          // Slider
          JSlider slider = new JSlider(JSlider.VERTICAL, 0, 12, 3);
          slider.setBackground(null);
          slider.setPaintLabels(false);
          slider.addChangeListener(new ChangeListener() {
               @Override
               public void stateChanged(ChangeEvent e) {
                    circles = Integer.valueOf(((JSlider) (e.getSource())).getValue());
                    repaint();
               }
          });
          slider.setMajorTickSpacing(1);
          slider.setPaintTicks(true);

          // Layout
          this.setLayout(new BorderLayout());
          this.add(slider, BorderLayout.EAST);

          frame.setVisible(true);
     }

     public void paint(Graphics g) {

          super.paint(g);

          // Pen properties
          Graphics2D g2 = (Graphics2D) g;
          g2.translate(this.getWidth() / 2, this.getHeight() / 2);
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          BasicStroke stroke = new BasicStroke(10);
          g2.setStroke(stroke);

          // Center circle parametres
          int center_diameter = 20;
          int center_x = -center_diameter / 2;
          int center_y = center_x;

          //Drawing
          for (int i = 0; i < circles; i++) {
               Pattern.drawPattern(center_x, center_y, center_diameter, i, g2);
          }
     }
}
