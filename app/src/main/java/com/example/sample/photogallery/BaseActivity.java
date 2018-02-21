package com.example.sample.photogallery;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    private int PERMISSION_REQUEST_CODE;

    public void onPermissionGrant(String[] permissions, int PERMISSION_REQUEST_CODE) {

    }

    public void onPermissionDenied(String[] permissions, int PERMISSION_REQUEST_CODE) {
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkPermission(String[] permissions, int requestCode) {
        PERMISSION_REQUEST_CODE = requestCode;
        String[] requestPermissions = hasPermission(permissions); // List of not granted permission

        if (requestPermissions.length > 0 && requestPermissions[0] != null && requestPermissions[0].length() > 0) {
            this.requestPermissions(requestPermissions, requestCode);
            return false;
        } else {
            onPermissionGrant(permissions, PERMISSION_REQUEST_CODE);
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> deniedPermission = new ArrayList<>(), grantPermission = new ArrayList<>();

        for (String permission : permissions) {
            if (checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                grantPermission.add(permission);
            } else {
                deniedPermission.add(permission);
            }
        }

        if (deniedPermission.size() > 0) {
            onPermissionDenied(deniedPermission.toArray(new String[deniedPermission.size()]), PERMISSION_REQUEST_CODE);
        } else {
            onPermissionGrant(grantPermission.toArray(new String[grantPermission.size()]), PERMISSION_REQUEST_CODE);
        }
    }

    public String[] hasPermission(String... permissions) {
        String[] denyPermission = new String[]{};
        ArrayList<String> permissionsList = new ArrayList<>();
        for (String permission : permissions) {

            if (checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission); // Add the permission that is not granted by user
            }
        }

        if (permissionsList.size() > 0) {
            denyPermission = new String[permissionsList.size()]; // Create the string array with size of permissions that is not granted by user
        }

        for (int i = 0; i < permissionsList.size(); i++) {
            denyPermission[i] = permissionsList.get(i); // Add one by one permission from array list to string array object
        }

        return denyPermission; // return not granted permission to request
    }

}
