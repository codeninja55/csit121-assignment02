import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainToolbar extends JPanel {

    private final JTextField loginUsername;
    private final JPasswordField loginPassword;
    private JButton loginButton;
    private JButton exitButton;

    public MainToolbar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        loginUsername = new JTextField("Username",30);
        loginPassword = new JPasswordField("Password",30);
        loginButton = new JButton("Login");

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new CloseListener());

        add(loginUsername);
        add(loginPassword);
        add(loginButton);
        add(exitButton);

    }

    private class CloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

}
