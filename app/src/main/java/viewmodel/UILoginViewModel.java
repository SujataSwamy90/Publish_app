package viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import view.UILoginScreen;

/**
 * Created by sujata on 07/07/15.
 */
public class UILoginViewModel implements UILoginScreen.ViewManager {

    private final Context main_activity;
    private String userEditTextValue;
    private String passwordEditTextValue;

    public UILoginViewModel(Context app_context)
    {
        main_activity = app_context;
    }

    @Override
    public void registerLoginButtonEvent(Button loginButton, final EditText userEditText, EditText passwordEditText) {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEditTextValue = userEditText.getText().toString();
                if(userEditTextValue.length()==0){
//                    Toast.makeText(this,"",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
