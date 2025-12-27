import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JobPost {

    JFrame frame = new JFrame("Job Posting");

    public JobPost() {
        JPanel contentPanel = new JPanel(null);
        contentPanel.setBackground(new Color(137, 198, 210));

        JLabel jp = new JLabel("POST A JOB");
        jp.setBounds(550, 25, 170, 40);
        jp.setForeground(new Color(0xFF000000, true));
        jp.setFont(new Font("SansSerif Bold", Font.BOLD, 24));

        //back button
        JButton back = new JButton("BACK");
        back.setBounds(20, 20, 100, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    frame.dispose();
                    new Company();
                }
            }
        });


        JLabel name = new JLabel("Job field");
        name.setBounds(250, 80, 100, 20);
        name.setForeground(new Color(0x000000));

        JTextField nametf = new JTextField();
        nametf.setBounds(250, 110, 257, 30);

        JLabel indt = new JLabel("salary range");
        indt.setBounds(800, 80, 100, 20);
        indt.setForeground(new Color(0x000000));

        JTextField indty = new JTextField();
        indty.setBounds(800, 110, 257, 30);

        JLabel jt = new JLabel("location");
        jt.setBounds(250, 150, 100, 20);
        jt.setForeground(new Color(0x000000));

        JTextField jty = new JTextField();
        jty.setBounds(250, 180, 257, 30);

        JLabel er = new JLabel("Job type");
        er.setBounds(800, 150, 200, 20);
        er.setForeground(new Color(0x000000));

        JTextField ert= new JTextField();
        ert.setBounds(800, 180, 257, 30);

        JLabel salary = new JLabel("Company");
        salary.setBounds(250,220, 100, 20);
        salary.setForeground(new Color(0x000000));

        JTextField salaryTF = new JTextField();
        salaryTF.setBounds(250, 250, 257, 30);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user1\\Downloads\\logo-removebg-preview.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(55,75,150,100);
        frame.add(imageLabel);

        // applied candidates Button
        JButton clientInfoButton = new JButton("APPLIED CANDIDATES");
        clientInfoButton.setBounds(1000, 20, 170, 40);
        clientInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == clientInfoButton) {
                    frame.dispose(); // Close the current page
                    new ClientInfoPage(); // Open the Client Info page
                }
            }
        });

//save details button
        JButton save = new JButton("SAVE");
        save.setBounds(587, 460, 100, 40);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == save) {

                    String job_field = nametf.getText();
                    String salary_range = indty.getText();
                    String location = jty.getText();
                    String job_type = ert.getText();
                    String Company = salaryTF.getText();


// Connect to the database and insert user data
                    try {
                        Connection connection = DriverManager.getConnection("****", "****", "****");
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO search (job_field ,salary_range, location ,job_type ,Company) VALUES ( ?, ?, ?, ?, ?)");

                        statement.setString(1, job_field);
                        statement.setString(2, salary_range);
                        statement.setString(3, location);
                        statement.setString(4, job_type);
                        statement.setString(5, Company);


                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted >0) {
                            // data saved
                            JOptionPane.showMessageDialog(null, "Data saved");
                            new UserProfile();
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

        // Add your components to the content panel
        contentPanel.add(jp);
        contentPanel.add(save);
        contentPanel.add(jty);
        contentPanel.add(jt);
        contentPanel.add(er);
        contentPanel.add(ert);
        contentPanel.add(salaryTF);
        contentPanel.add(salary);
        contentPanel.add(indty);
        contentPanel.add(indt);
        contentPanel.add(name);
        contentPanel.add(nametf);
        contentPanel.add(back);
        contentPanel.add(clientInfoButton);


        // Add your components to the content panel
        contentPanel.add(jp);

        // Set the preferred size of the content panel
        contentPanel.setPreferredSize(new Dimension(1000, 1000));

        // Create a JScrollPane and add the content panel to it
        JScrollPane scrollPane = new JScrollPane(contentPanel);

        // Add the scroll pane to the frame
        frame.getContentPane().add(scrollPane);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JobPost());
    }
}

