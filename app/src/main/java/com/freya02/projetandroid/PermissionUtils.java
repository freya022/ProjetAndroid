package com.freya02.projetandroid;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

public class PermissionUtils {
    private static final Map<Integer, CheckPermHolder> permMap = new HashMap<>();
    private static int id = 0;

    public static void handlePermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        CheckPermHolder holder = permMap.get(requestCode);

        if (holder != null) {
            if (grantResults.length == 0) {
                holder.onRefused.run();

                return;
            }

            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    holder.onRefused.run();

                    return;
                }
            }

            holder.onGranted.run();
        }
    }

    public static void checkPermission(Activity activity, Runnable onGranted, Runnable onRefused, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                int requestCode = ++id;

                permMap.put(requestCode, new CheckPermHolder(onGranted, onRefused));

                ActivityCompat.requestPermissions(activity, permissions, requestCode);

                return;
            }
        }

        //No missing permissions
        onGranted.run();
    }

    private static class CheckPermHolder {
        private final Runnable onGranted, onRefused;

        private CheckPermHolder(Runnable onGranted, Runnable onRefused) {
            this.onGranted = onGranted;
            this.onRefused = onRefused;
        }
    }
}
