package com.crud.tasks.config;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrelloConfigTestSuite {

    @Mock
    TrelloConfig trelloConfig;

    @Test
    public void testTrelloApiEndPoint() {
        //Given
        String apiEndPoint = "http://api.trello.com/1";
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn(apiEndPoint);
        //When
        String expectedApiEndPoint = trelloConfig.getTrelloApiEndpoint();
        //
        Assert.assertEquals(apiEndPoint, expectedApiEndPoint);
    }

    @Test
    public void testTrelloAppKey() {
        //Given
        String trelloAppKey = "47db89560fd287066743cbb02a7dd176";
        when(trelloConfig.getTrelloAppKey()).thenReturn(trelloAppKey);
        //When
        String expectedTrelloAppKey = trelloConfig.getTrelloAppKey();
        //
        Assert.assertEquals(trelloAppKey, expectedTrelloAppKey);
    }

    @Test
    public void testTrelloToken() {
        String trelloAppToken = "e9682863b388458415f77e016c7c68af27d480d7dae4d2f0a1585c902982791c";
        when(trelloConfig.getTrelloToken()).thenReturn(trelloAppToken);
        //When
        String expectedTrelloAppToken = trelloConfig.getTrelloToken();
        //
        Assert.assertEquals(trelloAppToken, expectedTrelloAppToken);
    }
}
