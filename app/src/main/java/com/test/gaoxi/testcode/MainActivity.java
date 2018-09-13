package com.test.gaoxi.testcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init();
        init1();
    }

    private void init1() {
        Retrofit retrofit1=new Retrofit.Builder()
                //此处url 必须以/结尾 不是就会抛异常
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APi request1=retrofit1.create(APi.class);

        Call<com.test.gaoxi.testcode.Translation1> call=request1.getCall("I love you");

        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }

    private void init() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl("http://fy.iciba.com/")
                //设置数据解析器
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        // 步骤5:创建 网络请求接口 的实例
        APi request =retrofit.create(APi.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
                response.body().show();
//                Translation body = response.body();
//                body.show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }

}
