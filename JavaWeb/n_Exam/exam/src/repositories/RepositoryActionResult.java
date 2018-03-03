package repositories;

public final class RepositoryActionResult {
    private Object actionResult;

    public RepositoryActionResult(Object actionResult) {
        this.actionResult = actionResult;
    }

    public Object getActionResult() {
        return this.actionResult;
    }

    public void setActionResult(Object actionResult) {
        this.actionResult = actionResult;
    }
}
