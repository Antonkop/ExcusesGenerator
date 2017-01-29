package com.kop.anton.excusesgnerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String EXCUSE_TEXT_KEY = "excuse text";
    private TextView excuseText;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, excuseText.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.app_name)));
                return true;
            }
        });
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (excuseText.getText().length() > 0) {
            outState.putCharSequence(EXCUSE_TEXT_KEY, excuseText.getText());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        excuseText = (TextView) findViewById(R.id.excuse_text);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXCUSE_TEXT_KEY)) {
                excuseText.setText(savedInstanceState.getCharSequence(EXCUSE_TEXT_KEY));
            }
        }
        final EditText nameInput = (EditText) findViewById(R.id.name_input_ET);
        nameInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    excuseText.setText(prepareExcuse(nameInput.getText()));
                }
                return false;
            }
        });
        Button getExcuseBTN = (Button) findViewById(R.id.generate_excuse_btn);
        getExcuseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excuseText.setText(prepareExcuse(nameInput.getText()));
            }
        });
    }

    private String prepareExcuse(Editable name) {
        ExcusesGenerator generator = new ExcusesGenerator(this);
        if (name.length() > 0) {
            return generator.generateExcuse(name.toString());
        } else {
            return generator.generateExcuse(null);
        }
    }
}
