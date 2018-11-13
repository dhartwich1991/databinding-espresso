package de.freenet.espresso_databinding.ui.main

sealed class MainViewState {
    data class ItemsLoaded(val itemList: List<MainItem>) : MainViewState()
    object Error : MainViewState()
}