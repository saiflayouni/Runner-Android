package com.example.runner.view;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class LocationPermissionHelper  {
    public static final int REQUEST_CODE = 1;
    //LOCATION_PERMISSIONS
    public static final String[] LOCATION_PERMISSIONS = {
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION"
    };
    //CHECK_LOCATION_PERMISSIONS
    public static final Function0<Unit> CHECK_LOCATION_PERMISSIONS = new Function0<Unit>() {
        @Override
        public Unit invoke() {
            return null;
        }
    };
    //REQUEST_LOCATION_PERMISSIONS
    public static void requestLocationPermissions(MainActivity activity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            activity.requestPermissions(LOCATION_PERMISSIONS, REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
            } else {
                // User refused to grant permission.
            }
        }
    }

    public void checkPermissions(@NotNull Function0<Unit> function) {



    }
}
