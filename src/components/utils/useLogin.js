// utils/useLogin.js
import { useQuery } from "@tanstack/react-query";
import fetchUser from "./fetchUser";

const useLogin = (email) => {
  return useQuery(["credentials", email], fetchUser, {
    enabled: false,
  });
};

export default useLogin;
