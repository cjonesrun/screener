package net.sarcastico.screener.tasks;

import net.sarcastico.screener.activities.MainActivity;
import net.sarcastico.screener.utils.Config;
import net.sarcastico.screener.utils.Util;

import org.json.JSONObject;

/**
 *
 */

public class FetchTask extends JSONAsyncTask {
    private MainActivity mActivity;
    public FetchTask(MainActivity activity) {
        mActivity = activity;
    }


    @Override
    protected JSONObject doInBackground(String... params) {
        if (params.length == 1) {
            String token = params[0];
            if (token != null) {
                return Util.get(Config.base(), "/camel-rest-jpa/library/", null);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject object) {
        mActivity.callBack(object);
    }
}
