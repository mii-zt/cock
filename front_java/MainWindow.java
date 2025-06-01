import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JLabel resultLabel1;
    
    public MainWindow() {
        setTitle("おうちシェフ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        setLocationRelativeTo(null);
        
        // レイアウト設定
        setLayout(new BorderLayout());
        
        // タイトルラベル
        JLabel titleLabel = new JLabel("本日の献立は？", JLabel.CENTER);
        titleLabel.setFont(new Font("MS Gothic", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        
        // お勧め表示エリア
        resultLabel1 = new JLabel("");
        resultLabel1.setFont(new Font("MS Gothic", Font.PLAIN, 14));
        resultLabel1.setBorder(BorderFactory.createTitledBorder("献立"));
        add(resultLabel1);
        
        // ボタンパネル
        JPanel buttonPanel = new JPanel();
        JButton openWindowButton = new JButton("入力ウィンドウを開く");
        openWindowButton.setFont(new Font("MS Gothic", Font.PLAIN, 14));
        
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
    public void updateResult1(String result) {
        
        resultLabel1.setText(result);
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