import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JLabel resultLabel1;
    private JLabel resultLabel2;
    
    public MainWindow() {
        setTitle("おうちシェフ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);

        // レイアウト設定
        setLayout(new BorderLayout());

        // タイトルラベル
        JLabel titleLabel = new JLabel("本日の献立は？", JLabel.CENTER);
        titleLabel.setFont(new Font("MS Gothic", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        
        // お勧め表示エリア
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); // 縦並び

        // 献立ラベル（resultLabel1）
        resultLabel1 = new JLabel();
        resultLabel1.setFont(new Font("MS Gothic", Font.PLAIN, 14));
        TitledBorder border1 = BorderFactory.createTitledBorder("食材");
        border1.setTitleJustification(TitledBorder.LEFT); // 左上にタイトル
        resultLabel1.setBorder(border1);
        resultLabel1.setAlignmentX(Component.LEFT_ALIGNMENT); // 左揃え
        resultPanel.add(resultLabel1);

         // 栄養情報ラベル（resultLabel2）
        resultLabel2 = new JLabel();
        resultLabel2.setFont(new Font("MS Gothic", Font.PLAIN, 14));
        TitledBorder border2 = BorderFactory.createTitledBorder("献立");
        border2.setTitleJustification(TitledBorder.LEFT); // 左上にタイトル
        resultLabel2.setBorder(border2);
        resultLabel2.setAlignmentX(Component.LEFT_ALIGNMENT); // 左揃え
        resultPanel.add(resultLabel2);

        // パネルを中央に追加
       add(resultPanel, BorderLayout.CENTER);
        
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
    public void updateResult(String result) {
        resultLabel1.setText(result);
    }

    public void updateResult(String[] resultParts) {
        if (resultParts == null || resultParts.length == 0) {
            resultLabel2.setText("結果がありません");
            return;
        }

        // 各要素を「<li>〜</li>」形式で表示する（または改行）
        StringBuilder sb = new StringBuilder("<html>");
        for (String part : resultParts) {
            sb.append(part).append("<br>");
        }
        sb.append("</html>");

        resultLabel2.setText(sb.toString());
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