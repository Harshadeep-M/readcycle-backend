import { Link } from 'react-router-dom'

function Navbar() {
  return (
    <nav className="navbar">
      <div className="logo">
        ReadCycle
      </div>

      <div className="nav-links">
        <Link to="/">Home</Link>
        <Link to="/books">Browse Books</Link>
        <Link to="/wishlist">Wishlist</Link>
        <Link to="/login">Login</Link>
      </div>
    </nav>
  )
}

export default Navbar