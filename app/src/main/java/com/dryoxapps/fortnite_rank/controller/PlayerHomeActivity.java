package com.dryoxapps.fortnite_rank.controller;

import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.CalculateAvgPercentile;
import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.CalculateAvgValue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import com.dryoxapps.fortnite_rank.R;
import com.dryoxapps.fortnite_rank.controller.domain.RankName;
import com.dryoxapps.fortnite_rank.controller.domain.RankPercentile;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameModes;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameMode;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStats;
import com.google.gson.Gson;
import java.text.DecimalFormat;
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
  private static double NON_EXISTENT = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_home);

    // Serialize the Bundle
    PlayerStats playerStats = SerializeBundle(getIntent().getExtras());

    // Set player name
    SetTextView(findViewById(R.id.playerName), playerStats.getEpicUserHandle());

    // Display default statistics (Overall)
    DisplayOverallStats(playerStats.getGameModes());

    // Create the Segmented Button Group, and change the value based on selection.
    SegmentedButtonGroup segmentedButtonGroup = findViewById(R.id.segmentedButtonGroup);
    segmentedButtonGroup
        .setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
          @Override
          public void onClickedButtonPosition(int position) {
            switch (position) {
              case 0:
                DisplayOverallStats(playerStats.getGameModes());
                break;
              case 1:
                DisplayStatistics(playerStats.getGameModes().getSoloStatistics());
                break;
              case 2:
                DisplayStatistics(playerStats.getGameModes().getDuoStatistics());
                break;
              case 3:
                DisplayStatistics(playerStats.getGameModes().getSquadStatistics());
                break;
            }
          }
        });
  }

  /**
   * Utility method that serializes the bundle object, which is a JSON object.
   *
   * @param bundle The bundle object
   * @return A PlayerStats POJO containing all player statistics
   */
  protected PlayerStats SerializeBundle(Bundle bundle) {
    String jsonMyObject = null;

    if (bundle != null) {
      jsonMyObject = bundle.getString(BUNDLE_OBJECT_NAME);
    }

    return (new Gson().fromJson(jsonMyObject, PlayerStats.class));
  }

  /**
   * Displays statistics for the overall tab.
   *
   * @param gameModes The GameModes POJO.
   */
  public void DisplayOverallStats(GameModes gameModes) {

    try {
      /* Set Profile rank image and values */
      SetRankIcon(findViewById(R.id.profileRank),
          RankPercentile.fromDouble(CalculateAvgPercentile(gameModes, "trnRating")));
      SetRankName(findViewById(R.id.profileRankName),
          RankPercentile.fromDouble(CalculateAvgPercentile(gameModes, "trnRating")));

      /* Set GameModeStats image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKdImage),
          CalculateAvgPercentile(gameModes, "kd"),
          findViewById(R.id.statKdVal), ToString(CalculateAvgValue(gameModes, "kd")));

      /* Set Kills image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKillsImage),
          CalculateAvgPercentile(gameModes, "kills"),
          findViewById(R.id.statKillsVal), ToString(CalculateAvgValue(gameModes, "kills")));

      /* Set Wins image and values */
      SetTableRowTextAndImage(findViewById(R.id.statWinsImage),
          CalculateAvgPercentile(gameModes, "wins"), findViewById(R.id.statWinsVal),
          ToString(CalculateAvgValue(gameModes, "wins")));

      /* Set Kpg image and values */
      SetTableRowTextAndImage(findViewById(R.id.statKpgImage),
          CalculateAvgPercentile(gameModes, "kpg"),
          findViewById(R.id.statKpgVal), ToString(CalculateAvgValue(gameModes, "kpg")));
    } catch (Exception e) {
      LOGGER.error("Error displaying Overall GameModes: " + e.getMessage().toString());
    }
  }

  /**
   * Displays the statistics for the currently selected game mmode.
   */
  protected void DisplayStatistics(GameMode gameMode) {
    try {
      if (gameMode != null) {
      /* Set Profile rank image and values */
        SetRankIcon(findViewById(R.id.profileRank),
            RankPercentile.fromDouble(gameMode.getTrnRating().getPercentile()));
        SetRankName(findViewById(R.id.profileRankName),
            RankPercentile.fromDouble(gameMode.getTrnRating().getPercentile()));

      /* Set GameModeStats image and values */
        SetTableRowTextAndImage(findViewById(R.id.statKdImage),
            gameMode.getKd().getPercentile(),
            findViewById(R.id.statKdVal), gameMode.getKd().getValue());

      /* Set Kills image and values */
        SetTableRowTextAndImage(findViewById(R.id.statKillsImage),
            gameMode.getKills().getPercentile(),
            findViewById(R.id.statKillsVal), gameMode.getKills().getValue());

      /* Set Wins image and values */
        SetTableRowTextAndImage(findViewById(R.id.statWinsImage),
            gameMode.getWins().getPercentile(),
            findViewById(R.id.statWinsVal), gameMode.getWins().getValue());

      /* Set Kpg image and values */
        SetTableRowTextAndImage(findViewById(R.id.statKpgImage),
            gameMode.getKpg().getPercentile(),
            findViewById(R.id.statKpgVal), gameMode.getKpg().getValue());

      } else {
        DisplayNotRanked();
      }
    } catch (Exception e) {
      LOGGER.error("Error Displaying Statistics: " + e.getMessage().toString());
    }
  }

  /**
   * Utility method that sets all text views and images to Not-Ranked values.
   */
  protected void DisplayNotRanked() {
    SetRankIcon(findViewById(R.id.profileRank), RankPercentile.fromDouble(NON_EXISTENT));
    SetRankName(findViewById(R.id.profileRankName), RankPercentile.fromDouble(NON_EXISTENT));


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
      LOGGER.error(
          "Error displaying values for Table Row " + String.valueOf(image.getTag()) + " :" + e
              .getMessage().toString());
    }
  }

  /**
   * Retrieves a list of POJOs for solo, duo, and squad statistics
   * objects.
   *
   * @param gameModes A POJO containing the game modes.
   * @return A list a game mode objects.
   */
  protected GameMode[] GetGameModes(GameModes gameModes) {
    GameMode[] gameMode = {gameModes.getSoloStatistics(),
        gameModes.getDuoStatistics(),
        gameModes.getSquadStatistics()};

    return gameMode;
  }

  /**
   * Stringifies a double value to two decimal places, and removes any trailing zero's, if any.
   *
   * @param value The double value.
   * @return A stringified double value.
   */
  protected String ToString(double value) {
    DecimalFormat format = new DecimalFormat("0.#");
    return String.format("%.2f", format.format(value));
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
        case NOT_RANKED:
          image.setImageResource(R.drawable.question);
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
        case NOT_RANKED:
          textView.setText(RankName.NOT_RANKED.toString());
          break;
      }
    }
  }
}
