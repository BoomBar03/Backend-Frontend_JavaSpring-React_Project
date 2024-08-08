import fetchUsersList from "./fetchUsersList";

const fetchUser = async ({ queryKey }) => {
  const email = queryKey[1];
  try {
    const results = await fetchUsersList();

    if (Array.isArray(results)) {
      for (let i = 0; i < results.length; i++) {
        if (email === results[i].email) {
          return results[i].password;
        }
      }
      throw new Error("No user found with the provided email");
    } else {
      throw new Error("Invalid data format");
    }
  } catch (error) {
    return new Error(error.message || "An error occurred while fetching users");
  }
};

export default fetchUser;
