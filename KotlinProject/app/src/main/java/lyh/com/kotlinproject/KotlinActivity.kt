package lyh.com.kotlinproject


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import lyh.com.kotlinproject.kotlinapi.KotlinApi
import lyh.com.kotlinproject.utils.CommonUtils


class KotlinActivity : AppCompatActivity() ,View.OnClickListener{
    private lateinit var iv_text_del: ImageView
    private lateinit var et_user_name : EditText
    private lateinit var iv_text_del_pass: ImageView
    private lateinit var et_pass_word : EditText
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var cb_remember_password:CheckBox
    private var is_first = false


    override fun onClick(v: View?) {
        var viewid : Int = v!!.id
        when(viewid){
            R.id.iv_text_del -> _delText(1)
            R.id.iv_text_del_pass -> _delText(2)
            R.id.btn_login -> _login()
        }

    }

    private fun _login(){

        //登录成功后保存数据
        if (cb_remember_password.isChecked){
            _saveData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        _initWidget()

        _setOnClickListener()
        
        _initEditor()
    }


    private fun _saveData(){

        if(!is_first){//如果不是第一次登录，已经记录数据

        }else{
            editor = CommonUtils.getEditorPreference(this)
            editor.putString(KotlinApi.preference.LOGINUSERNAME,et_user_name.text.toString())
            editor.putString(KotlinApi.preference.LOGINPASSWORD,et_pass_word.text.toString())
            //登录跳转界面，finish这个界面
            _loginIn()
        }
    }


    private fun _initEditor(){
        editor = CommonUtils.getEditorPreference(this)
        var username = BaseApplication.getSharePreference().getString(KotlinApi.preference.LOGINUSERNAME,null)
        var password = BaseApplication.getSharePreference().getString(KotlinApi.preference.LOGINPASSWORD,null)
        if (CommonUtils.notEmptyStr(username) && CommonUtils.notEmptyStr(password)){
            et_user_name.setText(username)
            et_pass_word.setText(password)
            //登录跳转界面， finish这个界面

            _loginIn()
            is_first = true
        }else{
            is_first = false
        }
    }

    private fun _loginIn(){
        var mintent :Intent = Intent(this@KotlinActivity,MainKotlinActivity::class.java)
        startActivity(mintent)
        finish()
    }


    private fun _delText(type : Int){
        if(type == 1)
            et_user_name.setText("")
        else
            et_pass_word.setText("")
    }

    private fun _initWidget() : Boolean{
        try{
            iv_text_del = findViewById(R.id.iv_text_del) as ImageView
            iv_text_del_pass = findViewById(R.id.iv_text_del_pass) as ImageView
            et_user_name = findViewById(R.id.et_user_name) as EditText
            et_pass_word = findViewById(R.id.et_pass_word) as EditText
            cb_remember_password = findViewById(R.id.cb_remember_password) as CheckBox

            return true
        }catch (e : ExceptionInInitializerError){
            e.printStackTrace()
        }
        return false
    }


    private fun _setOnClickListener(){
        iv_text_del.setOnClickListener(this)
        iv_text_del_pass.setOnClickListener(this)


//        cb_remember_password.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->  })
    }
    
    


}
