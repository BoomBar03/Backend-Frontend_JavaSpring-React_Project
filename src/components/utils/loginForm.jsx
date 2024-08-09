import React from "react";
import "./loginForm.css"; // Ensure this path is correct

const LoginForm = ({
  email,
  setEmail,
  password,
  setPassword,
  onButtonClick,
}) => (
  <div className="login-form-container">
    <div className="login-form-header">
      <h1>Login</h1>
    </div>
    <div className="input-container">
      <input
        value={email}
        placeholder="Enter your email here"
        onChange={(ev) => setEmail(ev.target.value)}
        className="input-box"
      />
    </div>
    <div className="input-container">
      <input
        value={password}
        placeholder="Enter your password here"
        onChange={(ev) => setPassword(ev.target.value)}
        className="input-box"
        type="password"
      />
    </div>
    <div className="button-container">
      <input
        className="submit-button"
        type="button"
        onClick={onButtonClick}
        value="Log in"
      />
    </div>
  </div>
);

export default LoginForm;
