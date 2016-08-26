package com.example.fuyifang.androidtv.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by fuyifang on 2016/8/3.
 */
public class Blurbitmap {

    private static final float BITMAP_SCALE = 0.4f;

    private static final float BLUR_RADIUS = 10f;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blur(Context context, Bitmap bitmap){
        //计算图片缩小后的宽度
        int width = Math.round(bitmap.getWidth() *BITMAP_SCALE);
        int heigh = Math.round(bitmap.getHeight()*BITMAP_SCALE);
        //将缩小后的图片作为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap,width,heigh,false);
        //创建一张渲染后的图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        //创建renderScript内核对象
        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //由于RenderScript并没有使用vm来分配内存，所以使用的是Allocation类来创建和分配内存空间。
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        //设置渲染的模糊程度，25f是最大模糊度
        intrinsicBlur.setRadius(BLUR_RADIUS);
        //设置blurscript对象输入内存
        intrinsicBlur.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        intrinsicBlur.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);




        return outputBitmap;


    }


}
