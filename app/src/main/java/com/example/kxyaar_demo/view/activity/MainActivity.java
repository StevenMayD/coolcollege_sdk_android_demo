package com.example.kxyaar_demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.coolcollege.aar.bean.NativeEventParams;
import com.coolcollege.aar.bean.PickImgBean;
import com.coolcollege.aar.bean.PickVideoBean;
import com.coolcollege.aar.bean.UploadFileBean;
import com.coolcollege.aar.bean.VideoRecordBean;
import com.coolcollege.aar.callback.KXYCallback;
import com.coolcollege.aar.module.APIModule;
import com.coolcollege.aar.selector.MediaSelector;
import com.example.kxyaar_demo.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView textView; // api调用展示框
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
        textView.setText("录制视频...");

        VideoRecordBean video = new VideoRecordBean();
        video.maxDuration = 60;

        NativeEventParams params = new NativeEventParams();
        params.methodName = "startVideoRecord";
        params.methodData = new Gson().toJson(video);

        callModule(params);
    }
    // 获取图片（相机/相册）
    public void click_chooseImage(View view) {
        Log.d("TAG", "click_chooseImage: " + "获取图片");
        textView.setText("获取图片...");

        PickImgBean imgBean = new PickImgBean();
        imgBean.count = 9;
        imgBean.percent = 80;
        imgBean.compressed = true;

        NativeEventParams params = new NativeEventParams();
        params.methodName = "chooseImage";
        params.methodData = new Gson().toJson(imgBean);

        callModule(params);
    }
    // 获取视频（相机/相册）
    public void click_chooseVideo(View view) {
        Log.d("TAG", "click_chooseVideo: " + "获取视频");
        textView.setText("获取视频...");

        PickVideoBean videoBean = new PickVideoBean();
        videoBean.count = 9;
        videoBean.maxDuration = 60;

        NativeEventParams params = new NativeEventParams();
        params.methodName = "chooseVideo";
        params.methodData = new Gson().toJson(videoBean);

        callModule(params);
    }
    // 通用文件上传（上传至指定接口）
    public void click_uploadFile(View view) {
        Log.d("TAG", "click_uploadFile: " + "通用文件上传");
        textView.setText("通用文件上传...");

        UploadFileBean uploadFileBean = new UploadFileBean();
        uploadFileBean.name = "file";
        uploadFileBean.url = "https://coolapi.coolcollege.cn/enterprise-api/common/upload";
        uploadFileBean.filePath = "/storage/emulated/0/Pictures/Img_1658912796525eb22ca6b-c8d4-480c-a1de-96d9722bc2a1.jpeg";

        Map<String, String> formMap = new HashMap<>();
        formMap.put("access_token", "7a99aadff7dd458f92c79d5379e37ecb");
        uploadFileBean.formData = formMap;

        NativeEventParams params = new NativeEventParams();
        params.methodName = "uploadFile";
        params.methodData = new Gson().toJson(uploadFileBean);
        /*
        * {
        * "code":0,
        * "data":{"code":0,"error":"","extension":"jpeg","fileName":"Img_1658912796525eb22ca6b-c8d4-480c-a1de-96d9722bc2a1.jpeg","fileNewName":"1878954705053421568.jpeg","fileSize":"66709","fileThumbName":"","fileType":"image/jpeg","server":"https://oss.coolcollege.cn"},
        *  "msg":"OK","success":true
        * }
        * 得到上传的图片地址：https://oss.coolcollege.cn/1878954705053421568.jpeg
        * */
        callModule(params);
    }
    // Oss文件上传（上传至阿里云）
    public void click_OSSUploadFile(View view) {
        Log.d("TAG", "click_OSSUploadFile: " + "Oss文件上传");
        textView.setText("Oss文件上传...");

        NativeEventParams params = new NativeEventParams();
        params.methodName = "OSSUploadFile";
//        params.methodData = "{\"accessToken\":\"7a99aadff7dd458f92c79d5379e37ecb\",\"type\":\"image\",\"files\":[{\"filePath\": \"/storage/emulated/0/Pictures/Img_1658912796525eb22ca6b-c8d4-480c-a1de-96d9722bc2a1.jpeg\", \"objectKey\":\"lu011\"}]}";
        params.methodData = "{\"accessToken\":\"7a99aadff7dd458f92c79d5379e37ecb\",\"type\":\"video\",\"files\":[{\"filePath\": \"/storage/emulated/0/Pictures/Screenshots/SVID_20221104_112754_1.mp4\", \"objectKey\":\"lu022\"}]}";
        // image和video均验证通过：filePath: 上传文件不存在的话，上传进度视图的进度条始终0% 无法上传(后续优化，检测文件是否存在、实际调用文件都会存在)；     accessToken不正确的话：上传报错：{"error":"获取凭证失败，请稍后重试", "isError":true}
        callModule(params);
        /*
        * 得到上传的图片地址：https://coolcollege-storage-hz.oss-accelerate.aliyuncs.com/lu011
        * 得到上传的视频videoId
        * */
    }
    // 系统分享
    public void click_shareUniversal(View view) {
        Log.d("TAG", "click_shareUniversal: " + "系统分享");
        textView.setText("系统分享...");

        NativeEventParams params = new NativeEventParams();
        params.methodName = "shareMenu";
        params.methodData = "{\"url\":\"https://sdn.coolcollege.cn/coolcollege-apps-share/hd/index.html?token=87fa67b66479891aa5f25a0ee86d01e6&kid=1810536062049718272&eid=1067985194709028888&aid=cool\",\"title\":\"sgyw图文课测试\",\"content\":\"QQ\",\"logo\":\"https://oss.coolcollege.cn/1810536106161213440.png\"}";

        callModule(params);
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
        APIModule.getAPIModule(this).moduleManage(params, enterpriseId, 123, new KXYCallback() {
            // aar 无需跳转界面的api回调
            @Override
            public void onOKCallback(Object o) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(new Gson().toJson(o)); // uploadFile ok
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

        Object obj1 = data.getParcelableArrayListExtra(MediaSelector.RESULT_DATA); // chooseImage 返回ArrayList 否则null
        Object obj2 = data.getStringExtra(MediaSelector.RESULT_DATA); // scan 返回String:https://mobile.coolcollege.cn/assets-share.html?short_link=https%3A%2F%2Fct12coolapi.coolcollege.cn%2Fenterprise-manage-api%2Fr%2F5520&eid=951057547274620933  否则null
        Object obj3 = data.getParcelableExtra(MediaSelector.RESULT_DATA); // null

        String text = null;
        if (obj1 != null) {
            text = new Gson().toJson(obj1); // chooseImage ok
        } else if (obj2 != null) {
            text = new Gson().toJson(obj2); // scan ok
        } else if (obj3 != null) {
            text = new Gson().toJson(obj3);
        }
        // 页面显示
        if (text != null) { textView.setText(text); }
    }
}