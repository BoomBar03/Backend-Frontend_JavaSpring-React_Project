const fetchMovie = async ({ queryKey }) => {
  const id = queryKey[1];
  const apiRes = await fetch(`http://localhost:8080/movie/v1/${id}`);

  if (!apiRes.ok) {
    throw new Error(`details/${id} fetch not ok`);
  }

  return apiRes.json();
};

export default fetchMovie;
