package ru.olegshulika.asmeet6_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class Fragment1_EditText extends Fragment {
    private static final String TAG = "Fragment1_EditText";
    private EditText mEditText;

    public void setEditTextValue(String editTextValue) { mEditText.setText(editTextValue); }
    public String getEditTextValue() { return mEditText.getText().toString(); }

    public Fragment1_EditText() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate");
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, " onCreateView");
        // Inflate the layout for this fragment
        View inflatedView =  inflater.inflate(R.layout.fragment1__edittext, container, false);
        mEditText = inflatedView.findViewById(R.id.fragment1_text);
        return inflatedView;
    }


}
