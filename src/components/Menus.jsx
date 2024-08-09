import React, { useEffect, useState } from "react";
import fetchMenusList from "./utils/fetchMenusList";
import { Link } from "react-router-dom";
import { NavbarElement } from "./Navbar";
import "bootstrap/dist/css/bootstrap.min.css";

const MenusElement = () => {
  const [menus, setMenus] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getMenus = async () => {
      try {
        const data = await fetchMenusList();
        setMenus(data);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching menus:", error);
        setLoading(false);
      }
    };
    getMenus();
  }, []);

  if (loading) {
    return (
      <div className="loading-pane">
        <h2 className="loader">🍿</h2>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <div className="row">
        <NavbarElement />
        <header>
          <h1>Menus</h1>
        </header>
        <header>
          <Link to="/home">Movie Ticket Store</Link>
        </header>
        {menus.map((item) => (
          <div className="col-md-4 mb-4" key={item.id}>
            <div className="card h-100">
              <div className="card-body">
                <h5 className="card-title">{item.menu}</h5>
                <p className="card-text">
                  <strong>Description:</strong> {item.description}
                </p>
                <p className="card-text">
                  <strong>Price:</strong> ${item.price}
                </p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MenusElement;
