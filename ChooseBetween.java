import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseBetween implements ActionListener {
    JFrame frame3 = new JFrame("Choose Between");

    public ChooseBetween() {
        //button
        JButton Company = new JButton("RECRUITERS");
        Company.setBounds(410,330,180,50);
        Company.addActionListener(this);

        JButton UserProfile = new JButton("JOB SEEKERS");
        UserProfile.setBounds(690, 330,180,50);
        UserProfile.addActionListener(this);

        JButton Ranking = new JButton("RANKINGS");
        Ranking.setBounds(548,450,180,50);
        Ranking.addActionListener(this);

        //back button
        JButton back = new JButton("BACK");
        back.setBounds(20, 20, 100, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==back){
                    frame3.dispose();
                    new Main();
                }
            }
        });
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\nitin\\Downloads\\logo-removebg-preview.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(55,75,150,100);
        frame3.add(imageLabel);
        frame3.add(back);

        frame3.getContentPane().setBackground(new Color(137, 198, 210)); // Replace with your RGB values

        //close previous window
        Company.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame3.dispose();
                new Company();
            }
        });

        UserProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame3.dispose();
                new UserProfile();
            }
        });

        Ranking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame3.dispose();
                new RankingPage();
            }
        });

        frame3.add(Company);
        frame3.add(Ranking);
        frame3.add(UserProfile);
        frame3.setSize(500, 500);
        frame3.setLayout(null);
        frame3.setVisible(true);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        Object Company = null;
        Object Ranking = null;
        Object UserProfile = null;
        if (e.getSource() == Company) {
            frame3.dispose();
            new Company();
        }

        else if(e.getSource() == UserProfile) {
            frame3.dispose();
            new UserProfile();
        }
        else if(e.getSource()==Ranking){
            frame3.dispose();
            new RankingPage();

        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ChooseBetween();
            }
        });
    }
}