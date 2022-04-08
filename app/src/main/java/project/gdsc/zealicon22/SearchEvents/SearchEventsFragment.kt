package project.gdsc.zealicon22.SearchEvents

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.gdsc.zealicon22.databinding.FragmentSearchEventsBinding
import project.gdsc.zealicon22.dayWiseEvent.EventsModel
import project.gdsc.zealicon22.utils.*
import project.gdsc.zealicon22.utils.COLORALO
import project.gdsc.zealicon22.utils.MECHAVOLTZ
import project.gdsc.zealicon22.utils.PLAY_IT_ON
import project.gdsc.zealicon22.utils.ROBOTILES
import project.gdsc.zealicon22.utils.Z_WARS


/**
 * @Author: Karan Verma
 * @Date: 3/04/22
 */

class SearchEventsFragment : Fragment() {

    private var _binding: FragmentSearchEventsBinding? = null
    private val binding get() = _binding!!

    private var searchEventsCategoryAdapter: SearchEventsCategoryAdapter? = null
    private var searchEventsAdapter : SearchEventsAdapter = SearchEventsAdapter()

    private val searchList : ArrayList<EventsModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchEventsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryList = listOf(PLAY_IT_ON, COLORALO, MECHAVOLTZ, ROBOTILES, Z_WARS, CODERZ)
        searchEventsCategoryAdapter = SearchEventsCategoryAdapter(categoryList)
        binding.categoriesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchEventsCategoryAdapter
        }
        binding.searchRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchEventsAdapter
        }
        searchEventsAdapter.setList(searchList)

        binding.searchBar.search.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}