package com.readcycle.readcycle.controller;

import com.readcycle.readcycle.dto.WishlistRequestDTO;
import com.readcycle.readcycle.dto.WishlistResponseDTO;
import com.readcycle.readcycle.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // ADD TO WISHLIST
    @PostMapping
    public WishlistResponseDTO addToWishlist(
            @Valid @RequestBody WishlistRequestDTO request) {

        return wishlistService.addToWishlist(request);
    }

    // GET USER WISHLIST
    @GetMapping("/{userId}")
    public List<WishlistResponseDTO> getWishlistByUser(
            @PathVariable Long userId) {

        return wishlistService.getWishlistByUser(userId);
    }

    // REMOVE FROM WISHLIST
    @DeleteMapping("/{id}")
    public String removeFromWishlist(@PathVariable Long id) {

        wishlistService.removeFromWishlist(id);

        return "Book removed from wishlist successfully.";
    }

}