package com.dryoxapps.fortnite_rank.service.fortnite;

import android.os.AsyncTask;
import java.util.Objects;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStatistics;

/**
 * This class implements the FortniteApiService interface.
 *
 * @author Drioux.Guidry
 */
public class FortniteApiServiceProvider extends AsyncTask<String, Void, PlayerStatistics> {

  private RestTemplate fortniteClient = new RestTemplate();

  private static String API_URL = "https://api.fortnitetracker.com/v1/profile/";
  private static String API_KEY_KEY = "TRN-Api-Key";
  private static String API_KEY = "a4565ca8-67aa-4f3e-8f7b-734c9f2c49da";
  private static String SLASH_KEY = "/";
  private static String PLAYER_NOT_FOUND = "Player Not Found";
  private static String ERROR_KEY = "error";
  private static int PLAYER_PLATFORM_INDEX = 0;
  private static int PLAYER_NAME_INDEX = 1;

  DataDownloadListener dataDownloadListener;

  /**
   * Setter method.
   *
   * @param dataDownloadListener The Asynchronous handler
   */
  public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
    this.dataDownloadListener = dataDownloadListener;
  }

  /**
   * Executes before doInBackground()
   */
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
  }

  /**
   * Asynchronously performs a GET request and serializes the JSON using RestTemplate and POJOs
   *
   * @param params A string array containing playerPlatform and playerName
   * @return A POJO containing statistical data for a player
   */
  @Override
  protected PlayerStatistics doInBackground(String... params) {

    // Set the headers for the request
    HttpHeaders headers = new HttpHeaders();
    headers.set(API_KEY_KEY, API_KEY);
    HttpEntity entity = new HttpEntity(headers);

    // Perform the GET request
    ResponseEntity<PlayerStatistics> respEntity = fortniteClient
        .exchange(BuildUri(params[PLAYER_PLATFORM_INDEX], params[PLAYER_NAME_INDEX]),
            HttpMethod.GET, entity, PlayerStatistics.class);

    // Check for No Player Found
    if (respEntity.getBody().getAdditionalProperties().get(ERROR_KEY) != null) {
      return null;
    } else {
      return respEntity.getBody();
    }
  }

  /**
   * Executes once doInBackground() completes.
   *
   * @param result A POJO containing a player's statistical data.
   */
  @Override
  protected void onPostExecute(PlayerStatistics result) {
    if (result != null) {
      dataDownloadListener.PlayerFoundHandler(result);
    } else {
      dataDownloadListener.PlayerNotFoundHandler();
    }
  }

  /**
   * The interface defining the asynchronous handler methods.
   */
  public static interface DataDownloadListener {

    void PlayerFoundHandler(PlayerStatistics playerStatistics);

    void PlayerNotFoundHandler();
  }

  /**
   * Utility method that builds the uri to connect to the Fortnite-Tracker API
   *
   * @param playerPlatform The platform that a player uses (PC, XBOX, PSN)
   * @param playerName Name of a player
   * @return The uri
   */
  protected String BuildUri(String playerPlatform, String playerName) {
    return API_URL + playerPlatform + SLASH_KEY + playerName;
  }
}
