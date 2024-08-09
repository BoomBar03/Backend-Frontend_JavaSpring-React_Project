import { useState, useEffect } from "react";
import fetchMenusList from "./fetchMenusList";

const useMenus = () => {
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

  return { menus, loading };
};

export default useMenus;
