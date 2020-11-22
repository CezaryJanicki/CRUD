package com.crud.tasks.domain;

import com.crud.tasks.trello.client.BoardNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloBoardDto {
    private String name;
    private String id;
}
