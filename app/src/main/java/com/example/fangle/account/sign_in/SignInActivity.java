package com.example.fangle.account.sign_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fangle.MainActivity;
import com.example.fangle.R;
import com.example.fangle.account.sign_up.SignUpActivity;
import com.example.fangle.intro.IntroActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;



public class SignInActivity extends AppCompatActivity {

    // 구글 로그인
    private FirebaseAuth mAuth = null;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    // 파이어베이스 로그인 관련 선언
    private FirebaseAuth firebaseAuth;

    // 레이아웃 관련 선언
    EditText sign_in_id,sign_in_password;
    Button sign_in_button,socialLoginButton;

    // 사용자가 입력한 로그인 정보 값 선언
    String id,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sign_in_id = (EditText) findViewById(R.id.sign_in_id);
        sign_in_password = (EditText) findViewById(R.id.sign_in_password);

        socialLoginButton = (Button) findViewById(R.id.social_login_button);
        sign_in_button = (Button) findViewById(R.id.sign_in_button);

        // 구글 로그인
        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // 파이어베이스 로그인
        firebaseAuth = FirebaseAuth.getInstance();

        // 구글 로그인 버튼
        socialLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                social_signIn();
            }
        });

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            // 회원정보 가 맞는지 확인하고 맞으면 메인화면 아니면 정보를 다시 확인 하라는 에러 표시
            @Override
            public void onClick(View v) {
                get_sign_in_data();
                sign_in();
            }
        });
    }

    // 사용자가 입력한 로그인정보를 받는다.
    public void get_sign_in_data(){
        id = sign_in_id.getText().toString().trim();
        password = sign_in_password.getText().toString().trim();
    }

    // [START signin]
    private void social_signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignInActivity.this, "구글 로그인 인증 성공", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignInActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) { //update ui code here
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    // 파이어베이스 로그인
    private void sign_in(){
        firebaseAuth.signInWithEmailAndPassword(id,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignInActivity.this,"로그인 오류",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}