import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class main {

    JLabel textfield = new JLabel();
    JFrame frame = new JFrame("Tic Tac Toe");
    Random random = new Random();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JButton[] buttons = new JButton[9];
    int turn; // 0 is player, 1 is AI
    boolean gameOver = false;
    String player = "Player";
    String ai = "AI";
    int playerScore = 0;
    int aiScore = 0;
    int turnCount;
    int currentTurn;

    main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setBackground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == buttons[i]) {
                            if (buttons[i].getText().equals("")) {
                                buttons[i].setText("X");
                                turn = 1;
                                checkForWin();
                                aiMove();
                                checkForWin();
                            }
                        }
                    }
                }
            });
        }

        int randomNum = random.nextInt(2);
        if (randomNum == 0) {
            turn = 0;
            textfield.setText("Player's turn");
        } else {
            turn = 1;
            textfield.setText("AI's turn");
        }

        title_panel.add(textfield);

        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

    }

    public void aiMove() {
        int choice;
        do {
            choice = random.nextInt(9);
        } while (buttons[choice].getText().equals("X") || buttons[choice].getText().equals("O"));
        buttons[choice].setText("O");
        checkForWin();
        checkForEnd("O");
        if (turn == 1 && !gameOver) {
            int index = random.nextInt(9);
            while (buttons[index].getText().equals("X") || buttons[index].getText().equals("O")) {
                index = random.nextInt(9);
            }
            buttons[index].setText("O");
            turn = 0;
            textfield.setText("Player's turn");
        }
    }

    public void checkForWin() {
        // check rows
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i + 1].getText())
                    && buttons[i].getText().equals(buttons[i + 2].getText()) && !buttons[i].getText().equals("")) {
                gameOver = true;
                if (buttons[i].getText().equals("X")) {
                    textfield.setText("Player wins!");
                    playerScore++;
                } else {
                    textfield.setText("AI wins!");
                    aiScore++;
                }
            }
        }

        // check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText())
                    && buttons[i].getText().equals(buttons[i + 6].getText()) && !buttons[i].getText().equals("")) {
                gameOver = true;
                if (buttons[i].getText().equals("X")) {
                    textfield.setText("Player wins!");
                    playerScore++;
                } else {
                    textfield.setText("AI wins!");
                    aiScore++;
                }
            }
        }

        // check diagonals
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
                && !buttons[0].getText().equals("")) {
            gameOver = true;
            if (buttons[0].getText().equals("X")) {
                textfield.setText("Player wins!");
                playerScore++;
            } else {
                textfield.setText("AI wins!");
                aiScore++;
            }
        }

        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
                && !buttons[2].getText().equals("")) {
            gameOver = true;
            if (buttons[2].getText().equals("X")) {
                textfield.setText("Player wins!");
                playerScore++;
            } else {
                textfield.setText("AI wins!");
                aiScore++;
            }
        }

        // check for tie
        if (turnCount == 9 && !gameOver) {
            gameOver = true;
            textfield.setText("It's a tie!");
        }
    }

    public void aiTurn() {
        int index = -1;
        int[] possibleMoves = new int[9];

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                possibleMoves[++index] = i;
            }
        }

        int move = -1;
        if (index == 0) {
            move = possibleMoves[0];
        } else {
            for (int i = 0; i < possibleMoves.length; i++) {
                if (possibleMoves[i] != 0) {
                    buttons[possibleMoves[i]].setText("");
                }
            }

            while (move == -1) {
                int random = new Random().nextInt(index + 1);
                int currentMove = possibleMoves[random];
                buttons[currentMove].setText("O");
                if (checkForEnd("O")) {
                    move = currentMove;
                } else {
                    buttons[currentMove].setText("");
                    possibleMoves[random] = 0;
                    if (possibleMoves[0] == 0) {
                        move = possibleMoves[1];
                    }
                }
            }
        }

        buttons[move].setText("O");
        turnCount++;
        currentTurn = 0;
    }

    public boolean checkForEnd(String player) {
        boolean result = false;

        // check for row wins
        if (buttons[0].getText().equals(player) && buttons[1].getText().equals(player)
                && buttons[2].getText().equals(player)) {
            result = true;
        } else if (buttons[3].getText().equals(player) && buttons[4].getText().equals(player)
                && buttons[5].getText().equals(player)) {
            result = true;
        } else if (buttons[6].getText().equals(player) && buttons[7].getText().equals(player)
                && buttons[8].getText().equals(player)) {
            result = true;
        }

        // check for column wins
        else if (buttons[0].getText().equals(player) && buttons[3].getText().equals(player)
                && buttons[6].getText().equals(player)) {
            result = true;
        } else if (buttons[1].getText().equals(player) && buttons[4].getText().equals(player)
                && buttons[7].getText().equals(player)) {
            result = true;
        } else if (buttons[2].getText().equals(player) && buttons[5].getText().equals(player)
                && buttons[8].getText().equals(player)) {
            result = true;
        }

        // check for diagonal wins
        else if (buttons[0].getText().equals(player) && buttons[4].getText().equals(player)
                && buttons[8].getText().equals(player)) {
            result = true;
        } else if (buttons[2].getText().equals(player) && buttons[4].getText().equals(player)
                && buttons[6].getText().equals(player)) {
            result = true;
        }

        // check for tie
        else if (!buttons[0].getText().equals("") && !buttons[1].getText().equals("")
                && !buttons[2].getText().equals("")
                && !buttons[3].getText().equals("") && !buttons[4].getText().equals("")
                && !buttons[5].getText().equals("")
                && !buttons[6].getText().equals("") && !buttons[7].getText().equals("")
                && !buttons[8].getText().equals("")) {
            result = true;
            textfield.setText("It's a tie!");
        }

        return result;
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
        gameOver = false;
        textfield.setText("Tic-Tac-Toe");
    }

    public void updateScoreboard() {
        textfield.setText("Player: " + playerScore + " | AI: " + aiScore);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (!gameOver) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setText("X");
                        checkForWin();
                        checkForEnd("X");
                        if (!gameOver) {
                            aiMove();
                        }
                    }
                } else {
                    int choice = JOptionPane.showOptionDialog(frame, "Play again?", "Game over",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (choice == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new main();
    }

}