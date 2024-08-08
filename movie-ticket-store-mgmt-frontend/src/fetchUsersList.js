async function fetchUsersList() {
  const res = await fetch("http://localhost:8080/user/v1/all");
  if (!res.ok) {
    throw new Error(`Users fetch not ok`);
  }

  return res.json();
}

export default fetchUsersList;
