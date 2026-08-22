import { Link } from 'react-router-dom'

function BookCard({ id, title, author }) {
  return (
    <div className="book-card">
      <div className="book-cover">
        <span>BOOK</span>
      </div>

      <div className="book-info">
        <h3>{title}</h3>
        <p>{author}</p>

        <Link to={`/books/${id}`}>
          <button>View Book</button>
        </Link>
      </div>
    </div>
  )
}

export default BookCard