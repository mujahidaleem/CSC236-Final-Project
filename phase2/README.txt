CSC207 Phase1 README

Running the program.
    First, please run setupLanguage to set up the ser file for language. You can comment it out after running it once.
	To run the program, please create an account after running the main pvsm method in Main.java.
Please follow the instructions on the string and make sure that all inputted text are spelled and formatted corectly.
If errors from the test files cause the program to not run, set the test file folder as 'excluded'.
After creating an account, please remember the ID of your account to login. Once you have logged in, commands to 
change password, logout, go to event menu, and go to message menu will be displayed on the screen. 

    In case there is error with running the PDF saver, please go to Project Structure ( Ctrl + Shift + Alt + S) > Libraries
    > + > from Maven and add com.itextpdf.maven:itextdoc:2.0.0

    For the MySQL Database, first add mysql:mysql-connector-java:8.0.22 to libraries.
    Then add a mysql database
    name = phase 2
    host = 127.0.0.1
    user = root
    password = csc207group0070


Functions of the program. 
Attendee:
	To browse the list of events or see the list of events you are attending, go to the event menu. On this menu, 
you can sign up for events or leave events you have already signed up for. Note that events have a capacity of two people. 
The commands for these actions will be shown on the screen. 
	To message other users that you are friends with, please go to the message menu. On this menu, you can add users to
your friend list and message them. The commands for these actions will be shown on the screen. 
	To logout, please return to the main menu and logout. 

Organizer: 
	In addition to having the same functions as an attendee, an organizer can also create events and change event details, 
provided that you created the event and the event has not yet occured. Additionally, you can also check the list of speakers in 
the system and create new speaker accounts. These commands will be shown on the screen. 
	An organizer can message all users attending an event,

Speaker: 
	In addition to having the same functions as an attendee, a speaker can view the list of events in which they will be 
speaking at.

Extra
    If needed, you can first run the initializer method in main.java to generate a few existing users and events so
testing the functions of the program will be easier. In this initializer method, all events are created by john, and
the event Halloween already has full capacity. This initializer will create 3 ser files that will be read when you
run the program.