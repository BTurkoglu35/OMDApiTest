package stepdefinition;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import java.util.List;



import static io.restassured.RestAssured.given;


public class OMDbStepDefinition {
       String imdbID;
       Response response;
       String url;
    @Given("Kullanici {string} icin request gonderir")
    public void kullaniciIcinSorguGonderir(String title) {
       url="http://www.omdbapi.com/?apikey=edd3d0e&s="+title;
        response =given().when().get(url);
    }
    @When("Kullanici  {string} filminin id sini alir")
    public void kullaniciFilmininIdSiniAlir(String film) {
        List<String> titles = response.jsonPath().getList("Search.Title");
        List<String> imdbIDS = response.jsonPath().getList("Search.imdbID");
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).equals(film)) {
                imdbID = imdbIDS.get(i);
            }}
    }


    @Then("Kullanici alinan imdbID ile request gonderir")
    public void kullaniciAlinanImdbIDIleRequestGonderir() {
        url="http://www.omdbapi.com/?apikey=edd3d0e&i="+imdbID;
        response =given().when().get(url);

    }

    @And("Kullanici gelen response ile dogrulama yapar")
    public void kullaniciGelenResponseIleDogrulamaYapar() {
        response.then().
                statusCode(200).
                 body("Title",equalTo("Harry Potter and the Sorcerer's Stone"),
                         "Year",equalTo("2001"),
                         "Released",equalTo("16 Nov 2001"));

    }


}



