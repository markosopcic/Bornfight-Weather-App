package com.markosopcic.permissionslib.di

import com.markosopcic.permissionslib.permissionsource.PermissionSource
import com.markosopcic.permissionslib.permissionsource.PermissionSourceImpl
import com.markosopcic.permissionslib.usecase.IsLocationEnabled
import com.markosopcic.permissionslib.usecase.IsLocationPermissionGranted
import com.markosopcic.permissionslib.usecase.RequestLocationPermission
import com.markosopcic.permissionslib.usecase.RequireLocationPermissionAndEnabled
import org.koin.dsl.module

fun permissionsModule() = module {

    single<PermissionSource> { PermissionSourceImpl(get(), get()) }

    single { IsLocationPermissionGranted(get()) }

    single { RequestLocationPermission(get()) }

    single { IsLocationEnabled(get()) }

    single { RequireLocationPermissionAndEnabled(get(), get(), get(), get()) }
}
