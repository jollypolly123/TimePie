package com.example.timepie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timepie.models.Supplement
import com.parse.ParseUser

open class AddSupplementFragment : Fragment() {

    lateinit var intakeAdapter: SpinnerAdapter
    private var intakeCount: MutableList<Int> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_supplement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner: Spinner = view.findViewById(R.id.dayFreqSpinner)
        val intakes: RecyclerView = view.findViewById(R.id.intakes)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.daily_frequency_array,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val frequencies = resources.getStringArray(R.array.daily_frequency_array)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(requireContext(),
                    getString(R.string.selected_item) + " " + frequencies[position], Toast.LENGTH_SHORT).show()
                intakeCount.clear()
                val spinnerVal = spinner.selectedItem.toString().toInt()
                (1.. spinnerVal).forEach { intakeCount.add(it) }
                Log.i(TAG, intakeCount.toString())

                intakeAdapter.notifyItemRangeChanged(0, 3)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        (1.. spinner.selectedItem.toString().toInt()).forEach { intakeCount.add(it) }
        intakeAdapter = SpinnerAdapter(requireContext(), intakeCount)
        intakes.adapter = intakeAdapter

        intakes.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<Button>(R.id.doneButton).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.etSupName).text.toString()
            Log.i(TAG, name)
            val frequency = spinner.selectedItem.toString().toInt()
            Log.i(TAG, frequency.toString())
            val user = ParseUser.getCurrentUser()
            Log.i(TAG, user.toString())
            submitSupplement(name, frequency, user)
        }
    }

    private fun submitSupplement(name: String, frequency: Number, user: ParseUser) {
        val supplement = Supplement()
        supplement.setName(name)
        supplement.setFrequency(frequency)
        supplement.setUser(user)
        supplement.saveInBackground { exception ->
            if (exception != null) {
                Log.e(TAG, "Error while saving supplement")
                exception.printStackTrace()
                if (name.isEmpty()) {
                    Toast
                        .makeText(requireContext(), "Please fill in the supplement name", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                view?.findViewById<EditText>(R.id.etSupName)?.text?.clear()
                Toast.makeText(requireContext(), "Supplement submitted!", Toast.LENGTH_SHORT).show()
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.flContainer, ViewSupplementsFragment())
                    .commit()
            }
        }
    }

    companion object {
        private const val TAG = "AddSupplement"
    }
}