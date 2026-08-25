import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { apiFetch } from '../api/api'

function Wishlist() {
  const [wishlist, setWishlist] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const userId = localStorage.getItem('userId')

    const fetchWishlist = async () => {
      try {
        const wishlistData = await apiFetch(`/wishlist/${userId}`)

        const wishlistWithBooks = await Promise.all(
          wishlistData.map(async item => {
            const book = await apiFetch(`/books/${item.bookId}`)

            return {
              wishlistId: item.id,
              ...book
            }
          })
        )

        setWishlist(wishlistWithBooks)
      } catch (error) {
        console.error('Error fetching wishlist:', error)
      } finally {
        setLoading(false)
      }
    }

    fetchWishlist()
  }, [])

  const handleRemove = async wishlistId => {
    try {
      await apiFetch(`/wishlist/${wishlistId}`, {
        method: 'DELETE'
      })

      setWishlist(
        wishlist.filter(item => item.wishlistId !== wishlistId)
      )
    } catch (error) {
      console.error('Error removing from wishlist:', error)
    }
  }

  if (loading) {
    return <p className="loading">Loading wishlist...</p>
  }

  return (
    <div className="wishlist-page">

      <div className="books-header">
        <p>YOUR COLLECTION</p>

        <h1>Wishlist</h1>

        <p>
          Books you want to read or exchange.
        </p>
      </div>

      {wishlist.length === 0 ? (
        <p className="no-results">
          Your wishlist is empty.
        </p>
      ) : (
        <div className="wishlist-grid">

          {wishlist.map(item => (
            <div
              className="wishlist-card"
              key={item.wishlistId}
            >

              <div className="book-cover">
                <span>BOOK</span>
              </div>

              <h3>{item.title}</h3>

              <p className="wishlist-author">
                {item.author}
              </p>

              <p className="wishlist-description">
                {item.description}
              </p>

              <div className="wishlist-status">
                <span
                  className={
                    item.available
                      ? 'status available'
                      : 'status unavailable'
                  }
                >
                  {item.available
                    ? 'Available'
                    : 'Not Available'}
                </span>
              </div>

              <div className="wishlist-actions">

                <Link to={`/books/${item.id}`}>
                  <button>
                    View Book
                  </button>
                </Link>

                <button
                  onClick={() =>
                    handleRemove(item.wishlistId)
                  }
                >
                  Remove
                </button>

              </div>

            </div>
          ))}

        </div>
      )}

    </div>
  )
}

export default Wishlist