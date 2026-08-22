import BookCard from './BookCard'

function FeaturedBooks() {
  return (
    <section className="featured-books">
      <div className="section-heading">
        <p>EXPLORE</p>
        <h2>Featured Books</h2>
      </div>

      <div className="book-grid">
        <BookCard
          title="Atomic Habits"
          author="James Clear"
        />

        <BookCard
          title="The Psychology of Money"
          author="Morgan Housel"
        />

        <BookCard
          title="1984"
          author="George Orwell"
        />

        <BookCard
          title="The Alchemist"
          author="Paulo Coelho"
        />
      </div>
    </section>
  )
}

export default FeaturedBooks