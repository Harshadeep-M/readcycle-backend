import { useEffect, useState } from 'react'
import BookCard from '../components/BookCard'

function Books() {
  const [books, setBooks] = useState([])

  useEffect(() => {
    fetch('http://localhost:8080/books')
      .then(response => response.json())
      .then(data => {
        setBooks(data.content)
      })
      .catch(error => {
        console.error('Error fetching books:', error)
      })
  }, [])

  return (
    <div className="books-page">
      <div className="books-header">
        <p>BROWSE</p>
        <h1>Browse Books</h1>
        <p>Find your next great read.</p>
      </div>

      <div className="books-grid">
        {books.map(book => (
         <BookCard
           key={book.id}
           id={book.id}
           title={book.title}
           author={book.author}
         />
        ))}
      </div>
    </div>
  )
}

export default Books