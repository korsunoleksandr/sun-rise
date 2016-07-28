package com.korsun.sunrise.di.component;

import com.korsun.sunrise.presentation.base.BaseActivity;
import com.korsun.sunrise.presentation.base.BaseFragment;
import com.korsun.sunrise.presentation.base.Presenter;
import com.korsun.sunrise.presentation.base.PresenterView;

/**
 * Created by okorsun on 28.07.16.
 */
public interface UiComponent<P extends Presenter<V>, V extends PresenterView> {

    void inject(BaseActivity<P, V> activity);
    void inject(BaseFragment<P, V> activity);

}
