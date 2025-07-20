import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Company {

    JFrame frame = new JFrame("Company details");

    public Company() {


        JLabel e = new JLabel("Employer Name");
        e.setBounds(800, 80, 100, 20);
        e.setForeground(new Color(0x000000));

        JTextField etf = new JTextField();
        etf.setBounds(800, 110, 257, 30);

        JLabel in = new JLabel("Industry");
        in.setBounds(250, 150, 100, 20);
        in.setForeground(new Color(0x000000));

        JTextField itf = new JTextField();
        itf.setBounds(250, 180, 257, 30);

        JLabel ad = new JLabel("Address");
        ad.setBounds(800, 150, 200, 20);
        ad.setForeground(new Color(0x000000));

        JTextField add = new JTextField();
        add.setBounds(800, 180, 257, 30);

        JLabel pin = new JLabel("Pincode");
        pin.setBounds(250, 220, 100, 20);
        pin.setForeground(new Color(0x000000));

        JTextField pnt = new JTextField();
      pnt.setBounds(250, 250, 257, 30);

        JLabel pho = new JLabel("Phone No");
        pho.setBounds(800, 220, 150, 20);
        pho.setForeground(new Color(0x000000));

        JTextField pn = new JTextField();
        pn.setBounds(800, 250, 257, 30);

        JLabel jobDescriptionLabel = new JLabel("PROFILE");
        jobDescriptionLabel.setBounds(595, 300, 600, 20);
        jobDescriptionLabel.setForeground(new Color(0x000000));

        // Create a JTextArea for job description
        JTextArea jobDescriptionTextArea = new JTextArea();
        jobDescriptionTextArea.setBounds(500, 330, 257, 257);

        //back button
        JButton back = new JButton("BACK");
        back.setBounds(20, 20, 100, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    frame.dispose();
                    new ChooseBetween();
                }
            }
        });
        //save details button
        JButton save = new JButton("SAVE");
        save.setBounds(580, 600, 100, 40);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == save) {
                    //String emp_id = em.getText();
                    String industry = itf.getText();
                    String pincode = pnt.getText();
                    String emp_name = etf.getText();
                    String address = add.getText();
                    String phone_no = pn.getText();
                    String profile = jobDescriptionTextArea.getText();

// Connect to the database and insert user data
                    try {
                        Connection connection = DriverManager.getConnection("****l", "****", "****");
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO recruiter_details (industry,pincode,emp_name,address,c_phone_no,profile) VALUES ( ?, ?, ?, ?, ?, ?)");
                        statement.setString(1, industry);
                        statement.setString(2, pincode);
                        statement.setString(3, emp_name);
                        statement.setString(4, address);
                        statement.setString(5, phone_no);
                        statement.setString(6, profile);

                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted >0) {
                            // data saved
                            JOptionPane.showMessageDialog(null, "Data saved");
                            new JobPost();
                            frame.dispose();

                        } else {
                            // failed
                            JOptionPane.showMessageDialog(null, "Data not saved");


                        }

                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();

                    }


                }
            }
        });
        JButton postJobButton = new JButton("POST A JOB");
        postJobButton.setBounds(1060, 26, 115, 40);
        postJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new JobPost();
            }
        });

        frame.add(postJobButton);
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\nitin\\Downloads\\logo-removebg-preview.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(55,75,150,100);
        frame.add(imageLabel);

        JLabel jm1 = new JLabel("RECRUITER DETAILS");

        jm1.setBounds(500, 25, 300, 70);
        jm1.setForeground(new Color(0xFF000000, true));
        jm1.setFont(new Font("SansSerif Bold", Font.BOLD, 24));
        frame.add(jobDescriptionLabel);
        frame.add(jobDescriptionTextArea);

        frame.add(jm1);
        frame.add(e);
        frame.add(etf);
        frame.add(pn);
        frame.add(pho);
        frame.add(pin);
        frame.add(pnt);
        frame.add(add);
        frame.add(ad);
        frame.add(in);
        frame.add(itf);
        frame.add(back);
        frame.add(save);
        frame.getContentPane().setBackground(new Color(137, 198, 210)); // Replace with your RGB values

        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Company());
    }
}
