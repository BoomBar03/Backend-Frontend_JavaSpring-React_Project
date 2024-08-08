import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import fetchUser from "./fetchUser";
import { useQuery } from "@tanstack/react-query";

const LoginElement = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const navigate = useNavigate();
  const { data, error, isFetching, refetch } = useQuery(
    ["credentials", email],
    fetchUser,
    {
      enabled: false,
    }
  );

  const onButtonClick = () => {
    // Set initial error values to empty
    setEmailError("");
    setPasswordError("");

    // Check if the user has entered both fields correctly
    if (email === "") {
      setEmailError("Please enter your email");
      return;
    }

    if (!/^[a-zA-Z0-9._:$!%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]+$/.test(email)) {
      setEmailError("Please enter a valid email");
      return;
    }

    if (password === "") {
      setPasswordError("Please enter a password");
      return;
    }

    if (password.length < 8) {
      setPasswordError("The password must be 8 characters or longer");
      return;
    }

    refetch();
  };

  useEffect(() => {
    if (!isFetching && data) {
      if (data instanceof Error) {
        setEmailError(data.message);
      } else if (data === password) {
        navigate("/home");
      } else {
        setPasswordError("Invalid Password or Email");
      }
    } else if (error) {
      setEmailError("Invalid Password or Email. Try again!");
    }
  }, [data, error, isFetching, navigate, password]);

  return (
    <div>
      <div>
        <header>
          <h1>Login</h1>
        </header>
      </div>
      <br />
      <div className="inputContainer">
        <input
          value={email}
          placeholder="Enter your email here"
          onChange={(ev) => setEmail(ev.target.value)}
          className="inputBox"
        />
        <label className="errorLabel">{emailError}</label>
      </div>
      <br />
      <div className="inputContainer">
        <input
          value={password}
          placeholder="Enter your password here"
          onChange={(ev) => setPassword(ev.target.value)}
          className="inputBox"
          type="password"
        />
        <label className="errorLabel">{passwordError}</label>
      </div>
      <br />
      <div className="inputContainer">
        <input
          className="inputButton"
          type="button"
          onClick={onButtonClick}
          value="Log in"
        />
      </div>
    </div>
  );
};

export default LoginElement;
