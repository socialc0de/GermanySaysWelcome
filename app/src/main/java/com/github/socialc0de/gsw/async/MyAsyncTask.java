package com.github.socialc0de.gsw.async;

import android.os.AsyncTask;
import android.widget.Toast;

import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.async.interfaces.AsyncCallBack;
import com.github.socialc0de.gsw.async.interfaces.CustomAsyncTask;

/**
 * Created by roman on 05.12.2015.
 */
public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    private AsyncCallBack callBack;
    private CustomAsyncTask asyncTask;
    private Object data;

    public MyAsyncTask(CustomAsyncTask asyncTask, Object data, AsyncCallBack callBack){
        this.asyncTask = asyncTask;
        this.data = data;
        this.callBack = callBack;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        final TaskResult taskResult = new TaskResult(data, false);
        try {
            asyncTask.run(taskResult);
        }catch (Exception e){
            taskResult.setError(true);
        }
        MainActivity.getMainActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(taskResult.isError())
                    callBack.error(taskResult);
                else
                    callBack.done(taskResult);
            }
        });
        return null;
    }
}
