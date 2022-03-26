package com.freya02.projetandroid;

import android.content.Context;
import android.content.Intent;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Utils {
    public interface IntentFunction<T> {
        Intent createIntent(@NonNull Context context, T input);
    }

    public interface ResultFunction<R> {
        R parseResult(int resultCode, @Nullable Intent intent);
    }

    public interface ResultConsumer<R> {
        void accept(@Nullable R r);
    }

    public static <T, R> ActivityResultLauncher<T> registerForActivityResult(ComponentActivity context, IntentFunction<T> intentFunction, ResultFunction<R> resultFunction, ResultConsumer<R> consumer) {
       return context.registerForActivityResult(new ActivityResultContract<T, R>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, T input) {
                return intentFunction.createIntent(context, input);
            }

            @Override
            public R parseResult(int resultCode, @Nullable Intent intent) {
                return resultFunction.parseResult(resultCode, intent);
            }
        }, consumer::accept);
    }
}
