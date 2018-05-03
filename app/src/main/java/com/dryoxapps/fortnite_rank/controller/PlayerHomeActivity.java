package com.dryoxapps.fortnite_rank.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import com.dryoxapps.fortnite_rank.R;
import com.dryoxapps.fortnite_rank.controller.domain.RankName;
import com.dryoxapps.fortnite_rank.controller.domain.RankPercentile;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.DuoStatistics;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.SoloStatistics;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.SquadStatistics;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStatistics;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the home player screen.
 *
 * @author Drioux.Guidry
 */
public class PlayerHomeActivity extends AppCompatActivity {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(PlayerHomeActivity.class);

  private static String BUNDLE_OBJECT_NAME = "myObject";
  private static double NON_EXISTENT = 500;

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
                DisplaySoloStats(playerStatistics.getStats().getSoloStatistics());
                break;
              case 2:
                DisplayDuoStats(playerStatistics.getStats().getDuoStatistics());
                break;
              case 3:
                DisplaySquadStats(playerStatistics.getStats().getSquadStatistics());
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

    try {
      /* Set Profile rank image and values */
      //SetTableRowTextAndImage(findViewById(R.id.profileRank),
      //    CalculateAverageRank(playerStatistics), findViewById(R.id.profileRankName),
      //    Double.toString(CalculateAverageRank(playerStatistics)));

      /* Set Profile rank image and values */
      SetRankIcon(findViewById(R.id.profileRank),
          RankPercentile.fromDouble(CalculateAverageRank(playerStatistics)));
      SetRankName(findViewById(R.id.profileRankName),
          RankPercentile.fromDouble(CalculateAverageRank(playerStatistics)));

      /* Set Kd image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKdImage), CalculateAverageKdPercentile(playerStatistics),
          findViewById(R.id.statKdVal), CalculateAverageKdVal(playerStatistics));

      /* Set Kills image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKillsImage), NON_EXISTENT,
          findViewById(R.id.statKdVal), CalculateTotalKills(playerStatistics));

      /* Set Wins image and values */
      SetTableRowTextAndImage(findViewById(R.id.statWinsImage),
          CalculateAverageWinsPercentile(playerStatistics), findViewById(R.id.statWinsVal),
          CalculateAverageWinsVal(playerStatistics));

      /* Set Kpg image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKpgImage), CalculateAverageKpgPercentile(playerStatistics),
          findViewById(R.id.statKpgVal), CalculateAverageKpgVal(playerStatistics));


      //SetRankIcon(findViewById(R.id.profileRank),
      //    RankPercentile.fromDouble(CalculateAverageRank(playerStatistics)));
      //SetRankName(findViewById(R.id.profileRankName),
      //    RankPercentile.fromDouble(CalculateAverageRank(playerStatistics)));
      //SetKillsTableRow(CalculateTotalKills(playerStatistics), NON_EXISTENT);
      //SetKdTableRow(CalculateAverageKdVal(playerStatistics),
      //    CalculateAverageKdPercentile(playerStatistics));
      //SetWinsTableRow(CalculateAverageWinsVal(playerStatistics),
      //    CalculateAverageWinsPercentile(playerStatistics));
      //SetKpgTableRow(CalculateAverageKpgVal(playerStatistics),
      //    CalculateAverageKpgPercentile(playerStatistics));
    } catch (Exception e) {
      LOGGER.error("Error displaying Overall Stats: " + e.getMessage().toString());
    }
  }

  /**
   * Displays statistics for the solo tab.
   *
   * @param soloStats The solo-gamemode POJO.
   */
  public void DisplaySoloStats(SoloStatistics soloStats) {
    try {
      /* Set Profile rank image and values */
      SetRankIcon(findViewById(R.id.profileRank),
          RankPercentile.fromDouble(soloStats.getTrnRating().getPercentile()));
      SetRankName(findViewById(R.id.profileRankName),
          RankPercentile.fromDouble(soloStats.getTrnRating().getPercentile()));

      /* Set Kd image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKdImage), soloStats.getKd().getPercentile(),
          findViewById(R.id.statKdVal), soloStats.getKd().getValue());

      /* Set Kills image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKillsImage), NON_EXISTENT,
          findViewById(R.id.statKdVal), soloStats.getKills().getValue());

      /* Set Wins image and values */
      SetTableRowTextAndImage(findViewById(R.id.statWinsImage),
          soloStats.getTop1().getPercentile(), findViewById(R.id.statWinsVal),
          soloStats.getTop1().getValue());

      /* Set Kpg image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKpgImage), soloStats.getKpg().getPercentile(),
          findViewById(R.id.statKpgVal), soloStats.getKpg().getValue());
    } catch (Exception e) {
      LOGGER.error("Error Displaying Solo Stats: " + e.getMessage().toString());
    }
  }

  /**
   * Displays statistics for the duo tab.
   *
   * @param duoStats The duo-gamemode POJO.
   */
  public void DisplayDuoStats(DuoStatistics duoStats) {
    try {
      /* Set Profile rank image and values */
      SetRankIcon(findViewById(R.id.profileRank),
          RankPercentile.fromDouble(duoStats.getTrnRating().getPercentile()));
      SetRankName(findViewById(R.id.profileRankName),
          RankPercentile.fromDouble(duoStats.getTrnRating().getPercentile()));

      /* Set Kd image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKdImage), duoStats.getKd().getPercentile(),
          findViewById(R.id.statKdVal), duoStats.getKd().getValue());

      /* Set Kills image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKillsImage), NON_EXISTENT,
          findViewById(R.id.statKdVal), duoStats.getKills().getValue());

      /* Set Wins image and values */
      SetTableRowTextAndImage(findViewById(R.id.statWinsImage),
          duoStats.getTop1().getPercentile(), findViewById(R.id.statWinsVal),
          duoStats.getTop1().getValue());

      /* Set Kpg image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKpgImage), duoStats.getKpg().getPercentile(),
          findViewById(R.id.statKpgVal), duoStats.getKpg().getValue());
    } catch (Exception e) {
      LOGGER.error("Error displaying Duo Stats: " + e.getMessage().toString());
    }
  }

  /**
   * Displays statistics for the squad tab.
   *
   * @param squadStats The squad-gamemode POJO.
   */
  public void DisplaySquadStats(SquadStatistics squadStats) {
    try {
      /* Set profile rank and image values */
      SetRankIcon(findViewById(R.id.profileRank),
          RankPercentile.fromDouble(squadStats.getTrnRating().getPercentile()));
      SetRankName(findViewById(R.id.profileRankName),
          RankPercentile.fromDouble(squadStats.getTrnRating().getPercentile()));

      /* Set Profile rank image and values */
      SetTableRowTextAndImage(findViewById(R.id.profileRank),
          squadStats.getTrnRating().getPercentile(), findViewById(R.id.profileRankName),
          Double.toString(squadStats.getTrnRating().getPercentile()));

      /* Set Kd image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKdImage), squadStats.getKd().getPercentile(),
          findViewById(R.id.statKdVal), squadStats.getKd().getValue());

      /* Set Kills image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKillsImage), NON_EXISTENT,
          findViewById(R.id.statKdVal), squadStats.getKills().getValue());

      /* Set Wins image and values */
      SetTableRowTextAndImage(findViewById(R.id.statWinsImage),
          squadStats.getTop1().getPercentile(), findViewById(R.id.statWinsVal),
          squadStats.getTop1().getValue());

      /* Set Kpg image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKpgImage), squadStats.getKpg().getPercentile(),
          findViewById(R.id.statKpgVal), squadStats.getKpg().getValue());
    } catch (Exception e) {
      LOGGER.error("Error displaying Squad Stats: " + e.getMessage().toString());
    }
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
   * Sets the values for a specified row in table.
   *
   * @param image The image representing a rank.
   * @param percentile The percentile value.
   * @param textView The text view
   * @param text The text to be set in the textview.
   */
  protected void SetTableRowTextAndImage(ImageView image, double percentile, TextView textView,
      String text) {
    try {
      SetRankIcon(image, RankPercentile.fromDouble(percentile));
      SetTextView(textView, text);
    } catch (Exception e) {
      LOGGER.error("Error displaying values for Table Row " + String.valueOf(image.getTag()) + " :" + e.getMessage().toString());
    }
  }

  ///**
  // * Utility method that sets the values and image KD.
  // *
  // * @param val The kill-death value
  // * @param percentile The double value percentile
  // */
  //protected void SetKdTableRow(String val, double percentile) {
  //  SetRankIcon(findViewById(R.id.statKdImage),
  //      RankPercentile.fromDouble(percentile));
//
  //  SetTextView(findViewById(R.id.statKdVal), val);
  //}
//
  ///**
  // * Utility method that sets the values and image Kills.
  // *
  // * @param val The kill-death value
  // * @param percentile The double value percentile
  // */
  //protected void SetKillsTableRow(String val, double percentile) {
  //  SetRankIcon(findViewById(R.id.statKillsImage),
  //      RankPercentile.fromDouble(percentile));
//
  //  SetTextView(findViewById(R.id.statKillsVal), val);
  //}
//
  ///**
  // * Utility method that sets the values and image Wins.
  // *
  // * @param val The kill-death value
  // * @param percentile The double value percentile
  // */
  //protected void SetWinsTableRow(String val, double percentile) {
  //  SetRankIcon(findViewById(R.id.statWinsImage),
  //      RankPercentile.fromDouble(percentile));
//
  //  SetTextView(findViewById(R.id.statWinsVal), val);
  //}
//
  ///**
  // * Utility method that sets the values and image Kpg.
  // *
  // * @param val The kill-death value
  // * @param percentile The double value percentile
  // */
  //protected void SetKpgTableRow(String val, double percentile) {
  //  SetRankIcon(findViewById(R.id.statKpgImage),
  //      RankPercentile.fromDouble(percentile));
//
  //  SetTextView(findViewById(R.id.statKpgVal), val);
  //}

  /**
   * Utility method that calculates the percentile average for squad stats.
   *
   * @param playerStatistics The statistics for a player.
   * @return The average percentile.
   */
  protected double CalculateAverageRank(PlayerStatistics playerStatistics) {
    double sum = 0;
    double numberOfGameModes = 0;

    if (playerStatistics.getStats().getSoloStatistics().getTrnRating().getPercentile() != null) {
      sum += playerStatistics.getStats().getSoloStatistics().getTrnRating().getPercentile();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getSquadStatistics().getTrnRating().getPercentile() != null) {
      sum += playerStatistics.getStats().getSquadStatistics().getTrnRating().getPercentile();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getDuoStatistics().getTrnRating().getPercentile() != null) {
      sum += playerStatistics.getStats().getDuoStatistics().getTrnRating().getPercentile();
      numberOfGameModes++;
    }

    return sum / numberOfGameModes;
  }

  /**
   * Utility method that calculates the total sum of kills;
   *
   * @param playerStatistics The statistics for a player
   * @return The total kills
   */
  protected String CalculateTotalKills(PlayerStatistics playerStatistics) {
    int sum = 0;

    if (playerStatistics.getStats().getSoloStatistics().getKills().getValueInt() != null) {
      sum += playerStatistics.getStats().getSoloStatistics().getKills().getValueInt();
    }
    if (playerStatistics.getStats().getSquadStatistics().getKills().getValueInt() != null) {
      sum += playerStatistics.getStats().getSquadStatistics().getKills().getValueInt();
    }
    if (playerStatistics.getStats().getDuoStatistics().getKills().getValueInt() != null) {
      sum += playerStatistics.getStats().getDuoStatistics().getKills().getValueInt();
    }

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
    double numberOfGameModes = 0;

    if (playerStatistics.getStats().getSoloStatistics().getKd().getPercentile() != null) {
      sum += playerStatistics.getStats().getSoloStatistics().getKd().getPercentile();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getSquadStatistics().getKd().getPercentile() != null) {
      sum += playerStatistics.getStats().getSquadStatistics().getKd().getPercentile();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getDuoStatistics().getKd().getPercentile() != null) {
      sum += playerStatistics.getStats().getDuoStatistics().getKd().getPercentile();
      numberOfGameModes++;
    }

    return sum / numberOfGameModes;
  }

  /**
   * Utility method that calculates the average value for Kd.
   *
   * @param playerStatistics The statistics for a player
   * @return The average value for Kd.
   */
  protected String CalculateAverageKdVal(PlayerStatistics playerStatistics) {
    double sum = 0;
    double numberOfGameModes = 0;

    if (playerStatistics.getStats().getSoloStatistics().getKd().getValueDec() != null) {
      sum += playerStatistics.getStats().getSoloStatistics().getKd().getValueDec();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getSquadStatistics().getKd().getValueDec() != null) {
      sum += playerStatistics.getStats().getSquadStatistics().getKd().getValueDec();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getDuoStatistics().getKd().getValueDec() != null) {
      sum += playerStatistics.getStats().getDuoStatistics().getKd().getValueDec();
      numberOfGameModes++;
    }

    return String.format("%.2f", sum / numberOfGameModes);
  }

  /**
   * Utility method that calculates the average value for wins.
   *
   * @param playerStatistics The statistics for a player
   * @return The average value for wins
   */
  protected String CalculateAverageWinsVal(PlayerStatistics playerStatistics) {
    int sum = 0;
    int numberOfGameModes = 0;

    if (playerStatistics.getStats().getSoloStatistics().getTop1().getValueInt() != null) {
      sum += playerStatistics.getStats().getSoloStatistics().getTop1().getValueInt();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getSquadStatistics().getTop1().getValueInt() != null) {
      sum += playerStatistics.getStats().getSquadStatistics().getTop1().getValueInt();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getDuoStatistics().getTop1().getValueInt() != null) {
      sum += playerStatistics.getStats().getDuoStatistics().getTop1().getValueInt();
      numberOfGameModes++;
    }

    return String.format("%.2f", sum / numberOfGameModes);
  }

  /**
   * Utility method that calculates the average percentile for for Wins
   *
   * @param playerStatistics The statistics for a player
   * @return The average percentile for wins
   */
  protected double CalculateAverageWinsPercentile(PlayerStatistics playerStatistics) {
    double sum = 0;
    double numberOfGameModes = 0;

    if (playerStatistics.getStats().getSoloStatistics().getTop1().getPercentile() != null) {
      sum += playerStatistics.getStats().getSoloStatistics().getTop1().getPercentile();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getSquadStatistics().getTop1().getPercentile() != null) {
      sum += playerStatistics.getStats().getSquadStatistics().getTop1().getPercentile();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getDuoStatistics().getTop1().getPercentile() != null) {
      sum += playerStatistics.getStats().getDuoStatistics().getTop1().getPercentile();
      numberOfGameModes++;
    }

    return sum / numberOfGameModes;
  }

  /**
   * Utility method that calculates the average value for wins.
   *
   * @param playerStatistics The statistics for a player
   * @return The average value for Kpg
   */
  protected String CalculateAverageKpgVal(PlayerStatistics playerStatistics) {
    double sum = 0;
    double numberOfGameModes = 0;

    if (playerStatistics.getStats().getSoloStatistics().getKpg().getValueDec() != null) {
      sum += playerStatistics.getStats().getSoloStatistics().getKpg().getValueDec();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getSquadStatistics().getKpg().getValueDec() != null) {
      sum += playerStatistics.getStats().getSquadStatistics().getKpg().getValueDec();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getDuoStatistics().getKpg().getValueDec() != null) {
      sum += playerStatistics.getStats().getDuoStatistics().getKpg().getValueDec();
      numberOfGameModes++;
    }

    return String.format("%.2f", sum / numberOfGameModes);
  }

  /**
   * Utility method that calculates the average percentile for for Kpg.
   *
   * @param playerStatistics The statistics for a player
   * @return The average percentile for Kpg
   */
  protected double CalculateAverageKpgPercentile(PlayerStatistics playerStatistics) {
    double sum = 0;
    double numberOfGameModes = 0;

    if (playerStatistics.getStats().getSoloStatistics().getKpg().getPercentile() != null) {
      sum += playerStatistics.getStats().getSoloStatistics().getKpg().getPercentile();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getSquadStatistics().getKpg().getPercentile() != null) {
      sum += playerStatistics.getStats().getSquadStatistics().getKpg().getPercentile();
      numberOfGameModes++;
    }
    if (playerStatistics.getStats().getDuoStatistics().getKpg().getPercentile() != null) {
      sum += playerStatistics.getStats().getDuoStatistics().getKpg().getPercentile();
      numberOfGameModes++;
    }

    return sum / numberOfGameModes;
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
