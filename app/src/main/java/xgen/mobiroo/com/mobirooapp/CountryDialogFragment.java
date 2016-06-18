package xgen.mobiroo.com.mobirooapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

/**
 * Created by tc on 17/06/16.
 */
public class CountryDialogFragment extends DialogFragment {

    private Button m_btnStart,m_btnCancel;
    DialogReturn m_ReturnListener;

    public interface DialogReturn{
        public void onBtnClicked(boolean flag);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        m_ReturnListener = (DialogReturn)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog, container);


        m_btnStart = (Button)view.findViewById(R.id.btn_start);
        m_btnCancel = (Button)view.findViewById(R.id.btn_cancel);

        m_btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_ReturnListener.onBtnClicked(true);
                dismiss();
            }
        });

        m_btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_ReturnListener.onBtnClicked(false);
                dismiss();
            }
        });

        return view;
    }
}
