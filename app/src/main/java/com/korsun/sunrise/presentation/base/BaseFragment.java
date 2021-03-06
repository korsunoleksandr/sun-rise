package com.korsun.sunrise.presentation.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.korsun.sunrise.core.AndroidApplication;
import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.di.component.UiComponent;

import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by okorsun on 28.07.16.
 */
public abstract class BaseFragment<P extends Presenter<V>, V extends PresenterView> extends Fragment implements PresenterView {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        componentName = savedInstanceState == null ? UUID.randomUUID().toString() : savedInstanceState.getString(KEY_COMPONENT_NAME);

        getComponent().inject(this);

        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_COMPONENT_NAME, componentName);
        presenter.saveState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attach(getPresenterView());
    }

    @Override
    public void onStop() {
        presenter.detach();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (isRemoving()  || getActivity().isFinishing()) {
            presenter.destroy();
            getComponentProvider().remove(componentName);
            presenter = null;
        }

        super.onDestroy();
    }

    protected abstract UiComponent<P, V> createComponent(ApplicationComponent applicationComponent);

    private AndroidApplication getAndroidApplication() {
        return (AndroidApplication) getActivity().getApplicationContext();
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
