import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class MainFrame extends JFrame {
    private JTextField num;
    private JProgressBar progressBar;
    private Thread searching;

    MainFrame() throws HeadlessException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Main");
        this.setBounds(400, 100, 500, 500);
        this.setBackground(Color.WHITE);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        JButton button = new JButton("search");
        JButton result = new JButton("Result");
        result.setEnabled(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel explanation = new JLabel("Enter queen number: ");
        ActionListener listener = e -> {
            Thread startThread = new Thread(() -> {
                if (button.isEnabled()) {
                    result.setEnabled(false);
                    button.setEnabled(false);
                    this.progressBar.setValue(0);
                    Main.N = Integer.parseInt(num.getText());
                    this.searching = Main.start();
                    while (this.searching.isAlive()) {
                        progressBar.setValue((int) ((Main.count / Main.cases) * 100));
                    }
                    progressBar.setValue(100);
                    result.setEnabled(true);
                    button.setEnabled(true);
                }
            });
            startThread.start();
        };
        this.num = new JTextField();
        this.num.addActionListener(listener);
        button.addActionListener(listener);

        constraints.gridx = 0;
        constraints.weightx = 0.05;
        panel.add(explanation, constraints);
        constraints.gridx = 2;
        constraints.insets = new Insets(5, 0, 5, 0);
        panel.add(button, constraints);
        constraints.gridx = 1;
        constraints.weightx = 0.9;
        constraints.ipady = 7;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 0);
        panel.add(this.num, constraints);

        container.add(panel, BorderLayout.NORTH);

        this.progressBar = new JProgressBar();
        this.progressBar.setStringPainted(true);
        container.add(this.progressBar, BorderLayout.SOUTH);

        result.addActionListener(e -> Main.showResult());
        container.add(result, BorderLayout.CENTER);
    }
}
