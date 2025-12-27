import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserProfile implements ActionListener {
    // Map to store applied jobs and their application status
    private Map<String, Boolean> appliedJobs = new HashMap<>();
    // Frame for displaying search results
    private JFrame resultFrame;
    // Main frame for user profile
    private JFrame frame;
    // Text field for job title
    private JTextField titleTextField;
    // Combo boxes for filtering search
    private JComboBox<String> salaryComboBox;
    private JComboBox<String> locationComboBox;
    private JComboBox<String> jobTypeComboBox;
    // Text field for company name
    private JTextField CompanyTextField;
    // Text fields for full name and email
    private JTextField fullNameTextField;
    private JTextField emailTextField;
    // Button for initiating the search
    private JButton Searchbutton;

    // Constructor for creating the user profile interface
    public UserProfile() {
        frame = new JFrame("User Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLayout(null);

        //button
        JButton AboutUs = new JButton("JOB SEEKER PROFILE");
        AboutUs.setBounds(990, 30, 200, 37);
        AboutUs.addActionListener(this);
        frame.add(AboutUs);

        AboutUs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new JobSeeker();
            }
        });

        // Label and text field for job title
        JLabel titleLabel = new JLabel("Job Field:");
        titleLabel.setBounds(170, 380, 95, 35);
        titleTextField = new JTextField();
        titleTextField.setBounds(170, 420, 100, 35);
        frame.add(titleLabel);
        frame.add(titleTextField);

        // Label and combo box for salary range
        JLabel salaryLabel = new JLabel("Salary Range:");
        salaryLabel.setBounds(370, 380, 90, 35);
        String[] salaryRanges = {"3L-5L", "5L-8L", "8L-12L", "12L-17L", "17L-25L", "25L+"};
        salaryComboBox = new JComboBox<>(salaryRanges);
        salaryComboBox.setBounds(370, 420, 100, 35);
        frame.add(salaryLabel);
        frame.add(salaryComboBox);

        // Label and combo box for location
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(570, 380, 100, 35);
        String[] locations = {"Mumbai", "Delhi", "Bangalore", "Gujarat"};
        locationComboBox = new JComboBox<>(locations);
        locationComboBox.setBounds(570, 420, 100, 35);
        frame.add(locationLabel);
        frame.add(locationComboBox);

        // Label and combo box for job type
        JLabel jobTypeLabel = new JLabel("Job Type:");
        jobTypeLabel.setBounds(770, 380, 100, 35);
        String[] jobTypes = {"Full Time", "Part Time", "Internship"};
        jobTypeComboBox = new JComboBox<>(jobTypes);
        jobTypeComboBox.setBounds(770, 420, 100, 35);
        frame.add(jobTypeLabel);
        frame.add(jobTypeComboBox);

        // Label and text field for company name
        JLabel CompanyLabel = new JLabel("Company name:");
        CompanyLabel.setBounds(970, 380, 116, 35);
        CompanyTextField = new JTextField();
        CompanyTextField.setBounds(970, 420, 100, 37);
        frame.add(CompanyLabel);
        frame.add(CompanyTextField);

        // Label and text field for full name
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(170, 460, 95, 35);
        fullNameTextField = new JTextField();
        fullNameTextField.setBounds(170, 500, 100, 35);
        frame.add(fullNameLabel);
        frame.add(fullNameTextField);

        //back button
        JButton back = new JButton("BACK");
        back.setBounds(20, 20, 100, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==back){
                    frame.dispose();
                    new ChooseBetween();
                }
            }
        });
        frame.add(back);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user1\\Downloads\\logo-removebg-preview.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(55,75,150,100);
        frame.add(imageLabel);



