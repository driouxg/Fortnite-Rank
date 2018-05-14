package com.dryoxapps.fortnite_rank.service.fortnite;

import android.os.AsyncTask;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.PlayerStats;

/**
 * This class implements the FortniteApiService interface.
 *
 * @author Drioux.Guidry
 */
public class FortniteApiServiceProvider extends AsyncTask<String, Void, PlayerStats> {

  private RestTemplate fortniteClient = new RestTemplate();

  private static String API_URL = "https://api.fortnitetracker.com/v1/profile/";
  private static String API_KEY_KEY = "TRN-Api-Key";
  private static String API_KEY = "a4565ca8-67aa-4f3e-8f7b-734c9f2c49da";
  private static String SLASH_KEY = "/";
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
  protected PlayerStats doInBackground(String... params) {

    // Perform the GET request
    ResponseEntity<PlayerStats> respEntity = PerformHttpRequest(params);

    return RetrieveBody(respEntity);
  }

  /**
   * Utility method that performs the GET request.
   *
   * @param params Contains a player's name and platform.
   * @return A Query POJO containing serialized JSON from the request.
   */
  protected ResponseEntity<PlayerStats> PerformHttpRequest(String... params) {
    return fortniteClient
        .exchange(BuildUri(params[PLAYER_PLATFORM_INDEX], params[PLAYER_NAME_INDEX]),
            HttpMethod.GET, SetHeaders(), PlayerStats.class);
  }

  /**
   * Utility method that checks for a null response body.
   *
   * @param respEntity An array list of PlayerStats POJOs.
   * @return A single POJO containing serialized JSON.
   */
  protected PlayerStats RetrieveBody(ResponseEntity<PlayerStats> respEntity) {
    if (respEntity.getBody().getAdditionalProperties().get(ERROR_KEY) != null) {
      return null;
    } else {
      return respEntity.getBody();
    }
  }

  /**
   * Sets the header values for the Http request.
   *
   * @return A HttpEntity with headers set.
   */
  protected HttpEntity SetHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(API_KEY_KEY, API_KEY);
    return new HttpEntity(headers);
  }

  /**
   * Executes once doInBackground() completes.
   *
   * @param result A POJO containing a player's statistical data.
   */
  @Override
  protected void onPostExecute(PlayerStats result) {
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

    void PlayerFoundHandler(PlayerStats playerStats);

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
