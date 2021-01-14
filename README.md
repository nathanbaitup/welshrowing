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
Running of Project:
1.	After cloning the project and creating the database, either run the application from within IntelliJ IDE using Gradle, under the bootRunProd task in the application folder or using the provided jar in the root folder and the command below.

java -jar c1926084_welsrowing.jar --spring.profiles.active=prod

2.	Within a web browser, navigate to HTTPS://localhost:8080/ to reach the home page.

3.	To log in as a coach, press the login button in the navigation bar and enter the following credentials:

Username: coachAccount
Password: @coachPassword1

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

## Run tests

```sh
test
```

## Author

👤 **Nathan Baitup, Hamid Iqbal, Oliver Holloway, Joe Baiely**


issues board aviable via this link <br />Feel free to check [issues page](https://git.cardiff.ac.uk/c1926084/clientproject-group7/-/issues?scope=all&utf8=%E2%9C%93&state=all). 
