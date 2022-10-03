package com.hib.data.repository;

import com.hib.data.entity.User;

public interface UserRepository extends Repository<Long, User>{
    User findUserByEmail(String email);
}
