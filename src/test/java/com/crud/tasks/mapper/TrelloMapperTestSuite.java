package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    TrelloMapper trelloMapper;

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card 1", "Taking orders", "Three", "14");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertNotEquals(trelloCardDto, trelloCard);
        Assert.assertEquals(trelloCardDto.getName(), trelloCard.getName());
        Assert.assertEquals("Taking orders", trelloCard.getDescription());
        Assert.assertEquals("Three", trelloCard.getPos());
        Assert.assertEquals("14", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card 2", "Taking orders 2", "One", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertNotEquals(trelloCard, trelloCardDto);
        Assert.assertEquals(trelloCard.getName(), trelloCardDto.getName());
        Assert.assertEquals("Taking orders 2", trelloCardDto.getDescription());
        Assert.assertEquals("One", trelloCardDto.getPos());
        Assert.assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "List 1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "List 2", false);
        List<TrelloListDto> trelloListDtoList = Arrays.asList(trelloListDto1, trelloListDto2);
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDtoList);
        //Then
        Assert.assertNotEquals(trelloListDtoList, trelloList);
        Assert.assertEquals(2, trelloList.size());
        Assert.assertEquals("1", trelloList.get(0).getId());
        Assert.assertEquals("List 2", trelloList.get(1).getName());
        Assert.assertFalse(trelloList.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "List 3", false);
        TrelloList trelloList2 = new TrelloList("5", "List 5", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertNotEquals(trelloLists, trelloListDtoList);
        Assert.assertEquals(2, trelloListDtoList.size());
        Assert.assertEquals("1", trelloListDtoList.get(0).getId());
        Assert.assertEquals("List 5", trelloListDtoList.get(1).getName());
        Assert.assertTrue(trelloListDtoList.get(1).isClosed());
    }

    @Test
    public void testMapToBoard() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("2", "List 2", true);
        List<TrelloListDto> trelloListDtoList = Arrays.asList(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Board 1", trelloListDtoList);
        //When
        TrelloBoard trelloBoard = trelloMapper.mapToBoard(trelloBoardDto);
        //Then
        Assert.assertNotEquals(trelloBoardDto,trelloBoard);
        Assert.assertEquals("1", trelloBoard.getId());
        Assert.assertEquals("2", trelloBoard.getLists().get(0).getId());
        Assert.assertEquals(1, trelloBoard.getLists().size());
        Assert.assertEquals("Board 1", trelloBoard.getName());
        Assert.assertEquals("List 2", trelloBoard.getLists().get(0).getName());
        Assert.assertTrue(trelloBoard.getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("2", "List 2", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("3", "List 3", false);
        List<TrelloListDto> trelloListDtoList1 = Arrays.asList(trelloListDto1);
        List<TrelloListDto> trelloListDtoList2 = Arrays.asList(trelloListDto1, trelloListDto2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "Board 1", trelloListDtoList1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "Board 2", trelloListDtoList2);
        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(trelloBoardDto1,trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        Assert.assertNotEquals(trelloBoardDtoList, trelloBoardList);
        Assert.assertEquals(2, trelloBoardList.size());
        Assert.assertEquals("1", trelloBoardList.get(0).getId());
        Assert.assertEquals("2", trelloBoardList.get(0).getLists().get(0).getId());
        Assert.assertEquals("Board 2", trelloBoardList.get(1).getName());
        Assert.assertEquals("List 3", trelloBoardList.get(1).getLists().get(1).getName());
        Assert.assertEquals(2, trelloBoardList.get(1).getLists().size());
        Assert.assertTrue(trelloBoardList.get(1).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("4", "List 4", false);
        TrelloList trelloList2 = new TrelloList("5", "List 5", true);
        List<TrelloList> trelloListList1 = Arrays.asList(trelloList1);
        List<TrelloList> trelloListList2 = Arrays.asList(trelloList1, trelloList2);
        TrelloBoard trelloBoard1 = new TrelloBoard("3", "Board 3", trelloListList1);
        TrelloBoard trelloBoard2 = new TrelloBoard("4", "Board 4", trelloListList2);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard1,trelloBoard2);
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        Assert.assertNotEquals(trelloBoardList, trelloBoardDtoList);
        Assert.assertEquals(2, trelloBoardDtoList.size());
        Assert.assertEquals("3", trelloBoardDtoList.get(0).getId());
        Assert.assertEquals("4", trelloBoardDtoList.get(0).getLists().get(0).getId());
        Assert.assertEquals("Board 4", trelloBoardDtoList.get(1).getName());
        Assert.assertEquals("List 5", trelloBoardDtoList.get(1).getLists().get(1).getName());
        Assert.assertEquals(2, trelloBoardDtoList.get(1).getLists().size());
        Assert.assertFalse(trelloBoardDtoList.get(1).getLists().get(0).isClosed());

    }
}
