import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Provider, useSelector } from "react-redux";
import store from "./store";
import MoviesElement from "./components/Movies";
import Details from "./components/Details";
import HomePage from "./components/HomePage";
import LoginElement from "./components/Login";
// import MenusElement from "./components/Menus";

import "bootstrap/dist/css/bootstrap.min.css";
import SearchParams from "./components/SearchParams";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: Infinity,
      cacheTime: Infinity,
    },
  },
});

const App = () => {
  const isLoggedIn = useSelector((state) => state.auth.isLoggedIn);

  return (
    <Routes>
      <Route
        path="/"
        element={isLoggedIn ? <Navigate to="/home" /> : <LoginElement />}
      />
      <Route
        path="/home"
        element={isLoggedIn ? <HomePage /> : <Navigate to="/" />}
      />
      <Route
        path="/movies"
        element={isLoggedIn ? <MoviesElement /> : <Navigate to="/" />}
      />
      <Route
        path="/details/:id"
        element={isLoggedIn ? <Details /> : <Navigate to="/" />}
      />
      <Route
        path="/menus"
        element={isLoggedIn ? <SearchParams /> : <Navigate to="/menus" />}
      />
      <Route path="*" element={<Navigate to="/" />} />
    </Routes>
  );
};

const container = document.getElementById("root");

if (container) {
  const root = createRoot(container);
  root.render(
    <Provider store={store}>
      <QueryClientProvider client={queryClient}>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </QueryClientProvider>
    </Provider>
  );
} else {
  console.error("Root container not found");
}
