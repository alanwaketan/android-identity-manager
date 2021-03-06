package com.ndn.jwtan.identitymanager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.List;


public class UriHandler extends Activity {

    public final static String EXTRA_MESSAGE_EMAIL = "com.ndn.jwtan.identitymanager.MESSAGE_EMAIL";
    public final static String EXTRA_MESSAGE_TOKEN = "com.ndn.jwtan.identitymanager.MESSAGE_TOKEN";
    public final static String EXTRA_MESSAGE_NAME = "com.ndn.jwtan.identitymanager.MESSAGE_NAME";

    private final static String submitFirst = "cert-requests";
    private final static String submitSecond = "submit";

    private final static String installFirst = "cert";
    private final static String installSecond = "get";

    ////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri data = getIntent().getData();
        List<String> params = data.getPathSegments();
        if (params.size() < 2)
            return;

        String first = params.get(0);
        String second = params.get(1);

        if (first.equals(submitFirst) && second.equals(submitSecond)) {
            String email = data.getQueryParameter("email");
            String token = data.getQueryParameter("token");

            Intent intent = new Intent(this, SubmitIdentityRequest.class);
            intent.putExtra(EXTRA_MESSAGE_EMAIL, email);
            intent.putExtra(EXTRA_MESSAGE_TOKEN, token);
            startActivity(intent);
        }
        else if (first.equals(installFirst) && second.equals(installSecond)) {
            String name = data.getQueryParameter("name");

            Intent intent = new Intent(this, InstallCertificate.class);
            intent.putExtra(EXTRA_MESSAGE_NAME, name);
            startActivity(intent);
        }

        finish();
    }
}
