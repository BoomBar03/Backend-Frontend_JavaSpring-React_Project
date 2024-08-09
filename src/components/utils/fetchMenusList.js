async function fetchMenusList() {
  const res = await fetch("http://localhost:8080/cinema-bar/all");
  if (!res.ok) {
    throw new Error("Menus fetch not okay");
  }

  return res.json();
}

export default fetchMenusList;
