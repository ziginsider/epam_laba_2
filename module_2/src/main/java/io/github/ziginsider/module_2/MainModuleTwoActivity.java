package io.github.ziginsider.module_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Alex Kisel
 * @since 2018-03-09
 */
public class MainModuleTwoActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText subjectField;
    private EditText bodyField;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_module_two);

        emailField = findViewById(R.id.email_edit_text);
        subjectField = findViewById(R.id.subject_edit_text);
        bodyField = findViewById(R.id.body_edit_text);
        sendButton = findViewById(R.id.send_email_button);
        initSendEmailButton();
    }

    private void initSendEmailButton() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String uriText =
                        "mailto:" + emailField.getText().toString() +
                        "?subject=" + Uri.encode(subjectField.getText().toString()) +
                        "&body=" + Uri.encode(bodyField.getText().toString());
                Uri uri = Uri.parse(uriText);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}
