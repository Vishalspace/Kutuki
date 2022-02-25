package com.kutuki.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kutuki.App
import com.kutuki.di.AppComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun Activity.injector(): AppComponent {
    return (application as App).component
}

fun Fragment.injector(): AppComponent {
    return activity!!.injector()
}

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)