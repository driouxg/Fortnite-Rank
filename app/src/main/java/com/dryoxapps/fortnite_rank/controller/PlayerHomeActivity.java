package com.dryoxapps.fortnite_rank.controller;

import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.CalculateAvgPercentile;
import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.CalculateAvgRank;
import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.CalculateTotalNumberOfMatches;
import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.CalculateTotalRatingDelta;
import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.CalculateAvgValues;
import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.CalculateNumberOfGameModes;
import static com.dryoxapps.fortnite_rank.service.fortnite.RankCalculator.GetRecentMatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import com.dryoxapps.fortnite_rank.R;
import com.dryoxapps.fortnite_rank.controller.domain.RankName;
import com.dryoxapps.fortnite_rank.controller.domain.RankPercentile;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameModeStats;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameModes;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameMode;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStats;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.RecentMatch;
import com.google.gson.Gson;
import java.text.DecimalFormat;
import java.util.List;
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

  private static String TRN_RATING = "trnRating";
  private static String SCORE = "score";
  private static String WINS = "wins";
  private static String KD = "kd";
  private static String WIN_RATIO = "trnRating";
  private static String KILLS = "kills";
  private static String KPG = "kpg";
  private static String SCORE_PER_MATCH = "scorePerMatch";
  private static String SOLO_STATS = "p2";
  private static String DUO_STATS = "p10";
  private static String SQUAD_STATS = "p9";

  private static String YOUR_LAST_GAME = "Your last game ";
  private static String YOUR_LAST = "Your last ";
  private static String YIELDED = "yielded";
  private static String SPACE = " ";
  private static String RATING = "rating";
  private static String GAMES = "games";

  private static String NOT_RANKED_STRING = "Not Ranked";
  private static String BUNDLE_OBJECT_NAME = "myObject";
  private static double NOT_RANKED = 0;
  private static double NO_VALUE = 0;
  private static int NO_VALUE_INT = 0;
  private static int ONE = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_home);

    // Serialize the Bundle
    PlayerStats playerStats = SerializeBundle(getIntent().getExtras());

    // Set player name
    SetTextView(findViewById(R.id.playerName), playerStats.getEpicUserHandle());

    // Display default statistics (Overall)
    DisplayOverallStats(playerStats.getGameModes(), playerStats.getRecentMatches());

    // Create the Segmented Button Group, and change the value based on selection.
    SegmentedButtonGroup segmentedButtonGroup = findViewById(R.id.segmentedButtonGroup);
    segmentedButtonGroup
        .setOnClickedButtonPosition(new SegmentedButtonGroup.OnClickedButtonPosition() {
          @Override
          public void onClickedButtonPosition(int position) {
            switch (position) {
              case 0:
                DisplayOverallStats(playerStats.getGameModes(), playerStats.getRecentMatches());
                break;

              case 1:
                DisplayStatistics(playerStats.getGameModes().getSoloStatistics(),
                    playerStats.getRecentMatches(), SOLO_STATS);
                break;

              case 2:
                DisplayStatistics(playerStats.getGameModes().getDuoStatistics(),
                    playerStats.getRecentMatches(), DUO_STATS);
                break;

              case 3:
                DisplayStatistics(playerStats.getGameModes().getSquadStatistics(),
                    playerStats.getRecentMatches(), SQUAD_STATS);
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
  public void DisplayOverallStats(GameModes gameModes, List<RecentMatch> recentMatches) {

    try {
      if (CalculateNumberOfGameModes(gameModes) != NO_VALUE) {
        DisplayAvgRanked(gameModes, recentMatches);
      } else {
        DisplayNotRanked();
      }
    } catch (Exception e) {
      LOGGER.error("Error displaying Overall GameModes: " + e.getMessage().toString());
    }
  }

  /**
   * Displays the statistics for the currently selected game mmode.
   */
  protected void DisplayStatistics(GameMode gameMode, List<RecentMatch> recentMatches, String key) {
    try {
      if (gameMode != null) {
        DisplayRanked(gameMode, recentMatches, key);
      } else {
        DisplayNotRanked();
      }
    } catch (Exception e) {
      LOGGER.error("Error Displaying Statistics: " + e.getMessage().toString());
    }
  }

  /**
   * Utility method that displays the overall, average ranked stats.
   *
   * @param gameModes A GameModes POJO containing
   */
  protected void DisplayAvgRanked(GameModes gameModes, List<RecentMatch> recentMatches) {
    /* Set Profile rank image, name, and number */
    SetRankIcon(findViewById(R.id.profileRank),
        RankPercentile.fromDouble(CalculateAvgPercentile(gameModes, TRN_RATING)));
    SetRankName(findViewById(R.id.profileRankName),
        RankPercentile.fromDouble(CalculateAvgPercentile(gameModes, TRN_RATING)));
    SetRankName(findViewById(R.id.profileRankNumber),
        RankPercentile.fromDouble(CalculateAvgRank(gameModes, TRN_RATING)));

    /* Set Rating image and values */
    SetTableRowValues(findViewById(R.id.statRatingImage), findViewById(R.id.statRatingVal),
        CalculateAvgValues(gameModes, TRN_RATING));

      /* Set GameModeStats image and values */
    SetTableRowValues(findViewById(R.id.statKdImage), findViewById(R.id.statKdVal),
        CalculateAvgValues(gameModes, KD));

      /* Set Kills image and values */
    SetTableRowValues(findViewById(R.id.statKillsImage), findViewById(R.id.statKillsVal),
        CalculateAvgValues(gameModes, KILLS));

      /* Set Wins image and values */
    SetTableRowValues(findViewById(R.id.statWinsImage), findViewById(R.id.statWinsVal),
        CalculateAvgValues(gameModes, WINS));

      /* Set Kpg image and values */
    SetTableRowValues(findViewById(R.id.statKpgImage), findViewById(R.id.statKpgVal),
        CalculateAvgValues(gameModes, KPG));

    /* Set Score image and values */
    SetTableRowValues(findViewById(R.id.statScoreImage), findViewById(R.id.statScoreVal),
        CalculateAvgValues(gameModes, SCORE));

    /* Set Score Per Match image and values */
    SetTableRowValues(findViewById(R.id.statScorePerMatchImage),
        findViewById(R.id.statScorePerMatchVal), CalculateAvgValues(gameModes, SCORE_PER_MATCH));

    /* Set MatchHistory image and values */
    SetTableRowValues(findViewById(R.id.matchHistoryImage), findViewById(R.id.matchHistoryVal),
        CalculateTotalRatingDelta(recentMatches),
        CalculateTotalNumberOfMatches(recentMatches));
  }

  /**
   * Utility method that displays the rank names, images, and values for various statistics in the
   * User Interface.
   *
   * @param gameMode The GameMode POJO.
   */
  protected void DisplayRanked(GameMode gameMode, List<RecentMatch> recentMatches, String key) {
    /* Set Profile rank image and values */
    SetRankIcon(findViewById(R.id.profileRank),
        RankPercentile.fromDouble(gameMode.getTrnRating().getPercentile()));
    SetRankName(findViewById(R.id.profileRankName),
        RankPercentile.fromDouble(gameMode.getTrnRating().getPercentile()));
    SetTextView(findViewById(R.id.profileRankNumber), ToString(gameMode.getTrnRating().getRank()));

    /* Set Rating image and values */
    SetTableRowValues(findViewById(R.id.statRatingImage), findViewById(R.id.statRatingVal),
        gameMode.getTrnRating());

    /* Set Kd image and values */
    SetTableRowValues(findViewById(R.id.statKdImage), findViewById(R.id.statKdVal),
        gameMode.getKd());

    /* Set Kills image and values */
    SetTableRowValues(findViewById(R.id.statKillsImage), findViewById(R.id.statKillsVal),
        gameMode.getKills());

    /* Set Wins image and values */
    SetTableRowValues(findViewById(R.id.statWinsImage), findViewById(R.id.statWinsVal),
        gameMode.getWins());

    /* Set Kpg image and values */
    SetTableRowValues(findViewById(R.id.statKpgImage), findViewById(R.id.statKpgVal),
        gameMode.getKpg());

    /* Set Score image and values */
    SetTableRowValues(findViewById(R.id.statScoreImage), findViewById(R.id.statScoreVal),
        gameMode.getScore());

    /* Set Score Per Match image and values */
    SetTableRowValues(findViewById(R.id.statScorePerMatchImage),
        findViewById(R.id.statScorePerMatchVal), gameMode.getScorePerMatch());

    /* Set Match History image and values */
    SetTableRowValues(findViewById(R.id.matchHistoryImage), findViewById(R.id.matchHistoryVal),
        GetRecentMatch(recentMatches, key).getTrnRatingChange(),
        GetRecentMatch(recentMatches, key).getMatches());
  }

  /**
   * Utility method that sets all text views and images to Not-Ranked values.
   */
  protected void DisplayNotRanked() {
    SetRankIcon(findViewById(R.id.profileRank), RankPercentile.fromDouble(NOT_RANKED));
    SetRankName(findViewById(R.id.profileRankName), RankPercentile.fromDouble(NOT_RANKED));
    SetRankName(findViewById(R.id.profileRankNumber), RankPercentile.fromDouble(NOT_RANKED));

    /* Set Rating image and values */
    SetTableRowValues(findViewById(R.id.statRatingImage), findViewById(R.id.statRatingVal), null);

    /* Set Kd image and values */
    SetTableRowValues(findViewById(R.id.statKdImage), findViewById(R.id.statKdVal), null);

    /* Set Kills image and values */
    SetTableRowValues(findViewById(R.id.statKillsImage), findViewById(R.id.statKillsVal),
        null);

    /* Set Wins image and values */
    SetTableRowValues(findViewById(R.id.statWinsImage), findViewById(R.id.statWinsVal), null);

    /* Set Kpg image and values */
    SetTableRowValues(findViewById(R.id.statKpgImage), findViewById(R.id.statKpgVal), null);

    /* Set Score image and values */
    SetTableRowValues(findViewById(R.id.statScoreImage), findViewById(R.id.statScoreVal), null);

    /* Set Score Per Match image and values */
    SetTableRowValues(findViewById(R.id.statScorePerMatchImage),
        findViewById(R.id.statScorePerMatchVal), null);

    /* Set Match history image and values */
    SetTableRowValues(findViewById(R.id.matchHistoryImage), findViewById(R.id.matchHistoryVal),
        NO_VALUE, NO_VALUE_INT);
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
   * @param textView The text view
   * @param gameModeStats The GameModeStats POJO.
   */
  protected void SetTableRowValues(ImageView image, TextView textView,
      GameModeStats gameModeStats) {
    try {
      if (gameModeStats == null) {
        SetRankIcon(image, RankPercentile.fromDouble(NOT_RANKED));
        SetTextView(textView, NOT_RANKED_STRING);
      } else {
        SetRankIcon(image, RankPercentile.fromDouble(gameModeStats.getPercentile()));
        SetTextView(textView, gameModeStats.getValue());
      }
    } catch (Exception e) {
      LOGGER.error(
          "Error displaying values for Table Row:" + e.getMessage().toString());
    }
  }

  /**
   * Sets the values for the match history row.
   *
   * @param image The image representinga rank
   * @param textView The text view
   * @param value The rating change value
   */
  protected void SetTableRowValues(ImageView image, TextView textView, double value,
      int numMatches) {
    try {
      SetRankIcon(image, value);
      SetTextView(textView, GeneratePhrase(value, numMatches));
    } catch (Exception e) {
      LOGGER.error("Error displaying match history: " + e.getMessage().toString());
    }
  }

  /**
   * Creates a phrase to express the change in rating based on recent matches.
   *
   * @param value The TRN-Rating value
   * @param numMatches The number of matches
   * @return A phrase expressing the change in rating
   */
  protected String GeneratePhrase(double value, int numMatches) {
    String phrase = "";

    if (numMatches == ONE) {
      phrase += YOUR_LAST_GAME + YIELDED + SPACE + ToString(value) + SPACE + RATING;
    } else {
      phrase +=
          YOUR_LAST + ToString(numMatches) + SPACE + GAMES + SPACE + YIELDED + SPACE + ToString(
              value) + SPACE + RATING;
    }

    return phrase;
  }

  /**
   * Stringifies a double value to one decimal places, and removes any trailing zero's, if any.
   *
   * @param value The double value.
   * @return A stringified double value.
   */
  protected String ToString(double value) {
    DecimalFormat format = new DecimalFormat("0.#");
    return format.format(value);
  }

  /**
   * Sets the match history table row image and values.
   *
   * @param image The match history image
   * @param value The double value for rating change
   */
  protected void SetRankIcon(ImageView image, double value) {
    if (value > 0) {
      image.setImageResource(R.drawable.green_plus);
    } else if (value == 0) {
      image.setImageResource(R.drawable.question);
    } else {
      image.setImageResource(R.drawable.red_plus);
    }
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
