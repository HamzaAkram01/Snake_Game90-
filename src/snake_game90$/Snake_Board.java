package snake_game90$;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author DELL
 */
// Board Class and extends Jpanel and implements Keyslistener
public class Snake_Board extends JPanel implements ActionListener {

    // Images
    private Image apple;
    private Image dot;
    private Image head;
    // Coordinate of panel and x andd y axis
    private final int DOT_SIXE = 10;
    private final int Total_DOTS = 4200;
    // apple pickups axis
    private int apple_x;
    private int apple_y;

    // array store to dots
    private final int x[] = new int[Total_DOTS];
    private final int y[] = new int[Total_DOTS];

    // direction componenets
    private boolean LeftDirection = false;
    private boolean RightDirection = false;
    private boolean UpDirection = false;
    private boolean DownDirection = false;

    // moves and level 
    private int moves = 0;
    private int level = 0;
    private float X_Speed = 0;

    // starting point and collision
    private boolean inGame = true;
    public boolean collision = true;

    //timer to start and stop
    private Timer timer;

    // sanke lenght
    private int dots;
    private int count;

    // constructor of board
    Snake_Board() {
        // key listener
        addKeyListener(new Adapter());
        // board color
        setBackground(Color.BLACK);
        // panel sixe
        setPreferredSize(new Dimension(875, 475));
        // sanke movements
        setFocusable(true);
        // image loaders
        ImageContainer();
        // Main gaming points
        StGame();
    }

    // function to loaders image

