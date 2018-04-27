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
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class implements the Main Activity.
 *
 * @author Drioux.Guidry
 */
public class MainActivity extends AppCompatActivity {

  private FortniteApiServiceProvider fortniteApiServiceProvider;
  Timer timer = new Timer();

  private boolean searchingForPlayer = false;
  private boolean timerRunning = false;
  private String playerPlatform = PlayerPlatform.PC.toString();
  private static String ERROR_MESSAGE = "Player not found";
  private static String BUNDLE_OBJECT_NAME = "myObject";

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
    String playerName = ((EditText) findViewById(R.id.searchName)).getText().toString();

    if (!searchingForPlayer && !timerRunning) {
      searchingForPlayer = true;
      fortniteApiServiceProvider = new FortniteApiServiceProvider();

      try {
        fortniteApiServiceProvider
            .setDataDownloadListener(new FortniteApiServiceProvider.DataDownloadListener() {
              @SuppressWarnings("unchecked")
              @Override
              public void PlayerFoundHandler(PlayerStatistics playerStatistics) {
                // Go to player page
                Intent intent = new Intent(getApplicationContext(), PlayerHomeActivity.class);
                System.out.println("Player Found -----------------");
                PlayerFound(view, intent, playerStatistics);
                searchingForPlayer = false;
              }

              @Override
              public void PlayerNotFoundHandler() {
                // Display error to user
                Toast.makeText(MainActivity.this, ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                System.out.println("SOMETHING WENT WRONG !!!!!!!!!!!!");
                searchingForPlayer = false;
              }
            });
        fortniteApiServiceProvider.execute(playerPlatform, playerName);

      } catch (Exception e) {
        System.out.println("EXCEPTION IN SEARCH PAGE: " + e.toString());
      }
    }
    else {
      Toast.makeText(MainActivity.this, "You must wait", Toast.LENGTH_SHORT).show();
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

    intent.putExtra(BUNDLE_OBJECT_NAME, new Gson().toJson(playerStatistics));

    // Put the bundle in the intent.
    intent.putExtras(bundle);

    // Redirect to PlayerHome page
    startActivity(intent);
  }

  protected void StartTimer() {
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        // Your database code here
      }
    }, 2 * 60 * 1000);
  }
}
