package com.bookmarkit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookmarkit.domain.Bookmark;

/**
 * Created by 580782 on 11/29/2015.
 */

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
    Bookmark findOne(Long id);
    List<Bookmark> findAll();
}
