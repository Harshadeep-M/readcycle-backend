function BookCard({ title, author }) {
  return (
    <div className="book-card">
      <div className="book-cover">
        <span>BOOK</span>
      </div>

      <div className="book-info">
        <h3>{title}</h3>
        <p>{author}</p>
        <button>View Book</button>
      </div>
    </div>
  )
}

export default BookCard