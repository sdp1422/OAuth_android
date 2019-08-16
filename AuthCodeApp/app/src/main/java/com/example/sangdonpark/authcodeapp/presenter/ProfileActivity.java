package com.example.sangdonpark.authcodeapp.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sangdonpark.authcodeapp.R;
import com.example.sangdonpark.authcodeapp.client.ClientAPI;
import com.example.sangdonpark.authcodeapp.client.oauth2.TokenStore;
import com.example.sangdonpark.authcodeapp.client.profile.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private TextView textName;
    private TextView textEmail;
    private TokenStore tokenStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tokenStore = new TokenStore(this);

        textName = (TextView) findViewById(R.id.profile_name);
        textEmail = (TextView) findViewById(R.id.profile_email);

        Call<UserProfile> call = ClientAPI
                .userProfile().token(tokenStore.getToken().getValue());

        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                UserProfile userProfile = response.body();
                textName.setText(userProfile.getName());
                textEmail.setText(userProfile.getEmail());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.e("ProfileActivity", "Error trying to retrieve user profile", t);
            }
        });

    }
}
