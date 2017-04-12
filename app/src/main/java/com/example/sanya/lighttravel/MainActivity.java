package com.example.sanya.lighttravel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // start the phone call intent if that view is pressed
        findViewById(R.id.phonecall).setOnClickListener(this);
        // start the maps intent if that view is pressed
        findViewById(R.id.geoloc).setOnClickListener(this);
        // start the emailing intent if that view is pressed
        findViewById(R.id.email).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId())   {
            case R.id.phonecall:
                // start the phonecall
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1115489772"));
                startActivity(intent);
                break;
            case R.id.geoloc:
                // start the map thing
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 47.523988, 19.038137);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
                break;
            case R.id.email:
                // start the e-mail thing
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"letstravel@universewaits.ea"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Inquery about travelling possibilities");
                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
