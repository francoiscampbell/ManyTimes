package io.github.francoiscampbell.manytimes

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import butterknife.bindViews

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ClockFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ClockFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClockFragment : Fragment() {
    private lateinit var centerViewText: String
    private var mListener: OnFragmentInteractionListener? = null

    private val centerText by bindView<TextView>(R.id.center)
    private val clockButtons by bindViews<FloatingActionButton>(
            R.id.cl_btn0,
            R.id.cl_btn1,
            R.id.cl_btn2,
            R.id.cl_btn3,
            R.id.cl_btn4,
            R.id.cl_btn5,
            R.id.cl_btn6,
            R.id.cl_btn7,
            R.id.cl_btn8,
            R.id.cl_btn9)
    private val deleteButton by bindView<FloatingActionButton>(R.id.cl_btnDelete)

    private val onClickDigit = { v: View ->
        if (centerText.text == centerViewText) {
            centerText.text = ""
        }
        centerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48f)
        centerText.append(clockButtons.indexOf(v).toString())
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            centerViewText = arguments.getString(ARG_CENTER_VIEW_TEXT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_clock, container, false);
    }

    override fun onStart() {
        super.onStart()

        centerText.text = centerViewText

        for (button in clockButtons) {
            button.setOnClickListener(onClickDigit)
        }

        deleteButton.setOnClickListener {
            centerText.text = centerText.text.take(Math.max(centerText.text.length - 1, 0))
        }
    }

    override fun onResume() {
        super.onResume()
    }jasdf

    override fun onPause() {
        super.onPause()
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    public fun getTime(): Int {
        return centerText.text.toString().toInt()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private val ARG_CENTER_VIEW_TEXT = "ARG_CENTER_VIEW_TEXT"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param centerViewText Parameter 1.
         * @return A new instance of fragment ClockFragment.
         */
        fun newInstance(centerViewText: String = ""): ClockFragment {
            val fragment = ClockFragment()
            val args = Bundle()
            args.putString(ARG_CENTER_VIEW_TEXT, centerViewText)
            fragment.arguments = args
            return fragment
        }
    }
}
