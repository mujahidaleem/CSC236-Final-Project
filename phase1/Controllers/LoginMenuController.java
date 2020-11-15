package Controllers;

import UseCases.LoginMenuManager;
import Presenters.LoginMenuPresenter;
import UseCases.EventManager;
import Entities.User;
import UseCases.UserManager;
import Gateways.UserReader;
import sun.font.TrueTypeFont;

//import database from gateway?


public class  LoginMenuController {
    // initialize login menu
    public UserManager userManager;
    public UserLoginManager userLoginManager;
    public UserLoginPresenter presenter;
    public AttendeeManager attendeeManager;
    public OrganizerManager organizerManager;
    public AttendeeMenuController attendeeMenuController;
    public OrganizerMenuController organizerMenuController;

    public LoginMenuController(UserManager UserManager;
            UserLoginManager userLoginManager, UserLoginPresenter userLoginPresenter,
                               AttendeeManager attendeeManager, OrganizerManager organizerManager,
                               AttendeeMenuController attendeeMenuController,
                               OrganizerMenuController organizerMenuController) {
        this.userManager = userManager;
        this.userLoginManager = userLoginManager;
        this.presenter = userLoginPresenter;
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.attendeeMenuController = attendeeMenuController;
        this.organizerMenuController = organizerMenuController;
    }

    /**
     * command should follow format of "id, password"
     */

    public String login(String command) {
        // read command to see if user exists
        int id = Integer.parseInt(command.split(" ")[0]);
        String password = command.substring(id.length() + 1);

        if (LoginMenuManager.password_matches_id( id, password)){
            present_menu(id);
        }
        else{
            String e = "incorrect";
            return e;
        }

        public void present_menu( int id){
            if (LoginMenuManager.type_of_user(id).equals("Attendee")) {
                //display attendee event presenter
            } else if (LoginMenuController.type_of_user(id).equals("Organizer")) {

            }
            //display organizer screen
            else if (LoginMenuManager.type_of_user(id).equals("Speaker")) {
                //display speaker screen;
            }



