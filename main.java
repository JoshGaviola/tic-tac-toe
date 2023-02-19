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
    int turn;
    boolean gameOver = false;
    String player = "Player";
    String ai = "AI";
    int playerScore = 0;
    int aiScore = 0;

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
                            buttons[i].setText("X");
                            turn = 1;
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
        if (turn == 1 && !gameOver) {
            int index = random.nextInt(9);
            while (buttons[index].getText().equals("X") || buttons[index].getText().equals("0")) {
                index = random.nextInt(9);
            }
            buttons[index].setText("0");
            turn = 0;
            textfield.setText("Player's turn");
        }
    }

    public void aiTurn() {
        int index = -1;
        int[] possibleMoves = new int[9];

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                possibleMoves[index++] = i;
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
                buttons[currentMove].setText("0");
                if (checkForEnd("0")) {
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
    }

    public boolean checkForEnd(String player) {
        boolean result = false;

        return result;
    }

    public static void main(String[] args) {
        new main();
    }

}