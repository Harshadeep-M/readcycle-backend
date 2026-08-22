import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'

function BookDetails() {
  const { id } = useParams()
  const [book, setBook] = useState(null)

  useEffect(() => {
    fetch(`http://localhost:8080/books/${id}`)
      .then(response => response.json())
      .then(data => {
        setBook(data)
      })
      .catch(error => {
        console.error('Error fetching book:', error)
      })
  }, [id])

  if (!book) {
    return <p className="loading">Loading...</p>
  }

  return (
    <div className="book-details-page">

      <Link to="/books" className="back-link">
        ← Back to Books
      </Link>

      <div className="book-details-card">

        <div className="details-cover">
          <span>BOOK</span>
        </div>

        <div className="details-info">

          <p className="details-label">BOOK DETAILS</p>

          <h1>{book.title}</h1>

          <h2>by {book.author}</h2>

          <p className="details-description">
            {book.description}
          </p>

          <div className="book-status">
            <span
              className={
                book.available
                  ? 'status available'
                  : 'status unavailable'
              }
            >
              {book.available ? 'Available' : 'Not Available'}
            </span>
          </div>

          <p className="owner-info">
            Owner ID: {book.ownerId}
          </p>

          <button className="exchange-button">
            Request Exchange
          </button>

        </div>

      </div>

    </div>
  )
}

export default BookDetails