package view;

import android.app.Activity;
import android.os.Bundle;

import helper.NetworkOperations;
import publish_app.raweng.com.publish_app.R;
import utils.AppConstant;

/**
 * Created by sujata on 6/7/15.
 */
public class UILoginScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        NetworkOperations operations = new NetworkOperations();
        operations.execute(AppConstant.USER_SESSION_URL);

    }
}
