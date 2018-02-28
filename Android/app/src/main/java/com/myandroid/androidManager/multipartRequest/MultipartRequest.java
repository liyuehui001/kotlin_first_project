package com.myandroid.androidManager.multipartRequest;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 小黑 on 2017/12/8.
 */

public class MultipartRequest extends Request<JSONObject> {
    /**
     * 正确数据的时候回掉用
     */
    private Response.Listener mListener ;
    /*请求 数据通过参数的形式传入*/
    private ArrayList<FormImg> mListItem =new ArrayList<>();

    private String BOUNDARY = "--------------520-13-14"; //数据分隔线
    private String MULTIPART_FORM_DATA = "multipart/form-data";
    private String end = "\r\n";
    private String prefix = "--";
    private HashMap<String,String> mParams;




    public MultipartRequest(int method, String url, ArrayList<FormImg> lists,
                            HashMap<String,String> params,
                            Response.Listener<JSONObject> listenersuccess,
                            Response.ErrorListener listener) {
        super(method, url, listener);
        setShouldCache(false);
        this.mListItem = lists;
        mListener = listenersuccess;
        this.mParams = params;

        //设置请求的响应事件，因为文件上传需要较长的时间，所以在这里加大了，设为5秒
        setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) throws NoSuchMethodException {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            Log.e("json_response",jsonString);
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

    @Override
    public byte[] getBody() throws AuthFailureError ,IOException{
        if (mListItem == null||mListItem.size() == 0){
            return super.getBody() ;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;




        int N = mListItem.size() ;
        FormImg formImage ;
        for (int i = 0; i < N ;i++){
            formImage = mListItem.get(i) ;
            StringBuffer sb= new StringBuffer() ;
            /*第一行*/
            //`"--" + BOUNDARY + "\r\n"`
            sb.append(prefix+BOUNDARY+end);
            /*第二行*/
            //Content-Disposition: form-data; name="参数的名称"; filename="上传的文件名" + "\r\n"
            sb.append("Content-Disposition: form-data;");
            sb.append(" name=\"");
            sb.append(formImage.getName()+String.valueOf(i)) ;
            sb.append("\"") ;
            sb.append("; filename=\"") ;
            sb.append(formImage.getFileName()) ;
            sb.append("\""+end);
            /*第三行*/
            //Content-Type: 文件的 mime 类型 + "\r\n"
            sb.append("Content-Type: ");
            sb.append(formImage.getMime()) ;
            sb.append(end) ;
            /*第四行*/
            //"\r\n"
            sb.append(end) ;
            try {
                bos.write(sb.toString().getBytes("utf-8"));
                /*第五行*/
                //文件的二进制数据 + "\r\n"
                bos.write(formImage.getValue());
                bos.write(end.getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }//写文件数据结束
        //写文字数据信息


        try {
            for (Map.Entry<String,String> entry :mParams.entrySet()) {
                StringBuffer sb = new StringBuffer();


                sb.append(prefix + BOUNDARY + end);//第一行<<<<"--" + boundary + "\r\n" ;
                sb.append("Content-Disposition: form-data;name=\"" + entry.getKey() + "\"" + end);//第二行<<<<"Content-Disposition: form-data; name="参数的名称"" + "\r\n" ;
                sb.append("Content-Type: text/plain;charset=UTF-8" + end);//第三行<<<<"Content-Type: text/plain;charset=UTF-8" + /r/n
                sb.append(end);
                sb.append(entry.getValue() + end);
                String str = sb.toString();
                bos.write(str.getBytes("utf-8"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        /*结尾行*/
        //`"--" + BOUNDARY + "--" + "\r\n"`
        String endLine = prefix + BOUNDARY + "--" + end ;

        bos.write(endLine.toString().getBytes("utf-8"));

        Log.v("zgy","=====formImage====\n"+bos.toString()) ;



        return bos.toByteArray();
    }
    //Content-Type: multipart/form-data; boundary=----------8888888888888
    @Override
    public String getBodyContentType() {
        return MULTIPART_FORM_DATA+"; boundary="+BOUNDARY;
    }


}
