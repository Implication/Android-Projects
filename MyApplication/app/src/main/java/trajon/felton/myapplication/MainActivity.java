package trajon.felton.myapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    String url = "http://107.184.164.127:8040/Images/Card_Addiction/1.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView)findViewById(R.id.iv);
        loadImageFromURL(url);
    }

    private void loadImageFromURL(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher) //optimal
        .error(R.mipmap.ic_launcher) // if error
        .into(image, new com.squareup.picasso.Callback(){


            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
        }
        }
