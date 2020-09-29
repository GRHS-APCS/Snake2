import java.awt.EventQueue;
import javax.swing.JFrame;

public class RunSnake extends JFrame {

    public RunSnake() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new RunSnake();
            ex.setVisible(true);
        });
    }
}