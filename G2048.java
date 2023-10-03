public class G2048
{
    public static void main(String[] args)
    {
        G2048GUI game = new G2048GUI();
        game.createHeader();
        game.createButton();
        game.repaint();
        game.revalidate();
    }
}


