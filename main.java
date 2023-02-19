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
                        }
                    }
                }
            });
        }
    }

    public static void main(String[] args) {

    }

}