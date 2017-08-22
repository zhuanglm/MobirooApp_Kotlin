package xgen.mobiroo.com.mobirooapp

import android.app.Activity
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button

/**
 * Created by tc on 17/06/16.
 */
class CountryDialogFragment : DialogFragment() {

    private lateinit var m_btnStart: Button
    private lateinit var m_btnCancel: Button
    private lateinit var m_ReturnListener: DialogReturn

    interface DialogReturn {
        fun onBtnClicked(flag: Boolean)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        m_ReturnListener = context as DialogReturn
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.fragment_dialog, container)

        m_btnStart = view.findViewById(R.id.btn_start) as Button
        m_btnCancel = view.findViewById(R.id.btn_cancel) as Button

        m_btnStart.setOnClickListener {
            m_ReturnListener.onBtnClicked(true)
            dismiss()
        }

        m_btnCancel.setOnClickListener {
            m_ReturnListener.onBtnClicked(false)
            dismiss()
        }

        return view
    }
}
