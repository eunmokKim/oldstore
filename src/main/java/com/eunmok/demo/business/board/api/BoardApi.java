package com.eunmok.demo.business.board.api;

import com.eunmok.demo.business.board.domain.BoardRequest;
import com.eunmok.demo.business.board.domain.BoardService;
import com.eunmok.demo.business.board.query.BoardSearchRequest;
import com.eunmok.demo.business.board.query.BoardView;
import com.eunmok.demo.business.board.query.BoardQueryService;
import com.eunmok.demo.business.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardApi {
    private final BoardQueryService boardQueryService;
    private final BoardService boardService;

    public BoardApi(BoardQueryService boardQueryService, BoardService boardService) {
        this.boardQueryService = boardQueryService;
        this.boardService = boardService;
    }

    @RequestMapping(path = "/board/create", method = RequestMethod.POST)
    public ApiResponse<BoardView> boardCreate(@RequestBody BoardRequest req){
        return ApiResponse.ok(boardService.create(req));
    }

    @RequestMapping(path = "/board/update", method = RequestMethod.POST)
    public ApiResponse<BoardView> boardUpdate(@RequestBody BoardRequest req){
        return ApiResponse.ok(boardService.update(req));
    }

    @RequestMapping(path = "/board/list", method = RequestMethod.GET)
    public ApiResponse<List<BoardView>> getBoardList(BoardSearchRequest req){
        return ApiResponse.ok(boardQueryService.getBoardList(req));
    }

    @RequestMapping(path = "/board/{id}", method = RequestMethod.GET)
    public ApiResponse<BoardView> getBoard(@PathVariable Long id){
        return ApiResponse.ok(boardQueryService.getBoard(id));
    }
}
