package io.github.francoiscampbell.manytimes

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.bindView


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GetTimeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GetTimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GetTimeFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    private val btnGetTime by bindView<Button>(R.id.btnGetTime)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_get_time, container, false)
    }

    override fun onStart() {
        super.onStart()

        btnGetTime.setOnClickListener {
            mListener?.getTime()
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
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
        fun getTime()
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment GetTimeFragment.
         */
        fun newInstance(): GetTimeFragment {
            val fragment = GetTimeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
