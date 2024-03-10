import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RankingPage extends JFrame {
    private JList<String> jobSeekerList;
    private JList<String> jobPostingList;

    public RankingPage() {
        // Create and configure the frame
        setTitle("Rankings");
        setSize(900, 600); // Increased frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the job seeker and job posting lists
        jobSeekerList = new JList<>();
        jobPostingList = new JList<>();

        // Create labels for the lists
        JLabel jobSeekerLabel = new JLabel("Ranked Job Seekers");
        JLabel jobPostingLabel = new JLabel("Ranked Job Postings");

        // Add the components to the frame
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(jobSeekerLabel, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(jobSeekerList), BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(jobPostingLabel, BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(jobPostingList), BorderLayout.CENTER);

        // Divide the panels equally in the center of the page
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel, BorderLayout.CENTER);

        // Fetch data from the database and populate the lists
        fetchAndPopulateData();

        // Create a small "Back" button in the lower corner of the page
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ChooseBetween();
            }
        });

        // Create a panel for the "Back" button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.add(backButton);

        // Add the "Back" button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
    }

    private void fetchAndPopulateData() {
        try {
            // Establish a database connection (replace with your database details)
            String jdbcURL = "jdbc:mysql://localhost:3306/job_portal";
            String username = "root";
            String password = "Root@1234";
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            // Fetch ranked job seekers from the database (replace with your SQL query)
            String jobSeekerQuery = "SELECT name FROM job_seekers ORDER BY ranks ASC";
            PreparedStatement jobSeekerStatement = connection.prepareStatement(jobSeekerQuery);
            ResultSet jobSeekerResultSet = jobSeekerStatement.executeQuery();
            List<String> jobSeekers = new ArrayList<>();
            while (jobSeekerResultSet.next()) {
                jobSeekers.add(jobSeekerResultSet.getString("name"));
            }
            jobSeekerList.setListData(jobSeekers.toArray(new String[0]));

            // Fetch ranked job postings from the database (replace with your SQL query)
            String jobPostingQuery = "SELECT title FROM job_postings ORDER BY ranks ASC";
            PreparedStatement jobPostingStatement = connection.prepareStatement(jobPostingQuery);
            ResultSet jobPostingResultSet = jobPostingStatement.executeQuery();
            List<String> jobPostings = new ArrayList<>();
            while (jobPostingResultSet.next()) {
                jobPostings.add(jobPostingResultSet.getString("title"));
            }
            jobPostingList.setListData(jobPostings.toArray(new String[0]));

            // Close the resources
            jobSeekerResultSet.close();
            jobSeekerStatement.close();
            jobPostingResultSet.close();
            jobPostingStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RankingPage());
    }
}