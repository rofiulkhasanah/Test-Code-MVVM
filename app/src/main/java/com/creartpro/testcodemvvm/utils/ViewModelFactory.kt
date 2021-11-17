package com.creartpro.testcodemvvm.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.creartpro.testcodemvvm.data.repository.MainRepository
import com.creartpro.testcodemvvm.ui.viewmodel.MainViewModel

class ViewModelFactory private constructor(
    private val mainRepository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideMainRepository(context)
            )
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(mainRepository) as T
            else -> throw Throwable("Unknwon viewmodel class")
        }
    }
}