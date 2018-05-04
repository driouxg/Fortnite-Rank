package com.dryoxapps.fortnite_rank.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the Main Activity.
 *
 * @author Drioux.Guidry
 */
public class MainActivity extends AppCompatActivity {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MainActivity.class);

  Timer timer;
  private FortniteApiServiceProvider fortniteApiServiceProvider;
  private boolean searchingForPlayer = false;
  private boolean timerRunning = false;
  private String playerPlatform = PlayerPlatform.PC.toString();
  private static String ERROR_MESSAGE = "Player not found";
  private static String ERROR_MESSAGE_TIMER = "Too many requests, wait 30 seconds.";
  private static String BUNDLE_OBJECT_NAME = "myObject";
  private static int THIRTY_SECONDS = 30000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Create the Segmented Button Group, and change the value based on selection.
    SegmentedButtonGroup segmentedButtonGroup = findViewById(R.id.segmentedButtonGroup);
    segmentedButtonGroup
        .setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
          @Override
          public void onClickedButtonPosition(int position) {
            switch (position) {
              case 0:
                playerPlatform = PlayerPlatform.PC.toString();
                break;
              case 1:
                playerPlatform = PlayerPlatform.XBOX.toString();
                break;
              case 2:
                playerPlatform = PlayerPlatform.PSN.toString();
                break;
            }
          }
        });
  }

  /**
   * Redirects the view to the player's home screen.
   */
  public void SearchForPlayer(View view) {
    if (!searchingForPlayer && !timerRunning) {
      StartTimer();
      PerformAsyncRequest();
    } else if (timerRunning) {
      DisplayErrorMessage(ERROR_MESSAGE_TIMER);
    }
  }

  /**
   * Performs an asynchronous request
   */
  protected void PerformAsyncRequest() {
    try {
      searchingForPlayer = true;
      fortniteApiServiceProvider = new FortniteApiServiceProvider();
      fortniteApiServiceProvider
          .setDataDownloadListener(new FortniteApiServiceProvider.DataDownloadListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void PlayerFoundHandler(PlayerStatistics playerStatistics) {
              PlayerFound(playerStatistics);
            }

            @Override
            public void PlayerNotFoundHandler() {
              PlayerNotFound();
            }
          });
      fortniteApiServiceProvider.execute(playerPlatform, GetPlayerName());

    } catch (Exception e) {
      LOGGER.error("Error during player search: " + e.getMessage().toString());
    }
  }

  /**
   * Utility method that gets the searched player name from the view.
   *
   * @return The name of a player from the view.
   */
  protected String GetPlayerName() {
    return ((EditText) findViewById(R.id.searchName)).getText().toString();
  }

  /**
   * Redirects the user to the specified player page.
   *
   * @param playerStatistics The POJO containing player statistics.
   */
  protected void PlayerFound(PlayerStatistics playerStatistics) {
    // Create bundle for data passing.
    Bundle bundle = new Bundle();

    System.out.println("PLAYER WAS FOUND!");

    Intent intent = new Intent(getApplicationContext(), PlayerHomeActivity.class);
    intent.putExtra(BUNDLE_OBJECT_NAME, new Gson().toJson(playerStatistics));

    // Put the bundle in the intent.
    intent.putExtras(bundle);

    // Redirect to PlayerHome page
    startActivity(intent);
  }

  /**
   * Displays an error message and resets the searchingForPlayer flag to false;
   */
  protected void PlayerNotFound() {
    DisplayErrorMessage(ERROR_MESSAGE);
    searchingForPlayer = false;
  }

  /**
   * Displays an error message to the user.
   *
   * @param errorMessage The error message to be displayed to the user.
   */
  protected void DisplayErrorMessage(String errorMessage) {
    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
  }

  /**
   * Creates a timer and sets the timer to perform an action
   */
  protected void StartTimer() {
    timer = new Timer();
    timerRunning = true;
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        timerRunning = false;
        timer.cancel();
      }
    }, THIRTY_SECONDS);
  }
}
