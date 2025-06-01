import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JLabel resultLabel;
    
    public MainWindow() {
        setTitle("おうちクッキング");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        
        // レイアウト設定
        setLayout(new BorderLayout());
        
        // タイトルラベル
        JLabel titleLabel = new JLabel("GUI", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        
        // 結果表示エリア
        resultLabel = new JLabel("result:", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setBorder(BorderFactory.createTitledBorder("結果"));
        add(resultLabel, BorderLayout.CENTER);
        
        // ボタンパネル
        JPanel buttonPanel = new JPanel();
        JButton openWindowButton = new JButton("入力ウィンドウを開く");
        openWindowButton.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // ボタンのアクションリスナー
        openWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInputWindow();
            }
        });
        
        buttonPanel.add(openWindowButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void openInputWindow() {
        InputWindow inputWindow = new InputWindow(this);
        inputWindow.setVisible(true);
    }
    
    // 結果を更新するメソッド
    public void updateResult(String result) {
        resultLabel.setText(result);
    }
    
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        // Look and Feelを設定
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}