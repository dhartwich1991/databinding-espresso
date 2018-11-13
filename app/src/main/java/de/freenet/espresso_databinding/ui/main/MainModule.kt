package de.freenet.espresso_databinding.ui.main

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel { MainViewModel() }
}