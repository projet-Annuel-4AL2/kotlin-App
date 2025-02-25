package com.example.pa4a.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa4a.Adapter.GroupAdapter
import com.example.pa4a.R
import com.example.pa4a.Utils.OnGroupClickListener
import com.example.pa4a.core.SessionManager
import com.example.pa4a.view.MainViewModel

class GroupFragment : Fragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var mainViewModel: MainViewModel


    private lateinit var groupRecyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_group, container, false)

        sessionManager = SessionManager(requireContext())
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        groupRecyclerView = view.findViewById(R.id.group_recycler_view)
        val groupLayoutManager = LinearLayoutManager(requireContext())
        groupRecyclerView.layoutManager = groupLayoutManager

        mainViewModel.groups.observe(viewLifecycleOwner) { groupResponse ->
            println("Observed group response: $groupResponse")
            groupRecyclerView.adapter = GroupAdapter(groupResponse ?: emptyList(), object :
                OnGroupClickListener {
                override fun onGroupClick(groupId: String) {
                    // Handle the click event, e.g., navigate to a detail view
                    println("Group clicked: $groupId")
                    //charger groupinfOfragment en mettant le groupid en argument
                    val groupInfoFragment = GroupInfoFragment()
                    val bundle = Bundle()
                    bundle.putString("groupId", groupId)
                    groupInfoFragment.arguments = bundle
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main_content_container, groupInfoFragment)
                        .addToBackStack(null)
                        .commit()


                }
            })
        }


        mainViewModel.getGroups()

        val groups = sessionManager.groups



        return view

    }
}