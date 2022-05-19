package com.example.timepie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timepie.models.Supplement
import com.parse.ParseQuery
import com.parse.ParseUser

open class ViewSupplementsFragment : Fragment() {

    lateinit var adapter: SupplementAdapter
    private var allSupplements: MutableList<Supplement> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_supplements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val supplementsRecyclerView: RecyclerView = view.findViewById(R.id.supplementsRecyclerView)

        adapter = SupplementAdapter(requireContext(), allSupplements)
        supplementsRecyclerView.adapter = adapter

        supplementsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<Button>(R.id.newButton).setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.flContainer, AddSupplementFragment())
                .commit()
        }
//        swipeContainer = view.findViewById(R.id.swipeContainer)
//
//        swipeContainer.setOnRefreshListener {
//            allPosts.clear()
//            queryPosts()
//            swipeContainer.isRefreshing = false
//        }

        querySupplements()
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun querySupplements() {
        val query: ParseQuery<Supplement> = ParseQuery.getQuery(Supplement::class.java)

        query.include(Supplement.KEY_USER)
        query.whereEqualTo(Supplement.KEY_USER, ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")

        query.findInBackground { supplements, e ->
            if (e != null) {
                Log.e(TAG, "some problem")
            } else {
                if (supplements != null) {
                    Log.i(TAG, "Supplements retrieved, total ${supplements.size}")
                    for (supplement in supplements) {
                        Log.i(
                            TAG,
                            "Successful query ${supplement.getName()}"
                        )
                    }
                    allSupplements.addAll(supplements)
                    adapter.notifyDataSetChanged()
                } else {
                    Log.i(TAG, "No supplements")
                }
            }
        }
    }

    companion object {
        const val TAG = "ViewSupplementsFragment"
    }
}