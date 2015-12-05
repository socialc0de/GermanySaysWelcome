package com.github.socialc0de.gsw.api.interfaces;

import com.github.socialc0de.gsw.customClasses.api.FaqCategory;
import com.github.socialc0de.gsw.customClasses.api.PhraseCategory;

import java.util.ArrayList;

/**
 * Created by roman on 05.12.2015.
 */

public interface RestArrayRequestCallBack {
    public void onRestResults(final int state, final ArrayList<?> results);
    public boolean isDestroyed();
}
