import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InputWindow extends JDialog {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3; // アレルギー食材の入力フィールド
    private MainWindow parentWindow;

    public InputWindow(MainWindow parent) {
        super(parent, "入力ウィンドウ", true); // モーダルダイアログ
        this.parentWindow = parent;

        setSize(400, 350);
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
        gbc.insets = new Insets(10, 10, 10, 10);

        // 1つ目のテキストボックス
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("持っている食材(カンマ区切りで入力)"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        textField1 = new JTextField(15);
        inputPanel.add(textField1, gbc);

        // 2つ目のテキストボックス
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("最近食べた料理（最大６品まで、カンマ区切りで入力)"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        textField2 = new JTextField(15);
        inputPanel.add(textField2, gbc);

        // アレルギー食材の入力フィールド
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("アレルギー（カンマ区切りで入力)"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        textField3 = new JTextField(15);
        inputPanel.add(textField3, gbc);
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
        String allergyString = textField3.getText().trim(); // アレルギー食材の入力

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

        // 結果を作成
        String[] likedIngredients = likeString.split(",");
        String[] dislikedIngredients = dislikeString.split(",");
        String[] allergy = allergyString.split(",");
        List<Recipe> recommendedList = Cockservice.recommend(likedIngredients, dislikedIngredients, allergy);
        if (recommendedList == null || recommendedList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "お勧めの料理が見つかりませんでした。", "結果なし", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("<html>");
        for (Recipe r : recommendedList) {
            resultBuilder.append(r.getName()).append(":");
            for (String ing : r.getIngredients()) {
                resultBuilder.append(ing).append("、");
            }
            resultBuilder.append("<br>");
        }
        resultBuilder.append("</html>");
        String result = resultBuilder.toString();
        // メインウィンドウに結果を送信
        parentWindow.updateResult(result);

        // 成功メッセージを表示
        JOptionPane.showMessageDialog(this, "ありがとうございます。お勧めを表示します", "成功", JOptionPane.INFORMATION_MESSAGE);

        // ウィンドウを閉じる
        dispose();

    }
}
