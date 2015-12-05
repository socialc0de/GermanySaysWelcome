package com.github.socialc0de.gsw.async.interfaces;

import com.github.socialc0de.gsw.async.TaskResult;

/**
 * Created by roman on 05.12.2015.
 */
public interface AsyncCallBack {

    public void done(TaskResult data);

    public void error(TaskResult data);

}
