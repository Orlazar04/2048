import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Tile extends JLabel
{
    public Tile(int num, int x, int y)
    {
        setBounds(x, y, 75, 75);
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(new Font("Chalkboard", Font.BOLD, 32));
        setForeground(Color.WHITE);
        setOpaque(true);

        if (num == 0)
        {
            setBackground(Color.WHITE);
        }
        else if (num == 2)
        {
            setText("2");
            setBackground(new Color(245, 200, 255));
        }
        else if (num == 4)
        {
            setText("4");
            setBackground(new Color(185, 105, 200));
        }
        else if (num == 8)
        {
            setText("8");
            setBackground(new Color(135, 100, 195));
        }
        else if (num == 16)
        {
            setText("16");
            setBackground(new Color(200, 30, 125));
        }
        else if (num == 32)
        {
            setText("32");
            setBackground(new Color(145, 10, 65));
        }
        else if (num == 64)
        {
            setText("64");
            setBackground(new Color(245, 50, 20));
        }
        else if (num == 128)
        {
            setText("128");
            setBackground(new Color(255, 140, 0));
        }
        else if (num == 256)
        {
            setText("256");
            setBackground(new Color(250, 205, 30));
        }
        else if (num == 512)
        {
            setText("512");
            setBackground(new Color(85, 230, 69));
        }
        else if (num == 1024)
        {
            setText("1024");
            setBackground(new Color(30, 225, 220));
        }
        else if (num == 2048)
        {
            setFont(new Font("Chalkboard", Font.BOLD, 25));
            setText("2048");
            setBackground(new Color(30, 135, 225));
        }
    }
}
