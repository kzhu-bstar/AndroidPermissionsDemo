package pig.dream.androidpermissionsdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import pig.dream.permissionslibrary.CheckPermission;
import pig.dream.permissionslibrary.Constants;
import pig.dream.permissionslibrary.PDPermissions;
import pig.dream.permissionslibrary.PermissionActivity;
import pig.dream.permissionslibrary.PermissionsUtils;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;//请求码

    private CheckPermission checkPermission;//检测权限器

    //配置需要取的权限
    static final String[] PERMISSION = new String[]{
            Manifest.permission.CAMERA,// 写入权限
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission = new CheckPermission(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //缺少权限时，进入权限设置页面
//        if (checkPermission.permissionSet(PERMISSION)) {
//            startPermissionActivity();
//        }
    }

    //返回结果回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //拒绝时，没有获取到主要权限，无法运行，关闭页面
        if (requestCode == REQUEST_CODE && resultCode == PermissionActivity.PERMISSION_DENIEG) {
            Log.i(Constants.TAG, "MainActivity onActivityResult PERMISSION_DENIEG.");
            finish();
        } else if (requestCode == REQUEST_CODE && resultCode == PermissionActivity.PERMISSION_GRANTED){
            Log.i(Constants.TAG, "MainActivity onActivityResult PERMISSION_GRANTED.");
        }
    }

    public void requestCamera(View view) {
        int temp = PermissionChecker.checkSelfPermission(this, Manifest.permission.CAMERA);
        Log.i(Constants.TAG, "MainActivity requestCamera " + (temp == PackageManager.PERMISSION_GRANTED));
        PermissionActivity.startActivityForResult(this, REQUEST_CODE, PERMISSION);
    }
}
