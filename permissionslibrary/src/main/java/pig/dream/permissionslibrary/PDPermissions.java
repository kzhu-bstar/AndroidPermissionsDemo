package pig.dream.permissionslibrary;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import java.lang.ref.SoftReference;

/**
 * 申请权限core类
 *
 * Created by kzhu on 2016/11/30.
 */
public class PDPermissions {

    private static PDPermissions instance;
    private SoftReference<Activity> currentActivity;

    public static PDPermissions Instance() {
        if (instance == null) {
            instance = new PDPermissions();
        }
        return instance;
    }

    public static PDPermissions Instance(Activity activity) {
        if (instance == null) {
            instance = new PDPermissions();
        }
        instance.with(activity);
        return instance;
    }

    private PDPermissions with(Activity activity) {
        currentActivity = new SoftReference<Activity>(activity);
        return this;

    }

    private boolean validation() {
        return currentActivity.get() == null;
    }

    private void showMessageOKCancel(Activity activity, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void requestCameraPermission(View view) {
        Log.i(Constants.TAG, "CAMERA permission has NOT been granted. Requesting permission.");
        final Activity activity = currentActivity.get();
        if (activity == null) {
            return;
        }

        Log.i(Constants.TAG, "CAMERA permission has NOT been granted. Requesting permission.");
        // BEGIN_INCLUDE(camera_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA)) {
            Log.i(Constants.TAG,
                    "Displaying camera permission rationale to provide additional context.");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, Constants.REQUEST_CAMERA);
        } else {
            // Camera permission has not been granted yet. Request it directly.
            //                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
            //                        Constants.REQUEST_CAMERA);
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, Constants.REQUEST_CAMERA);
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i(Constants.TAG, "Requesting permission result. " + PermissionsUtils.verifyPermissions(grantResults));
        if (!PermissionsUtils.verifyPermissions(grantResults)) {
            showMessageOKCancel(currentActivity.get(), "请求照相机权限失败,请前往设置修改",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
        }

    }
}
