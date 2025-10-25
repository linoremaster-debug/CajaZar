package com.dmo.cajazar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editName, editAge, editEmail;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar vistas
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);
        btnSend = findViewById(R.id.btnSend);

        // Botón que usa el Builder
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserData();
            }
        });
    }

    private void sendUserData() {
        // Obtener datos del formulario
        String name = editName.getText().toString();
        String age = editAge.getText().toString();
        String email = editEmail.getText().toString();

        // ✅ USANDO EL BUILDER - ¡Así de simple!
        UserProfileActivity._intent(this)
                .userName(name)
                .userAge(age)
                .userEmail(email)
                .showWelcomeMessage(true)
                .start();
    }
}