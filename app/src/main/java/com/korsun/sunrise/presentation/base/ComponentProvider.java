package com.korsun.sunrise.presentation.base;

import com.korsun.sunrise.di.component.UiComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by okorsun on 28.07.16.
 */
public class ComponentProvider {

    private final Map<String, UiComponent<?, ?>> components = new HashMap<>();

    public <P extends Presenter<V>, V extends PresenterView> UiComponent<P, V> get(String key) {
        if (hasComponent(key)){
            return (UiComponent<P, V>) components.get(key);
        } else {
            throw new IllegalStateException("component with given key " + key + " doesn't exist");
        }
    }

    public void put(String key, UiComponent<?, ?> component){
        components.put(key, component);
    }

    public void remove(String key) {
        components.remove(key);
    }

    public boolean hasComponent(String key) {
        return components.containsKey(key);
    }
}
