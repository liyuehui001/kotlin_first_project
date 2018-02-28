package com.myandroid.androidManager.multipartRequest;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 小黑 on 2017/12/9.
 */

public class FormImg {
    //参数的名称
    private String mName ;
    //文件名
    private String mFileName ;
    //文件的 mime，需要根据文档查询
    private String mMime ;
    //需要上传的图片资源，因为这里测试为了方便起见，直接把 bigmap 传进来，真正在项目中一般不会这般做，而是把图片的路径传过来，在这里对图片进行二进制转换
    private File mfile ;


    public FormImg(File file) {
        this.mfile = file;
    }

    public String getName() {
//        return mName;
//测试，把参数名称写死
        return "uploadimg" ;
    }

    public String getFileName() {
        //测试，直接写死文件的名字
        return "test.png";
    }
    //对图片进行二进制转换
    public byte[] getValue() throws IOException {
        FileInputStream fs = new FileInputStream(mfile);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = fs.read(buffer))) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        fs.close();
        return outStream.toByteArray();
    }
    //因为我知道是 png 文件，所以直接根据文档查的
    public String getMime() {
        return "image/png";
    }


}
