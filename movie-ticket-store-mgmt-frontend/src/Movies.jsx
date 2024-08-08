import React, { useEffect, useState } from "react";
import fetchMoviesList from "./fetchMovies";
import { Link } from "react-router-dom";
import { NavbarElement } from "./Navbar";
import "bootstrap/dist/css/bootstrap.min.css";

const MoviesElement = () => {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getMovies = async () => {
      try {
        const data = await fetchMoviesList();
        setMovies(data);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching movies:", error);
        setLoading(false);
      }
    };
    getMovies();
  }, []);

  if (loading) {
    return (
      <div className="loading-pane">
        <h2 className="loader">🎬</h2>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <div className="row">
        <NavbarElement />
        <header>
          <h1>Available Movies</h1>
        </header>
        <header>
          <Link to="/home">Movie Ticket Store</Link>
        </header>
        {movies.map((item) => (
          <div className="col-md-4 mb-4" key={item.id}>
            <div className="card h-100">
              <div className="card-body">
                <h5 className="card-title">{item.title}</h5>
                <p className="card-text">
                  <strong>Ticket Price:</strong> ${item.ticketPrice}
                </p>
                <p className="card-text">
                  <strong>Genre:</strong> {item.genre}
                </p>
                <Link to={`/details/${item.id}`} className="btn btn-primary">
                  View Details
                </Link>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MoviesElement;
