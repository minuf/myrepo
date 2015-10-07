package com.minuf.minuf.socialnetworksample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.minuf.minuf.socialnetworksample.R;
import com.minuf.minuf.socialnetworksample.tools.classes.MyApplication_Singleton;
import com.minuf.minuf.socialnetworksample.tools.encryption.Encryption;

/**
 * Created by jorge on 30/09/15.
 */
public class FacebookActivitySample extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private Button btn_logout, btn_perfil, btn_enter_app;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.lay_facebook_activity);

        info = (TextView)findViewById(R.id.info);



        loginButton = (LoginButton)findViewById(R.id.login_button);
        btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_perfil = (Button)findViewById(R.id.btn_perfil);
        btn_enter_app = (Button)findViewById(R.id.btn_enter_app);
        btn_perfil.setVisibility(View.GONE);

        //-loginButton.setReadPermissions(Arrays.asList("email"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                MyApplication_Singleton.getInstance().setFACEBOOK_USER_ID(loginResult.getAccessToken().getUserId());
                MyApplication_Singleton.getInstance().setFACEBOOK_USER_AUTH_TOKEN(loginResult.getAccessToken().getToken());

                info.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\nAuth Token: "
                        + loginResult.getAccessToken().getToken());

                btn_perfil.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("ERROR: \nLogin attempt failed.");
            }

        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnectFromFacebook();

                btn_perfil.setVisibility(View.GONE);

            }
        });

        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile = Profile.getCurrentProfile();
                String firstName = profile.getFirstName();
                String imguriprofile = profile.getProfilePictureUri(20,20).toString();
                String linkuri = profile.getLinkUri().toString();

                info.setText("name: " + firstName + "\nimg uri: " + imguriprofile + "\nlinkuri: " + linkuri);
            }
        });

        btn_enter_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FacebookActivitySample.this, "VIENVENIDO: ID=" + MyApplication_Singleton.getInstance().getFACEBOOK_USER_ID(),
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(FacebookActivitySample.this, ActivityHome.class));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //calls onActivityResult to Facebook.CallbackManager
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //CUSTOM METHOD FROM DISCONECT COMPLETELY FROM FACEBOOK. ADDED TO LOGOUT BUTTON(CUSTOM TOO)
    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),"/{user-id}",null,HttpMethod.GET,new GraphRequest
                .Callback() {
            public void onCompleted(GraphResponse response) {

            }
        }
        ).executeAsync();
    }









}
//instead = en vez de
// GET THE REGISTERCALLBACK FROM LOGINMANAGER INSTEAD OF LOGINBUTTON
/**LoginManager.getInstance().registerCallback(callbackManager,
 new FacebookCallback<LoginResult>() {
@Override
public void onSuccess(LoginResult loginResult) {
// App code
info.setText(
"User ID: "
+ loginResult.getAccessToken().getUserId()
+ "\n" +
"Auth Token: "
+ loginResult.getAccessToken().getToken()
);
}

@Override
public void onCancel() {
// App code
}

@Override
public void onError(FacebookException exception) {
// App code
}
});**/
