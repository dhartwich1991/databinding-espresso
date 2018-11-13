package de.freenet.espresso_databinding.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.freenet.espresso_databinding.R
import kotlinx.android.synthetic.main.main_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : androidx.fragment.app.Fragment() {

    private val mainViewModel: MainViewModel by viewModel()
    private val mainRecyclerAdapter = MainRecyclerAdapter()

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        view.itemList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        view.itemList.adapter = mainRecyclerAdapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.viewState.observe(this, Observer { render(it) })
        mainViewModel.loadItems()
    }

    private fun render(viewState: MainViewState) {
        when (viewState) {
            is MainViewState.ItemsLoaded -> {
                mainRecyclerAdapter.addItems(viewState.itemList)
            }
            is MainViewState.Error -> {

            }
        }
    }
}
