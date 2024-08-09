import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { findAll } from "../store/searchParams-slice";
import useMenus from "./utils/useMenus";
import Results from "./Results";
import { NavbarElement } from "./Navbar";
import { Link } from "react-router-dom";
const MENUS = ["Snack", "Beverage", "Dessert"];

const SearchParams = () => {
  const [menu, setMenu] = useState("");
  const [filteredMenus, setFilteredMenus] = useState([]);
  const dispatch = useDispatch();
  const { menus = [], loading } = useMenus(); // Ensure menus defaults to an empty array

  // Filter menus whenever `menu` or `menus` changes
  useEffect(() => {
    if (menus.length) {
      setFilteredMenus(menu ? menus.filter((m) => m.menu === menu) : menus);
    } else {
      setFilteredMenus([]);
    }
  }, [menu, menus]);

  if (loading) {
    return (
      <div className="loading-pane">
        <h2 className="loader">🍿</h2>
      </div>
    );
  }

  return (
    <div className="search-params">
      <NavbarElement />
      <header>
        <h1>Available Menus</h1>
      </header>
      <header>
        <Link to="/home">Movie Ticket Store</Link>
      </header>
      <br></br>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          const formData = new FormData(e.target);
          const obj = {
            menu: formData.get("menu") ?? "",
            description: formData.get("description") ?? "",
            price: formData.get("price") ?? "",
          };
          dispatch(findAll(obj));
        }}
      >
        <label htmlFor="menu">
          Menu
          <select
            id="menu"
            name="menu"
            value={menu}
            onChange={(e) => {
              setMenu(e.target.value);
            }}
            onBlur={(e) => {
              setMenu(e.target.value);
            }}
          >
            <option value="">All</option>
            {MENUS.map((menu) => (
              <option key={menu} value={menu}>
                {menu}
              </option>
            ))}
          </select>
        </label>
      </form>
      <Results menus={filteredMenus} />
    </div>
  );
};

export default SearchParams;
