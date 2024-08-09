import React from "react";
import { Link } from "react-router-dom";
import { NavbarElement } from "./Navbar";
// import { useNavigate } from "react-router-dom";

const HomePage = () => {
  return (
    <div>
      <NavbarElement />
      <header>
        <h1>Movie Ticket Store Home Page</h1>
      </header>
      <header>
        <Link to="/home">Movie Ticket Store</Link>
      </header>
    </div>
  );
};

export default HomePage;
