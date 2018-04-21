package domhelp.com.minor;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;



public class PhoneAuthActivity extends AppCompatActivity implements
        View.OnClickListener {

    EditText mPhoneNumberField, mVerificationField;
    Button mStartButton, mVerifyButton, mResendButton;

    public FirebaseAuth mAuth;
    public PhoneAuthProvider.ForceResendingToken mResendToken;
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    //{pass data to string variables<ain't working or letting the other operations succeed>
//    Bundle bundle=getIntent().getExtras();
//    String status=bundle.getString("Status");}

    private static final String TAG = "PhoneAuthActivity";

    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);



        mPhoneNumberField =  findViewById(R.id.field_phone_number);
        mVerificationField = findViewById(R.id.field_verification_code);


        mStartButton = findViewById(R.id.button_start_verification);
        mVerifyButton =findViewById(R.id.button_verify_phone);
        mResendButton =findViewById(R.id.button_resend);

        mStartButton.setOnClickListener(this);
        mVerifyButton.setOnClickListener(this);
        mResendButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {



            @Override            public void onVerificationCompleted(PhoneAuthCredential credential) {

                Log.d(TAG, "onVerificationCompleted:" + credential);
                Toast.makeText(PhoneAuthActivity.this, "Verification Completed", Toast.LENGTH_SHORT).show();

                signInWithPhoneAuthCredential(credential);


            }

            @Override            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    mPhoneNumberField.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    mPhoneNumberField.setError("Too many requests.Try Again");

                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(PhoneAuthActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText(PhoneAuthActivity.this, "Sign In Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PhoneAuthActivity.this, EnterDetails.class));
                            finish();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                mVerificationField.setError("Invalid code.");
                            }
                        }
                    }
                });
    }


    private void startPhoneNumberVerification(String phoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber, 60,  TimeUnit.SECONDS,   this,  mCallbacks);        }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber, 60,   TimeUnit.SECONDS,  this,  mCallbacks, token);      }

    private boolean validatePhoneNumber() {
        String phoneNumber = mPhoneNumberField.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberField.setError("Invalid phone number.");
            return false;
        }
        return true;
    }
    @Override    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null ) {//yaha pr decide ki work kholna h ya hire
            Intent intent=new Intent(PhoneAuthActivity.this,DashboardWork.class);
            startActivity(intent);
            finish();
        }
//        else if(status.equals("-1")){
//            startActivity(new Intent(PhoneAuthActivity.this, SignIn.class));
//
//        }


    }

    @Override    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_start_verification:
                if (!validatePhoneNumber()) {
                    return;
                }
                startPhoneNumberVerification(mPhoneNumberField.getText().toString());
                break;
            case R.id.button_verify_phone:
                String code = mVerificationField.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    mVerificationField.setError("Cannot be empty.");
                    return;
                }

                verifyPhoneNumberWithCode(mVerificationId, code);
                break;
            case R.id.button_resend:
                resendVerificationCode(mPhoneNumberField.getText().toString(), mResendToken);
                break;
        }

    }


}