import { createSlice } from "@reduxjs/toolkit";

export const searchParamsSlice = createSlice({
  name: "searchParams",
  initialState: {
    value: {
      menu: "",
      description: "",
      price: "",
    },
  },
  reducers: {
    findAll(state, action) {
      state.value = action.payload;
    },
  },
});

export const { findAll } = searchParamsSlice.actions;
export default searchParamsSlice.reducer;
