function SearchBar() {
  return (
    <section className="search-section">
      <h2>Find your next book</h2>

      <div className="search-bar">
        <input
          type="text"
          placeholder="Search by title or author..."
        />

        <button>Search</button>
      </div>
    </section>
  )
}

export default SearchBar