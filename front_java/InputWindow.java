import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow extends JDialog {
    private JTextField textField1;
    private JTextField textField2;
    private MainWindow parentWindow;
    
    public InputWindow(MainWindow parent) {
        super(parent, "入力ウィンドウ", true); // モーダルダイアログ
        this.parentWindow = parent;
        
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        initializeComponents();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());
        
        // タイトル
        JLabel titleLabel = new JLabel("入力してください", JLabel.CENTER);
        titleLabel.setFont(new Font("MS Gothic", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);
        
        // 入力パネル
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // 1つ目のテキストボックス
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("食べたいもの（食材）"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        textField1 = new JTextField(15);
        inputPanel.add(textField1, gbc);
        
        // 2つ目のテキストボックス
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("食べたくないもの（食材）"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        textField2 = new JTextField(15);
        inputPanel.add(textField2, gbc);
        
        add(inputPanel, BorderLayout.CENTER);
        
        // ボタンパネル
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton submitButton = new JButton("入力");
        JButton cancelButton = new JButton("キャンセル");
        
        // 送信ボタンのアクションリスナー
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });
        
        // キャンセルボタンのアクションリスナー
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Enterキーで送信
        getRootPane().setDefaultButton(submitButton);
    }
    
    private void processInput() {
        String likeString = textField1.getText().trim();
        String dislikeString = textField2.getText().trim();
        
        // 入力検証
        if (likeString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "食べたいものを入力してください。", "入力エラー", JOptionPane.WARNING_MESSAGE);
            textField1.requestFocus();
            return;
        }
        
        if (dislikeString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "食べたくないものを入力してください。", "入力エラー", JOptionPane.WARNING_MESSAGE);
            textField2.requestFocus();
            return;
        }

        
            
        // 成功メッセージを表示
        JOptionPane.showMessageDialog(this, "ありがとうございます。お勧めを表示します", "成功", JOptionPane.INFORMATION_MESSAGE);
            
        // ウィンドウを閉じる
        dispose();
        
    }
}
