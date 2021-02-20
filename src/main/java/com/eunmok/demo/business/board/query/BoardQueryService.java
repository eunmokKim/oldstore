package com.eunmok.demo.business.board.query;

import com.eunmok.demo.business.board.domain.Board;
import com.eunmok.demo.business.board.domain.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class BoardQueryService {

    private final BoardRepository boardRepository;

    public BoardQueryService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardView getBoard(Long id){
        return convert(boardRepository.findById(id));
    }

    public List<BoardView> getBoardList(BoardSearchRequest req){
        return Optional.ofNullable(boardRepository.findAllByTitleContains(req.getTitle()))
                .map(list -> list.stream().map(this::convert).collect(toList()))
                .orElse(Collections.emptyList());
    }

    private BoardView convert(Board data){
        return BoardView.builder().id(data.getId()).content(data.getContent()).title(data.getTitle()).build();
    }

}
