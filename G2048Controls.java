public class G2048Controls
{
    private int[][] board;
    private int[][] posBoard;
    private int[][] pastBoard;
    private int totalScore = 0;
    private int addScore = 0;
    private static int highScore = 0;

    public G2048Controls()
    {
        board = new int[4][4];
        posBoard = new int[4][4];
        pastBoard = new int[4][4];
        
        spawnTile();
        updatePastBoard();
    }
    
    private void spawnTile()
    {
        int val = 0;
        if ((int)(Math.random() * 10) == 4)
            val = 4;
        else
            val = 2;
        int empty = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j] == 0)
                    empty++;
            }
        }
        int loc = (int)(Math.random() * empty);
        int c = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j] == 0)
                {
                    if (c == loc)
                    {
                        board[i][j] = val;
                        c++;
                    }
                    else
                        c++;
                }
            }
        }
    }
    
    private void updatePastBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                pastBoard[i][j] = board[i][j];
            }
        }
    }
    
    private void updatePosBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                posBoard[i][j] = board[i][j];
            }
        }
    }
    
    private void updateBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = posBoard[i][j];
            }
        }
    }
    
    public void undoBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = pastBoard[i][j];
            }
        }
        totalScore -= addScore;
        addScore = 0;
    }
    
    public void resetBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = 0;
            }
        }
        spawnTile();
        updatePastBoard();
        totalScore = 0;
    }
    
    private boolean diffBoards()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (posBoard[i][j] != board[i][j])
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean movePossible()
    {
        boolean filled = true;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j] == 0)
                {
                    filled = false;
                    break;
                }
            }
        }
        if (filled)
        {
            if (moveLeftPossible() || moveRightPossible() || moveUpPossible() || moveDownPossible())
            {
                return true;
            }
            return false;
        }
        return true;
    }
    
    private boolean moveLeftPossible()
    {
        updatePosBoard();
        addScore = 0;
        for (int i = 0; i < posBoard.length; i++)
        {
            for (int j = 1; j < posBoard[i].length; j++)
            {
                int k = j;
                while (k != 0 && posBoard[i][k] != 0 && posBoard[i][k - 1] == 0)
                {
                    posBoard[i][k - 1] = posBoard[i][k];
                    posBoard[i][k] = 0;
                    k--;
                }
            }
            for (int j = 1; j < posBoard[i].length; j++)
            {
               if (posBoard[i][j - 1] == posBoard[i][j])
               {
                   addScore += (posBoard[i][j - 1] * 2);
                   posBoard[i][j - 1] *= 2;
                   for (int k = j + 1; k < posBoard[i].length; k++)
                   {
                       posBoard[i][k - 1] = posBoard[i][k];
                   }
                   posBoard[i][posBoard[i].length - 1] = 0;
               }
            }
        }
        return diffBoards();
    }
    
    public void moveLeft()
    {
        if (moveLeftPossible())
        {
            updatePastBoard();
            updateBoard();
            totalScore += addScore;
            spawnTile();
        }
    }
    
    private boolean moveRightPossible()
    {
        updatePosBoard();
        addScore = 0;
        for (int i = posBoard.length - 1; i >= 0; i--)
        {
            for (int j = posBoard[i].length - 2; j >= 0; j--)
            {
                int k = j;
                while ((k != posBoard[i].length - 1) && posBoard[i][k] != 0 && posBoard[i][k + 1] == 0)
                {
                    posBoard[i][k + 1] = posBoard[i][k];
                    posBoard[i][k] = 0;
                    k++;
                }
            }
            for (int j = posBoard[i].length - 2; j >= 0; j--)
            {
               if (posBoard[i][j + 1] == posBoard[i][j])
               {
                   addScore += (posBoard[i][j + 1] * 2);
                   posBoard[i][j + 1] *= 2;
                   for (int k = j - 1; k >= 0; k--)
                   {
                       posBoard[i][k + 1] = posBoard[i][k];
                   }
                   posBoard[i][0] = 0;
               }
            }
        }
        return diffBoards();
    }
    
    public void moveRight()
    {
        if (moveRightPossible())
        {
            updatePastBoard();
            updateBoard();
            totalScore += addScore;
            spawnTile();
        }
    }
    
    private boolean moveUpPossible()
    {
        updatePosBoard();
        addScore = 0;
        for (int j = 0; j < posBoard[0].length; j++)
        {
            for (int i = 1; i < posBoard.length; i++)
            {
                int k = i;
                while (k != 0 && posBoard[k][j] != 0 && posBoard[k - 1][j] == 0)
                {
                    posBoard[k - 1][j] = posBoard[k][j];
                    posBoard[k][j] = 0;
                    k--;
                }
            }
            for (int i = 1; i < posBoard.length; i++)
            {
               if (posBoard[i - 1][j] == posBoard[i][j])
               {
                   addScore += (posBoard[i - 1][j] * 2);
                   posBoard[i - 1][j] *= 2;
                   for (int k = i + 1; k < posBoard.length; k++)
                   {
                       posBoard[k - 1][j] = posBoard[k][j];
                   }
                   posBoard[posBoard.length - 1][j] = 0;
               }
            }
        }
        return diffBoards();
    }
    
    public void moveUp()
    {
        if (moveUpPossible())
        {
            updatePastBoard();
            updateBoard();
            totalScore += addScore;
            spawnTile();
        }
    }
    
    private boolean moveDownPossible()
    {
        updatePosBoard();
        addScore = 0;
        for (int j = posBoard[0].length - 1; j >= 0; j--)
        {
            for (int i = posBoard.length - 2; i >= 0; i--)
            {
                int k = i;
                while ((k != posBoard.length - 1) && posBoard[k][j] != 0 && posBoard[k + 1][j] == 0)
                {
                    posBoard[k + 1][j] = posBoard[k][j];
                    posBoard[k][j] = 0;
                    k++;
                }
            }
            for (int i = posBoard.length - 2; i >= 0; i--)
            {
               if (posBoard[i + 1][j] == posBoard[i][j])
               {
                   addScore += (posBoard[i + 1][j] * 2);
                   posBoard[i + 1][j] *= 2;
                   for (int k = i - 1; k >= 0; k--)
                   {
                       posBoard[k + 1][j] = posBoard[k][j];
                   }
                   posBoard[0][j] = 0;
               }
            }
        }
        return diffBoards();
    }
    
    public void moveDown()
    {
        if (moveDownPossible())
        {
            updatePastBoard();
            updateBoard();
            totalScore += addScore;
            spawnTile();
        }
    }
    
    public boolean acheived2048()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j] == 2048)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int getBoard(int i, int j)
    {
        return board[i][j];
    }
    
    public int getTotalScore()
    {
        return totalScore;
    }
    
    public int getHighScore()
    {
        return highScore;
    }
    
    public boolean newHighScore()
    {
        if (totalScore >= highScore)
        {
            highScore = totalScore;
            return true;
        }
        return false;
    }
}
