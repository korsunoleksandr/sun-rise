package com.korsun.sunrise.presentation.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.korsun.sunrise.core.AndroidApplication;
import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.di.component.UiComponent;

import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by okorsun on 28.07.16.
 */
public abstract class BaseActivity<P extends Presenter<V>, V extends PresenterView> extends AppCompatActivity implements PresenterView {

    private static final String KEY_COMPONENT_NAME = "key_component_name";

    @Inject
    P presenter;

    private String componentName;

    protected abstract V getPresenterView();

    protected final P getPresenter() {
        return presenter;
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        componentName = savedInstanceState == null ? UUID.randomUUID().toString() : savedInstanceState.getString(KEY_COMPONENT_NAME);

        getComponent().inject(this);

        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_COMPONENT_NAME, componentName);
        presenter.saveState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attach(getPresenterView());
    }

    @Override
    protected void onStop() {
        presenter.detach();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            presenter.destroy();
            getComponentProvider().remove(componentName);
            presenter = null;
        }

        super.onDestroy();
    }

    protected abstract UiComponent<P, V> createComponent(ApplicationComponent applicationComponent);

    private AndroidApplication getAndroidApplication() {
        return (AndroidApplication) getApplicationContext();
    }

    private ComponentProvider getComponentProvider() {
        return getAndroidApplication().getComponentProvider();
    }

    private UiComponent<P, V> getComponent() {
        if (!getComponentProvider().hasComponent(componentName)) {
            getComponentProvider().put(componentName, createComponent(getAndroidApplication().getApplicationComponent()));
        }
        return getComponentProvider().get(componentName);
    }

}
