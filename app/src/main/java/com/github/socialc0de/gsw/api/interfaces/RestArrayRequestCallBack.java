package com.github.socialc0de.gsw.api.interfaces;

import java.util.ArrayList;

/**
 * Created by roman on 05.12.2015.
 */

public interface RestArrayRequestCallBack {
    public void onRestResults(final int state, final ArrayList<?> results);

    public boolean isDestroyed();
}
