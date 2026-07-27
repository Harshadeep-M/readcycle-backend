package com.readcycle.readcycle.service;

import com.readcycle.readcycle.dto.WishlistRequestDTO;
import com.readcycle.readcycle.dto.WishlistResponseDTO;
import com.readcycle.readcycle.entity.Book;
import com.readcycle.readcycle.entity.Wishlist;
import com.readcycle.readcycle.exception.ResourceNotFoundException;
import com.readcycle.readcycle.mapper.WishlistMapper;
import com.readcycle.readcycle.repository.BookRepository;
import com.readcycle.readcycle.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;

    public WishlistService(WishlistRepository wishlistRepository,
                           BookRepository bookRepository) {

        this.wishlistRepository = wishlistRepository;
        this.bookRepository = bookRepository;
    }

    // ADD BOOK TO WISHLIST
    public WishlistResponseDTO addToWishlist(WishlistRequestDTO request) {

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(request.getUserId());
        wishlist.setBookId(book.getId());

        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        return WishlistMapper.toResponseDTO(savedWishlist);
    }

    // GET USER WISHLIST
    public List<WishlistResponseDTO> getWishlistByUser(Long userId) {

        List<Wishlist> wishlist = wishlistRepository.findByUserId(userId);

        List<WishlistResponseDTO> response = new ArrayList<>();

        for (Wishlist item : wishlist) {
            response.add(WishlistMapper.toResponseDTO(item));
        }

        return response;
    }

    // REMOVE FROM WISHLIST
    public void removeFromWishlist(Long id) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist item not found"));

        wishlistRepository.delete(wishlist);
    }

}