// Label and text field for email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(370, 460, 90, 35);
        emailTextField = new JTextField();
        emailTextField.setBounds(370, 500, 100, 35);
        frame.add(emailLabel);
        frame.add(emailTextField);

        frame.getContentPane().setBackground(new Color(171, 215, 224)); // Replace with your RGB values


        // Button for initiating the job search
        Searchbutton = new JButton("SEARCH");
        Searchbutton.setBounds(570, 545, 100, 37);
        Searchbutton.addActionListener(this);
        frame.add(Searchbutton);

        frame.setVisible(true);
        // Create the "Back" button
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 620, 80, 35);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultFrame.dispose();  // Close the search result frame
                frame.setVisible(true);  // Show the user profile frame
            }
        });
        // Create the "Back" button
        backButton = new JButton("Back");
        backButton.setBounds(20, 620, 80, 35);
        resultFrame.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultFrame.dispose();  // Close the search result frame
                frame.setVisible(true);  // Show the user profile frame
            }
        });
        resultFrame = new JFrame("Search Results");
        frame.setVisible(true);
    }



    // Action performed when the search button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Searchbutton) {
            // Retrieve user-selected search criteria
            String selectedJobTitle = titleTextField.getText();
            String selectedSalary = (String) salaryComboBox.getSelectedItem();
            String selectedLocation = (String) locationComboBox.getSelectedItem();
            String selectedJobType = (String) jobTypeComboBox.getSelectedItem();
            String selectedCompany = CompanyTextField.getText();
            String fullName = fullNameTextField.getText();
            String email = emailTextField.getText();

            try {
                // Establish a database connection
                Connection connection = DriverManager.getConnection("****", "****", "****");
                PreparedStatement statement;

                // Check if all filter fields are empty, then fetch all jobs
                if (selectedJobTitle.isEmpty() && selectedSalary.isEmpty() && selectedLocation.isEmpty() && selectedJobType.isEmpty() && selectedCompany.isEmpty()) {
                    statement = connection.prepareStatement("SELECT * FROM search");
                } else {
                    // Otherwise, apply filters
                    statement = connection.prepareStatement("SELECT * FROM search WHERE job_field = ? and salary_range = ? and location = ? and job_type = ? or Company = ?");
                    statement.setString(1, selectedJobTitle);
                    statement.setString(2, selectedSalary);
                    statement.setString(3, selectedLocation);
                    statement.setString(4, selectedJobType);
                    statement.setString(5, selectedCompany);
                }

                // Execute the SQL query and retrieve the results
                ResultSet resultSet = statement.executeQuery();
                frame.dispose();

                // Create the result frame and table
                resultFrame = new JFrame("Search Results");
                resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                resultFrame.setSize(800, 600);
                resultFrame.setLayout(new BorderLayout());

                DefaultTableModel resultTableModel = new DefaultTableModel();
                resultTableModel.addColumn("Job Title");
                resultTableModel.addColumn("Salary Range");
                resultTableModel.addColumn("Location");
                resultTableModel.addColumn("Job Type");
                resultTableModel.addColumn("Company");
                resultTableModel.addColumn("Apply");

                JTable resultTable = new JTable(resultTableModel);

                // Add a custom renderer and editor for the "Apply" button column
                resultTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
                resultTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(resultTable, this));

                // Populate the table with the results
                while (resultSet.next()) {
                    String jobTitle = resultSet.getString("job_field");
                    String salaryRange = resultSet.getString("salary_range");
                    String location = resultSet.getString("location");
                    String jobType = resultSet.getString("job_type");
                    String company = resultSet.getString("Company");

                    resultTableModel.addRow(new Object[]{jobTitle, salaryRange, location, jobType, company, "Apply"});
                }

                JScrollPane scrollPane = new JScrollPane(resultTable);
                resultFrame.add(scrollPane, BorderLayout.CENTER);
                resultFrame.setVisible(true);

                // Close the database connection
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method to insert a job application into the database
    private void insertJobApplication(String jobTitle, String fullName, String email) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection("****", "****", "****");
            statement = connection.prepareStatement("INSERT INTO applied_candidates (email, full_name, job_field, salary_range, location, job_type, Company) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, email);
            statement.setString(2, fullName);
            statement.setString(3, jobTitle);
            statement.setString(4, (String) salaryComboBox.getSelectedItem());
            statement.setString(5, (String) locationComboBox.getSelectedItem());
            statement.setString(6, (String) jobTypeComboBox.getSelectedItem());
            statement.setString(7, CompanyTextField.getText());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserProfile userProfile = new UserProfile(); // Create an instance of UserProfile
            }
        });
    }

    // Custom renderer for the "Apply" button column
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Custom editor for the "Apply" button column
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor(JTable table, UserProfile userProfile) {
            super(new JCheckBox());
            button = new JButton("Apply");
            button.setOpaque(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    String jobTitle = (String) table.getValueAt(selectedRow, 0);

                    if (userProfile.appliedJobs.containsKey(jobTitle) && userProfile.appliedJobs.get(jobTitle)) {
                        JOptionPane.showMessageDialog(userProfile.resultFrame, "Already Applied", "Application Status", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        userProfile.appliedJobs.put(jobTitle, true);
                        userProfile.insertJobApplication(jobTitle, fullNameTextField.getText(), emailTextField.getText());
                        JOptionPane.showMessageDialog(userProfile.resultFrame, "Applied", "Application Status", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }
    }
}

