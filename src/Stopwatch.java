
// awt imports
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// swing imports
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// GUI class
public class Stopwatch implements ActionListener {
    // varibles
    JButton start = new JButton("Start");
    JButton reset = new JButton("reset");
    int time = 0;
    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    String hours_String = String.format("%02d", hours);
    String minutes_String = String.format("%02d", minutes);
    String seconds_String = String.format("%02d", seconds);
    boolean started = false;
    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    Timer timer = new Timer(1000, new ActionListener() {
        // sets values for each time interval
        public void actionPerformed(ActionEvent e) {
            time = time + 1000;
            hours = (time / 3600000);
            minutes = (time / 60000) % 60;
            seconds = (time / 1000) % 60;
            hours_String = String.format("%02d", hours);
            minutes_String = String.format("%02d", minutes);
            seconds_String = String.format("%02d", seconds);
            label.setText(hours + ":" + minutes + ":" + seconds);

        };
    });

    // public Stopwatch class
    public Stopwatch() {

        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel(hours_String + ":" + minutes_String + ":" + seconds_String);
        label.setBounds(100, 100, 200, 100);
        label.setHorizontalAlignment(JLabel.CENTER);
        start.setBounds(100, 200, 100, 50);
        start.setFocusable(false);
        reset.setBounds(100, 200, 100, 50);
        reset.setFocusable(false);
        // adding event listeners to start and reset buttons
        start.addActionListener(this);
        reset.addActionListener(this);
        panel.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
        panel.setLayout(new GridLayout(3, 0));
        // add label/buttons to panel
        panel.add(label);
        panel.add(start);
        panel.add(reset);
        // add panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window options
        frame.setTitle("Stopwatch");
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            start();
            if (started == false) {
                started = true;
                start.setText("Stop");
            } else {
                started = false;
                start.setText("Start");
                stop();
            }
        }
        if (e.getSource() == reset) {
            started = false;
            start.setText("Start");
            reset();
        }
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        time = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        hours_String = String.format("%02d", hours);
        minutes_String = String.format("%02d", minutes);
        seconds_String = String.format("%02d", seconds);
        label.setText(hours + ":" + minutes + ":" + seconds);
    }

};
