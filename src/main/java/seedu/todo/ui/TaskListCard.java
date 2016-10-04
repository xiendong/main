package seedu.todo.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.todo.model.task.ReadOnlyTask;

public class TaskListCard extends UiPart{

    private static final String FXML = "TaskListCard.fxml";
    private static final String TASK_TYPE = "Task";
    private static final String EVENT_TYPE = "Event";

    @FXML
    private VBox taskCard;
    @FXML
    private Label titleLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private HBox descriptionBox;
    @FXML
    private Label typeLabel;
    @FXML
    private Label tagsLabel;

    private ReadOnlyTask task;
    private int displayedIndex;

    public TaskListCard(){

    }

    public static TaskListCard load(ReadOnlyTask task, int displayedIndex){
        TaskListCard taskListCard = new TaskListCard();
        taskListCard.task = task;
        taskListCard.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(taskListCard);
    }

    @FXML
    public void initialize() {
        titleLabel.setText(String.valueOf(displayedIndex) + ". " + task.getTitle());
        if (task.getDescription().isEmpty()) {
            descriptionBox.setVisible(false);
        } else {
            descriptionLabel.setText(task.getDescription());
        }
        typeLabel.setText( (task.isTask()) ? TASK_TYPE : EVENT_TYPE );
        
    }

    public VBox getLayout() {
        return taskCard;
    }

    @Override
    public void setNode(Node node) {
        taskCard = (VBox)node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}
