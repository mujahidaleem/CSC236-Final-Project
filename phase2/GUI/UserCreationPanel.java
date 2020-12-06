package GUI;

import Controllers.LoginMenuController;
import UseCases.Language.LanguageManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCreationPanel extends JPanel{
    private int width;
    private int height;
    private String[] types;

    private JLabel name;
    private JTextField newName;
    private JLabel passwordLabel;
    private JTextField passwordText;
    private JLabel introMessage;
    private JButton createNewUser;
    private JComboBox type;

    private LanguageManager languageManager;


    public UserCreationPanel(JFrame frame, LanguageManager languageManager){
        this.languageManager = languageManager;
        this.width = frame.getWidth();
        this.height = frame.getHeight();
        this.setLayout(null);
        this.types = new String[]{languageManager.languagePack.userCreationPrompt()[3], languageManager.languagePack.userCreationPrompt()[4]};

        createPanel();
    }

    public void createPanel(){
        introMessage = new JLabel();
        introMessage.setBounds(273,100,500,40);

        name = new JLabel();
        name.setBounds(273,155,80,25);

        newName = new JTextField(20);
        newName.setBounds(363,155,165,25);

        passwordLabel = new JLabel();
        passwordLabel.setBounds(273,185,80,25);

        passwordText = new JTextField(20);
        passwordText.setBounds(363,185,165,25);


        type = new JComboBox(types);

        add(introMessage);
        add(name);
        add(newName);
        add(passwordLabel);
        add(passwordText);
        add(type);

        setSize(width, height);
    }

    public void setCreateAccountButton(JPanel original, JPanel panel1, JFrame frame, LoginMenuController loginMenuController){
        createNewUser = new JButton();
        createNewUser.setBounds(450, 300, 200, 25);

        JTextField tf3 = new JTextField();
        tf3.setBounds(50,150,150,20);
        tf3.setEditable(false);

        createNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = newName.getText();
                String password = passwordText.getText();
                String[] accountType = {"attendee, organizer"};
                loginMenuController.signUp(name, password, accountType[type.getSelectedIndex()]);
                JOptionPane.showMessageDialog(original,languageManager.languagePack.userCreationResult(String.valueOf(loginMenuController.return_id())));
                frame.setContentPane(panel1);
                frame.repaint();
                frame.revalidate();
            }
        });
        add(createNewUser);
    }

    public void setText(){
        introMessage.setText(languageManager.languagePack.userCreationPrompt()[0]);
        name.setText(languageManager.languagePack.userCreationPrompt()[1]);
        passwordLabel.setText(languageManager.languagePack.userCreationPrompt()[2]);
        createNewUser.setText(languageManager.languagePack.userCreationPrompt()[5]);
        types = new String[]{languageManager.languagePack.userCreationPrompt()[3], languageManager.languagePack.userCreationPrompt()[4]};
    }
}
