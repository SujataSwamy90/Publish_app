package view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import helper.NetworkOperations;
import publish_app.raweng.com.publish_app.R;
import utils.AppConstant;
import viewmodel.UILoginViewModel;

/**
 * Created by sujata on 6/7/15.
 */
public class UILoginScreen extends Activity {

    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
//        NetworkOperations operations = new NetworkOperations();
//        operations.execute(AppConstant.USER_SESSION_URL);

        this.delegate = new UILoginViewModel(this);
        this.delegate.registerLoginButtonEvent(loginButton,userNameEditText,passwordEditText);
    }



    public ViewManager delegate;

    public interface ViewManager{

        void registerLoginButtonEvent(Button loginButton,EditText userEditText,EditText passwordEditText);

    }

    public interface AsyncResponse {
        void processFinish(String output);
    }

}
