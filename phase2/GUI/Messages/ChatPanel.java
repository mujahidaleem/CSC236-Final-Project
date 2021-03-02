package GUI.Messages;

import Controllers.MessageMenu.UserFriendListController;
import Entities.Users.User;
import GUI.GUIPanel;
import Presenters.MessageMenu.MessageMenuPresenter;
import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;
import UseCases.Message.UserFriendManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ChatPanel extends GUIPanel {
    private JTextArea chatlogArea;
    private JTextField messageBox;
    private JButton send;
    private JButton returnToMessageMenu;
    private MessageMenuPresenter messageMenuPresenter;
    private UserFriendListController userFriendListController;
    private User targetUser;

    private LanguageManager languageManager;

    public ChatPanel(JFrame frame,
                     UserFriendListController userFriendListController,
                     LanguageManager languageManager,
                     MessageMenuPresenter messageMenuPresenter){
        super(frame, languageManager);
        this.languageManager = languageManager;
        this.userFriendListController = userFriendListController;
        this.messageMenuPresenter = messageMenuPresenter;
    }

    public void initializePanel(User targetUser){
        this.targetUser = targetUser;

        chatlogArea = new JTextArea();
        chatlogArea.setBounds(10, 10, 500, 500);
        this.panel.add(chatlogArea);

        messageBox = new JTextField();
        messageBox.setBounds(10, 520, 400, 50);
        this.panel.add(messageBox);

        send = new JButton();
        send.setBounds(450, 520, 80, 50);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sendText = messageBox.getText();
                userFriendListController.sendingMessage(userFriendListController.getCurrentUser(), targetUser, sendText, LocalDateTime.now());
            }
        });
        this.panel.add(send);

        returnToMessageMenu = new JButton();
        returnToMessageMenu.setBounds(550, 520, 80, 50);
        this.panel.add(send);
    }


    public void setText(LanguagePack languagePack){
        send.setText(languagePack.chatPanelButtons()[0]);
        returnToMessageMenu.setText(languagePack.chatPanelButtons()[1]);

        chatlogArea.setText(userFriendListController.userFriendManager.displayChatLog(userFriendListController.getCurrentUser(), targetUser));
    }
}
