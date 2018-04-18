package com.dryoxapps.fortnite_rank.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dryoxapps.fortnite_rank.R;
import com.dryoxapps.fortnite_rank.controller.domain.PlayerPlatform;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;

/**
 * This class implements the Main Activity.
 *
 * @author Drioux.Guidry
 */
public class MainActivity extends AppCompatActivity {

    private String playerPlatform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the Segmented Button Group
        SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) findViewById(R.id.segmentedButtonGroup);
        segmentedButtonGroup.setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
            @Override
            public void onClickedButtonPosition(int position) {
                if (position == 0) {
                    Toast.makeText(MainActivity.this, "PC", Toast.LENGTH_SHORT).show();
                    playerPlatform = PlayerPlatform.PC.toString();
                } else if (position == 1) {
                    Toast.makeText(MainActivity.this, "XBOX", Toast.LENGTH_SHORT).show();
                    playerPlatform = PlayerPlatform.XBOX.toString();
                } else if (position == 2) {
                    Toast.makeText(MainActivity.this, "PSN", Toast.LENGTH_SHORT).show();
                    playerPlatform = PlayerPlatform.PSN.toString();
                }
            }
        });
    }

    /**
     * Redirects the view to the player's home screen.
     *
     * @param view The corresponding view object for this activity class.
     */
    public void SearchForPlayer(View view) {
        Intent intent = new Intent(this, PlayerHomeActivity.class);
        String playerName = ((EditText) findViewById(R.id.searchName)).getText().toString();

        // Create bundle for data passing.
        Bundle bundle = new Bundle();

        // Put player's name in bundle.
        bundle.putString("playerName", playerName);

        // Put player's platform in bundle.
        bundle.putString("playerPlatform", playerPlatform);

        // Put the bundle in the intent.
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
