package com.toptal.data.di

import com.toptal.data.networking.GithubApi
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkingModule::class])
interface NetworkingComponent {

    fun api(): GithubApi

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance config: GithubConfig): NetworkingComponent
    }
}
