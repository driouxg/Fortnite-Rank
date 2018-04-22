package com.dryoxapps.fortnite_rank.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dryoxapps.fortnite_rank.R;

/**
 * This class implements the home player screen.
 *
 * @author Drioux.Guidry
 */
public class PlayerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String playerName = bundle.getString("playerName");
        String playerPlatform = bundle.getString("playerPlatform");
    }
}
