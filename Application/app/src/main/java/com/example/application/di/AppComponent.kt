package com.example.application.di

import com.example.application.data.di.CoreComponent
import com.example.application.presentation.main.MainActivity
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
}