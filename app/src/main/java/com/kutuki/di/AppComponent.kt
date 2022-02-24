package com.kutuki.di

import com.kutuki.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetModule::class])
interface AppComponent {
    fun inject(ma: MainActivity)
}