package com.myandroid.androidManager.volleyManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.internal.http.multipart.Part;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.myandroid.androidManager.multipartRequest.FormImg;
import com.myandroid.androidManager.multipartRequest.MultipartRequest;
import com.myandroid.application.AndroidApplication;
import com.myandroid.utils.NetworkState;
import com.staticVar.IUrl;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by 小黑 on 2017/11/1.
 */

public class VolleyManager {
    private static final  String volleyBaseUrl = IUrl.BASE_URL;
    private static RequestQueue mRequestQueue;

    public static byte[] syncObj=new byte[0];

    private VolleyManager(){}

    public static void init(Context context){
        if (mRequestQueue == null){
            synchronized (syncObj){
                mRequestQueue = Volley.newRequestQueue(context);
            }
        }
        mRequestQueue.start();
    }
    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public static Observable<JSONObject> RxVolleyRequest(final String url, final Map<String,String> map,
                                                         final Map<String, String> headersMap){
        //表示这个被观察者被订阅的时候，调用这个call方法，

        return Observable.create(new Observable.OnSubscribe<JSONObject>(){
            @Override
            public void call(Subscriber<? super JSONObject> subscriber) {

                try{
                    subscriber.onNext(postRequest(subscriber,url,map,headersMap));
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }


    public static Observable<JSONObject> RxVolleyRequestFile(final String url, final HashMap<String,String> params,
                                                             final HashMap<String, String> headersMap,
                                                             final String filePath){
        //表示这个被观察者被订阅的时候，调用这个call方法，

        return Observable.create(new Observable.OnSubscribe<JSONObject>(){
            @Override
            public void call(Subscriber<? super JSONObject> subscriber) {

                try{
                    subscriber.onNext(postFileRequest(subscriber,url,params,headersMap,filePath));
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }

    public static JSONObject postRequest(final Subscriber subscriber,
                                         String url,
                                         Map<String,String> map,
                                         final Map<String, String> headersMap) throws Exception{

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,volleyBaseUrl+url,
                new JSONObject(map), future,
                new VolleyErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        subscriber.onError(error);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headersMap;
            }
        };
        mRequestQueue.add(request);
        return future.get();
    }

    public static <T>Observable<T>schedulerThread(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //检查网络情况，
                        if (!NetworkState.NetworkIsConnect(AndroidApplication.getContext()))
                        {
                            throw new ApiException("网络未连接");
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static JSONObject postFileRequest(final Subscriber subscriber,
                                             String url,
                                             HashMap<String,String> params,
                                             HashMap<String, String> headersMap,
                                                     String filePath) throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        //设置future表示同步请求，
        // 不设置requestfuture表示异步请求，

        File f1 = new File(filePath);

        ArrayList<FormImg> imageList = new ArrayList<>() ;
        imageList.add(new FormImg(f1)) ;
        Request request = new MultipartRequest(Request.Method.POST,volleyBaseUrl+url,
                imageList,params,future,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                subscriber.onError(error);
            }
        }) ;
        mRequestQueue.add(request);
        return future.get();
    }




}
