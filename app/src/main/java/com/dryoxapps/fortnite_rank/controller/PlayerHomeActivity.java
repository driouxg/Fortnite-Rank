package com.dryoxapps.fortnite_rank.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import com.dryoxapps.fortnite_rank.R;
import com.dryoxapps.fortnite_rank.controller.domain.RankName;
import com.dryoxapps.fortnite_rank.controller.domain.RankPercentile;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.CurrP10;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.CurrP2;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.CurrP9;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStatistics;
import com.google.gson.Gson;

/**
 * This class implements the home player screen.
 *
 * @author Drioux.Guidry
 */
public class PlayerHomeActivity extends AppCompatActivity {

  private static String BUNDLE_OBJECT_NAME = "myObject";
  private static double NON_EXISTENT = 500;
  private static double NUM_OF_GAME_MODES = 3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_home);

    // Serialize the Bundle
    PlayerStatistics playerStatistics = SerializeBundle(getIntent().getExtras());

    // Set player name
    SetTextView(findViewById(R.id.playerName), playerStatistics.getEpicUserHandle());

    // Display default statistics (Overall)
    DisplayOverallStats(playerStatistics);

    // Create the Segmented Button Group, and change the value based on selection.
    SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) findViewById(
        R.id.segmentedButtonGroup);
    segmentedButtonGroup
        .setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
          @Override
          public void onClickedButtonPosition(int position) {
            switch (position) {
              case 0:
                DisplayOverallStats(playerStatistics);
                break;
              case 1:
                DisplaySoloStats(playerStatistics.getStats().getCurrP2());
                break;
              case 2:
                DisplayDuoStats(playerStatistics.getStats().getCurrP10());
                break;
              case 3:
                DisplaySquadStats(playerStatistics.getStats().getCurrP9());
                break;
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

    SetRankIcon(findViewById(R.id.profileRank),
        RankPercentile.fromDouble(CalculateAverageRank(playerStatistics)));
    SetRankName(findViewById(R.id.profileRankName),
        RankPercentile.fromDouble(CalculateAverageRank(playerStatistics)));
    SetKillsTableRow(CalculateTotalKills(playerStatistics), NON_EXISTENT);
    SetKdTableRow(CalculateAverageKdVal(playerStatistics),
        CalculateAverageKdPercentile(playerStatistics));
    SetWinsTableRow(CalculateAverageWinsVal(playerStatistics),
        CalculateAverageWinsPercentile(playerStatistics));
    SetKpgTableRow(CalculateAverageKpgVal(playerStatistics),
        CalculateAverageKpgPercentile(playerStatistics));
  }

  /**
   * Displays statistics for the solo tab.
   *
   * @param soloStats The solo-gamemode POJO.
   */
  public void DisplaySoloStats(CurrP2 soloStats) {
    try {
      SetRankIcon(findViewById(R.id.profileRank),
          RankPercentile.fromDouble(soloStats.getTrnRating().getPercentile()));
      SetRankName(findViewById(R.id.profileRankName),
          RankPercentile.fromDouble(soloStats.getTrnRating().getPercentile()));
      SetKdTableRow(soloStats.getKd().getValue(), soloStats.getKd().getPercentile());
      SetKillsTableRow(soloStats.getKills().getValue(), NON_EXISTENT);
      SetWinsTableRow(soloStats.getTop1().getValue(), soloStats.getTop1().getPercentile());
      SetKpgTableRow(soloStats.getKpg().getValue(), soloStats.getKpg().getPercentile());
    } catch (Exception e) {
      System.out.println("Error Displaying Solo Stats: " + e.getMessage().toString());
    }
  }

  /**
   * Displays statistics for the duo tab.
   *
   * @param duoStats The duo-gamemode POJO.
   */
  public void DisplayDuoStats(CurrP10 duoStats) {
    SetRankIcon(findViewById(R.id.profileRank),
        RankPercentile.fromDouble(duoStats.getTrnRating().getPercentile()));
    SetRankName(findViewById(R.id.profileRankName),
        RankPercentile.fromDouble(duoStats.getTrnRating().getPercentile()));
    SetKdTableRow(duoStats.getKd().getValue(), duoStats.getKd().getPercentile());
    SetKillsTableRow(duoStats.getKills().getValue(), NON_EXISTENT);
    SetWinsTableRow(duoStats.getTop1().getValue(), duoStats.getTop1().getPercentile());
    SetKpgTableRow(duoStats.getKpg().getValue(), duoStats.getKpg().getPercentile());
  }

  /**
   * Displays statistics for the squad tab.
   *
   * @param squadStats The squad-gamemode POJO.
   */
  public void DisplaySquadStats(CurrP9 squadStats) {
    SetRankIcon(findViewById(R.id.profileRank),
        RankPercentile.fromDouble(squadStats.getTrnRating().getPercentile()));
    SetRankName(findViewById(R.id.profileRankName),
        RankPercentile.fromDouble(squadStats.getTrnRating().getPercentile()));
    SetKdTableRow(squadStats.getKd().getValue(), squadStats.getKd().getPercentile());
    SetKillsTableRow(squadStats.getKills().getValue(), NON_EXISTENT);
    SetWinsTableRow(squadStats.getTop1().getValue(), squadStats.getTop1().getPercentile());
    SetKpgTableRow(squadStats.getKpg().getValue(), squadStats.getKpg().getPercentile());
  }

  /**
   * Utility method that sets the value for a text view.
   *
   * @param textView The textview
   * @param text The string of text
   */
  protected void SetTextView(TextView textView, String text) {
    textView.setText(text);
  }

  /**
   * Utility method that sets the values and image KD.
   *
   * @param val The kill-death value
   * @param percentile The double value percentile
   */
  protected void SetKdTableRow(String val, double percentile) {
    SetRankIcon(findViewById(R.id.statKdImage),
        RankPercentile.fromDouble(percentile));

    SetTextView(findViewById(R.id.statKdVal), val);
  }

  /**
   * Utility method that sets the values and image Kills.
   *
   * @param val The kill-death value
   * @param percentile The double value percentile
   */
  protected void SetKillsTableRow(String val, double percentile) {
    SetRankIcon(findViewById(R.id.statKillsImage),
        RankPercentile.fromDouble(percentile));

    SetTextView(findViewById(R.id.statKillsVal), val);
  }

  /**
   * Utility method that sets the values and image Wins.
   *
   * @param val The kill-death value
   * @param percentile The double value percentile
   */
  protected void SetWinsTableRow(String val, double percentile) {
    SetRankIcon(findViewById(R.id.statWinsImage),
        RankPercentile.fromDouble(percentile));

    SetTextView(findViewById(R.id.statWinsVal), val);
  }

  /**
   * Utility method that sets the values and image Kpg.
   *
   * @param val The kill-death value
   * @param percentile The double value percentile
   */
  protected void SetKpgTableRow(String val, double percentile) {
    SetRankIcon(findViewById(R.id.statKpgImage),
        RankPercentile.fromDouble(percentile));

    SetTextView(findViewById(R.id.statKpgVal), val);
  }

  /**
   * Utility method that calculates the percentile average for squad stats.
   *
   * @param playerStatistics The statistics for a player.
   * @return The average percentile.
   */
  protected double CalculateAverageRank(PlayerStatistics playerStatistics) {
    double sum = 0;

    sum += playerStatistics.getStats().getCurrP2().getTrnRating().getPercentile();
    sum += playerStatistics.getStats().getCurrP9().getTrnRating().getPercentile();
    sum += playerStatistics.getStats().getCurrP10().getTrnRating().getPercentile();

    return sum / NUM_OF_GAME_MODES;
  }

  /**
   * Utility method that calculates the total sum of kills;
   *
   * @param playerStatistics The statistics for a player
   * @return The total kills
   */
  protected String CalculateTotalKills(PlayerStatistics playerStatistics) {
    int sum = 0;

    sum += playerStatistics.getStats().getCurrP2().getKills().getValueInt();
    sum += playerStatistics.getStats().getCurrP9().getKills().getValueInt();
    sum += playerStatistics.getStats().getCurrP10().getKills().getValueInt();

    return Integer.toString(sum);
  }

  /**
   * Utility method that calculates the average percentile rank for Kd
   *
   * @param playerStatistics The statistics for a player
   * @return The average percentile for Kd
   */
  protected double CalculateAverageKdPercentile(PlayerStatistics playerStatistics) {
    double sum = 0;

    sum += playerStatistics.getStats().getCurrP2().getKd().getPercentile();
    sum += playerStatistics.getStats().getCurrP9().getKd().getPercentile();
    sum += playerStatistics.getStats().getCurrP10().getKd().getPercentile();

    return sum / NUM_OF_GAME_MODES;
  }

  /**
   * Utility method that calculates the average value for Kd.
   *
   * @param playerStatistics The statistics for a player
   * @return The average value for Kd.
   */
  protected String CalculateAverageKdVal(PlayerStatistics playerStatistics) {
    double sum = 0;

    sum += playerStatistics.getStats().getCurrP2().getKd().getValueDec();
    sum += playerStatistics.getStats().getCurrP9().getKd().getValueDec();
    sum += playerStatistics.getStats().getCurrP10().getKd().getValueDec();

    return String.format("%.2f", sum / NUM_OF_GAME_MODES);
  }

  /**
   * Utility method that calculates the average value for wins.
   *
   * @param playerStatistics The statistics for a player
   * @return The average value for wins
   */
  protected String CalculateAverageWinsVal(PlayerStatistics playerStatistics) {
    int sum = 0;

    sum += playerStatistics.getStats().getCurrP2().getTop1().getValueInt();
    sum += playerStatistics.getStats().getCurrP9().getTop1().getValueInt();
    sum += playerStatistics.getStats().getCurrP10().getTop1().getValueInt();

    return String.format("%.2f", sum / NUM_OF_GAME_MODES);
  }

  /**
   * Utility method that calculates the average percentile for for Wins
   *
   * @param playerStatistics The statistics for a player
   * @return The average percentile for wins
   */
  protected double CalculateAverageWinsPercentile(PlayerStatistics playerStatistics) {
    double sum = 0;

    sum += playerStatistics.getStats().getCurrP2().getTop1().getPercentile();
    sum += playerStatistics.getStats().getCurrP9().getTop1().getPercentile();
    sum += playerStatistics.getStats().getCurrP10().getTop1().getPercentile();

    return sum / NUM_OF_GAME_MODES;
  }

  /**
   * Utility method that calculates the average value for wins.
   *
   * @param playerStatistics The statistics for a player
   * @return The average value for Kpg
   */
  protected String CalculateAverageKpgVal(PlayerStatistics playerStatistics) {
    double sum = 0;

    sum += playerStatistics.getStats().getCurrP2().getKpg().getValueDec();
    sum += playerStatistics.getStats().getCurrP9().getKpg().getValueDec();
    sum += playerStatistics.getStats().getCurrP10().getKpg().getValueDec();

    return String.format("%.2f", sum / NUM_OF_GAME_MODES);
  }

  /**
   * Utility method that calculates the average percentile for for Kpg.
   *
   * @param playerStatistics The statistics for a player
   * @return The average percentile for Kpg
   */
  protected double CalculateAverageKpgPercentile(PlayerStatistics playerStatistics) {
    double sum = 0;

    sum += playerStatistics.getStats().getCurrP2().getKpg().getPercentile();
    sum += playerStatistics.getStats().getCurrP9().getKpg().getPercentile();
    sum += playerStatistics.getStats().getCurrP10().getKpg().getPercentile();

    return sum / NUM_OF_GAME_MODES;
  }

  /**
   * Sets the Profile rank image, based on the TRN-rating percentile.
   *
   * @param image The profile rank image
   * @param rankPercentile The double percentile value.
   */
  protected void SetRankIcon(ImageView image, RankPercentile rankPercentile) {
    if (rankPercentile != null) {
      switch (rankPercentile) {
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

  /**
   * Sets the Profile rank image, based on the TRN-rating percentile.
   *
   * @param textView The profile rank
   * @param rankPercentile The double percentile value.
   */
  protected void SetRankName(TextView textView, RankPercentile rankPercentile) {
    if (rankPercentile != null) {
      switch (rankPercentile) {
        case CHAMPION:
          textView.setText(RankName.CHAMPION.toString());
          break;
        case SOUL:
          textView.setText(RankName.SOUL.toString());
          break;
        case DIAMOND1:
          textView.setText(RankName.DIAMOND1.toString());
          break;
        case DIAMOND2:
          textView.setText(RankName.DIAMOND2.toString());
          break;
        case DIAMOND3:
          textView.setText(RankName.DIAMOND3.toString());
          break;
        case DIAMOND4:
          textView.setText(RankName.DIAMOND4.toString());
          break;
        case DIAMOND5:
          textView.setText(RankName.DIAMOND5.toString());
          break;
        case PLATINUM1:
          textView.setText(RankName.PLATINUM1.toString());
          break;
        case PLATINUM2:
          textView.setText(RankName.PLATINUM2.toString());
          break;
        case PLATINUM3:
          textView.setText(RankName.PLATINUM3.toString());
          break;
        case PLATINUM4:
          textView.setText(RankName.PLATINUM4.toString());
          break;
        case PLATINUM5:
          textView.setText(RankName.PLATINUM5.toString());
          break;
        case GOLD1:
          textView.setText(RankName.GOLD1.toString());
          break;
        case GOLD2:
          textView.setText(RankName.GOLD2.toString());
          break;
        case GOLD3:
          textView.setText(RankName.GOLD3.toString());
          break;
        case GOLD4:
          textView.setText(RankName.GOLD4.toString());
          break;
        case GOLD5:
          textView.setText(RankName.GOLD5.toString());
          break;
        case SILVER1:
          textView.setText(RankName.SILVER1.toString());
          break;
        case SILVER2:
          textView.setText(RankName.SILVER2.toString());
          break;
        case SILVER3:
          textView.setText(RankName.SILVER3.toString());
          break;
        case SILVER4:
          textView.setText(RankName.SILVER4.toString());
          break;
        case SILVER5:
          textView.setText(RankName.SILVER5.toString());
          break;
        case BRONZE1:
          textView.setText(RankName.BRONZE1.toString());
          break;
        case BRONZE2:
          textView.setText(RankName.BRONZE2.toString());
          break;
        case BRONZE3:
          textView.setText(RankName.BRONZE3.toString());
          break;
        case BRONZE4:
          textView.setText(RankName.BRONZE4.toString());
          break;
        case BRONZE5:
          textView.setText(RankName.BRONZE5.toString());
          break;
      }
    }
  }
}
