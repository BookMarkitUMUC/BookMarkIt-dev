package com.bookmarkit.controller;

import com.bookmarkit.domain.Bookmark;
import com.bookmarkit.domain.User;
import com.bookmarkit.form.BookmarkCreateForm;
import com.bookmarkit.service.BookmarkSerivce;
import com.bookmarkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by 580782 on 11/29/2015.
 */

@RestController
public class BookmarkController {

    private final UserService userService;

    private final BookmarkSerivce bookmarkSerivce;

    @Autowired
    public BookmarkController(UserService userService, BookmarkSerivce bookmarkSerivce) {
        this.userService = userService;
        this.bookmarkSerivce = bookmarkSerivce;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public Bookmark addBookmark(@PathVariable("userId") Long userId, @RequestBody BookmarkCreateForm form) {

        System.out.println("adding bookmark");
        System.out.println(form.getUrl());
        System.out.println(form.getDescription());

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", userId)));

        form.setUser(user);
        Bookmark bookmark = bookmarkSerivce.create(form);
        System.out.println(bookmark);

        return bookmark;
    }
    
    @RequestMapping(value="/{bookmarkId}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id){
    	bookmarkSerivce.deleteById(id);
    	return new ModelAndView("redirect:/dashboard");
    }

/*    @RequestMapping("{userId}/view/{bookmarkId}")
    public Bookmark viewBookmark(@PathVariable("userId") Long userId, @PathVariable("bookmarkId") Long bookmarkId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", userId)));

        Bookmark bookmark =
    }*/

}
