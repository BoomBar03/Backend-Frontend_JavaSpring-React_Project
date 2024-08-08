import { useParams, Link } from "react-router-dom";
import fetchMovie from "./fetchMovie";
import { useQuery } from "@tanstack/react-query";
import ErrorBoundary from "./ErrorBoundary";

const Details = () => {
  const { id } = useParams();
  const results = useQuery(["details", id], fetchMovie);

  if (results.isLoading) {
    return (
      <div className="loading-pane">
        <h2 className="loader">🎬</h2>
      </div>
    );
  }

  const movie = results.data;

  return (
    <div>
      <div>
        <header>
          <h1>Details</h1>
        </header>
        <header>
          <Link to="/home">Movie Ticket Store</Link>
        </header>
      </div>
      <div className="details">
        <div>
          <h2>Movie title: {movie.title}</h2>
          <h2>Movie Genre: {movie.genre}</h2>
          <h2>Movie Ticket Price: {movie.ticketPrice}$</h2>
          <h2>Description: {movie.details}</h2>
        </div>
      </div>
    </div>
  );
};

export default function DetailsErrorBoundary(props) {
  return (
    <ErrorBoundary>
      <Details {...props} />
    </ErrorBoundary>
  );
}
