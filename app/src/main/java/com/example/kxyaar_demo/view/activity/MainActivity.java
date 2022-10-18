package com.example.kxyaar_demo.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.kxyaar_demo.R;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

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
        textView.setText("扫码");
    }
    // 获取位置信息
    public void click_getLocation(View view) {
        Log.d("TAG", "click_getLocation: " + "获取位置信息");
        textView.setText("获取位置信息");
    }
}