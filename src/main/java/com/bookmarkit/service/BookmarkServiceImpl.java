package com.bookmarkit.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmarkit.domain.Bookmark;
import com.bookmarkit.form.BookmarkCreateForm;
import com.bookmarkit.repository.BookmarkRepository;

import javassist.NotFoundException;

/**
 * Created by 580782 on 11/29/2015.
 */

@Service
public class BookmarkServiceImpl implements BookmarkSerivce {

    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }
    
    @Transactional
    @Override
    public Bookmark create(BookmarkCreateForm form) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUrl(form.getUrl());
        bookmark.setDescription(form.getDescription());
        bookmark.setUser(form.getUser());
        return bookmarkRepository.save(bookmark);
    }

    @Transactional
	@Override
	public Bookmark deleteById(Long id) throws NotFoundException {
		Bookmark deleted = findById(id);
		bookmarkRepository.delete(deleted);
		return null;
	}

	@Transactional
	@Override
	public List<Bookmark> findAll() {
		return bookmarkRepository.findAll();
	}
	
	@Transactional
	@Override
	public Bookmark findById(Long id) throws NotFoundException {
		Bookmark bookmark = bookmarkRepository.findOne(id);
		
		if (bookmark == null) {
			throw new NotFoundException("No contact found with id: " + id);
		}
		return bookmark;
	}
	
	@Transactional
	@Override
	public Bookmark update(Bookmark updatedbookmark) throws NotFoundException {
		Bookmark bookmark = findById(updatedbookmark.getId());
		bookmark.update(updatedbookmark.getUrl(), updatedbookmark.getDescription());
		return bookmark;
	}




}
