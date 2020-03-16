package fithelper.logic.commands;

import static fithelper.logic.commands.CommandResult.DisplayedPage.TODAY;
import static java.util.Objects.requireNonNull;
import static javafx.application.Platform.exit;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private DisplayedPage displayedPage = TODAY;
    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, DisplayedPage displayedPage, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.displayedPage = requireNonNull(displayedPage);
        this.exit = exit;
        if (this.exit) {
            exit();
        }
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, DisplayedPage displayedPage) {
        this(feedbackToUser, displayedPage, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.exit = exit;
        if (this.exit) {
            exit();
        }
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, TODAY, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public DisplayedPage getDisplayedPage() {
        return displayedPage;
    }

    public boolean isExit() {
        return this.exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, displayedPage, exit);
    }

    @Override
    public String toString() {
        return "Feedback: " + feedbackToUser + "; " + "Display Page: " + displayedPage;
    }

    /**
     * The page shown to the user.
     */
    public enum DisplayedPage {
        DASH,
        TODAY,
        CALENDAR,
        REPORT,
        PROFILE,
        HELP
    }

}
