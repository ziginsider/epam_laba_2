package io.github.ziginsider.module_2;

import android.content.Intent;
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
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailField.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, subjectField.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, bodyField.getText().toString());
                try {
                    startActivity(Intent.createChooser(intent,
                            getResources().getString(R.string.send_email)));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainModuleTwoActivity.this,
                            "There are no email clients installed.",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
