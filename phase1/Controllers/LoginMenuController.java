package Controllers;

import UseCases.LoginMenuManager;
import Presenters.LoginMenuPresenter;
import UseCases.EventManager;
import Entities.User;
import UseCases.UserManager;
import Gateways.UserReader;

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

    public LoginMenuController(UserManager userManager;
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

    // show login screen to type username and password, ie. show or initialize presenter?

    public boolean username_exists(String username) {
        //if (username in database){return True;} else{return False;}
        users_list = userManager.getUsers();

    }

    public static boolean password_matches_username(String username, String password) {
        //check system to see if password matches username

    }

    public String type_of_user(String username) {
        //check database to return a string of "Attendee", "Organizer" or "Speaker"
    }

    //exit program (cancel login)
    public void exit() {
    }

    //logout
    public void logout() {
    }
