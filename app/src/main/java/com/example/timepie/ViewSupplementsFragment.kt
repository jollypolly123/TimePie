package com.example.timepie

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timepie.models.Supplement
import com.parse.ParseQuery

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

//        swipeContainer = view.findViewById(R.id.swipeContainer)
//
//        swipeContainer.setOnRefreshListener {
//            Log.i(TAG, "Refreshing...")
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
        query.addDescendingOrder("createdAt")
//        query.limit = 20
        query.findInBackground { supplements, e ->
            if (e != null) {
                Log.e(TAG, "some problem")
            } else {
                if (supplements != null) {
                    for (supplement in supplements) {
                        Log.i(
                            TAG,
                            "Successful query ${supplement.getDescription()} by ${supplement.getUser()
                                ?.fetchIfNeeded()?.username
                            }"
                        )
                    }
                    allSupplements.addAll(supplements)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        const val TAG = "ViewSupplementsFragment"
    }
}