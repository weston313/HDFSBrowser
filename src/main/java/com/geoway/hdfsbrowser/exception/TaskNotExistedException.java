package com.geoway.hdfsbrowser.exception;

/**
 * Created by USER on 2017/4/17.
 */
public class TaskNotExistedException extends RuntimeException {

    private String message;

    public TaskNotExistedException(String message)
    {
        super(message);
    }

    public TaskNotExistedException()
    {
        super();
    }
}
