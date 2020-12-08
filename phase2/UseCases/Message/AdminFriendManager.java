package UseCases.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminFriendManagerextends extends UserFriendManager  {
        /**
         * AdminFriendManager constructor
         *
         * @param userToMessages - a dictionary mapping users to their messages sent and received from friends
         */
        public AdminFriendManager(HashMap<ArrayList<User>, ArrayList<Message>> userToMessages, Admin admin) {
            super(userToMessages, attendee);
            this.currentAdmin=admin;
        }
    }

}
