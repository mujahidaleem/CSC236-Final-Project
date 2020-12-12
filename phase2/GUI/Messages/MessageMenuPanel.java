package GUI;

import Controllers.MessageMenu.UserFriendListController;
import Entities.Users.User;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;
import UseCases.Message.UserFriendManager;
import UseCases.Users.UserManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageMenuPanel extends GUIPanel {
    private JTextArea myFriendList;

    private JTextField userIDField;


    private JButton addFriend;
    private JButton deleteFriend;
    private JButton startChat;
    private JButton returnToMainMenu;

    private JLabel friendListLabel;
    private JLabel friendIDLabel;

    private final UserFriendListController userFriendListController;
    private final UserFriendManager userFriendManager;
    private final UserManager userManager;

    public MessageMenuPanel(UserFriendListController userFriendListController, UserFriendManager userFriendManager, UserManager userManager, JFrame frame, LanguageManager languageManager){
        super(frame, languageManager);
        this.panel = new JPanel();
        this.userFriendListController = userFriendListController;
        this.userFriendManager = userFriendManager;
        this.userManager = userManager;
    }

    public void setUpMenu(){
        createButtons();
        createLabels();
        createFriendListArea();
    }

    public void createButtons(){
        addFriend = new JButton();
        addFriend.setBounds(500,10,100,50);
        addFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFriendManager.addNewFriend(userManager.findUser(Integer.parseInt(userIDField.getText())));
            }
        });
        this.panel.add(addFriend);

        deleteFriend = new JButton();
        deleteFriend.setBounds(570, 10, 100, 50);
        deleteFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int targetUserID = Integer.parseInt(userIDField.getText());
                User targetUser = userManager.findUser(targetUserID);
                userFriendListController.removeFrom(targetUser);
            }
        });
        this.panel.add(deleteFriend);

        startChat = new JButton();
        startChat.setBounds(670, 10, 100, 50);
        startChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        this.panel.add(startChat);

        returnToMainMenu = new JButton();
        returnToMainMenu.setBounds(770, 10, 100, 50);
        startChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFriendListController.returnToMainMenu();
            }
        });
        this.panel.add(returnToMainMenu);
    }

    public void createLabels(){
        friendListLabel = new JLabel();
        friendListLabel.setBounds(30, 10, 100, 20);
        this.panel.add(friendListLabel);

        friendIDLabel = new JLabel();
        friendIDLabel.setBounds(800, 10, 100, 20);
        this.panel.add(friendIDLabel);
    }

    public void createFriendListArea(){
        myFriendList = new JTextArea();
        myFriendList.setBounds(30, 50, 100, 400);
        this.panel.add(myFriendList);
    }

    public void printFriendList(){

    }
    public void setText(LanguagePack languagePack){
        addFriend.setText(languagePack.messageMenuButtons()[0]);
        deleteFriend.setText(languagePack.messageMenuButtons()[1]);
        startChat.setText(languagePack.messageMenuButtons()[2]);
        returnToMainMenu.setText(languagePack.messageMenuButtons()[3]);

        friendListLabel.setText(languagePack.messageMenuLabels()[0]);

        myFriendList.setText("");
    }
}


