package com.dmo.cajazar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    // Constantes para los extras
    private static final String EXTRA_USER_NAME = "user_name";
    private static final String EXTRA_USER_AGE = "user_age";
    private static final String EXTRA_USER_EMAIL = "user_email";
    private static final String EXTRA_SHOW_WELCOME = "show_welcome";

    // 📌 CLASE BUILDER - El corazón del patrón
    public static class IntentBuilder {
        private final Context context;
        private final Intent intent;

        private String userName = "";
        private String userAge = "";
        private String userEmail = "";
        private boolean showWelcome = false;

        // Constructor
        public IntentBuilder(Context context) {
            this.context = context;
            this.intent = new Intent(context, UserProfileActivity.class);
        }

        // 🔧 MÉTODOS DE CONFIGURACIÓN
        public IntentBuilder userName(String name) {
            this.userName = name;
            intent.putExtra(EXTRA_USER_NAME, name);
            return this; // ← Importante: siempre retornar this
        }

        public IntentBuilder userAge(String age) {
            this.userAge = age;
            intent.putExtra(EXTRA_USER_AGE, age);
            return this;
        }

        public IntentBuilder userEmail(String email) {
            this.userEmail = email;
            intent.putExtra(EXTRA_USER_EMAIL, email);
            return this;
        }

        public IntentBuilder showWelcomeMessage(boolean show) {
            this.showWelcome = show;
            intent.putExtra(EXTRA_SHOW_WELCOME, show);
            return this;
        }

        // 🚀 MÉTODOS FINALES
        public void start() {
            context.startActivity(intent);
        }

        public Intent build() {
            return intent;
        }
    }

    // 🏭 MÉTODO FÁBRICA ESTÁTICO
    public static IntentBuilder _intent(Context context) {
        return new IntentBuilder(context);
    }

    // 🎯 ACTIVITY NORMAL
    private TextView txtName, txtAge, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Configurar vistas
        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        txtEmail = findViewById(R.id.txtEmail);

        // Recibir datos del Intent
        receiveIntentData();
    }

    private void receiveIntentData() {
        Intent intent = getIntent();

        String name = intent.getStringExtra(EXTRA_USER_NAME);
        String age = intent.getStringExtra(EXTRA_USER_AGE);
        String email = intent.getStringExtra(EXTRA_USER_EMAIL);
        boolean showWelcome = intent.getBooleanExtra(EXTRA_SHOW_WELCOME, false);

        // Mostrar datos en pantalla
        txtName.setText("Nombre: " + (name != null ? name : "No proporcionado"));
        txtAge.setText("Edad: " + (age != null ? age : "No proporcionada"));
        txtEmail.setText("Email: " + (email != null ? email : "No proporcionado"));

        // Mostrar mensaje de bienvenida si está activado
        if (showWelcome) {
            Toast.makeText(this, "¡Bienvenido " + name + "!", Toast.LENGTH_LONG).show();
        }
    }
}