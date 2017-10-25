package view;

import encryption.IEncryptSV;
import encryption.impl.EncryptSVImpl;

import javax.swing.*;
import java.awt.*;

public class EncryView {

    private JTextField srcPWDJTF, expPWDJTF;
    private JTextArea encryJTA, secPWDJTA;
    private IEncryptSV iEncryptSV;

    public EncryView() throws Exception {
        iEncryptSV = new EncryptSVImpl();
        JFrame jf = new JFrame();
        jf.setTitle("密码加解密工具");
        JTabbedPane tabbedPane = new JTabbedPane();
        final String[] tabName = {"加密", "解密"};
        tabbedPane.addTab(tabName[0], enCodeJPanel());
        tabbedPane.addTab(tabName[1], deCodeJPanel());
        jf.add(tabbedPane);
        jf.setBounds(300, 200, 400, 350);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }

    private JPanel enCodeJPanel() {
        JLabel srcPWDJL = new JLabel("原密码:");
        srcPWDJTF = new JTextField(25);
        JPanel northJP = new JPanel();
        northJP.add(srcPWDJL);
        northJP.add(srcPWDJTF);

        JPanel centerJP = new JPanel();
        JLabel encryJl = new JLabel("密文：");
        encryJTA = new JTextArea(10,40);
        JScrollPane jsp = new JScrollPane(encryJTA);
        centerJP.add(encryJl);
        centerJP.add(jsp);

        JButton jb = new JButton("确定");
        jb.addActionListener((e)->{
            try {
                String ciphertext = iEncryptSV.encrypt(srcPWDJTF.getText().trim());
                encryJTA.setText(ciphertext);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        JPanel enCodePanel = new JPanel();
        enCodePanel.setLayout(new BorderLayout());
        enCodePanel.add(northJP, BorderLayout.NORTH);
        enCodePanel.add(centerJP, BorderLayout.CENTER);
        enCodePanel.add(jb, BorderLayout.SOUTH);
        return enCodePanel;
    }

    private JPanel deCodeJPanel() {

        JPanel centerJP = new JPanel();
        JLabel secJl = new JLabel("密文：");
        secPWDJTA = new JTextArea(10,60);
        JScrollPane jsp = new JScrollPane(secPWDJTA);
        centerJP.add(secJl);
        centerJP.add(jsp);

        JLabel expPWDJL = new JLabel("解析出密码:");
        expPWDJTF = new JTextField(25);
        JPanel northJP = new JPanel();
        northJP.add(expPWDJL);
        northJP.add(expPWDJTF);

        JButton jb = new JButton("确定");
        jb.addActionListener((e)->{
            try {
                String expPwd = iEncryptSV.decrypt(secPWDJTA.getText().trim());
                expPWDJTF.setText(expPwd);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JPanel deCodePanel = new JPanel();
        deCodePanel.setLayout(new GridLayout(3,1));
        deCodePanel.add(centerJP);
        deCodePanel.add(northJP);
        deCodePanel.add(jb);
        return deCodePanel;

    }

    public static void main(String[] args) {
        try {
            new EncryView();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
