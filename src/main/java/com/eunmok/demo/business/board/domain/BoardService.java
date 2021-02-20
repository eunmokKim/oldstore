package com.eunmok.demo.business.board.domain;

import com.eunmok.demo.business.board.query.BoardView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @javax.transaction.Transactional
    public BoardView create(BoardRequest req){
        Board board = boardRepository.save(Board.builder().title(req.getTitle()).content(req.getContent()).price(req.getPrice()).build());
        return convert(board);
    }

    @Transactional
    public BoardView update(BoardRequest req){
        Board board = boardRepository.findById(req.getId());
        board.updated(req);
        return convert(board);
    }

    private BoardView convert(Board board){
        return BoardView.builder()
                .id(board.getId())
                .content(board.getContent())
                .title(board.getTitle())
                .build();
    }
}
