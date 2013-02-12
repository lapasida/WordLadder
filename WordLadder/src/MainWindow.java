import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/*
 * Class which is responsible for GUI.
 */

/**
 * @version 2.0
 * @author Stanislaw Jakub Klimaszewski
 */
public class MainWindow extends JFrame implements ActionListener {
    
    private JTextField startWord, endWord, word, stepsNumber;
    
    /**
     * Creates and sets up window.
     */
    public MainWindow(){
        this.setLocation(200,100);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Word Ladder 2.0");
	this.setVisible(true);
	this.setResizable(false);
	this.initialiseComponents();
	this.pack();
	this.setSize(200,180);
    }
    
    /**
     * Initialise two tabs.
     */
    private void initialiseComponents(){
        this.setLayout(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent firstTab = this.getGenerationTab();
        tabbedPane.addTab("Generation", firstTab);
        JComponent secondTab = this.getDiscoveryTab();
        tabbedPane.addTab("Discovery", secondTab);
        this.add(tabbedPane);
    }
    
    /**
     * Creates first tab - "Generation".
     * @return JPanel with all coponents
     */
    private JPanel getGenerationTab(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Start word:"));
        word = new JTextField(20);
        word.addActionListener(this);
        panel.add(word);
        panel.add(new JLabel("Number of steps:"));
        stepsNumber = new JTextField(20){
            @Override
            public void processKeyEvent(KeyEvent ev) {
                char c = ev.getKeyChar();
                try{
                    // Ignore all non-printable characters. Just check the printable ones.
                    if(c > 31 && c < 127){
                        Integer.parseInt(c + "");
                    }
                    super.processKeyEvent(ev);
                }
                catch(NumberFormatException e) {
                    // Do nothing. Character inputted is not a number, so ignore it.
                }
            }
        };
        stepsNumber.addActionListener(this);
        panel.add(stepsNumber);
        JButton generate = new JButton("Generate");
        generate.addActionListener(this);
        panel.add(generate);
        return panel;
    }
    
    /**
     * Creates second tab - "Discovery".
     * @return JPanel with all coponents
     */
    private JPanel getDiscoveryTab(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Start word:"));
        startWord = new JTextField(20);
        startWord.addActionListener(this);
        panel.add(startWord);
        panel.add(new JLabel("End word:"));
        endWord = new JTextField(20);
        endWord.addActionListener(this);
        panel.add(endWord);
        JButton discover = new JButton("Discover");
        discover.addActionListener(this);
        panel.add(discover);
        return panel;
    }
    
    /**
     * Method inherited from ActionListener.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Generate":
                JOptionPane.showMessageDialog(this, Model.generateLadder(word.getText(), stepsNumber.getText()));
                break;
            case "Discover":
                JOptionPane.showMessageDialog(this, Model.discoverShortestPath(startWord.getText(), endWord.getText()));
                break;
        }
    }
}