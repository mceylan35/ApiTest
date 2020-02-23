package com.omdbapi.test;

import com.omdbapi.common.ITestLogger;
import com.omdbapi.entities.Movie;
import com.omdbapi.entities.MovieDetail;
import com.omdbapi.entities.Search;
import com.omdbapi.common.EndPoint;
import com.omdbapi.common.TestLogger;
import com.omdbapi.trendyol.RestAssuredConfiguration;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
public class OmdbApiTest {

    protected ITestLogger testLogger;
    public OmdbApiTest(){
        testLogger = new TestLogger();
    }

    //Film adına göre apiye istek atıp eğer sonuç (200 OK) dönüyorsa filmin imdbId'sini döndürür
    public String getMovieById(String movieName){

        String filmId="";
        RequestSpecification requestSpecification=new RestAssuredConfiguration().getRequestSpecification();
        Response response=new RestAssuredConfiguration()
                .getResponse(requestSpecification,String.format("%s?s=%s&apiKey=%s", EndPoint.GET_FILM, movieName, EndPoint.API_KEY), HttpStatus.SC_OK);
        Movie movies=response.as(Movie.class, ObjectMapperType.GSON);

        String test="Harry Potter and the Sorcerer's Stone";
        for (Search item:movies.getSearch())
        {

            if (test.equals(item.getTitle()))
            {
                filmId=item.getImdbID();
                testLogger.info(item.getTitle(),"filmi başarıyla bulundu.");

            }

        }
        return filmId;

    }

    // Harry Potter and the Sorcerer's Stone filminin testini yaptım
    //Title, getYear, Released bilgilerinin doğruluğunu kontrol ettim.
    @Test
    public void searchFilmTest(){
        RequestSpecification requestSpecification =new RestAssuredConfiguration().getRequestSpecification();
        requestSpecification.queryParam("type","movie");
        requestSpecification.queryParam("plot","full");
        Response response=new RestAssuredConfiguration()
                .getResponse(requestSpecification,String.format("%s?i=%s&apiKey=%s", EndPoint.GET_FILM, getMovieById("Harry Potter"),EndPoint.API_KEY), HttpStatus.SC_OK);
        MovieDetail movieDetail= response.as(MovieDetail.class, ObjectMapperType.GSON);

        Assert.assertEquals("Harry Potter and the Sorcerer's Stone",movieDetail.getTitle(),"Filmin Başlığı");
        Assert.assertEquals("2001",movieDetail.getYear(),"Filmin Çıkış Yılı");
        Assert.assertEquals("16 Nov 2001",movieDetail.getReleased(),"Filmin Çıkış Tarihi");
        Assert.assertEquals(200,response.statusCode(),"Status Code");

        testLogger.info("Title :",movieDetail.getTitle());
        testLogger.info("Year :",movieDetail.getYear());
        testLogger.info("Released :",movieDetail.getReleased());
        testLogger.info("Status Code :",response.statusCode());


    }
}
