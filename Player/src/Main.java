//public class Main {

  //  public static void main(String[] args) {
    //    Player testPlayer = new Player(0, 0);
     //   testPlayer.movement(null);
       // testPlayer.printInfo();
    //}
//}
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

//this line benath it makes my programm use jframe to open windows and the 
// keylistener to take in key inputs extends= inheritance from jframe to use it´s 
// functions and implemt means that the programm can listen to the key inpust
public class Main extends JFrame implements KeyListener {

    Player player = new Player(0, 0);

    // this constructor called main is what is used to make a window open up since keylistners only work in combination with Gui´s
    public Main() {
        addKeyListener(this);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
// the main method only needs to do on thing creat the window so the rest does work
    public static void main(String[] args) {
        new Main();
    }

   // @Override
   //this methode uses what is in the player class to listen to key inputs and change the x an y coordinates
    public void keyPressed(KeyEvent e) {
        player.movement(e);
        player.printInfo();
    }

   // @Override
    public void keyReleased(KeyEvent e) {}
   // @Override 
    public void keyTyped(KeyEvent e) {}
}