<h1 align="center">Welcome to Group 7 - Client Project - Welsh Rowing Association</h1>
<p>
</p>

> Client Project for the Welsh Rowing Association on behalf of group 7

### 🏠 [GitLab Homepage](https://git.cardiff.ac.uk/c1926084/clientproject-group7)

## Install

```sh
Creation of Database:
1.	Clone the project from the GitLab repository links provided above.
2.	Open MySQL Workbench using your root account as a new user will be created within the SQL execution.
3.	Open the SQL Document named ‘rowingDatabase.sql’ and execute the file, creating the database and populating with data.

```

## Usage

```sh
Test Cases are provided below usage instructions under 'Three Testing Scenarios heading':

Running of Project:
1.	After cloning the project and creating the database, either run the application from within IntelliJ IDE using Gradle, under the bootRunProd task in the application folder or using the provided jar in the root folder and the command below.

java -jar c1926084_welsrowing.jar --spring.profiles.active=prod

2.	Within a web browser, navigate to HTTPS://localhost:8080/ to reach the home page.

3.	To log in as a coach, press the login button in the navigation bar and enter the following credentials:

Username: coachAccount
Password: @coachAccount1

4.	You will be redirected to the Coach Dashboard. From this screen you can visit each page by pressing on the respected button. To return to the Coach Dashboard from these pages, press the back arrow on your browser.
        a.	Visit all Applicants shows you all the current applicants in the system, where pressing the accept button will accept the applicant and remove them from the list and rejecting an applicant will do the same, where an email will be sent to the athlete if rejected.
        b.	Interview Process allows you to fill in an interview for an athlete.
        c.	Athlete Morning Monitoring shows a list of all completed morning monitoring data.
        d.	Applicant Testing allows you to enter information on the applicant that you want to test, if selecting START, or 8 Weeks for the follow up question, the applicant will be upgraded to an athlete and will be removed from the list of applicants.
        e.	The morning monitoring tile gives a brief overview of all athletes who haven’t completed their morning monitoring form

5.	Once finished as a coach, please press the log out button on the navigation bar.

6.	To log in as an athlete press the login button on the navigation bar and enter the following credentials:

Username: hamidiqbal
Password: hamidIqbal1!

7.	You will be redirected to the athlete dashboard. From this screen you can visit each page by pressing on the respected button. To return to the Athlete Dashboard from these pages, press the back arrow on your browser.
        a.	Medical Data allows you to enter your medical data, upon submission the data is encrypted.
        b.	The update medical data button allows you to modify your medical data if necessary.
        c.	Previous sports allows you to enter your previous sporting history.
        d.	Morning monitoring allows you to enter your morning monitoring data. When 7 days have been entered for a week, the graph will update showing the last 7 days of data.
        e.	Workout Training allows you to enter your workout data.
        f.	Cross Training allows you to enter your cross-training data. 

8.	Once finished as a coach, please press the log out button on the navigation bar.

```

## Three Testing Scenarios
<h4> Selenium Browser Tests </h4>

```sh
A Selenium test has been written for Test Case 1: Creating a New Account. 
To run this test please go to the package named 'Selenium' within the Test folder and there will be a class named 'SeleniumTests'. 
Before running the test class, please ensure that the server is running for the test to complete. 
The test will create a new login with the details:
username: zfarro
password: @zFarro1
```

<h4> Test Case 1: Creating a New Account </h4>

```sh
Navigate to https://localhost:8080/ This will take you to the homepage.
At the homepage, press the sign up link on the navigation bar, redirecting you to the sign-up page.
At this page, please enter your name, create a username and enter a password.
* Note: A username must be unique and at least 3 characters along with no numbers.
* Note: Your password must be at least 8 characters, and include:
        uppercase and lowercase letters, numbers, and a special character consisting of:   # ? ! @ $ % ^ & * -

Once a username and password have been entered and submitted, you will be directed to the further details page.
At this page you are required to enter your email address, date of birth and gender to submit your data.
Once completing all fields and pressing submit, you will be taken to the athlete dashboard and will recieve a welcome email.
Please log out of your account when complete.
```

<h4> Test Case 2: Submitting and Updating Medical Data and Entering Morning Monitoring Data </h4>

```sh
Log into the athlete dashboard using the login credentials you just created or the login information for an athlete provided above.
On the athlete dashboard, press the button to submit your medical data.
At this page, you can enter any medical injuries, your height, weight and armspan and submit those detials.
If you make a mistake, press the update medical data button, where you are able to update the information you have entered.

Back at the athlete dashboard, press the Morning Monitoring button to enter the Morning Monitoring submission page.
Here please follow the form and enter all the requried information.
Sliders are provided for percieved shape, percieved mental state and sleep quantity.
Once submitted your data, you will be taken to the athlete dashbaord and to view your latest entry, press the view data button below the graph.
Once done, sign out of the athlete account.
```

<h4> Test Case 3: Logging in as a coach, viewing current applicants and completing an applicant test </h4>

```sh
Log into the coach dashboard using the login credentials:
username: coachaccount
password: @coachAccount1

When on the coach dashboard, press the View all Applicants button to view current applicants who are waiting to be upgraded to an athlete. 
On this page you should see an existing applicant and the account that you have just made.
Return to the coach dashboard using the back arrow in your browser, and select the button for Applicant Testing.
Whilst here, either select to complete testing for user 'James Dean' or the user that you have created.
Fill in the form, entering data to simulate a test has been completed.
When at the follow up section at the bottom of the form, select the 'START' option. This will accept the applicant you are testing.
When returning to the coach dashboard, press the view all applicants button and the applicant that you have just tested will be removed.
Please log out of the coach account.
```
## Author

👤 **Nathan Baitup, Hamid Iqbal, Oliver Holloway, Joe Baiely**


issues board aviable via this link <br />Feel free to check [issues page](https://git.cardiff.ac.uk/c1926084/clientproject-group7/-/issues?scope=all&utf8=%E2%9C%93&state=all). 
