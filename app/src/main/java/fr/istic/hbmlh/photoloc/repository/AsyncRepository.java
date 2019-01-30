package fr.istic.hbmlh.photoloc.repository;

import static fr.istic.hbmlh.photoloc.exception.PhotoLocException.MESSAGE;

import java.util.concurrent.Callable;

import android.os.AsyncTask;
import fr.istic.hbmlh.photoloc.exception.PhotoLocException;

public class AsyncRepository<T> extends AsyncTask<Void, Void, T> {

    private Callable<T> callable;

    private Runnable runnable;

    public AsyncRepository(Callable<T> callable) {
        this.callable = callable;
    }

    public AsyncRepository(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    protected T doInBackground(Void... voids) {
        try {
            if (callable != null) {
                return callable.call();
            } else if (runnable != null) {
                runnable.run();
            }
        } catch (Exception e) {
            throw new PhotoLocException(String.format(MESSAGE, "PhotoService", "callable error"), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);


    }
}
