package com.github.socialc0de.gsw.async;

/**
 * Created by roman on 05.12.2015.
 */
public class TaskResult {

    private Object data;
    private boolean error = false;

    public TaskResult(Object data, boolean error) {
        this.data = data;
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
