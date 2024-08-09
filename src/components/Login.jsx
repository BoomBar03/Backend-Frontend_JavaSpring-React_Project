import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { authActions } from "../store/auth-slice";
import { validateEmail, validatePassword } from "./utils/validation";
import useLogin from "./utils/useLogin";
import LoginForm from "./utils/loginForm";

const LoginElement = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { data, error, isFetching, refetch } = useLogin(email);

  const onButtonClick = () => {
    setEmailError("");
    setPasswordError("");

    const emailError = validateEmail(email);
    const passwordError = validatePassword(password);

    if (emailError) {
      setEmailError(emailError);
      return;
    }

    if (passwordError) {
      setPasswordError(passwordError);
      return;
    }

    refetch();
  };

  React.useEffect(() => {
    if (!isFetching) {
      if (data === password) {
        dispatch(authActions.login());
        navigate("/home");
      } else if (error) {
        setEmailError("Invalid Password or Email");
      }
    }
  }, [data, error, isFetching, password, navigate, dispatch]);

  return (
    <div className="center-container">
      <div className="login-form-container">
        <LoginForm
          email={email}
          setEmail={setEmail}
          password={password}
          setPassword={setPassword}
          onButtonClick={onButtonClick}
        />
        <label className="errorLabel">{emailError}</label>
        <label className="errorLabel">{passwordError}</label>
      </div>
    </div>
  );
};

export default LoginElement;
