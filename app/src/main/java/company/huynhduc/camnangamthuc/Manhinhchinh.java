package company.huynhduc.camnangamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import company.luongchung.camnangamthuc.R;

public class Manhinhchinh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh);
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(4000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent intent= new Intent(Manhinhchinh.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        bamgio.start();
    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }
    }
