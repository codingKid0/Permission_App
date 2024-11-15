package com.example.permission_app;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int contacts_permission = 1;
    private boolean is_permission_granted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button permission_button = findViewById(R.id.permission_button);
        permission_button.setOnClickListener(view -> handlePermission(view));
    }

    private void handlePermission(View view) {
        if (is_permission_granted) {
            Snackbar.make(view, "You've already granted this permission.", Snackbar.LENGTH_SHORT).show();
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            is_permission_granted = true;
            Snackbar.make(view, "Permission granted!", Snackbar.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, contacts_permission);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == contacts_permission) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                is_permission_granted = true;
                Snackbar.make(findViewById(android.R.id.content), "Permission granted!", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(findViewById(android.R.id.content), "You didn't grant this permission!", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}