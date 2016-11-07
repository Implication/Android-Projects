package trajon.felton.image_from_url;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = (ImageView) findViewById(R.id.iv);
    }

    @Override
   public boolean onCreateOptionMenu(Menu menu){
        //
        getMenuInflater().inflate(R.men);
    }
}
