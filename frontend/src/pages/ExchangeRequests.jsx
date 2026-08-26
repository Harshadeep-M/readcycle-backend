import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { apiFetch } from '../api/api'

function ExchangeRequests() {
  const [requests, setRequests] = useState([])
  const [loading, setLoading] = useState(true)
  const [message, setMessage] = useState('')

  const userId = Number(localStorage.getItem('userId'))

  useEffect(() => {
    const fetchExchangeRequests = async () => {
      try {
        const data = await apiFetch('/exchange')

        // Only show requests involving the logged-in user
        const userRequests = data.filter(
          request =>
            request.requesterId === userId ||
            request.ownerId === userId
        )

        const requestsWithBooks = await Promise.all(
          userRequests.map(async request => {
            try {
              const book = await apiFetch(
                `/books/${request.bookId}`
              )

              return {
                ...request,
                book
              }
            } catch (error) {
              console.error(
                `Error fetching book ${request.bookId}:`,
                error
              )

              return {
                ...request,
                book: null
              }
            }
          })
        )

        setRequests(requestsWithBooks)
      } catch (error) {
        console.error(
          'Error fetching exchange requests:',
          error
        )

        setMessage('Could not load exchange requests.')
      } finally {
        setLoading(false)
      }
    }

    if (userId) {
      fetchExchangeRequests()
    } else {
      setLoading(false)
      setMessage('Please login to view exchange requests.')
    }
  }, [userId])

  const handleAccept = async requestId => {
    try {
      const updatedRequest = await apiFetch(
        `/exchange/${requestId}/accept`,
        {
          method: 'PUT'
        }
      )

      setRequests(
        requests.map(request =>
          request.id === requestId
            ? {
                ...request,
                status: updatedRequest.status
              }
            : request
        )
      )

      setMessage('Exchange request accepted.')
    } catch (error) {
      console.error(
        'Error accepting exchange request:',
        error
      )

      setMessage('Could not accept exchange request.')
    }
  }

  const handleReject = async requestId => {
    try {
      const updatedRequest = await apiFetch(
        `/exchange/${requestId}/reject`,
        {
          method: 'PUT'
        }
      )

      setRequests(
        requests.map(request =>
          request.id === requestId
            ? {
                ...request,
                status: updatedRequest.status
              }
            : request
        )
      )

      setMessage('Exchange request rejected.')
    } catch (error) {
      console.error(
        'Error rejecting exchange request:',
        error
      )

      setMessage('Could not reject exchange request.')
    }
  }

  if (loading) {
    return (
      <p className="loading">
        Loading exchange requests...
      </p>
    )
  }

  return (
    <div className="exchange-page">

      <div className="books-header">

        <p>READCYCLE</p>

        <h1>Exchange Requests</h1>

        <p>
          Manage your book exchange requests.
        </p>

      </div>

      {message && (
        <p className="exchange-page-message">
          {message}
        </p>
      )}

      {requests.length === 0 ? (
        <p className="no-results">
          You don't have any exchange requests.
        </p>
      ) : (
        <div className="exchange-grid">

          {requests.map(request => {

            const isOwner =
              request.ownerId === userId

            const isRequester =
              request.requesterId === userId

            return (
              <div
                className="exchange-card"
                key={request.id}
              >

                <div className="exchange-cover">
                  <span>BOOK</span>
                </div>

                <div className="exchange-info">

                  <p className="exchange-label">
                    EXCHANGE REQUEST
                  </p>

                  {request.book ? (
                    <>
                      <h2>
                        {request.book.title}
                      </h2>

                      <p className="exchange-author">
                        by {request.book.author}
                      </p>
                    </>
                  ) : (
                    <h2>
                      Book #{request.bookId}
                    </h2>
                  )}

                  <p>
                    <strong>Request ID:</strong>{' '}
                    {request.id}
                  </p>

                  <p>
                    <strong>Your role:</strong>{' '}
                    {isOwner
                      ? 'Owner'
                      : isRequester
                        ? 'Requester'
                        : 'Unknown'}
                  </p>

                  <div className="exchange-status">
                    <span
                      className={`exchange-status-badge ${request.status.toLowerCase()}`}
                    >
                      {request.status}
                    </span>
                  </div>

                  <p className="exchange-date">
                    Requested:{' '}
                    {new Date(
                      request.requestDate
                    ).toLocaleString()}
                  </p>

                  {isOwner &&
                    request.status === 'PENDING' && (
                      <div className="exchange-actions">

                        <button
                          onClick={() =>
                            handleAccept(request.id)
                          }
                        >
                          Accept
                        </button>

                        <button
                          onClick={() =>
                            handleReject(request.id)
                          }
                        >
                          Reject
                        </button>

                      </div>
                    )}

                  {isRequester &&
                    request.status === 'PENDING' && (
                      <p className="waiting-message">
                        Waiting for the owner to respond.
                      </p>
                    )}

                  <Link
                    to={`/books/${request.bookId}`}
                    className="view-book-link"
                  >
                    View Book
                  </Link>

                </div>

              </div>
            )
          })}

        </div>
      )}

    </div>
  )
}

export default ExchangeRequests