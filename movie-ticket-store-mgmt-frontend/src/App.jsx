import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import MoviesElement from "./Movies";
import Details from "./Details";
import HomePage from "./HomePage";
import LoginElement from "./Login";

import "bootstrap/dist/css/bootstrap.min.css";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: Infinity,
      cacheTime: Infinity,
    },
  },
});

const App = () => {
  return (
    <BrowserRouter>
      <QueryClientProvider client={queryClient}>
        <Routes>
          <Route path="/" element={<LoginElement />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/movies" element={<MoviesElement />} />
          <Route path="/details/:id" element={<Details />} />
          {/* <Route path="/menu" element={<Menu />} />
            <Route path="/review" element={<Review />} /> */}
        </Routes>
      </QueryClientProvider>
    </BrowserRouter>
  );
};

const container = document.getElementById("root");
if (container) {
  const root = createRoot(container);
  root.render(<App />);
} else {
  console.error("Root container not found");
}
