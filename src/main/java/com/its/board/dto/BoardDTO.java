package com.its.board.dto;

import com.its.board.entity.BoardEntity;
import lombok.Data;

@Data
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardPassword;
    private String boardContents;
    private int boardHits;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
    BoardDTO boardDTO = new BoardDTO();
    boardDTO.setBoardTitle(boardEntity.getBoardTitle());
    boardDTO.setBoardWriter(boardEntity.getBoardWriter());
    boardDTO.setBoardPassword(boardEntity.getBoardPassword());
    boardDTO.setBoardContents(boardEntity.getBoardContents());
    boardDTO.setBoardHits(boardEntity.getBoardHits());
    return boardDTO;
    }
}
