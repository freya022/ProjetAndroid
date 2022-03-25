package com.freya02.projetandroid.today_food;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddFoodActivity extends AppCompatActivity {
    private ActivityResultLauncher<Void> launcher;

    private Intent resultIntent = new Intent();
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logo);

        launcher = registerForActivityResult(new ActivityResultContract<Void, Bitmap>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Void input) {
                return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            }

            @Override
            public Bitmap parseResult(int resultCode, @Nullable Intent intent) {
                if (resultCode == RESULT_OK && intent != null) {
                    return (Bitmap) intent.getExtras().get("data");
                }

                return null;
            }
        }, bitmap -> {
            if (bitmap != null) {
                ImageView foodPreview = findViewById(R.id.foodPreview);

                foodPreview.setImageBitmap(bitmap);

                this.bitmap = bitmap;
            }
        });
    }

    public void onAddPhotoClick(View v) {
        launcher.launch(null);
    }

    public void onValidateClick(View v) throws IOException {
        EditText foodNameField = findViewById(R.id.foodNameField);
        EditText foodKcalField = findViewById(R.id.foodKcalField);

        resultIntent.putExtra("name", foodNameField.getText().toString());
        resultIntent.putExtra("kcal", Integer.parseInt(foodKcalField.getText().toString()));

        String timeStamp = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss", Locale.getDefault()).format(new Date());

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile("FoodPic_" + timeStamp, ".jpg", storageDir);

        try (FileOutputStream outputStream = new FileOutputStream(image)) {
            if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
                throw new IOException("Unable to save bitmap");
            }
        }

        resultIntent.putExtra("imagePath", image.toString());

        setResult(RESULT_OK, resultIntent);

        finish();
    }
}