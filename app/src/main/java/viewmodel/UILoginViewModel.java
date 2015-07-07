package viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helper.NetworkOperations;
import publish_app.raweng.com.publish_app.R;
import utils.AppConstant;
import view.UILoginScreen;

/**
 * Created by sujata on 07/07/15.
 */
public class UILoginViewModel implements UILoginScreen.ViewManager,UILoginScreen.AsyncResponse {

    private final Context _context;
    private String userEditTextValue;
    private String passwordEditTextValue;

    public UILoginViewModel(Context app_context)
    {
        _context = app_context;
    }

    @Override
    public void registerLoginButtonEvent(Button loginButton, final EditText userEditText, final EditText passwordEditText) {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 userEditTextValue = userEditText.getText().toString();
                 passwordEditTextValue = passwordEditText.getText().toString();

                if(userEditTextValue.length() == 0 ){
                    Toast.makeText(_context,""+_context.getString(R.string.validation_username_text),Toast.LENGTH_LONG).show();
                    return;
                }else if(passwordEditTextValue.length() == 0){
                    Toast.makeText(_context,""+_context.getString(R.string.validation_password_text),Toast.LENGTH_LONG).show();
                    return;
                }
                NetworkOperations networkOperations = new NetworkOperations();
//                networkOperations.delegate = UI;
                String[] strArray= {AppConstant.USER_SESSION_URL,userEditTextValue,passwordEditTextValue};
                networkOperations.execute(strArray);
            }
        });
    }

    @Override
    public void processFinish(String output) {
       Log.d("Output: ",output + "");
    }
}
