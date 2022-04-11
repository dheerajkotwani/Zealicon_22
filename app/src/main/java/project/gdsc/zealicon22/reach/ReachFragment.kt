package project.gdsc.zealicon22.reach

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.gdsc.zealicon22.databinding.FragmentReachBinding
import project.gdsc.zealicon22.teams.ContactType
import project.gdsc.zealicon22.teams.TeamDetails
import project.gdsc.zealicon22.teams.TeamsModalItem

/**
 * @author Dheeraj Kotwani on 07/04/22.
 */
class ReachFragment : Fragment() {

    private var _binding: FragmentReachBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardMap.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:26.613390,77.360402?q=JSS Academy Of Technical Education")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        val teamDetails: ArrayList<TeamsModalItem> = ArrayList()
        teamDetails.add(TeamsModalItem("https://officialjssatenpc.com/about/images/prashant.jpeg", "Dr. Prashant Chauhan", "Festival Convener", ContactType.EMAIL, "zealicon@jssaten.ac.in"))
        teamDetails.add(TeamsModalItem("https://zealicon.in/static/media/team-1.403e5d999395f2bd9db7.png", "Prajwal Shirur", "Fest Secretary", ContactType.MOBILE, "9667935559"))
        teamDetails.add(TeamsModalItem("https://zealicon.in/static/media/team-8.6b4bb7fa963565d8c36b.png", "Nitin Patel", "Co-Fest Secretary", ContactType.MOBILE, "9005458865"))

        val adapter = ReachRVAdapter(teamDetails)
        binding.rvReach.adapter = adapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}