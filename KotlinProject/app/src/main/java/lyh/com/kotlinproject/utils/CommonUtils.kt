package lyh.com.kotlinproject.utils

import android.content.Context
import android.content.SharedPreferences
import lyh.com.kotlinproject.BaseApplication
import lyh.com.kotlinproject.kotlinapi.KotlinApi

/**
 * Created by 小黑 on 2017/9/22.
 */
class CommonUtils {
    companion object {
        fun getEditorPreference(mcontext:Context) : SharedPreferences.Editor{
            return mcontext.getSharedPreferences(KotlinApi.preference.preferenceName, Context.MODE_PRIVATE).edit()
        }

        fun notEmptyStr(str : String) : Boolean{
            return str!=null && str!=""
        }
    }
}