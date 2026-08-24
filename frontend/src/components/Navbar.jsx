import { useState } from 'react'
import { Link } from 'react-router-dom'

function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(
    !!localStorage.getItem('token')
  )

  const handleLogout = () => {
    localStorage.removeItem('token')
    setIsLoggedIn(false)
  }

  return (
    <nav className="navbar">
      <div className="logo">
        ReadCycle
      </div>

      <div className="nav-links">
        <Link to="/">Home</Link>
        <Link to="/books">Browse Books</Link>
        <Link to="/wishlist">Wishlist</Link>

        {isLoggedIn ? (
          <button onClick={handleLogout} className="logout-button">
            Logout
          </button>
        ) : (
          <Link to="/login">Login</Link>
        )}
      </div>
    </nav>
  )
}

export default Navbar