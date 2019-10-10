import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.util.stream.IntStream;

public class ResultWindow extends JDialog {
    private static String ans = "Answer";
    private JComboBox<String> count;
    private Vector<int[]> answers;
    private int index;

    ResultWindow(Vector<int[]> answers) throws HeadlessException {
        this.answers = answers;
        count = new JComboBox<>();
        IntStream.range(1, answers.size()+1).forEach(i -> count.addItem(ans + i));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Result");
        this.setBounds(400, 100, answers.get(0).length * 30 + 100, answers.get(0).length * 30 + 100);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.add(this.count, BorderLayout.NORTH);
        this.count.addItemListener(e -> {
            this.index = this.count.getSelectedIndex();
            this.repaint();
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 30, y = 60;
        for (int pos : answers.get(index)) {
            for (int i = 0; i < Main.N; i++) {
                if (i == pos) g.fillRect(x, y, 30, 30);
                else g.drawRect(x, y, 30, 30);
                x += 30;
            }
            x = 30;
            y += 30;
        }
    }
}
