package com.elder.eldermusicforartist.loginPage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elder.eldermusicforartist.MainActivity;
import com.elder.eldermusicforartist.R;
import com.elder.eldermusicforartist.ui.HomePage.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SignUpFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    EditText emailEditText, passwordEditText, confirmPasswordEditText;
    MaterialButton signUpContinue;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        mAuth = FirebaseAuth.getInstance();
        rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        signUpContinue = rootView.findViewById(R.id.signUpContinue);

        emailEditText = rootView.findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = rootView.findViewById(R.id.editTextPassword);
        confirmPasswordEditText = rootView.findViewById(R.id.editTextConfirmPassword);
        progressBar = rootView.findViewById(R.id.progressBar);

        textWatch(emailEditText);
        signUpContinue.setOnClickListener(v -> {
            signUpContinue.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            SignUpHandler();
        });
        return rootView;
    }

    private void SignUpHandler() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.e(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent i = new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                        getActivity().finish();
                        continueSignUp(user);
                    } else {
                        signUpContinue.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user.
                        Log.e(TAG, "createUserWithEmail:failure", task.getException());
                        String errorMessage;
                        if (Objects.requireNonNull(task.getException()).getMessage() != null) {
                            Toast.makeText(getContext(), task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        //        updateUI(null);
                    }

                    // ...
                });
    }

    private void continueSignUp(FirebaseUser user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name

        Map<String, Object> newUserData = new HashMap<>();
        newUserData.put("first", "Ada");
        newUserData.put("last", "Lovelace");
        newUserData.put("born", 1815);

        Map<String, Object> userData = new HashMap<>();
        newUserData.put("email", user.getEmail());
        newUserData.put("dateCreated", ServerValue.TIMESTAMP);
        CollectionReference artistCollectionFD = db.collection("artists").document(user.getUid()).collection("fullDetails");
        DocumentReference artistFDData = db.collection("artists").document(user.getUid()).collection("fullDetails").document("data");
        DocumentReference artistFDSubscriptionData = db.collection("artists").document(user.getUid()).collection("fullDetails").document("subscriptionData");

        artistFDData.set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }


    private void textWatch(final EditText emailEditText) {
        confirmPasswordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (loginDataChanged(emailEditText.getText().toString(), passwordEditText.getText().toString(), confirmPasswordEditText.getText().toString())) {
                    Toast.makeText(getContext(), "Login", Toast.LENGTH_LONG).show();
                }
            }
            return false;
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginDataChanged(emailEditText.getText().toString(), passwordEditText.getText().toString(), confirmPasswordEditText.getText().toString());
            }
        };
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        confirmPasswordEditText.addTextChangedListener(afterTextChangedListener);
    }

    public boolean loginDataChanged(String username, String password, String confirmPassword) {
        if (!isUserNameValid(username)) {
            emailEditText.setError(getString(R.string.invalid_email));
            signUpContinue.setEnabled(false);
        } else if (!isPasswordValid(password)) {
            passwordEditText.setError(getString(R.string.invalid_password));
            signUpContinue.setEnabled(false);
        } else if (!isConfirmPasswordValid(password, confirmPassword)) {
            confirmPasswordEditText.setError(getString(R.string.password_not_match));
            signUpContinue.setEnabled(false);
        } else {
            signUpContinue.setEnabled(true);
            return true;
        }
        return false;
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isConfirmPasswordValid(String password, String confirmPassword) {
        return confirmPassword != null && confirmPassword.equals(password);
    }


}