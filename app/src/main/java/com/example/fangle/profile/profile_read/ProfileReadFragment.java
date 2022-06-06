package com.example.fangle.profile.profile_read;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fangle.R;
import com.example.fangle.account.sign_in.SignInActivity;
import com.example.fangle.account.user_data.UserData;
import com.example.fangle.main.main_read.MainReadActivity;
import com.example.fangle.nft.nft_read.NftReadActivity;
import com.example.fangle.profile.profile_update.ProfileUpdateActivity;
import com.example.fangle.writing.writing_read.WritingListItem;

public class ProfileReadFragment extends Fragment {

    // 레이아웃 선언
    Button Log_out_button,nft_View_more_button,member_secession_button,member_modify_button;
    SignInActivity signInActivity;
    TextView nickname_text;

    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_read, container, false);

        Log_out_button = (Button) v.findViewById(R.id.Log_out_button);
        nft_View_more_button = (Button) v.findViewById(R.id.nft_View_more_button);
        member_secession_button = (Button) v.findViewById(R.id.member_secession_button);
        member_modify_button = (Button) v.findViewById(R.id.member_modify_button);
        nickname_text = (TextView) v.findViewById(R.id.nickname_text);

        signInActivity = new SignInActivity();

        nickname_text.setText(UserData.getInstance().getUserID());

        int sign = UserData.getInstance().getSign();

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data_intent = result.getData();
                    String data_result = data_intent.getExtras().getString("ResultData");
                    nickname_text.setText(data_result);
                }
            }
        });

        // 로그아웃 기능 구현
        Log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sign == 0){
                    UserData.getInstance().reset();
                    getActivity().finish();
                }else if(sign == 1){
                    signInActivity.signOut();
                }
            }

        });

        // nft 더보기 기능구현
        nft_View_more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nft 컬렉션으로 연결 시켜라
                Intent nft_intent = new Intent(getActivity(), NftReadActivity.class);
                startActivity(nft_intent);
            }
        });

        // 회원 탈퇴 기능 구현
        member_secession_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 회원 수정 기능 구현
        member_modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modify = new Intent(getActivity(), ProfileUpdateActivity.class);
                resultLauncher.launch(modify);
            }
        });

        return v;
    }

    // 개인 정보처리방침
    public void onClickPrivacyPolicy(View view){

    }

}