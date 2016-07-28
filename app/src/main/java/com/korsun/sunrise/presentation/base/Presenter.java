package com.korsun.sunrise.presentation.base;

import android.os.Bundle;

import com.korsun.sunrise.common.ArrayUtils;

import rx.Notification;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

/**
 * Created by okorsun on 28.07.16.
 */
public abstract class Presenter<V extends PresenterView> {

    public Presenter() {
        lifecycle.onNext(LifecycleEvent.CREATE);
    }

    enum LifecycleEvent {
        CREATE,
        ATTACH,
        DETACH,
        DESTROY
    }

    private final PublishSubject<LifecycleEvent> lifecycle = PublishSubject.create();

    protected final BehaviorSubject<V> views = BehaviorSubject.create();
    protected final BehaviorSubject<PresenterView> presenterViews = BehaviorSubject.create();

    private V view;

    protected final V getView() {
        if (!hasView()) {
            throw new IllegalStateException("idi poshel nahui");
        }

        return view;
    }

    protected final boolean hasView() {
        return view != null;
    }

    final void attach(V view) {
        this.view = view;
        lifecycle.onNext(LifecycleEvent.ATTACH);
        views.onNext(view);
        onAttach();
    }

    final void detach() {
        lifecycle.onNext(LifecycleEvent.DETACH);
        views.onNext(null);
        onDetach();
        this.view = null;
    }

    final void destroy() {
        lifecycle.onNext(LifecycleEvent.DESTROY);
        views.onCompleted();
        onDestroy();
    }

    final void saveState(Bundle outState) {
        onSaveInstanceState(outState);
    }

    final void restoreState(Bundle savedInstanceState) {
        onRestoreInstanceState(savedInstanceState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    protected void onSaveInstanceState(Bundle outState) {
    }

    protected void onAttach() {

    }

    protected void onDetach() {

    }

    protected void onDestroy() {

    }

    protected final <T> Observable.Transformer<T, T> bindUntilEvent(LifecycleEvent event) {
        return source -> source.takeUntil(lifecycle.takeFirst(event1 -> event1 == event));
    }

    protected final <T> Observable.Transformer<T, Delivery<V, T>> latestCache() {
        return source -> {
            ReplaySubject<Notification<T>> subject = ReplaySubject.createWithSize(2);
            Subscription subscription = source.materialize().subscribe(subject);

            return views.switchMap(v -> subject.filter(item -> {
                if (!item.isOnNext()) {
                    return true;
                }

                @SuppressWarnings("unchecked")
                Notification<T>[] items = subject.getValues(new Notification[subject.size()]);
                int lastOnNextIndex = items[items.length - 1].isOnCompleted() ? items.length - 2 : items.length - 1;

                int itemIndex = ArrayUtils.lastIndexOf(items, item);
                return itemIndex == lastOnNextIndex;
            }).map(notification -> new Delivery<V, T>(v, notification))).doOnUnsubscribe(subscription::unsubscribe)
                    .<Delivery<V, T>>compose(Presenter.this.<Delivery<V, T>>bindUntilEvent(LifecycleEvent.DESTROY))
                    .filter(delivery -> delivery.view != null);
        };
    }

    protected <T> Subscription subscribe(Observable<Delivery<V, T>> source, Action2<V, T> onNext, Action2<V, Throwable> onError, Action1<V> onCompleted) {
        return source.subscribe(delivery -> delivery.split(onNext, onError, onCompleted));
    }

    protected static final class Delivery<V, T> {
        private final V view;

        private final Notification<T> notification;

        public Delivery(V view, Notification<T> notification) {
            this.view = view;
            this.notification = notification;
        }

        public void split(Action2<V, T> onNext) {
            split(onNext, null, null);
        }

        public void split(Action2<V, T> onNext, Action2<V, Throwable> onError) {
            split(onNext, onError, null);
        }

        public void split(Action2<V, T> onNext, Action2<V, Throwable> onError, Action1<V> onCompleted) {
            switch (notification.getKind()) {
                case OnNext:
                    onNext.call(view, notification.getValue());
                    break;
                case OnError:
                    if (onError != null) onError.call(view, notification.getThrowable());
                    break;
                case OnCompleted:
                    if (onCompleted != null) onCompleted.call(view);
                    break;
            }
        }
    }

}

