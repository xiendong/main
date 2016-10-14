package seedu.todo.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.todo.commons.core.LogsCenter;
import seedu.todo.commons.util.FxViewUtil;

import java.util.logging.Logger;

/**
 * Display rich textual feedback to command input via this view with {@link #displayMessage(String)}.
 */
public class CommandFeedbackView extends UiPart {
    private final Logger logger = LogsCenter.getLogger(CommandFeedbackView.class);
    private static final String FXML = "CommandFeedbackView.fxml";
    
    @FXML
    private TextFlow commandFeedbackTextFlow;
    
    private AnchorPane placeHolder, textFlowContainer;
    
    public void displayMessage(String message) {
        commandFeedbackTextFlow.getChildren().clear();
        Text text = new Text(message);
        text.getStyleClass().add("commandFeedback");
        commandFeedbackTextFlow.getChildren().add(text);
        logger.info("Message returned: " + message);
    }

    public static CommandFeedbackView load(Stage primaryStage, AnchorPane placeHolder) {
        CommandFeedbackView feedbackView = UiPartLoader.loadUiPart(primaryStage, placeHolder, new CommandFeedbackView());
        feedbackView.configure();
        return feedbackView;
    }
    
    private void configure() {
        FxViewUtil.applyAnchorBoundaryParameters(textFlowContainer, 0.0, 0.0, 0.0, 0.0);
        FxViewUtil.applyAnchorBoundaryParameters(commandFeedbackTextFlow, 0.0, 0.0, 0.0, 0.0);
        this.placeHolder.getChildren().add(textFlowContainer);
    }
    
    @Override
    public void setPlaceholder(AnchorPane placeholder) {
        this.placeHolder = placeholder;
    }
    
    @Override
    public void setNode(Node node) {
        this.textFlowContainer = (AnchorPane) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}