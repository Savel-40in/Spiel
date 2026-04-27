import javax.swing.JPanel;
import java.awt.*;

public class Painting extends JPanel{

    public Painting(Frame f){

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Map m = new Map(21);
        
        m.drawMap(g);
        
        

    }


}