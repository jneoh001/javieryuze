<h1 align="center">MOBLIMA</h1>
<p>Welcome to our Movie Booking and Listing Management Application (MOBLIMA). This is a purely console based application, and allows for movie listings, reviews and even the booking and cancellation of tickets through an ASCII interface! This application was a project made for Nanyang Technological University, Singapore's CZ2002 course - Object Oriented Design & Programming.</p>
<p align="center"><img alt="Cinema booking screen." src="https://i.imgur.com/jt99Ypt.png"></img></p>

## Starting the Application
The class where you can start the MOBLIMA app is called DriverApp under src/boundaries package. It has a main class that when run, starts up the app and brings you to the first menu.
    
In any menu selection, enter 0 in order to return to the previous menu.

To test out Staff functions of the application, use the login details below.
```
Username: User1 (case-sensitive)
Password: 123
```

## Application Setup

### Prepopulated data

The preexisting data is already initialised, but if there are any loading issues, please go to the DataInitialiser under the src/utils package and run that class (using Run Configurations if you have to). It will throw out filenotfound errors and exceptions but we have dealt with them and they do not cause errors within the program. 
        
If you want to reset the data, please do run Reset, which can be found under the DataInitialiser class. However, we have initialised a company, the 3 cineplexes, 9 cinemas, 9 movies and a number of showtimes and reviews such that our app has full capabilities when started up.

## Using the Application
Reviews can be accessed after you have selected a movie. 
        
You have to make a booking before you can check booking history. Showtimes on both Staff and Customer side can be accessed after accessing the movie you want to view/edit/add/remove the showtime for.
