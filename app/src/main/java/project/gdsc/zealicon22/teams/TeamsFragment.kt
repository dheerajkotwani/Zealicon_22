package project.gdsc.zealicon22.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentTeamsBinding


/**
 * @Author: Karan Verma
 * @Date: 08/04/22
 */
class TeamsFragment : Fragment() {

    private var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!

//    private var managementTeamAdapter : ManagementTeamAdapter?= null
    private var managementTeamAdapter : TeamAdapter?= null
    private var coreTeamAdapter : TeamAdapter?= null
    private var techTeamAdapter : TeamAdapter?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val team = resources.openRawResource(R.raw.team_details).bufferedReader().use { it.readText() }
        val teamList : TeamDetails = Gson().fromJson(team, TeamDetails::class.java)

        managementTeamAdapter = TeamAdapter(teamList.teamsModal.management)
        binding.managementRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = managementTeamAdapter
        }

        coreTeamAdapter = TeamAdapter(teamList.teamsModal.core)
        binding.coreTeamRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = coreTeamAdapter
        }

        techTeamAdapter = TeamAdapter(teamList.teamsModal.technical)
        binding.techTeamRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = techTeamAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}