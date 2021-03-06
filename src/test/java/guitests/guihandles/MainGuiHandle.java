package guitests.guihandles;

import guitests.GuiRobot;
import javafx.stage.Stage;
import seedu.todo.TestApp;

//@@author reused
/**
 * Provides a handle for the main GUI.
 */
public class MainGuiHandle extends GuiHandle {

    //TODO: Where should the TestApp.APP_TITLE be stored?
    public MainGuiHandle(GuiRobot guiRobot, Stage primaryStage) {
        super(guiRobot, primaryStage, TestApp.APP_TITLE);
    }

    public TodoListViewHandle getTodoListView() {
        return new TodoListViewHandle(guiRobot, primaryStage);
    }

    public CommandInputViewHandle getCommandInputView() {
        return new CommandInputViewHandle(guiRobot, primaryStage);
    }

    public CommandFeedbackViewHandle getCommandFeedbackView() {
        return new CommandFeedbackViewHandle(guiRobot, primaryStage);
    }

    public CommandPreviewViewHandle getCommandPreviewView() {
        return new CommandPreviewViewHandle(guiRobot, primaryStage);
    }

    public CommandErrorViewHandle getCommandErrorView() {
        return new CommandErrorViewHandle(guiRobot, primaryStage);
    }

    public HelpViewHandle getHelpView() {
        return new HelpViewHandle(guiRobot, primaryStage);
    }

    public GlobalTagViewHandle getGlobalTagView() {
        return new GlobalTagViewHandle(guiRobot, primaryStage);
    }

    public FilterBarViewHandle getFilterBarView() {
        return new FilterBarViewHandle(guiRobot, primaryStage);
    }

    public SearchStatusViewHandle getSearchStatusView() {
        return new SearchStatusViewHandle(guiRobot, primaryStage);
    }
}
