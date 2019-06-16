package za.co.alstodt.amc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.alstodt.amc.model.form;
import za.co.alstodt.amc.remote.APIService;
import za.co.alstodt.amc.remote.ApiUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {


    private static final String TAG = "static";
    Button submit;
    APIService mAPIService;
    TextInputEditText email,name,phone;
    EditText issue;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        submit=view.findViewById(R.id.submit);
        mAPIService = ApiUtils.getAPIService();
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(getActivity(),name.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                try {
                    sendPost(name.getText().toString().trim(), email.getText().toString().trim(), phone.getText().toString().trim(), issue.getText().toString().trim());
                }catch (Exception e)
                {
                    Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_LONG).show();
                    Log.i(TAG, e.toString());
                }
            }
        });
        email=view.findViewById(R.id.email);
        name=view.findViewById(R.id.name);
        phone=view.findViewById(R.id.phone);
        issue=view.findViewById(R.id.editText);
        return view;
    }
    public void sendPost(String name, String email,String phone,String cmment) {

        mAPIService.insert("application/json",name,cmment,phone,email).enqueue(new Callback<form>() {
            @Override
            public void onResponse(Call<form> call, Response<form> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().getFeedback());
                    Log.e(TAG, "post submitted to API.  " + response.body().getFeedback());
                   // Toast.makeText(getActivity(),"sent "+response.body().getFeedback(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<form> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response) {
  
        Toast.makeText(getActivity(),response, Toast.LENGTH_SHORT).show();
    }


            // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
