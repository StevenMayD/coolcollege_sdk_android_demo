package com.example.kxyaar_demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.coolcollege.aar.bean.NativeEventParams;
import com.coolcollege.aar.callback.KXYCallback;
import com.coolcollege.aar.module.APIModule;
import com.coolcollege.aar.selector.MediaSelector;
import com.example.kxyaar_demo.R;
import com.google.gson.Gson;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView textView; // api调用展示框
    private String accessToken = "43707c097bdd4302a8b745d72b7381b8"; // 测试token（实际由前端调用方传递）
    private String enterpriseId = "1324923316665978965"; // 测试企业id（实际由前端调用方传递）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view_id);
    }
    // 录音
    public void click_recordAudio(View view) {
        Log.d("TAG", "click_recordAudio: " + "录音");
        textView.setText("录音");
    }
    // 录制视频
    public void click_recordVideo(View view) {
        Log.d("TAG", "click_recordVideo: " + "录制视频");
        textView.setText("录制视频");
    }
    // 获取图片（相机/相册）
    public void click_chooseImage(View view) {
        Log.d("TAG", "click_chooseImage: " + "获取图片");
        textView.setText("获取图片");
    }
    // 获取视频（相机/相册）
    public void click_chooseVideo(View view) {
        Log.d("TAG", "click_chooseVideo: " + "获取视频");
        textView.setText("获取视频");
    }
    // 通用文件上传（上传至指定接口）
    public void click_uploadFile(View view) {
        Log.d("TAG", "click_uploadFile: " + "通用文件上传");
        textView.setText("通用文件上传");
    }
    // Oss文件上传（上传至阿里云）
    public void click_OSSUploadFile(View view) {
        Log.d("TAG", "click_OSSUploadFile: " + "Oss文件上传");
        textView.setText("Oss文件上传");
    }
    // 系统分享
    public void click_shareUniversal(View view) {
        Log.d("TAG", "click_shareUniversal: " + "系统分享");
        textView.setText("系统分享");
    }
    // 扫码
    public void click_scan(View view) {
        Log.d("TAG", "click_scan: " + "扫码");
        textView.setText("扫码...");

        NativeEventParams params = new NativeEventParams();
        params.methodName = "scan";
        params.methodData = "{}";
        callModule(params);
    }
    // 获取位置信息
    public void click_getLocation(View view) {
        Log.d("TAG", "click_getLocation: " + "获取位置信息");
        textView.setText("获取位置信息...");

        NativeEventParams params = new NativeEventParams();
        params.methodName = "getLocation";
        params.methodData = "{}";

        callModule(params);
    }

    // 统一调用API
    public void callModule(NativeEventParams params) {
        APIModule.getAPIModule(this).moduleManage(params, accessToken, enterpriseId, 123, new KXYCallback() {
            // aar 无需跳转界面的api回调
            @Override
            public void onOKCallback(Object o) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(new Gson().toJson(o));
                    }
                });
            }

            @Override
            public void onErrorCallback(Object o) {
                textView.setText(new Gson().toJson(o));
            }
        });
    }

    @Override
    // aar 需跳转界面的api回调
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;
        String text = new Gson().toJson(data.getParcelableExtra(MediaSelector.RESULT_DATA) != null ? data.getParcelableExtra(MediaSelector.RESULT_DATA) : data.getParcelableArrayListExtra(MediaSelector.RESULT_DATA));
        text = (text != null && !("null".equals(text)))?text:data.getStringExtra(MediaSelector.RESULT_DATA);
        textView.setText(text);
    }
}