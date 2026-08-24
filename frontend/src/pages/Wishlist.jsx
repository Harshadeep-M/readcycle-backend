import { useEffect, useState } from 'react'
import { apiFetch } from '../api/api'

function Wishlist() {
  const [wishlist, setWishlist] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    // Temporary user ID.
    // We'll replace this with the authenticated user's ID.
    const userId = localStorage.getItem('userId')
    const fetchWishlist = async () => {
      try {
        const data = await apiFetch(`/wishlist/${userId}`)
        setWishlist(data)
      } catch (error) {
        console.error('Error fetching wishlist:', error)
      } finally {
        setLoading(false)
      }
    }

    fetchWishlist()
  }, [])

  if (loading) {
    return <p className="loading">Loading wishlist...</p>
  }

  return (
    <div className="wishlist-page">

      <div className="books-header">
        <p>YOUR COLLECTION</p>
        <h1>Wishlist</h1>
        <p>Books you want to read or exchange.</p>
      </div>

      {wishlist.length === 0 ? (
        <p className="no-results">
          Your wishlist is empty.
        </p>
      ) : (
        <div className="wishlist-grid">
          {wishlist.map(item => (
            <div className="wishlist-card" key={item.id}>
              <div className="book-cover">
                <span>BOOK</span>
              </div>

              <h3>Book ID: {item.bookId}</h3>

              <button>
                Remove
              </button>
            </div>
          ))}
        </div>
      )}

    </div>
  )
}

export default Wishlist