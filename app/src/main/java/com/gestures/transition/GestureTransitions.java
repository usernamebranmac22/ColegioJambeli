package com.gestures.transition;

import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.gestures.transition.ViewsTransitionAnimator.RequestListener;
import com.gestures.transition.internal.FromListViewListener;
import com.gestures.transition.internal.FromRecyclerViewListener;
import com.gestures.transition.internal.IntoViewPagerListener;
import com.gestures.transition.tracker.FromTracker;
import com.gestures.transition.tracker.IntoTracker;
import com.gestures.views.interfaces.AnimatorView;


/**
 * Transition animation builder.
 */
public class GestureTransitions<ID> {

    // ViewsTransitionAnimator' public constructor is temporary deprecated
    @SuppressWarnings("deprecation")
    private final ViewsTransitionAnimator<ID> animator = new ViewsTransitionAnimator<>();

    private GestureTransitions() {}

    public static <ID> GestureTransitions<ID> from(@NonNull RequestListener<ID> listener) {
        final GestureTransitions<ID> builder = new GestureTransitions<>();
        builder.animator.setFromListener(listener);
        return builder;
    }

    public static <ID> GestureTransitions<ID> from(@NonNull final View view) {
        return from(new RequestListener<ID>() {
            @Override
            public void onRequestView(@NonNull ID id) {
                getAnimator().setFromView(id, view);
            }
        });
    }

    public static <ID> GestureTransitions<ID> from(
            @NonNull RecyclerView recyclerView,
            @NonNull FromTracker<ID> tracker) {
        return from(recyclerView, tracker, true);
    }

    public static <ID> GestureTransitions<ID> from(
            @NonNull RecyclerView recyclerView,
            @NonNull FromTracker<ID> tracker,
            boolean autoScroll) {
        return from(new FromRecyclerViewListener<>(recyclerView, tracker, autoScroll));
    }

    public static <ID> GestureTransitions<ID> from(
            @NonNull ListView listView,
            @NonNull FromTracker<ID> tracker) {
        return from(listView, tracker, true);
    }

    public static <ID> GestureTransitions<ID> from(
            @NonNull ListView listView,
            @NonNull FromTracker<ID> tracker,
            boolean autoScroll) {
        return from(new FromListViewListener<>(listView, tracker, autoScroll));
    }

    @SuppressWarnings("unused") // Public API
    public static <ID> GestureTransitions<ID> fromNone() {
        return from(new RequestListener<ID>() {
            @Override
            public void onRequestView(@NonNull ID id) {
                getAnimator().setFromNone(id);
            }
        });
    }


    public ViewsTransitionAnimator<ID> into(@NonNull RequestListener<ID> listener) {
        animator.setToListener(listener);
        return animator;
    }

    public ViewsTransitionAnimator<ID> into(@NonNull final AnimatorView view) {
        return into(new RequestListener<ID>() {
            @Override
            public void onRequestView(@NonNull ID id) {
                getAnimator().setToView(id, view);
            }
        });
    }

    public ViewsTransitionAnimator<ID> into(
            @NonNull ViewPager viewPager,
            @NonNull IntoTracker<ID> tracker) {
        return into(new IntoViewPagerListener<>(viewPager, tracker));
    }

}
