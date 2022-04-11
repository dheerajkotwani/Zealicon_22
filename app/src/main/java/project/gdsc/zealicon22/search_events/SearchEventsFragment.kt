package project.gdsc.zealicon22.search_events

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import project.gdsc.zealicon22.DetailActivity
import project.gdsc.zealicon22.MainViewModel
import project.gdsc.zealicon22.databinding.FragmentSearchEventsBinding
import project.gdsc.zealicon22.utils.*
import project.gdsc.zealicon22.utils.COLORALO
import project.gdsc.zealicon22.utils.MECHAVOLTZ
import project.gdsc.zealicon22.utils.PLAY_IT_ON
import project.gdsc.zealicon22.utils.ROBOTILES
import project.gdsc.zealicon22.utils.Z_WARS
import timber.log.Timber


/**
 * @Author: Karan Verma
 * @Date: 3/04/22
 */

class SearchEventsFragment : Fragment() {

    private var _binding: FragmentSearchEventsBinding? = null
    private val binding get() = _binding!!

    private var searchEventsCategoryAdapter: SearchEventsCategoryAdapter? = null
    private var searchEventsAdapter : SearchEventsAdapter? = null

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchEventsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()
        handleSearchEventsCategoryAdapter()
        handleSearchEventsAdapter()
        handleCategories()
        handleSearch()

    }

    private fun handleSearchEventsCategoryAdapter() {
        val categoryList = listOf(PLAY_IT_ON, COLORALO, MECHAVOLTZ, ROBOTILES, Z_WARS, CODERZ)
        searchEventsCategoryAdapter = SearchEventsCategoryAdapter(categoryList)
        binding.categoriesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchEventsCategoryAdapter
        }
    }

    private fun handleSearchEventsAdapter() {
        searchEventsAdapter = SearchEventsAdapter()
        binding.searchRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchEventsAdapter
        }

        searchEventsAdapter?.onItemClick = {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra("EVENT_DETAIL", Gson().toJson(it).toString())
            intent.putExtra("fragment_to_show", "event_detail")
            startActivity(intent)
        }
    }

    private fun handleCategories() {
        searchEventsCategoryAdapter?.onItemClick = {
            Timber.d("categories $it")
            binding.categoryRoot.visibility = View.VISIBLE
            binding.categoryChip.text = it
            viewModel.selectCategory(it)
        }

        binding.cancelCategory.setOnClickListener {
            binding.categoryRoot.visibility = View.GONE
            viewModel.selectCategory(null)
        }
    }

    private fun handleSearch() {
        binding.searchBar.search.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewModel.searchEvents(p0)
            }
        })    }

    private fun observer() {
        viewModel.searchedEvents.observe(viewLifecycleOwner){
            searchEventsAdapter?.setList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}