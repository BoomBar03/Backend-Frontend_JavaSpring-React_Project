async function fetchMoviesList() {
  const res = await fetch("http://localhost:8080/movie/v1/all");
  if (!res.ok) {
    throw new Error(`Movies fetch not ok`);
  }

  return res.json();
}

export default fetchMoviesList;
