package com.ssafy.cafe.model.dao;

import com.ssafy.cafe.model.dto.Customer;
import com.ssafy.cafe.model.dto.Interest;

import java.util.List;

public interface GoogleBookDao {
    List<Long> selectAllIsbn();
}
