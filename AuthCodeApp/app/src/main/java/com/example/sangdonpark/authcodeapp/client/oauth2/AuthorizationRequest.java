package com.example.sangdonpark.authcodeapp.client.oauth2;

import android.net.Uri;

import com.example.sangdonpark.authcodeapp.client.ClientAPI;

public class AuthorizationRequest {

    public static final String REDIRECT_URI = "oauth2://profile/callback";

    public static Uri createAuthorizationUri(String state) {
        return new Uri.Builder()
                .scheme("http")
                .encodedAuthority(ClientAPI.BASE_URL)
                .path("/oauth/authorize")
                .appendQueryParameter("client_id", "clientapp")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("redirect_uri", REDIRECT_URI)
                .appendQueryParameter("scope", "read_profile")
                .appendQueryParameter("state", state)
                .build();
    }

}
