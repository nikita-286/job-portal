import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ClientInfoPage {
    private JFrame frame;
    private JTextField companyNameTextField;
    private JTable candidateTable;
    private DefaultTableModel tableModel;

    public ClientInfoPage() {
        frame = new JFrame("Client Info Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create a panel to hold the components
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create a JLabel and JTextField for the company name
        JLabel companyLabel = new JLabel("Company Name:");
        companyNameTextField = new JTextField(20);

        // Create a button to show applied candidates
        JButton showCandidatesButton = new JButton("Show Applied Candidates");

        // Create a table to display candidates
        tableModel = new DefaultTableModel();
        candidateTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(candidateTable);

        // Add components to the panel
        panel.add(companyLabel);
        panel.add(companyNameTextField);
        panel.add(showCandidatesButton);

        // Add the panel and table to the frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        // Define the columns for the table
        tableModel.addColumn("Email");
        tableModel.addColumn("Full Name");
        tableModel.addColumn("Job Field");
        tableModel.addColumn("Salary Range");
        tableModel.addColumn("Location");
        tableModel.addColumn("Job Type");

        // Create a panel for the back button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                JobPost jobPostPage = new JobPost(); // Create an instance of the JobPost class
            }
        });

        frame.add(bottomPanel, BorderLayout.SOUTH);

        showCandidatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String companyName = companyNameTextField.getText();
                showAppliedCandidates(companyName);
            }
        });

        frame.setVisible(true);
    }

    private void showAppliedCandidates(String companyName) {
        tableModel.setRowCount(0); // Clear the table before adding new data

        // Create a SQL connection and query the applied candidates
        try {
            Connection connection = DriverManager.getConnection("****", "****", "****");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM applied_candidates WHERE Company = ?");
            statement.setString(1, companyName);
            ResultSet resultSet = statement.executeQuery();

            // Add data to the table
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String fullName = resultSet.getString("full_name");
                String jobField = resultSet.getString("job_field");
                String salaryRange = resultSet.getString("salary_range");
                String location = resultSet.getString("location");
                String jobType = resultSet.getString("job_type");

                tableModel.addRow(new Object[]{email, fullName, jobField, salaryRange, location, jobType});
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClientInfoPage clientInfoPage = new ClientInfoPage();
            }
        });
    }
}
