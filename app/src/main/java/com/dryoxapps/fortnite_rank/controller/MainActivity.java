package com.dryoxapps.fortnite_rank.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.dryoxapps.fortnite_rank.R;
import com.dryoxapps.fortnite_rank.controller.domain.PlayerPlatform;
import com.dryoxapps.fortnite_rank.service.fortnite.FortniteApiServiceProvider;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStatistics;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import com.google.gson.Gson;

/**
 * This class implements the Main Activity.
 *
 * @author Drioux.Guidry
 */
public class MainActivity extends AppCompatActivity {

  private FortniteApiServiceProvider fortniteApiServiceProvider = new FortniteApiServiceProvider();

  private String playerPlatform = PlayerPlatform.PC.toString();
  private static String ERROR_MESSAGE = "Player not found";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Create the Segmented Button Group, and change the value based on selection.
    SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) findViewById(
        R.id.segmentedButtonGroup);
    segmentedButtonGroup
        .setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
          @Override
          public void onClickedButtonPosition(int position) {
            if (position == 0) {
              playerPlatform = PlayerPlatform.PC.toString();
            } else if (position == 1) {
              playerPlatform = PlayerPlatform.XBOX.toString();
            } else if (position == 2) {
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

    try {
      fortniteApiServiceProvider
          .setDataDownloadListener(new FortniteApiServiceProvider.DataDownloadListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void PlayerFoundHandler(PlayerStatistics playerStatistics) {
              // Go to player page

              System.out.println("Player Found -----------------");
              PlayerFound(view, intent, playerStatistics);
            }

            @Override
            public void PlayerNotFoundHandler() {
              // Display error to user
              Toast.makeText(MainActivity.this, ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
              System.out.println("SOMETHING WENT WRONG !!!!!!!!!!!!");
            }
          });
      fortniteApiServiceProvider.execute(playerPlatform, playerName);


    } catch (Exception e) {
      System.out.println("EXCEPTION IN SEARCH PAGE: " + e.toString());
    }
  }

  /**
   * Redirects the user to the specified player page.
   *
   * @param view The view
   * @param intent The intent that will perform the redirection
   * @param playerStatistics The POJO containing player statistics.
   */
  protected void PlayerFound(View view, Intent intent, PlayerStatistics playerStatistics) {
    // Create bundle for data passing.
    Bundle bundle = new Bundle();

    intent.putExtra("myObject", new Gson().toJson(playerStatistics));

    // Put the bundle in the intent.
    intent.putExtras(bundle);

    // Redirect to PlayerHome page
    startActivity(intent);
  }
}
