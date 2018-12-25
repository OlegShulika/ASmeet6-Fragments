package ru.olegshulika.asmeet6_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment2_Button extends Fragment {
    private static final String TAG = "Fragment2_Button";
    private Button mF2Button;
    private OnF2ButtonPressedListener mListener;
    private View mInflatedView;

    private Fragment3_TextView fragment3;

    private String mTextValue;
    public String getTextValue() { return mTextValue; }
    public void setTextValue(String textValue) {
        mTextValue = textValue;
        if (fragment3!=null)
            fragment3.appendTextViewValue(textValue);
    }

    public Fragment2_Button() {
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
        // Inflate the layout for this fragment
        Log.d(TAG, " onCreateView");
        mInflatedView =  inflater.inflate(R.layout.fragment2__button, container, false);
        mF2Button = mInflatedView.findViewById(R.id.fragment2_button);
        initListeners();
        return mInflatedView;
    }

    private void initListeners() {
        mF2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnF2ButtonPressed();
                CreateFragment3_TextView();
            }
        });
    }

    private void CreateFragment3_TextView() {
        if (fragment3!=null) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.remove(fragment3).commit();
            fragment3=null;
        }
        if (fragment3==null) {
            fragment3 = Fragment3_TextView.newInstance(mTextValue);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.placeholder3, fragment3).commit();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, " onAttach");
        if (context instanceof OnF2ButtonPressedListener) {
            mListener = (OnF2ButtonPressedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnF2ButtonPressedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, " onDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnF2ButtonPressedListener {
        void OnF2ButtonPressed();
    }

}
