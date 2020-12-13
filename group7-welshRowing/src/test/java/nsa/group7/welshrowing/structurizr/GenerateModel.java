package nsa.group7.welshrowing.structurizr;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.ReferencedTypesSupportingTypesStrategy;
import com.structurizr.analysis.SpringComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.Container;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.*;
import org.junit.jupiter.api.Test;


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

        //Components
        ComponentFinder componentFinder = new ComponentFinder(
                webApplication,
                "nsa.group7.welshrowing",
                new SpringComponentFinderStrategy(
                        new ReferencedTypesSupportingTypesStrategy()
                ));

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

        //Styling for Components
        styles.addElementStyle("Spring MVC Controller").background("#D4F3C0").color("#000000");
        styles.addElementStyle("Spring Service").background("#6CB33E").color("#000000");
        styles.addElementStyle("Spring Repository").background("#95D46C").color("#000000");

        // Leave this at bottom, generates the workspace.
        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }
}
