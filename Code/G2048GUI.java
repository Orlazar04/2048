import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class G2048GUI extends JFrame implements ActionListener, KeyListener
{
    private final Color lightBlue = new Color(175, 200, 255);
    private final Color darkBlue = new Color(110, 160, 255);
    private JPanel board;
    private G2048Controls control;
    
    public G2048GUI()
    {
        setSize(385, 515);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("2048!");
        getContentPane().setBackground(lightBlue);
        
        board = new JPanel();
        board.setBounds(30, 130, 325, 325);
        board.setBackground(darkBlue);
        board.setLayout(null);
        
        control = new G2048Controls();
        updateBoard();
        addKeyListener(this);
        
        add(board);
    }
    
    private JPanel header;
    private JLabel hiscore;
    private JLabel score;

    public void createHeader()
    {
        header = new JPanel();
        header.setBounds(30, 30, 325, 75);
        header.setBackground(lightBlue);
        header.setOpaque(true);
        header.setVisible(true);
        header.setLayout(null);
        
        JLabel title = new JLabel("2048!");
        title.setBounds(15, 0, 150, 75);
        title.setFont(new Font("Chalkboard", Font.BOLD, 50));
        title.setForeground(darkBlue);
        header.add(title);

        hiscore = new JLabel(" HS: 0");
        hiscore.setBounds(185, 0, 125, 35);
        hiscore.setBackground(Color.WHITE);
        hiscore.setBorder(BorderFactory.createLineBorder(darkBlue, 3));
        hiscore.setFont(new Font("Chalkboard", Font.BOLD, 20));
        hiscore.setForeground(darkBlue);
        hiscore.setOpaque(true);
        header.add(hiscore);
        
        score = new JLabel(" S: 0");
        score.setBounds(185, 40, 125, 35);
        score.setBackground(Color.WHITE);
        score.setBorder(BorderFactory.createLineBorder(darkBlue, 3));
        score.setFont(new Font("Chalkboard", Font.BOLD, 20));
        score.setForeground(darkBlue);
        score.setOpaque(true);
        header.add(score);
        
        add(header);
    }

    private JButton htp;
    
    public void createButton()
    {
        JPanel corner = new JPanel();
        corner.setBounds(360, 460, 20, 20);
        corner.setBackground(lightBlue);
        corner.setOpaque(true);
        corner.setVisible(true);
        corner.setLayout(null);
        
        htp = new JButton("?");
        htp.setSize(20, 20);
        htp.setFont(new Font("Chalkboard", Font.BOLD, 20));
        htp.setForeground(darkBlue);

        htp.addActionListener(this);
        htp.setFocusable(false);
        corner.add(htp);
        add(corner);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == htp)
        {
            JFrame htpWindow = new JFrame("How to Play");
            htpWindow.setSize(350, 260);
            htpWindow.setLocationRelativeTo(htp);
            htpWindow.setVisible(true);
            htpWindow.setResizable(false);
            htpWindow.setLayout(null);
            htpWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            htpWindow.getContentPane().setBackground(darkBlue);
            
            JLabel R1 = new JLabel("<html>1. Use the WASD or arrow keys to slide the tiles towards one side of the board</html>");
            R1.setBounds(15, 5, 320, 48);
            R1.setFont(new Font("Chalkboard", Font.BOLD, 16));
            R1.setForeground(Color.WHITE);
            htpWindow.add(R1);
            
            JLabel R2 = new JLabel("<html>2. Tiles of the same value will merge when they slide into each other</html>");
            R2.setBounds(20, 55, 320, 48);
            R2.setFont(new Font("Chalkboard", Font.BOLD, 16));
            R2.setForeground(lightBlue);
            htpWindow.add(R2);
            
            JLabel R3 = new JLabel("<html>3. The game is over when the board fills and there are no more moves</html>");
            R3.setBounds(22, 105, 320, 48);
            R3.setFont(new Font("Chalkboard", Font.BOLD, 16));
            R3.setForeground(Color.WHITE);
            htpWindow.add(R3);
            
            JLabel R5 = new JLabel("<html>4. Use the U key to undo the previous move and the R key to restart the game</html>");
            R5.setBounds(15, 155, 320, 48);
            R5.setFont(new Font("Chalkboard", Font.BOLD, 16));
            R5.setForeground(lightBlue);
            htpWindow.add(R5);
            
            JLabel R4 = new JLabel("<html>5. Merge until tile 2048 is acheived!</html>");
            R4.setBounds(20, 205, 320, 18);
            R4.setFont(new Font("Chalkboard", Font.BOLD, 16));
            R4.setForeground(Color.WHITE);
            htpWindow.add(R4);
        }
        
    }
    
    private void updateBoard()
    {
        board.removeAll();
        for (int i = 1; i <= 4; i++)
        {
            for (int j = 1; j <= 4; j++)
            {
                board.add(new Tile(control.getBoard(i - 1, j - 1), (5 * j) + (75 * (j - 1)), (5 * i) + (75 * (i - 1))));
            }
        }
        board.repaint();
        board.revalidate();
    }
    
    private void updateScores()
    {
        score.setText(" S: " + control.getTotalScore());
        if (control.newHighScore())
            hiscore.setText(" HS: " + control.getHighScore());
        header.repaint();
        header.revalidate();
    }
    
    private void endScreen(boolean win)
    {
        JFrame end = new JFrame();
        end.setSize(310, 140);
        end.setLocationRelativeTo(null);
        end.setVisible(true);
        end.setResizable(false);
        end.setLayout(null);
        end.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        if (win)
        {
            end.getContentPane().setBackground(new Color(69, 230, 69));
            JLabel gameWon = new JLabel("<html>CONGRATULATIONS, YOU WON!</html>");
            gameWon.setBounds(30, 15, 260, 18);
            gameWon.setFont(new Font("Chalkboard", Font.BOLD, 16));
            gameWon.setForeground(Color.WHITE);
            end.add(gameWon);
        }
        else
        {
            end.getContentPane().setBackground(new Color(245, 55, 55));
            JLabel gameOver = new JLabel("<html>GAME OVER!</html>");
            gameOver.setBounds(108, 15, 100, 18);
            gameOver.setFont(new Font("Chalkboard", Font.BOLD, 16));
            gameOver.setForeground(Color.WHITE);
            end.add(gameOver);
        }
        
        JLabel playAgain = new JLabel("<html>Please press the R key to play again</html>");
        playAgain.setBounds(10, 45, 300, 18);
        playAgain.setFont(new Font("Chalkboard", Font.BOLD, 16));
        playAgain.setForeground(Color.WHITE);
        end.add(playAgain);
        
        if (control.newHighScore())
        {
            JLabel improved = new JLabel("<html>NEW HIGHSCORE!</html>");
            improved.setBounds(90, 75, 150, 18);
            improved.setFont(new Font("Chalkboard", Font.BOLD, 16));
            improved.setForeground(Color.WHITE);
            end.add(improved);
        }
    }
    
    private void updateStatus()
    {
        if (control.acheived2048())
        {
            endScreen(true);
        }
        else if (!control.movePossible())
        {
            endScreen(false);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == 87 || ke.getKeyCode() == 38)
        {
            control.moveUp();
            updateBoard();
            updateScores();
            updateStatus();
        }
        else if (ke.getKeyCode() == 65|| ke.getKeyCode() == 37)
        {
            control.moveLeft();
            updateBoard();
            updateScores();
            updateStatus();
        }
        else if (ke.getKeyCode() == 83 || ke.getKeyCode() == 40)
        {
            control.moveDown();
            updateBoard();
            updateScores();
            updateStatus();
        }
        else if (ke.getKeyCode() == 68 || ke.getKeyCode() == 39)
        {
            control.moveRight();
            updateBoard();
            updateScores();
            updateStatus();
        }
        else if (ke.getKeyCode() == 85)
        {
            control.undoBoard();
            updateBoard();
            updateScores();
        }
        else if (ke.getKeyCode() == 82)
        {
            control.resetBoard();
            updateBoard();
            updateScores();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
    }
}
