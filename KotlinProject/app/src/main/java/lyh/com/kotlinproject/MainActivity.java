package lyh.com.kotlinproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_jump_kotlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_jump_kotlin = (Button)findViewById(R.id.btn_jump_kotlin);


        btn_jump_kotlin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        switch (viewid){
            case R.id.btn_jump_kotlin:
                startActivity(new Intent(this,KotlinActivity.class));
                break;
        }
    }
}
