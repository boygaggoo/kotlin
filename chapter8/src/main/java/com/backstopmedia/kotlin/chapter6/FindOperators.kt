package com.backstopmedia.kotlin.chapter6

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.ViewGroup


@Suppress("UNCHECKED_CAST")
operator fun <T : View> Activity.get(resId: Int): T {
    return findViewById(resId) as T
}

@Suppress("UNCHECKED_CAST")
operator fun <T : Fragment> FragmentManager.get(key: String): T? {
    return findFragmentByTag(key) as T
}

operator fun rx.subscriptions.CompositeSubscription.plus(other: rx.Subscription): Unit {
    add(other)
}

@Suppress("UNCHECKED_CAST")
operator fun <T : View> ViewGroup.get(@IdRes resId: Int): T {
    return findViewById(resId) as T
}