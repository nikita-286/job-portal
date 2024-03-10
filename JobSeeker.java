import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JobSeeker {
    JFrame frame = new JFrame("Job Seeker details");

    public JobSeeker() {

        JLabel e = new JLabel("Name");
        e.setBounds(800, 80, 100, 20);
        e.setForeground(new Color(0x000000));

        JTextField etf = new JTextField();
        etf.setBounds(800, 110, 257, 30);

        JLabel in = new JLabel("Phone No");
        in.setBounds(250, 150, 100, 20);
        in.setForeground(new Color(0x000000));

        JTextField itf = new JTextField();
        itf.setBounds(250, 180, 257, 30);

        JLabel ad = new JLabel("Address");
        ad.setBounds(800, 150, 200, 20);
        ad.setForeground(new Color(0x000000));

        JTextField add = new JTextField();
        add.setBounds(800, 180, 257, 30);

        JLabel pin = new JLabel("Work Experience");
        pin.setBounds(250, 220, 100, 20);
        pin.setForeground(new Color(0x000000));

        JTextField pnt = new JTextField();
        pnt.setBounds(250, 250, 257, 30);

        JLabel pho = new JLabel("Education");
        pho.setBounds(800, 220, 150, 20);
        pho.setForeground(new Color(0x000000));

        JTextField pn = new JTextField();
        pn.setBounds(800, 250, 257, 30);

        JLabel jobDescriptionLabel = new JLabel("Skills");
        jobDescriptionLabel.setBounds(635, 370, 200, 20);
        jobDescriptionLabel.setForeground(new Color(0x000000));

        JTextArea jobDescriptionTextArea = new JTextArea();
        jobDescriptionTextArea.setBounds(490, 400, 330, 170);

        JLabel cer = new JLabel("Certifications");
        cer.setBounds(250, 290, 100, 20);
        cer.setForeground(new Color(0x000000));

        JTextField ce = new JTextField();
        ce.setBounds(250, 330, 257, 30);

        JLabel sal = new JLabel("Salary Spectrum");
        sal.setBounds(800, 290, 150, 20);
        sal.setForeground(new Color(0x000000));

        JTextField sl = new JTextField();
        sl.setBounds(800, 330, 257, 30);

//back button
        JButton back = new JButton("BACK");
        back.setBounds(20, 20, 100, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    frame.dispose();
                    new UserProfile();
                }
            }
        });
        //save details button
        JButton save = new JButton("SAVE");
        save.setBounds(610, 590, 100, 40);
        save.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == save) {
                    //String js_id = em.getText();
                    String phone_no = itf.getText();
                    String skills = jobDescriptionTextArea.getText();
                    String certifications = ce.getText();
                    String name = etf.getText();
                    String address = add.getText();
                    String education = pn.getText();
                    String salary = sl.getText();
                    String work_exp = pnt.getText();

// Connect to the database and insert user data
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal", "root", "Root@1234");
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO jobseeker_details (js_phone_no,skills,certifications,js_name,address,education,salary,work_exp) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)");
                        //statement.setString(1, js_id);
                        statement.setString(1, phone_no);
                        statement.setString(2, skills);
                        statement.setString(3, certifications);
                        statement.setString(4, name);
                        statement.setString(5, address);
                        statement.setString(6, education);
                        statement.setString(7, salary);
                        statement.setString(8, work_exp);

                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            // data saved
                            JOptionPane.showMessageDialog(null, "Data saved");
                            frame.dispose();
                            new UserProfile();

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
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\nitin\\Downloads\\logo-removebg-preview.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(55,75,150,100);
        frame.add(imageLabel);

        frame.getContentPane().setBackground(new Color(137, 198, 210)); // Replace with your RGB values


        JLabel jm1 = new JLabel("JOB SEEKER DETAILS");
        jm1.setBounds(500, 25, 300, 70);
        jm1.setForeground(new Color(0xFF000000, true));
        jm1.setFont(new Font("SansSerif Bold", Font.BOLD, 24));


        frame.add(jobDescriptionLabel);
        frame.add(jobDescriptionTextArea);
        frame.add(cer);
        frame.add(ce);
        frame.add(sl);
        frame.add(sal);
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
        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JobSeeker());
    }


    }
