package GUI.Main;

import Controller.LoginSystem;
import GUI.PanelBuilder.LoginPanelBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI implements ActionListener {
    private PanelStack panelStack;
    private LoginSystem loginSystem;
    private JPanel loginPanel = new JPanel();
    private JLabel titleLabel = new JLabel("login");
    private JLabel usernameJLabel = new JLabel("username");
    private JLabel passwordJLabel = new JLabel("password");
    private JLabel programTitleJLabel = new JLabel("the imposter summit");
    private JTextField userTextField = new JTextField(20);
    private JPasswordField passwordTextField = new JPasswordField(20);
    private JButton logInButton = new JButton("login");
    private JButton backButton = new JButton("back");
    private MainMenuGUI mainMenuGUI;
    private LoginPanelBuilder panelBuilder = new LoginPanelBuilder(loginPanel);


    public LoginGUI(MainMenuGUI menu, LoginSystem loginSystem, PanelStack panelStack) {
        this.panelStack = panelStack;
        this.loginSystem = loginSystem;
        this.mainMenuGUI = menu;
        backButtonListen();
        logInButton.addActionListener(this);
    }

    public boolean getIsVisible() {
        return loginPanel.isVisible();
    }

    public JPanel logInPage(){
        // PANEL:
        panelBuilder.buildMainPanel();
        // PROGRAM TITLE:
        panelBuilder.buildPanelLabel(programTitleJLabel, 32,  65, 10, 500, 60);
        // LOGIN TITLE:
        panelBuilder.buildPanelLabel(titleLabel, 20, 229, 164, 80, 30);
        // USERNAME:
        panelBuilder.buildComponent(usernameJLabel, 123, 214, 80, 25);
        panelBuilder.buildComponent(userTextField,193, 214, 165, 25);
        // PASSWORD:
        panelBuilder.buildComponent(passwordJLabel, 123, 264, 80, 25);
        panelBuilder.buildComponent(passwordTextField,193, 264, 165, 25);
        // LOGIN BUTTON:
        panelBuilder.buildButton(logInButton, 214, 344, 80, 25);
        // BACK BUTTON:
        panelBuilder.buildButton(backButton, 10, 410, 80, 25);
        usernameJLabel.setFont(panelBuilder.getInfoFont());
        passwordJLabel.setFont(panelBuilder.getInfoFont());
        userTextField.setFont(panelBuilder.getInfoFont());
        passwordTextField.setFont(panelBuilder.getInfoFont());
        return loginPanel;
    }

    private void backButtonListen(){
        backButton.addActionListener(e -> {
            panelStack.pop();
            JPanel panel = (JPanel) panelStack.pop();
            panelStack.loadPanel(panel);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String uname = userTextField.getText();
        String pword = passwordTextField.getText();

        if (loginSystem.canLogin(uname, pword)) {
            JLabel label = new JLabel("You have successfully logged in!");
            panelBuilder.buildPanelLabel(label,14,0,0,0, 0);
            JOptionPane.showMessageDialog(null, label,
                    "Nice!",
                    JOptionPane.INFORMATION_MESSAGE);
            userTextField.setText("");
            passwordTextField.setText("");
            panelStack.loadPanel(mainMenuGUI.startMainMenuPage());
        }
        else {
            JLabel label = new JLabel("Invalid username or password.");
            panelBuilder.buildPanelLabel(label,14,0,0,0, 0);
            JOptionPane.showMessageDialog(null, label,
                    "Oops...",
                    JOptionPane.WARNING_MESSAGE);
        }

    }
}
