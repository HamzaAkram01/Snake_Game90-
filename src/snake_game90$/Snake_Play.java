 


package snake_game90$;
import javax.swing.*;


public class Snake_Play extends  JFrame{
    
    // When Constructor call then Game on
    Snake_Play()
    {
        // add panel
        add(new Snake_Board());
        // open to java Screen
        pack();
        // setLocation into center
        setLocationRelativeTo(null);
        // Title set
        setTitle("Snake Game");
        // nobody cant resixeable
        setResizable(false);
        // when press exit then exit all screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
