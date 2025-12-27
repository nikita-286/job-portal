
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main implements ActionListener {
    JFrame f = new JFrame();

    Main() {

        //title
        JLabel signIn = new JLabel("SIGN IN");
        signIn.setBounds(580, 117, 300, 290);
        signIn.setForeground(new Color(0x000000));
        Font f1 = new Font(Font.DIALOG, Font.BOLD, 20);
        signIn.setFont(f1);

        //email id
        JLabel email = new JLabel("Email address");
        email.setBounds(500, 310, 100, 20);
        email.setForeground(new Color(0x000000));

        JTextField emailTF = new JTextField();
        emailTF.setBounds(500, 340, 257, 30);

        //password
        JLabel password = new JLabel("Password");
        password.setBounds(500, 355, 80, 67);
        password.setForeground(new Color(0x000000));

        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(500, 405, 257, 30);

        //register now
        JLabel noAccount = new JLabel("Don't have an account? Register now");
        noAccount.setBounds(520, 455, 220, 150);
        noAccount.setForeground(new Color(0x151313));

        //insert image
        ImageIcon image = new ImageIcon("");


        //Buttons
        JButton register = new JButton("REGISTER");
        register.setBounds(570, 566, 115, 40);
        register.addActionListener(this);

        JButton login1 = new JButton("LOGIN");
        login1.setBounds(500, 455, 257, 40);
        login1.addActionListener(this);


        //To close prev window
        login1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                String email = emailTF.getText();
                String passwordChars = passwordTF.getText();
                String password = new String(passwordChars);

                /* Check if the email contains "@" only
                if (!email.equals("@.com")) {
                    JOptionPane.showMessageDialog(null, "Invalid email address");
                    return; // Do not proceed further
                }*/
                // Connect to the database and verify credentials
                try {
                    Connection connection = DriverManager.getConnection("****", "****", "****");
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM userss WHERE email = ?");
                    statement.setString(1, email);
                    ResultSet result = statement.executeQuery();

                    if (result.next()) {
                        String storedPassword = result.getString("password");
                        if (password.equals(storedPassword)) {
                            // Authentication successful
                            JOptionPane.showMessageDialog(null, "Login successful");
                            f.dispose();
                            new ChooseBetween();
                        } else {
                            // Invalid password
                            JOptionPane.showMessageDialog(null, "Invalid password");
                        }
                    } else {

                        // User not found
                        JOptionPane.showMessageDialog(null, "User not found");
                    }

                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                f.dispose();
                new SecondFrame();
            }
        });

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user1\\Downloads\\logo-removebg-preview.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(55,75,150,100);
        f.add(imageLabel);
        f.getContentPane().setBackground(new Color(137, 198, 210)); // Replace with your RGB values



        f.add(email);
        f.add(password);
        f.add(signIn);
        f.add(emailTF);
        f.add(passwordTF);
        f.add(register);
        f.add(login1);
        f.add(noAccount);
        f.add(new JLabel(image));
        f.pack();
        //f.add(amountField);

        f.setSize(1000, 1000);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
// TODO Auto-generated method stub

        JFrame f = new JFrame();
        Main s = new Main();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


 }

    class SecondFrame implements ActionListener {
        JFrame frame2 = new JFrame("Register page");

        public SecondFrame() {
            //title
            JLabel registerNew = new JLabel("REGISTER");
            registerNew.setBounds(600, 70, 300, 290);
            registerNew.setForeground(new Color(0x000000));
            Font f1 = new Font(Font.DIALOG, Font.BOLD, 20);
            registerNew.setFont(f1);


            //first name
            JLabel firstName = new JLabel("First Name");
            firstName.setBounds(535, 250, 100, 20);

            JTextField firstNameTF = new JTextField();
            firstNameTF.setBounds(535, 280, 257, 30);

            //last name
            JLabel lastName = new JLabel("Last Name");
            lastName.setBounds(535, 320, 100, 20);

            JTextField lastNameTF = new JTextField();
            lastNameTF.setBounds(535, 350, 257, 30);

            //email id
            JLabel email = new JLabel("Email address");
            email.setBounds(535, 390, 100, 20);

            JTextField emailTF = new JTextField();
            emailTF.setBounds(535, 417, 257, 30);

            //password
            JLabel password = new JLabel("Password");
            password.setBounds(535, 453, 100, 20);

            JPasswordField passwordTF = new JPasswordField();
            passwordTF.setBounds(535, 480, 257, 30);


            ImageIcon imageIcon = new ImageIcon("C:\\Users\\user1\\Downloads\\logo-removebg-preview.png");
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(55,75,150,100);
            frame2.add(imageLabel);

            //login button
            JButton loginNew = new JButton("LOGIN");
            loginNew.setBounds(535, 535, 257, 40);
            loginNew.addActionListener(this);

            loginNew.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {

                    String f_name = firstNameTF.getText();
                    String l_name = lastNameTF.getText();
                    String email = emailTF.getText();
                    String passwordChars = passwordTF.getText();
                    String password = new String(passwordChars);

                    // Check if any of the fields are empty
                    if (f_name.isEmpty() || l_name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields");
                        return; // Do not proceed further
                    }

                    // Check if the email is correctly formatted
                    if (!isValidEmail(email)) {
                        JOptionPane.showMessageDialog(null, "Invalid email address");
                        return; // Do not proceed further
                    }


                    try {
                        Connection connection = DriverManager.getConnection("****", "****", "****");
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO userss(f_name,l_name,email,password) VALUES (?, ?, ?, ?)");
                        statement.setString(1, f_name);
                        statement.setString(2, l_name);
                        statement.setString(3, email);
                        statement.setString(4, password);
                        int rowsInserted = statement.executeUpdate();

                        if (rowsInserted >0) {
                            // Registration successful
                            JOptionPane.showMessageDialog(null, "Registration successful");
                            frame2.dispose();
                          // new ChooseBetween();

                        } else {
                            // Registration failed
                            JOptionPane.showMessageDialog(null, "Registration failed");

                        }

                        //connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            frame2.add(firstName);
            frame2.add(lastName);
            frame2.add(registerNew);
            frame2.add(firstNameTF);
            frame2.add(lastNameTF);
            frame2.add(loginNew);
            frame2.add(email);
            frame2.add(password);
            frame2.add(emailTF);
            frame2.add(passwordTF);

            frame2.getContentPane().setBackground(new Color(137, 198, 210)); 


            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(null);
            frame2.setSize(1000, 1000);
            frame2.setVisible(true);

        }

        private boolean isValidEmail(String email) {
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return email.matches(regex);
        }


        @Override
        public void actionPerformed(ActionEvent e) {

            frame2.dispose();
            new ChooseBetween();
        }
    }