    public void ImageContainer() {
        // get apple image
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("snake_game90$\\Icon\\apple.png"));
        apple = I1.getImage();
        // get dots image
        ImageIcon I2 = new ImageIcon(ClassLoader.getSystemResource("snake_game90$\\Icon\\dot.png"));
        dot = I2.getImage();
        // get head image
        ImageIcon I3 = new ImageIcon(ClassLoader.getSystemResource("snake_game90$\\Icon\\head.png"));
        head = I3.getImage();
    }

    // function  to main game

    public void StGame() {
        // initially sanke lenght 3
        dots = 3;
        // loop to check when picks apple then increase sixe
        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * DOT_SIXE;
            y[i] = 50;
        }

        // randomly apple in x to y axis
        Randomly_Frog();
        // timer to start game
        timer = new Timer(50, this);
        timer.start();
    }

    // Fucntion to Randomly apple assign
    public void Randomly_Frog() {
        int x = (int) (Math.random() * 80);// randomly apple assign into X axis
        apple_x = (x * DOT_SIXE);

        int y = (int) (Math.random() * 40);// randomly apple assign into Y axis
        apple_y = (y * DOT_SIXE);

    }

    // functios to check apple to  pickups

    public void checkApple() {
        // if apples got it in X and Y axis then increments 
        // lengh and again Randomly assign apples
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            count++;
            Randomly_Frog();
        }
    }

    // Extends funtion to java libaray for graphics
    public void paintComponent(Graphics g) {

        // if all direction means no move then snake not to 
        // move and every time snake on to this poition 
        // always when starting of game
        if (moves == 0) {
            x[2] = 80;
            x[1] = 90;
            x[0] = 100;

            y[2] = 160;
            y[1] = 160;
            y[0] = 160;
        }

        // this is to call java libaray
        super.paintComponent(g);

        // here are some diplays writing Lines
        g.setColor(Color.WHITE);
        g.drawRect(2, 410, 880, 73);

        g.setColor(Color.RED);
        g.setFont(new Font("Ink free", Font.BOLD, 35));
        g.drawString("Score: " + count, 380, 25);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("arial", Font.BOLD, 30));
        level = count / 5;
        g.drawString("Level: " + level, 750, 460);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        X_Speed = (float) (moves * 0.3);
        g.drawString("X-Speed: " + X_Speed, 20, 25);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Lenght: " + dots, 780, 25);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 30));
        g.drawString("Pressed Keys:", 10, 440);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 22));
        g.drawString("RESET: R", 380, 435);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Enter: HOME", 20, 465);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("ESC: Exit", 200, 465);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("SPACE: RESTART", 350, 465);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Collision: C ", 580, 465);
        // function to draw snake and dots , apple and head
        draw(g);
    }

    // function to draw snake
    public void draw(Graphics g) {
        // if game is on then make a sanke
        if (inGame) {
            // draw apple
            g.drawImage(apple, apple_x, apple_y, this);
            //loop to start dots and head attach together
            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(dot, x[i], y[i], this);
                }
            }
            // call to libaray function
            // its work like even apple picksup 
            // then its runnig
            Toolkit.getDefaultToolkit().sync();

        }// other wise Game over 
        else {
            GameOver(g);
        }
    }

    // funciton to gaame over
    public void GameOver(Graphics g) {
        // all directionns false
        LeftDirection = false;
        RightDirection = false;
        UpDirection = false;
        DownDirection = false;

        // some display writes
        g.setColor(Color.RED);
        g.setFont(new Font("Ink free", Font.BOLD, 35));
        g.drawString("Score: " + count, 380, 25);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("arial", Font.BOLD, 30));
        level = count / 5;
        g.drawString("Level: " + level, 750, 460);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        X_Speed = (float) (moves * 0.3);
        g.drawString("X-Speed: " + X_Speed, 20, 25);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Lenght: " + dots, 780, 25);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Algerian", Font.BOLD, 60));
        g.drawString("Game Over", 280, 200);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Space to RESTART", 360, 230);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Enter to HOME", 370, 260);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Esc to EXIT", 380, 290);
    }

    // function to check collison
    // means if snake touch the border then 
    // game over
    public void checkCollision() {
        // loop to check snake lenght means dot to dot touch 
        // then games over
        for (int i = dots; i > 0; i--) {
            if (i > 4 && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }
        // if snake touch the border of y aixs
        // then game over
        if (y[0] == 400) {
            inGame = false;
        }
        // if snake touch the border of x aixs
        // then game over
        if (x[0] == 880) {
            inGame = false;
        }
        // if snake touch the border of x aixs
        // then game over
        if (x[0] < 0) {
            inGame = false;
        }
        // if snake touch the border of y aixs
        // then game over
        if (y[0] < 0) {
            inGame = false;
        }

        // if game is false then timer stop
        if (!inGame) {
            timer.stop();
        }
    }

    // functions to movements of snake
    public void Move() {
        // loop to incresae lenght
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        // if press to left then 
        // its going to left direction
        if (LeftDirection) {
            x[0] = x[0] - DOT_SIXE;
        }
        // if press to Right then 
        // its going to Right direction
        if (RightDirection) {
            x[0] += DOT_SIXE;
        }
        // if press to Up then 
        // its going to Up direction
        if (UpDirection) {
            y[0] = y[0] - DOT_SIXE;
        }
        // if press to Down then 
        // its going to Down direction
        if (DownDirection) {
            y[0] += DOT_SIXE;
        }
    }

    // function to check no collision
    // means if snake touch the border then its 
    // comes back to other sides

    public void Check2() {

        // sanke to its body then game over
        for (int i = dots; i >= 0; i--) {
            if (i > 4 && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }
        // if press right then its moverment
        // and when its going to be right then 
        // its come back to other sides means opposite site
        // and also increments of snake lenght
        if (RightDirection) {
            for (int i = dots - 1; i >= 0; i--) {
                y[i + 1] = y[i];
            }
            for (int i = dots - 1; i >= 0; i--) {
                if (i == 0) {
                    x[i] = x[i] + 10;
                } else {
                    x[i] = x[i - 1];
                }
                if (x[i] > 875) {
                    x[i] = 0;
                }
            }
            repaint();

        }
        // if press Left then its moverment
        // and when its going to be Left then 
        // its come back to other sides means opposite site
        // and also increments of snake lenght
        if (LeftDirection) {
            for (int i = dots - 1; i >= 0; i--) {
                y[i + 1] = y[i];
            }
            for (int i = dots - 1; i >= 0; i--) {
                if (i == 0) {
                    x[i] = x[i] - 10;
                } else {
                    x[i] = x[i - 1];
                }
                if (x[i] < 0) {
                    x[i] = 875;
                }
            }

            repaint();
        }
        // if press Up then its moverment
        // and when its going to be UP then 
        // its come back to other sides means opposite site
        // and also increments of snake lenght
        if (UpDirection) {
            for (int i = dots - 1; i >= 0; i--) {
                x[i + 1] = x[i];
            }
            for (int i = dots - 1; i >= 0; i--) {
                if (i == 0) {
                    y[i] = y[i] - 10;
                } else {
                    y[i] = y[i - 1];
                }
                if (y[i] < 0) {
                    y[i] = 400;
                }
            }
            repaint();

        }
        // if press Down then its moverment
        // and when its going to be Down then 
        // its come back to other sides means opposite site
        // and also increments of snake lenght
        if (DownDirection) {
            for (int i = dots - 1; i >= 0; i--) {
                x[i + 1] = x[i];
            }
            for (int i = dots - 1; i >= 0; i--) {
                if (i == 0) {
                    y[i] = y[i] + 10;
                } else {
                    y[i] = y[i - 1];
                }
                if (y[i] > 400) {
                    y[i] = 0;
                }
            }

            repaint();
        }
    }
// extends function to Actionlistener

    public void actionPerformed(ActionEvent e) {
        // if game is true then 
        // start game with collision
        if (inGame) {
            if (collision) {
                checkApple();
                checkCollision();
                Move();
            }// if game is true then 
            // start game with no collision
            else {
                checkApple();
                Check2();
            }
        }
        // also call repaints  to whole diplays 
        // its call to paintscomponents
        repaint();
    }

    //class Key adapter means Listener
    public class Adapter extends KeyAdapter {

        // functions to key pressed
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();// key is equal to key code
            // if press R then all setting and score++ is eeual to 0
            if (key == KeyEvent.VK_R) {
                collision = true;
                count = 0;
                level = 0;
                dots = 3;
                moves = 0;
                X_Speed = (float) 0;

            }
            //if press C then collison is on
            if (key == KeyEvent.VK_C) {
                collision = false;
            }
            // if press space then restart and showing new sreens
            if (key == KeyEvent.VK_SPACE) {
                new Snake_Play().setVisible(true);
            }
            // if press Escape tp EXIT
            if (key == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
            // if Press Enter then showing home page
            if (key == KeyEvent.VK_ENTER) {
                new Snake_Game().setVisible(true);
            }
            // if press left arrow and not equal to right arrow then goto left
            if ((key == KeyEvent.VK_LEFT) && (!RightDirection)) {
                moves++;
                LeftDirection = true;
                UpDirection = false;
                DownDirection = false;

            } // if press Right arrow and not equal to Left arrow then goto Right
            else if ((key == KeyEvent.VK_RIGHT) && (!LeftDirection)) {
                moves++;
                RightDirection = true;
                UpDirection = false;
                DownDirection = false;

            } // if press Up arrow and not equal to Down arrow then goto UP
            else if ((key == KeyEvent.VK_UP) && (!DownDirection)) {
                moves++;
                LeftDirection = false;
                UpDirection = true;
                RightDirection = false;
            } // if press Down arrow and not equal to UP arrow then goto Down
            else if ((key == KeyEvent.VK_DOWN) && (!UpDirection)) {
                moves++;
                LeftDirection = false;
                RightDirection = false;
                DownDirection = true;

            }

        }

    }

}
