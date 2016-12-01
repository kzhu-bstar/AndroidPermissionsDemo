package pig.dream.permissionslibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

/**
 * 工具类 用于申请权限
 *
 * Created by pig on 16-11-28.
 */

public class PermissionsUtils {


    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i(Constants.TAG, "Requesting permission result. " + verifyPermissions(grantResults));
        if (verifyPermissions(grantResults)) {

        } else {

        }
    }

    public static void checkSelfPermission() {
//        ContextCompat.checkSelfPermission()
    }

    /**
     * Checks all given permissions have been granted.
     * 检查返回值  所有权限是否已授权
     *
     * @param grantResults results
     * @return returns true if all permissions have been granted.
     */
    public static boolean verifyPermissions(int... grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void requestCameraPermission(@NonNull final Activity activity, @NonNull final View view) {
        Log.i(Constants.TAG, "CAMERA permission has NOT been granted. Requesting permission.");

        // BEGIN_INCLUDE(camera_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Log.i(Constants.TAG,
                    "Displaying camera permission rationale to provide additional context.");
            Snackbar.make(view, "请求照相机权限。",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.CAMERA},
                                    Constants.REQUEST_CAMERA);
                        }
                    })
                    .show();
        } else {
            // Camera permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                    Constants.REQUEST_CAMERA);
        }
    }


}
