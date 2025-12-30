package com.ch.shop.model.board;

import java.util.List;

import com.ch.shop.dto.Board;

public interface BoardDAO {
	public void insert(Board board);
	public List selectAll();
	public Board select(int board_id);
	public void update(Board board);
	public void delete(int board_id);
}
