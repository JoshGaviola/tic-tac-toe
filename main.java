import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class main {
    private final JLabel textfield = new JLabel();
    private final JFrame frame = new JFrame("Tic Tac Toe");
    private final Random random = new Random();
    private final JPanel title_panel = new JPanel();
    private final JPanel button_panel = new JPanel();
    private final JButton[] buttons = new JButton[9];
    private final JButton restart_button = new JButton("Play Again");
    private int turn; // 0 is player, 1 is AI
    private boolean gameOver = false;
    private final String player = "Player";
    private final String ai = "AI";
    private int playerScore = 0;
    private int aiScore = 0;
    private int turnCount;

    public main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
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
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!gameOver) {
                        JButton button = (JButton) e.getSource();
                        if (button.getText().equals("")) {
                            button.setText("X");
                            turnCount++;
                            checkForWin();
                            if (!gameOver) {
                                aiMove();
                                checkForWin();
                            }
                        }
                    }
                }
            });
        }

        restart_button.setFocusable(false);
        restart_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        int randomNum = random.nextInt(2);
        if (randomNum == 0) {
            turn = 0;
            textfield.setText("Player's turn");
        } else {
            turn = 1;
            textfield.setText("AI's turn");
            aiMove();
        }

        title_panel.add(textfield);

        JPanel button_pane_with_restart = new JPanel();
        button_pane_with_restart.setLayout(new BorderLayout());
        button_pane_with_restart.add(button_panel, BorderLayout.CENTER);
        button_pane_with_restart.add(restart_button, BorderLayout.SOUTH);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_pane_with_restart);

    }

    public void aiMove() {
        if (!gameOver) {
            int choice;
            do {
                choice = random.nextInt(9);
            } while (!buttons[choice].getText().equals(""));
            buttons[choice].setText("O");
            turnCount++;
        }
    }

    public void checkForWin() {
        // check rows
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i + 1].getText())
                    && buttons[i].getText().equals(buttons[i + 2].getText()) && !buttons[i].getText().equals("")) {
                gameOver = true;
                if (buttons[i].getText().equals("X")) {
                    textfield.setText("Player wins");
                    return;
                }
            }
        }
        // check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[j].getText().equals(buttons[j + 3].getText())
                    && buttons[j].getText().equals(buttons[j + 6].getText()) && !buttons[j].getText().equals("")) {
                gameOver = true;
                if (buttons[j].getText().equals("X")) {
                    textfield.setText("Player wins!");
                    playerScore++;
                } else {
                    textfield.setText("AI wins!");
                    aiScore++;
                }
                updateScore();
                return;
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
            updateScore();
            return;
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
            updateScore();
            return;
        }
        // check for tie
        if (turnCount == 9) {
            gameOver = true;
            textfield.setText("It's a tie!");
            updateScore();
        }
    }

    public void updateScore() {
        textfield.setText("Player: " + playerScore + "   AI: " + aiScore);
        int delay = 1000; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetGame();
            }
        };
        if (playerScore >= 3 || aiScore >= 3) {
            Timer timer = new Timer(delay, taskPerformer);
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        turnCount = 0;
        gameOver = false;
        int randomNum = random.nextInt(2);
        if (randomNum == 0) {
            turn = 0;
            textfield.setText("Player's turn");
        } else {
            turn = 1;
            textfield.setText("AI's turn");
            aiMove();
        }

        restart_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

    }

    public static void main(String[] args) {
        new main();
    }
}