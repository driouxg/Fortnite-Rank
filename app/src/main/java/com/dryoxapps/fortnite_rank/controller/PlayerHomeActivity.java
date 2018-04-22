package com.dryoxapps.fortnite_rank.controller;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import com.dryoxapps.fortnite_rank.R;
import com.dryoxapps.fortnite_rank.controller.domain.RankPercentile;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.CurrP10;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.CurrP2;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.CurrP9;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStatistics;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.Stats;
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
              DisplaySoloStats(playerStatistics.getStats().getCurrP2());
            } else if (position == 2) {
              DisplayDuoStats(playerStatistics.getStats().getCurrP10());
            } else if (position == 3) {
              DisplaySquadStats(playerStatistics.getStats().getCurrP9());
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

    // Need to compute overall sums of percentiles for curr9 curr10 and curr2
  }

  /**
   * Displays statistics for the solo tab.
   *
   * @param soloStats The solo-gamemode POJO.
   */
  public void DisplaySoloStats(CurrP2 soloStats) {
    SetProfileRankIcon((ImageView) findViewById(R.id.profileRank),
        RankPercentile.fromDouble(soloStats.getTrnRating().getPercentile()));
  }

  /**
   * Displays statistics for the duo tab.
   *
   * @param duoStats The duo-gamemode POJO.
   */
  public void DisplayDuoStats(CurrP10 duoStats) {
    SetProfileRankIcon((ImageView) findViewById(R.id.profileRank),
        RankPercentile.fromDouble(duoStats.getTrnRating().getPercentile()));
  }

  /**
   * Displays statistics for the squad tab.
   *
   * @param squadStats The squad-gamemode POJO.
   */
  public void DisplaySquadStats(CurrP9 squadStats) {
    SetProfileRankIcon((ImageView) findViewById(R.id.profileRank),
        RankPercentile.fromDouble(squadStats.getTrnRating().getPercentile()));
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

  /**
   * Utility method that calculates the percentile average for squad stats.
   *
   * @param squadStats The statistics in squad gamemode
   * @return The average percentile for squad matches.
   */
  protected double CalculatePercentileAverage(CurrP9 squadStats) {
    double sum = 0;
    double items = 4;

    sum += squadStats.getKd().getPercentile();
    sum += squadStats.getKills().getPercentile();
    sum += squadStats.getKpg().getPercentile();
    sum += squadStats.getTop1().getPercentile();

    return sum / items;
  }

  /**
   * Sets the Profile rank image, based on the TRN-rating percentile.
   *
   * @param image The profile rank image
   * @param rankPercentile The double percentile value.
   */
  protected void SetProfileRankIcon(ImageView image, RankPercentile rankPercentile) {
    if (rankPercentile != null) {
      switch(rankPercentile) {
        case CHAMPION:
          image.setImageResource(R.drawable.champion);
          break;
        case SOUL:
          image.setImageResource(R.drawable.soul);
          break;
        case DIAMOND1:
          image.setImageResource(R.drawable.diamond1);
          break;
        case DIAMOND2:
          image.setImageResource(R.drawable.diamond2);
          break;
        case DIAMOND3:
          image.setImageResource(R.drawable.diamond3);
          break;
        case DIAMOND4:
          image.setImageResource(R.drawable.diamond4);
          break;
        case DIAMOND5:
          image.setImageResource(R.drawable.diamond5);
          break;
        case PLATINUM1:
          image.setImageResource(R.drawable.plat1);
          break;
        case PLATINUM2:
          image.setImageResource(R.drawable.plat2);
          break;
        case PLATINUM3:
          image.setImageResource(R.drawable.plat3);
          break;
        case PLATINUM4:
          image.setImageResource(R.drawable.plat4);
          break;
        case PLATINUM5:
          image.setImageResource(R.drawable.plat5);
          break;
        case GOLD1:
          image.setImageResource(R.drawable.gold1);
          break;
        case GOLD2:
          image.setImageResource(R.drawable.gold2);
          break;
        case GOLD3:
          image.setImageResource(R.drawable.gold3);
          break;
        case GOLD4:
          image.setImageResource(R.drawable.gold4);
          break;
        case GOLD5:
          image.setImageResource(R.drawable.gold5);
          break;
        case SILVER1:
          image.setImageResource(R.drawable.silver1);
          break;
        case SILVER2:
          image.setImageResource(R.drawable.silver2);
          break;
        case SILVER3:
          image.setImageResource(R.drawable.silver3);
          break;
        case SILVER4:
          image.setImageResource(R.drawable.silver4);
          break;
        case SILVER5:
          image.setImageResource(R.drawable.silver5);
          break;
        case BRONZE1:
          image.setImageResource(R.drawable.bronze1);
          break;
        case BRONZE2:
          image.setImageResource(R.drawable.bronze2);
          break;
        case BRONZE3:
          image.setImageResource(R.drawable.bronze3);
          break;
        case BRONZE4:
          image.setImageResource(R.drawable.bronze4);
          break;
        case BRONZE5:
          image.setImageResource(R.drawable.bronze5);
          break;
      }
    }
  }
}
