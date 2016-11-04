package com.kodonho.android.rxandroid_basic07_rxbinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

import rx.Observable;

import static com.jakewharton.rxbinding.view.RxView.clicks;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Rx View Binding
        clicks(findViewById(R.id.bind))
            .map(event -> new Random().nextInt())
            .subscribe(
                   rand -> ((TextView)findViewById(R.id.textView)).setText("value="+rand)
            );

        // merge
        Observable<String> leftObs = RxView.clicks(findViewById(R.id.left))
                .map(e -> "left");
        Observable<String> rightObs = RxView.clicks(findViewById(R.id.right))
                .map(e -> "right");
        Observable.merge(leftObs, rightObs)
                .subscribe(
                  text -> Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                );

        // text Change event
        RxTextView.textChangeEvents((TextView)findViewById(R.id.search))
                .subscribe(
                        word -> Log.i("SEARCH","WORD:"+word.text().toString())
                );
    }
}
