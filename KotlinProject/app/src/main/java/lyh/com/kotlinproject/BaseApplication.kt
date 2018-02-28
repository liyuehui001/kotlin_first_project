package lyh.com.kotlinproject

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import lyh.com.kotlinproject.kotlinapi.KotlinApi

/**
 * Created by 小黑 on 2017/9/22.
 */
class BaseApplication : Application() {



    companion object {

        private lateinit var sharePre :SharedPreferences
        private lateinit var instance :BaseApplication
        private lateinit var mBaseContext :Context

        fun getmBaseApplication() : BaseApplication{
            return instance
        }

        fun getmBaseContext():Context{
            return mBaseContext
        }
        fun getSharePreference() : SharedPreferences{
            return this.sharePre
        }
    }


    override fun onCreate() {
        super.onCreate()

        sharePre = getSharedPreferences(KotlinApi.preference.preferenceName, Context.MODE_PRIVATE)
        instance = this
        mBaseContext = applicationContext
    }





}