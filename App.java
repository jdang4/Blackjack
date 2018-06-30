import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;


public class App extends JFrame {
    private JButton Hit;
    private JButton Stay;
    private JPanel panel;
    private JList UserHand;
    private JLabel CurrentCard;

    private static Hand dealer = new Hand();
    private static Hand user = new Hand();
    private static Deck deck = new Deck();
    private static int userScore;
    private static int dealerScore;
    public static boolean aceValueOne;

    public App() {

        deck.initialize();
        deck.shuffle();

        Card userCard1 = deck.draw();
        Card userCard2 = deck.draw();

        user.addCard(userCard1);
        user.addCard(userCard2);

        DefaultListModel list = new DefaultListModel();
        list.addElement(userCard1.getType() + ", " + userCard1.getValue());
        list.addElement(userCard2.getType() + ", " + userCard2.getValue());
        UserHand.setModel(list);


        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());

        boolean dealer_aceValueOne = false;

        userScore = user.score(aceValueOne);
        dealerScore = dealer.score(dealer_aceValueOne);

        CurrentCard.setText("Current User Score: " + String.valueOf(userScore));

        while (dealerScore < 17) {
            Card newCard = deck.draw();
            if (newCard.isAce()) {

                int eleven_diff_21 = 21 - (dealerScore + 11);
                int one_diff_21 = 21 - (dealerScore + 1);

                /* which ever one is the lowest will decide the value of the ace */
                // first need to make sure none have a negative value as the difference
                if (eleven_diff_21 >= 0 && one_diff_21 >= 0) {
                    if (eleven_diff_21 < one_diff_21) {
                        dealerScore += 11;
                    }

                    else if (eleven_diff_21 > one_diff_21) {
                        dealerScore += 1;
                    }
                }
            }

            else {
                if ((dealerScore + newCard.getActualScore(dealer_aceValueOne)) > 21) {
                    dealerScore += newCard.getActualScore(dealer_aceValueOne);
                    break;
                }
            }

        }


        Hit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Hit");
                Card newCard = deck.draw();
                user.addCard(newCard);
                list.addElement(newCard.getType() + ", " + newCard.getValue());
                UserHand.setModel(list);
                if (newCard.isAce()) {
                    int answer = JOptionPane.showConfirmDialog(null,
                            "Do you want to treat the ace as 11?", "Title", JOptionPane.YES_NO_OPTION);

                    if (answer == 0) {
                        userScore += 11;
                    }

                    else if (answer == 1) {
                        userScore += 1;
                    }
                }

                else {
                    userScore += newCard.getActualScore(aceValueOne);
                }

                if (userScore > 21) {
                    JOptionPane.showMessageDialog(null, "Sorry, you went above the limit of 21");
                    System.exit(0);
                }

                CurrentCard.setText("Current User Score: " + String.valueOf(userScore));
            }
        });

        Stay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // once the stay button is clicked, the user cannot use the hit button anymore
                Hit.setEnabled(false);

                if ((dealerScore > userScore) && (dealerScore <= 21)) {
                    JOptionPane.showMessageDialog(null, "Sorry, but the Dealer beat you");
                }

                else if ((userScore > dealerScore) && (userScore <= 21)) {
                    JOptionPane.showMessageDialog(null, "Congratulations!!! You won!");
                }

                else {
                    JOptionPane.showMessageDialog(null, "It was a tie");
                }

                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Blackjack");

        frame.setContentPane(new App().panel);
        frame.setSize(1000, 600);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        if (user.containsAce()) {
            int answer = JOptionPane.showConfirmDialog(null,
                    "You have an ace in your Do you want to treat the ace as 11?", "Title", JOptionPane.YES_NO_OPTION);

            if (answer == 0) {
                aceValueOne = false;
            }

            else if (answer == 1) {
                aceValueOne = true;
            }

        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
