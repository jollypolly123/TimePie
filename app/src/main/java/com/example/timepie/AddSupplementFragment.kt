package com.example.timepie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.timepie.models.Supplement
import com.parse.ParseFile
import com.parse.ParseUser
import java.io.File

class AddSupplementFragment : Fragment() {

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

            val frequencies = resources.getStringArray(R.array.daily_frequency_array)
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(requireContext(),
                        getString(R.string.selected_item) + " " +
                                "" + frequencies[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        view.findViewById<Button>(R.id.doneButton).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.etSupName).text.toString()
            val frequency = view.findViewById<TextView>(R.id.tvDayFreq).text.toString().toInt()
            val user = ParseUser.getCurrentUser()
            submitSupplement(name, frequency, user)
//            requireFragmentManager().beginTransaction()
//                .replace(R.id.activity_main, ViewSupplementsFragment()).addToBackStack(null).commit()
        }
    }

    private fun submitSupplement(name: String, frequency: Number, user: ParseUser) {
        val supplement = Supplement()
        supplement.setName(name)
        supplement.setFrequency(frequency)
        supplement.setUser(user)
        supplement.saveInBackground { exception ->
            if (exception != null) {
                Log.e(TAG, "Error while saving post")
                exception.printStackTrace()
            } else {
                Log.i(TAG, "Successfully saved post")
                view?.findViewById<EditText>(R.id.etSupName)?.text?.clear()
                Toast.makeText(requireContext(), "Post submitted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val TAG = "AddSupplement"
    }
}