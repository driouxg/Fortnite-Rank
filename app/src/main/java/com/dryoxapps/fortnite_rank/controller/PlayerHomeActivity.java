package com.dryoxapps.fortnite_rank.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import com.dryoxapps.fortnite_rank.R;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStatistics;
import com.google.gson.Gson;

/**
 * This class implements the home player screen.
 *
 * @author Drioux.Guidry
 */
public class PlayerHomeActivity extends AppCompatActivity {

  private static String BUNDLE_OBJECT_NAME = "myObject";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_home);

    // Serialize the Bundle
    PlayerStatistics playerStatistics = SerializeBundle(getIntent().getExtras());

    // Set player name
    DisplayPlayerName(playerStatistics);

    // Create the Segmented Button Group, and change the value based on selection.
    SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) findViewById(
        R.id.segmentedButtonGroup);
    segmentedButtonGroup
        .setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
          @Override
          public void onClickedButtonPosition(int position) {
            if (position == 0) {
              DisplayOverallStats(playerStatistics);
            } else if (position == 1) {
              DisplaySoloStats(playerStatistics);
            } else if (position == 2) {
              DisplayDuoStats(playerStatistics);
            } else if (position == 3) {
              DisplaySquadStats(playerStatistics);
            }
          }
        });
  }

  /**
   * Utility method that serializes the bundle object, which is a JSON object.
   *
   * @param bundle The bundle object
   * @return A PlayerStatistics POJO containing all player statistics
   */
  protected PlayerStatistics SerializeBundle(Bundle bundle) {
    String jsonMyObject = null;

    if (bundle != null) {
      jsonMyObject = bundle.getString(BUNDLE_OBJECT_NAME);
    }

    return (new Gson().fromJson(jsonMyObject, PlayerStatistics.class));
  }

  /**
   * Displays statistics for the overall tab.
   *
   * @param playerStatistics The PlayerStatistics POJO.
   */
  public void DisplayOverallStats(PlayerStatistics playerStatistics) {

  }

  /**
   * Displays statistics for the solo tab.
   *
   * @param playerStatistics The PlayerStatistics POJO.
   */
  public void DisplaySoloStats(PlayerStatistics playerStatistics) {

  }

  /**
   * Displays statistics for the duo tab.
   *
   * @param playerStatistics The PlayerStatistics POJO.
   */
  public void DisplayDuoStats(PlayerStatistics playerStatistics) {

  }

  /**
   * Displays statistics for the squad tab.
   *
   * @param playerStatistics The PlayerStatistics POJO.
   */
  public void DisplaySquadStats(PlayerStatistics playerStatistics) {

  }

  /**
   * Displays the Player's Name in a textview
   *
   * @param playerStatistics The PlayerStatistics POJO
   */
  public void DisplayPlayerName(PlayerStatistics playerStatistics) {
    final TextView textViewToChange = (TextView) findViewById(R.id.playerName);
    textViewToChange.setText(playerStatistics.getEpicUserHandle());
  }
}
