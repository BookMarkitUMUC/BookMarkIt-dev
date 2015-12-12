package com.bookmarkit.service;

import java.util.List;
import java.util.Optional;

import com.bookmarkit.domain.Bookmark;
import com.bookmarkit.form.BookmarkCreateForm;
import com.bookmarkit.repository.BookmarkRepository;

import javassist.NotFoundException;

/**
 * Created by 580782 on 11/29/2015.
 */
public interface BookmarkSerivce {
    public Bookmark create(BookmarkCreateForm bookmarkCreateForm);
    
    public Bookmark deleteById(Long id) throws NotFoundException;
    
    public List<Bookmark> findAll();
    
    public Bookmark findById(Long id) throws NotFoundException;
    
    Bookmark update(Bookmark bookmark) throws NotFoundException;
    
    

    
}
