package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.NumberFormatException

class FirstFragment : Fragment() {





    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    private var listner: OnDataPass? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listner = context as OnDataPass
    }





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    fun Int?.isLessOrEqualsThan(other: Int?) =
        this != null && other != null && this <= other

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"


        val minview: TextView= view.findViewById(R.id.min_value)
        val maxview: TextView = view.findViewById(R.id.max_value)
        var min :Int? = 0
        var max :Int? = 0

            generateButton?.setOnClickListener {
                min = minview.text.toString().toIntOrNull()

                max = maxview.text.toString().toIntOrNull()

                if(min.isLessOrEqualsThan(max) ) {
                    listner?.openSecondFragment(
                        min!!,
                        max!!
                    )
                }else{
                    val text = "Entry data is invalid"
                    val duration = Toast.LENGTH_SHORT

                    val applicationContext = context
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                }





        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}


