package com.sec.trolley.myapplication;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mSingIn, mSkip;
    TextInputLayout mUserNameWrapper, mPasswordWrapper;
    String mMobileNumber, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSingIn = (Button) findViewById(R.id.sing_in);
        mSingIn.setOnClickListener(this);
        mSkip = (Button) findViewById(R.id.skip);
        mSkip.setOnClickListener(this);
        mUserNameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        mPasswordWrapper = (TextInputLayout) findViewById(R.id.passwordwrapper);
    }

    @Override
    public void onClick(View v) {
        mMobileNumber = mUserNameWrapper.getEditText().getText().toString();
        mPassword = mPasswordWrapper.getEditText().getText().toString();
        switch (v.getId()) {
            case R.id.sing_in:
                if (!validatePassword(mPassword)) {
                    mPasswordWrapper.setError("Please Enter More than 5 char");
                } else {
                    mPasswordWrapper.setErrorEnabled(false);
                    //Check with server for login details
                    // if successfull save data into prefernces
                    doLogin();
                }
                // finish();
                return;
        }

    }

    private void doLogin() {
        String url = "http://192.168.1.102/rahul/";
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage(" ");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pDialog.hide();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String userName = jsonObject.getString("Rahul");
                    String pass = jsonObject.getString("pass");

                    Toast.makeText(MainActivity.this, "UserName:" + userName + "\nPass : " + pass, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                pDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile", mMobileNumber);
                params.put("password", mPassword);
                return params;
            }
        };
        MyApplicaion.getInstance().getRequestQueue().add(stringRequest);
    }

    public boolean validatePassword(String password) {
        if (password.length() < 5)
            return false;
        return true;
    }
}
