package com.alevel.textboard.dao.user;

import com.alevel.textboard.dao.DAO;
import com.alevel.textboard.model.Persisted;
import com.alevel.textboard.model.User;

import java.util.Optional;

public interface UserDAO extends DAO<Long, User> {

    Optional<Persisted<Long, User>> findByEmail(String email);

    Optional<Persisted<Long, User>> findByNickname(String nickname);

}
