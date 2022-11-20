package com.beta.orange.base

import androidx.room.Room
import com.beta.orange.database.AppDatabase
import com.beta.orange.datasource.UserEntityDataSource
import com.beta.orange.net.ApiManager
import com.beta.orange.net.AppService
import com.beta.orange.repository.MainActivityRepository
import com.beta.orange.utils.BASE_URL
import com.beta.orange.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appViewModelModule = module {
    viewModel {
        MainActivityViewModel(get())
    }
}

val appServiceModule = module {
    single { get<ApiManager>().createService<AppService>(BASE_URL) }
}

val appDataSourceModule = module {
    single {
        UserEntityDataSource(get())
    }
}

val appRepositoryModule = module {
    single {
        MainActivityRepository(get(), get())
    }
}

val appExtraModule = module {
    single {
        ApiManager()
    }
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "orange3").build()
    }
}

