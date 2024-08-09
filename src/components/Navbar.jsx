import React from "react";
import { Link, useNavigate } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { useDispatch } from "react-redux";
import { authActions } from "../store/auth-slice";

export const NavbarElement = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const logoutHandler = (event) => {
    event.preventDefault();
    dispatch(authActions.logout());
    navigate("/");
  };
  return (
    <>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/home">
              Home
            </Nav.Link>
            <Nav.Link as={Link} to="/movies">
              Movies
            </Nav.Link>
            <Nav.Link as={Link} to="/menus">
              Menus
            </Nav.Link>
            <Nav.Link as={Link} to="/review">
              Reviews
            </Nav.Link>
            <Nav.Link
              onClick={logoutHandler}
              className="nav navbar-right"
              as={Link}
              to="/"
            >
              Logout
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
};
