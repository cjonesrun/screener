package net.sarcastico.screener.tasks;

import android.content.Intent;
import android.util.Log;

import net.sarcastico.screener.R;
import net.sarcastico.screener.activities.LoginActivity;
import net.sarcastico.screener.activities.MainActivity;
import net.sarcastico.screener.utils.Config;
import net.sarcastico.screener.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Blah
 */

public class UserLoginTask extends JSONAsyncTask {

    private final String mEmail;
    private final String mPassword;
    private final LoginActivity mActivity;

    public UserLoginTask(LoginActivity activity, String email, String password) {
        mActivity = activity;
        mEmail = email;
        mPassword = password;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        // TODO: attempt authentication against a network service.
        System.out.println("logging in: " + params[0] + " " + params[1]);
        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return null;
        }

        for (String credential : Config.DUMMY_CREDENTIALS) {
            String[] pieces = credential.split(":");
            if (pieces[0].equals(mEmail)) {
                // Account exists, return true if the password matches.
                return Util.build(
                        new String[]{"success","token"},
                        new String[]{""+pieces[1].equals(mPassword), UUID.randomUUID().toString()} );
            }
        }

        // TODO: register the new account here.
        return null;
    }

    @Override
    protected void onPreExecute() {
        String message = mActivity.getResources().getString(R.string.status_sign_in_message_processing);
        mActivity.setText(message, mActivity.getResources().getColor(R.color.waitingMessage));
    }

    @Override
    protected void onPostExecute(final JSONObject o) {
        mActivity.showProgress(false);

        if (o != null) {
            try {
                if (o.has("success") && o.getBoolean("success")) {
                    mActivity.handleLoginSuccess(o);
                    return;
                }
            } catch (JSONException e) {
                Log.e("tag", "msg", e);
            }
        }
        mActivity.handleLoginError();
    }

    @Override
    protected void onCancelled() {
        mActivity.showProgress(false);
    }


}
