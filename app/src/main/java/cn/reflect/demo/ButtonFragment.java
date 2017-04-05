package cn.reflect.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import cn.reflect.dialogs.fragment.SimpleDialogFragment;
import cn.reflect.dialogs.iface.ISimpleDialogListener;


/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link ButtonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonFragment extends Fragment implements ISimpleDialogListener {
    private static final int REQUEST_SIMPLE_DIALOG = 42;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button mPlusOneButton;

    public ButtonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ButtonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ButtonFragment newInstance(String param1, String param2) {
        ButtonFragment fragment = new ButtonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button, container, false);

        //Find the +1 button
        mPlusOneButton = (Button) view.findViewById(R.id.plus_one_button);
        mPlusOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment.createBuilder(getActivity(), getFragmentManager())
                        .setTitle("Do you like this quote?")
                        .setMessage("Jayne: \"Shiny. Let's be bad guys.\"")
                        .setPositiveButtonText("Love")
                        .setNegativeButtonText("Hate")
                        .setNeutralButtonText("WTF?")
                        .setRequestCode(REQUEST_SIMPLE_DIALOG)
                        .setTargetFragment(ButtonFragment.this, REQUEST_SIMPLE_DIALOG)
                        .show();
            }
        });

        return view;
    }

    @Override
    public void onNegativeButtonClicked(int requestCode) {

    }

    @Override
    public void onNeutralButtonClicked(int requestCode) {

    }

    @Override
    public void onPositiveButtonClicked(int requestCode) {
        if (requestCode == REQUEST_SIMPLE_DIALOG) {
            Toast.makeText(getActivity(), "Positive button clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
