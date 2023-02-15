package BricksGame;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePlay extends JPanel implements KeyListener, ActionListener 
{
    
    private boolean play = false;
    private int score = 0;
    private int totalbricks = 21;
    private Timer Timer;
    private int playerX = 300; // middle pos
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -2;
    private int ballYdir = -3;
    private MapGenerator map;

    public GamePlay() 
    {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(0,this);
        Timer.start();
    }
    
     public void paint(Graphics g) 
     {
        g.setColor(Color.black);
        g.fillRect(0, 0, 700, 600); // 1, 1, 692, 592

        map.draw((Graphics2D) g);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 600); // left border -> w = 3, h = 600
        g.fillRect(0, 0, 700, 3); // top border -> Horizontal -> length = 700 , width = 3
        g.fillRect(683, 0, 3, 600); // Right Border
        g.fillRect(0,560,700,3); // Bottom Border

        g.setColor(Color.white); // This is for show score
        Font f1 = new Font("DIALOG", Font.BOLD, 16); //create font
        g.setFont(f1);
        g.drawString("Score : " + score, 590, 30);

        g.setColor(Color.green);
        g.fillRect(playerX, 500, 115, 10);

        //ball
        g.setColor(Color.WHITE);
        g.fillOval(ballposX, ballposY, 25, 25);

        if (ballposY > 520) 
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("    Game Over !! Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
        }
        if(totalbricks == 0){
            play = false;
            ballYdir = -2;
            ballXdir = -1;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    You Won !! Score: "+score,190,300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);


        }
        //g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Timer.start();

        if(play) 
        {
        	// When the ball intersect with paddle
            if (new Rectangle(ballposX, ballposY, 25, 25).intersects(new Rectangle(playerX, 500, 115, 10))) 
            {
                ballYdir = -ballYdir;
            }

            for (int i = 0; i < map.map.length; i++) 
            {
                for (int j = 0; j < map.map[0].length; j++) 
                {
                    if (map.map[i][j] > 0) 
                    {
                        int brickX = j * map.bricksWidth + 80;
                        int brickY = i * map.bricksHeight + 50;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 25, 25);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) 
                        {
                            map.setBricksValue(0, i, j);
                            totalbricks--;
                            score += 1;
                            if (ballposX + 10 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) 
                            {
                                ballXdir = -ballXdir;
                            } 
                            else 
                            {
                                ballYdir = -ballYdir;
                            }
                        }
                    }
                }
            }


            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) 
            {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) 
            {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) 
            {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {

    }


    @Override
    public void keyReleased(KeyEvent e) 
    {

    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
        {
            if (playerX >= 600) 
            {
                playerX = 600;
            } 
            else 
            {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) 
        {
            if (playerX < 10) 
            {
                playerX = 10;
            } 
            else 
            {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            if (!play) 
            {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -2;
                ballYdir = -3;
                score = 0;
                playerX = 310;
                totalbricks = 21;
                map = new MapGenerator(3, 7);

                repaint();
            }
        }
        
        }

        public void moveRight ()
        {
            play = true;
            playerX += 30;
        }
        public void moveLeft ()
        {
            play = true;
            playerX -= 30;
        }
        
}






