package nsa.group7.welshrowing.structurizr;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;
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

        athlete.uses(welshRowing, "Uses");
        coach.uses(welshRowing, "Uses");

        ViewSet viewSet = workspace.getViews();
        SystemContextView contextView = viewSet.createSystemContextView(welshRowing, "context", " System Context diagram for the Welsh Rowing system.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        welshRowing.addTags("Welsh Rowing");

        Styles styles = viewSet.getConfiguration().getStyles();
        styles.addElementStyle("Welsh Rowing").background("#6CB33E").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#519823").color("#ffffff").shape(Shape.Person);

        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }
}
