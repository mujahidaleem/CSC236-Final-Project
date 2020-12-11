package GUI;

import UseCases.Language.LanguageManager;
import UseCases.Language.LanguagePack;
import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageMenuPanel extends GUIPanel{
        private JTextArea myFriendList;
        private JButton addFriend;
        private JButton deleteFriend;
        private JButton startChat;

        public MessageMenuPanel(JFrame frame, LanguageManager languageManager){
            super(frame, languageManager);
            this.panel = new JPanel();
        }

        public void setUpMenu(){
            createButtons();
        }

        public void createButtons(){
            addFriend = new JButton();
            addFriend.setBounds(500,10,100,50);
            addFriend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
            this.panel.add(addFriend);

            deleteFriend = new JButton();
            deleteFriend.setBounds(600, 10, 100, 50);
            deleteFriend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
            this.panel.add(deleteFriend);

            startChat = new JButton();
            startChat.setBounds(700, 10, 100, 50);
            startChat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
            this.panel.add(startChat);
        }

        public void createFriendListArea(){
            myFriendList = new JTextArea();
            myFriendList.setBounds(30, 50, 100, 400);

        }
}


