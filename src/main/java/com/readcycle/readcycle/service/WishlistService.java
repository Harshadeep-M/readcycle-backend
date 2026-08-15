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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.readcycle.readcycle.repository.UserRepository;
import com.readcycle.readcycle.entity.User;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public WishlistService(WishlistRepository wishlistRepository,
                           BookRepository bookRepository,
                           UserRepository userRepository) {

        this.wishlistRepository = wishlistRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // ADD BOOK TO WISHLIST
    public WishlistResponseDTO addToWishlist(WishlistRequestDTO request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        System.out.println("JWT EMAIL = " + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        boolean alreadyExists =
                wishlistRepository.existsByUserIdAndBookId(
                        user.getId(),
                        book.getId()
                );

        if (alreadyExists) {
            throw new IllegalArgumentException(
                    "Book is already in the wishlist"
            );
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(user.getId());
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