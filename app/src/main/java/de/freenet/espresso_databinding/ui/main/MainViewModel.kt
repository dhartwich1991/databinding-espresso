package de.freenet.espresso_databinding.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.freenet.espresso_databinding.testutils.OpenForTesting

@OpenForTesting
class MainViewModel : ViewModel() {

    val viewState: LiveData<MainViewState>
        get() = state

    private val state = MutableLiveData<MainViewState>()

    fun loadItems() {
        val items = listOf(
            MainItem("First", 1),
            MainItem("Second", 2),
            MainItem("Third", 3),
            MainItem("Fourth", 4),
            MainItem("Fifth", 5),
            MainItem("Sixth", 6),
            MainItem("Seventh", 7),
            MainItem("Eigth", 8),
            MainItem("Nineth", 9),
            MainItem("Tenth", 10)
        )

        state.postValue(MainViewState.ItemsLoaded(items))
    }

}
