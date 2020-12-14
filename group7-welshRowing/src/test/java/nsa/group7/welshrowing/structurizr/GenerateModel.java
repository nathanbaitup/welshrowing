package nsa.group7.welshrowing.structurizr;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.ReferencedTypesSupportingTypesStrategy;
import com.structurizr.analysis.SourceCodeComponentFinderStrategy;
import com.structurizr.analysis.SpringComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.*;
import com.structurizr.view.*;
import org.junit.jupiter.api.Test;

import java.io.File;


public class GenerateModel {
    private final int WORKSPACE_ID = 61706;
    private final String API_KEY =  "d8767cb4-8b1d-413f-b73d-209eba284cdb";
    private final String API_SECRET = "de1900b6-40b2-4052-9978-fa1d89b2c260";

    @Test
    public void generateModel() throws Exception{
        Workspace workspace = new Workspace("Group 7 - Welsh Rowing Project", "This is the Welsh Rowing Client Project for Group 7. ");
        Model model = workspace.getModel();

        // Creates the basic model
        // One system and two end users, an athlete and a coach
        SoftwareSystem welshRowing = model.addSoftwareSystem("Welsh Rowing", "Allowing the tracking of rowing data.");
        Person athlete = model.addPerson("Athlete", "A person who will be entering their training data.");
        Person coach = model.addPerson("Coach", "The administrator of the app. Will be able to view athletes data and have full access to the site.");

        // Sets a relationship between the users and the system.
        athlete.uses(welshRowing, "Uses");
        coach.uses(welshRowing, "Uses");

        // Creates a SystemContext view for the system.
        ViewSet viewSet = workspace.getViews();
        SystemContextView contextView = viewSet.createSystemContextView(welshRowing, "context", " System Context diagram for the Welsh Rowing system.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        // Tag
        welshRowing.addTags("Welsh Rowing");

        //Styling for Model
        Styles styles = viewSet.getConfiguration().getStyles();
        styles.addElementStyle("Welsh Rowing").background("#6CB33E").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#519823").color("#ffffff").shape(Shape.Person);

        //Model
        Container webApplication = welshRowing.addContainer("Spring Boot Application", "The Web Application", "Embedded web container. Tomcat 7.0");
        Container relationalDatabase = welshRowing.addContainer("Relational Database", "Stores information regarding athlete data, medical data and athlete workout data.", "MariaDB");
        athlete.uses(webApplication, "Uses", "HTML/HTTPS");
        coach.uses(webApplication, "Uses", "HTML/HTTPS");
        webApplication.uses(relationalDatabase, "Reads from and writes to", "JPA, port 3306");

        // View for Containers.
        ContainerView containerView = viewSet.createContainerView(welshRowing, "containers", "The Containers Diagram for the Welsh Rowing System.");
        containerView.addAllPeople();
        containerView.addAllSoftwareSystems();
        containerView.addAllContainers();

        //Tag
        relationalDatabase.addTags("Database");

        // Styling for Containers
        styles.addElementStyle(Tags.CONTAINER).background("#91D366").color("#ffffff");
        styles.addElementStyle("Database").shape(Shape.Cylinder);

        String path = System.getProperty("user.dir");
        File sourceRoot = new File(path);

        //Components
        ComponentFinder componentFinder = new ComponentFinder(
                webApplication,
                "nsa.group7.welshrowing",
                new SpringComponentFinderStrategy(
                        new ReferencedTypesSupportingTypesStrategy()
                ),
                new SourceCodeComponentFinderStrategy(new File(sourceRoot, "/src/main/java/"), 150)
        );

        componentFinder.findComponents();

        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER))
                .forEach(c -> athlete.uses(c, "Uses", "HTTPS"));

        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER))
                .forEach(c -> coach.uses(c, "Uses", "HTTPS"));

        // connect all of the repository components to the relational database
        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_REPOSITORY))
                .forEach(c -> c.uses(relationalDatabase, "Reads from and writes to", "JDBC"));

        //View for Component.
        ComponentView componentView = viewSet.createComponentView(webApplication, "components", "The Components diagram for the Welsh Rowing web application.");
        componentView.addAllComponents();
        componentView.addAllPeople();
        componentView.add(relationalDatabase);

        // Tags
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER)).forEach(c -> c.addTags("Spring MVC Controller"));
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_SERVICE)).forEach(c -> c.addTags("Spring Service"));
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_REPOSITORY)).forEach(c -> c.addTags("Spring Repository"));

        //Added External Components
        Component login = webApplication.addComponent("Login", "Template to show Login.","Thymeleaf");
        Component updateAthlete = webApplication.addComponent("update-athlete", "Template to update an athlete.","Thymeleaf");
        Component newApplicant = webApplication.addComponent("new-applicant", "Template to create a new applicant within the system.","Thymeleaf");
        Component athleteDashboard = webApplication.addComponent("athlete-dashboard", "Template that acts as a dashboard for when an athlete logs in.","Thymeleaf");
        Component coachDashboard = webApplication.addComponent("coach-dashboard", "Template that acts as a dashboard for when an coach logs in.","Thymeleaf");
        Component interviews = webApplication.addComponent("interview-form", "Template to create an interview form for an applicant.","Thymeleaf");
        Component applicantTesting = webApplication.addComponent("applicant-testing", "Template to submit an applicants test after interview.","Thymeleaf");
        Component anthropometry = webApplication.addComponent("applicant-anthropometry", "Template to submit an applicants anthropometric data.","Thymeleaf");
        Component sessionRPE = webApplication.addComponent("session-rpe", "Template to submit an athletes workout session.","Thymeleaf");

        Component entryForms = webApplication.addComponent("entryForms", "Styling that is used on each form to follow a common style across the application.", "CSS");
        Component formDashboard = webApplication.addComponent("formDashboard", "Styling that allows the dashboard to be responsive", "CSS");
        Component athleteForms = webApplication.addComponent("athleteForms", "Script used for validation on athlete data forms.", "JavaScript");
        Component setDate = webApplication.addComponent("setDate", "Script that sets the current date for workout forms.", "JQuery");
        Component validation = webApplication.addComponent("validation", "Script that validates a workout form.", "JavaScript");

        // Tags
        login.addTags("Thymeleaf");
        updateAthlete.addTags("Thymeleaf");
        newApplicant.addTags("Thymeleaf");
        athleteDashboard.addTags("Thymeleaf");
        coachDashboard.addTags("Thymeleaf");
        interviews.addTags("Thymeleaf");
        applicantTesting.addTags("Thymeleaf");
        anthropometry.addTags("Thymeleaf");
        sessionRPE.addTags("Thymeleaf");
        entryForms.addTags("CSS");
        formDashboard.addTags("CSS");
        athleteForms.addTags("JavaScript");
        validation.addTags("JavaScript");
        setDate.addTags("JQuery");

        // Linking Components.
        Component loginController = webApplication.getComponentOfType("nsa.group7.welshrowing.web.LoginController");
        Component workoutController = webApplication.getComponentOfType("nsa.group7.welshrowing.web.WorkoutController");
        Component athleteController = webApplication.getComponentOfType("nsa.group7.welshrowing.web.AthleteController");
        Component interviewController = webApplication.getComponentOfType("nsa.group7.welshrowing.web.InterviewController");
        Component coachDashboardController = webApplication.getComponentOfType("nsa.group7.welshrowing.web.CoachDashboardController");


        // Add components to the controller
        loginController.uses(login,"as view","Spring MVC");
        athleteController.uses(updateAthlete,"as view","Spring MVC");
        interviewController.uses(interviews,"as view","Spring MVC");
        coachDashboardController.uses(coachDashboard,"as view","Spring MVC");
        coachDashboardController.uses(applicantTesting,"as view","Spring MVC");
        athleteController.uses(newApplicant,"as view","Spring MVC");
        athleteController.uses(athleteDashboard, "as view", "Spring MVC");
        workoutController.uses(sessionRPE, "as view", "Spring MVC");
        athleteController.uses(anthropometry, "as view", "Spring MVC");
        sessionRPE.uses(setDate, "as script", "JQuery");
        newApplicant.uses(athleteForms, "as script", "JavaScript");
        newApplicant.uses(entryForms, "as styling", "CSS");
        applicantTesting.uses(validation, "as script", "JavaScript");
        athleteDashboard.uses(formDashboard, "as styling", "CSS");

        ComponentView mvcComponentView = viewSet.createComponentView(webApplication, "mvcComponents", "The Components diagram for the MVC of the Welsh Rowing application.");
        mvcComponentView.add(login);
        mvcComponentView.add(updateAthlete);
        mvcComponentView.add(newApplicant);
        mvcComponentView.add(athleteDashboard);
        mvcComponentView.add(coachDashboard);
        mvcComponentView.add(interviews);
        mvcComponentView.add(applicantTesting);
        mvcComponentView.add(anthropometry);
        mvcComponentView.add(sessionRPE);
        mvcComponentView.add(loginController);
        mvcComponentView.add(athleteController);
        mvcComponentView.add(workoutController);
        mvcComponentView.add(interviewController);
        mvcComponentView.add(coachDashboardController);
        mvcComponentView.add(entryForms);
        mvcComponentView.add(formDashboard);
        mvcComponentView.add(athleteForms);
        mvcComponentView.add(setDate);
        mvcComponentView.add(validation);
        mvcComponentView.addAllPeople();

        //Styling for Components
        styles.addElementStyle("Spring MVC Controller").background("#D4F3C0").color("#000000");
        styles.addElementStyle("Spring Repository").background("#95D46C").color("#000000");
        styles.addElementStyle("Spring Service").background("#6CB33E").color("#000000");
        styles.addElementStyle("JavaScript").background("#FF5733").color("#000000");
        styles.addElementStyle("Thymeleaf").background("#15ECC0").color("#000000");
        styles.addElementStyle("JQuery").background("#FF0000").color("#000000");
        styles.addElementStyle("CSS").background("#71D7FB").color("#000000");




        // Leave this at bottom, generates the workspace.
        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }
}
