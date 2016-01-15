package io.github.francoiscampbell.manytimes

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
    private var centerViewText: String = DEFAULT_CENTER_VIEW_TEXT
    private var centerViewTextSizeSp = DEFAULT_CENTER_VIEW_TEXT_SIZE

    private val centerView by bindView<TextView>(R.id.center)
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
        if (!centerView.text.isNumeric()) {
            centerView.text = ""
            centerViewTextSizeSp = BIG_CENTER_VIEW_TEXT_SIZE
            centerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, centerViewTextSizeSp)
        }
        centerView.append(clockButtons.indexOf(v).toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        centerViewText = savedInstanceState?.getString(ARG_CENTER_VIEW_TEXT) ?:
                arguments?.getString(ARG_CENTER_VIEW_TEXT) ?: DEFAULT_CENTER_VIEW_TEXT
        centerViewTextSizeSp = savedInstanceState?.getFloat(ARG_CENTER_VIEW_TEXT_SIZE) ?:
                arguments?.getFloat(ARG_CENTER_VIEW_TEXT_SIZE) ?: DEFAULT_CENTER_VIEW_TEXT_SIZE
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_clock, container, false);
    }

    override fun onStart() {
        super.onStart()

        centerView.text = centerViewText
        centerView.setTextSize(TypedValue.COMPLEX_UNIT_SP, centerViewTextSizeSp)

        for (button in clockButtons) {
            button.setOnClickListener(onClickDigit)
        }

        deleteButton.setOnClickListener {
            centerView.text = centerView.text.take(Math.max(centerView.text.length - 1, 0))
        }
    }

    public fun getTime(): Int {
        return if (centerView.text.isNumeric()) centerView.text.toString().toInt() else 0
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(ARG_CENTER_VIEW_TEXT, centerView.text.toString())
        outState?.putFloat(ARG_CENTER_VIEW_TEXT_SIZE, centerViewTextSizeSp)
    }

    companion object {
        const val ARG_CENTER_VIEW_TEXT = "ARG_CENTER_VIEW_TEXT"
        const val ARG_CENTER_VIEW_TEXT_SIZE = "ARG_CENTER_VIEW_TEXT_SIZE"

        private const val DEFAULT_CENTER_VIEW_TEXT = ""
        private const val DEFAULT_CENTER_VIEW_TEXT_SIZE = 24f
        private const val BIG_CENTER_VIEW_TEXT_SIZE = 48f

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param centerViewText Parameter 1.
         * @return A new instance of fragment ClockFragment.
         */
        fun newInstance(centerViewText: String = DEFAULT_CENTER_VIEW_TEXT,
                        centerViewTextSizeSp: Float = DEFAULT_CENTER_VIEW_TEXT_SIZE): ClockFragment {
            val fragment = ClockFragment()
            val args = Bundle()
            args.putString(ARG_CENTER_VIEW_TEXT, centerViewText)
            args.putFloat(ARG_CENTER_VIEW_TEXT_SIZE, centerViewTextSizeSp)
            fragment.arguments = args
            return fragment
        }
    }
}
