package com.bigPicture.backend.controller;

import com.bigPicture.backend.payload.response.BookInfoResponse;
import com.bigPicture.backend.service.FavoriteService;
import com.bigPicture.backend.security.CurrentUser;
import com.bigPicture.backend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    // 즐겨찾기 추가
    @PostMapping("/add/{bookId}")
    public ResponseEntity<?> addFavorite(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long bookId) {
        favoriteService.addFavorite(userPrincipal, bookId);
        return ResponseEntity.status(201).build();
    }

    // 즐겨찾기 삭제
    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<?> removeFavorite(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long bookId) {
        favoriteService.removeFavorite(userPrincipal, bookId);
        return ResponseEntity.status(204).build();
    }

    // 즐겨찾기한 책 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<BookInfoResponse>> getFavoriteBooks(@CurrentUser UserPrincipal userPrincipal) {
        List<BookInfoResponse> favoriteBooks = favoriteService.getFavoriteBooks(userPrincipal);
        return ResponseEntity.ok(favoriteBooks);
    }
}