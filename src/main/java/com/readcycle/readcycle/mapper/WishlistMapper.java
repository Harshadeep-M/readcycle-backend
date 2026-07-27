package com.readcycle.readcycle.mapper;

import com.readcycle.readcycle.dto.WishlistResponseDTO;
import com.readcycle.readcycle.entity.Wishlist;

public class WishlistMapper {

    public static WishlistResponseDTO toResponseDTO(Wishlist wishlist) {

        return new WishlistResponseDTO(
                wishlist.getId(),
                wishlist.getUserId(),
                wishlist.getBookId()
        );
    }
}