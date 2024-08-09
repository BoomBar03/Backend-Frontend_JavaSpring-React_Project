export const validateEmail = (email) => {
  if (!email) return "Please enter your email";
  if (!/^[a-zA-Z0-9._:$!%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]+$/.test(email))
    return "Please enter a valid email";
  return "";
};

export const validatePassword = (password) => {
  if (!password) return "Please enter a password";
  if (password.length < 8) return "The password must be 8 characters or longer";
  return "";
};
